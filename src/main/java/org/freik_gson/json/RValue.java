package org.freik_gson.json;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

@JsonAdapter(RValue.AdapterFactory.class)
public class RValue<T> {
    private String ref;
    private T value;

    public boolean isRef() {
        return ref != null && !ref.isEmpty();
    }

    public String getRef() {
        return ref;
    }

    public T getValue() {
        return value;
    }

    public RValue(T value) {
        this.value = value;
        this.ref = null;
    }

    public RValue(String ref) {
        this.ref = ref;
        this.value = null;
    }

    public RValue() {
    }

    // 1. The Factory that catches generic RValue<T> instances
    public static class AdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            // Check if the type being parsed/written is assignable from RValue
            if (!RValue.class.isAssignableFrom(type.getRawType())) {
                return null;
            }

            // Extract the inner type argument T (e.g., PoseDTO from RValue<PoseDTO>)
            Type societalType = type.getType();
            Type innerType = Object.class;
            if (societalType instanceof ParameterizedType) {
                innerType = ((ParameterizedType) societalType).getActualTypeArguments()[0];
            }

            // Get Gson's built-in adapter for the inner type
            TypeAdapter<?> innerAdapter = gson.getAdapter(TypeToken.get(innerType));

            // Return our custom RValue adapter bound to this specific inner type
            return (TypeAdapter<T>) new RValueAdapter<>(innerAdapter);
        }
    }

    // 2. The actual Adapter that handles serialization and deserialization
    private static class RValueAdapter<T> extends TypeAdapter<RValue<T>> {
        private final TypeAdapter<T> innerAdapter;

        public RValueAdapter(TypeAdapter<T> innerAdapter) {
            this.innerAdapter = innerAdapter;
        }

        @Override
        public void write(JsonWriter out, RValue<T> value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            if (value.isRef()) {
                out.beginObject();
                out.name("ref").value(value.getRef());
                out.endObject();
            } else {
                // Delegate writing the inline value to its native adapter
                innerAdapter.write(out, value.getValue());
            }
        }

        @Override
        public RValue<T> read(JsonReader in) throws IOException {
            // Parse incoming JSON into a tree structure for easy inspection
            JsonElement element = JsonParser.parseReader(in);
            RValue<T> rVal = new RValue<>();

            if (element.isJsonObject() && element.getAsJsonObject().has("ref")) {
                rVal.ref = element.getAsJsonObject().get("ref").getAsString();
            } else {
                // Delegate reading the inline value back to the inner adapter
                rVal.value = innerAdapter.fromJsonTree(element);
            }

            return rVal;
        }
    }
}