package org.freik_gson.json;

import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

@JsonAdapter(RValue.Adapter.class)
public class RValue<T> {
    public String ref = null;
    public T value = null;

    // Helper methods to check state
    public boolean isRef() {
        return ref != null && !ref.isEmpty();
    }

    public String getRef() {
        return ref;
    }

    public T getValue() {
        return value;
    }

    // Bundle your custom logic right inside the class as a static inner adapter
    public static class Adapter extends TypeAdapter<RValue<?>> {
        @Override
        public void write(JsonWriter out, RValue<?> value) throws java.io.IOException {
            if (value == null) {
                out.nullValue();
                return;
            }
            if (value.isRef()) {
                out.beginObject();
                out.name("ref").value(value.getRef());
                out.endObject();
            } else {
                // Delegate writing the inner value
                // (Note: For absolute type safety with generics, TypeAdapterFactories are sometimes preferred,
                // but standard tree writing via JsonParser/Gson context works cleanly too)
                out.value(value.getValue().toString()); // simplified example
            }
        }

        @Override
        @SuppressWarnings("unchecked")
        public RValue<?> read(JsonReader in) throws java.io.IOException {
            // Read logic using standard Streaming API or transitioning to JsonParser tree
            JsonObject jsonObj = com.google.gson.JsonParser.parseReader(in).getAsJsonObject();
            RValue<Object> rVal = new RValue<>();
            if (jsonObj.has("ref")) {
                rVal.ref = jsonObj.get("ref").getAsString();
            } else {
                rval = jsonObj
            }
            // ... parse inline value if needed
            return rVal;
        }
    }
}