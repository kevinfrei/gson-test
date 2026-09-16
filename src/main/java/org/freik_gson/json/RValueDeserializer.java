package org.freik_gson.json;

import com.google.gson.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class RValueDeserializer implements JsonDeserializer<RValue<?>> {
    @Override
    public RValue<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {

        RValue<Object> rValue = new RValue<>();

        // Check if the JSON is an object containing a "ref" property
        if (json.isJsonObject() && json.getAsJsonObject().has("ref")) {
            JsonObject obj = json.getAsJsonObject();
            // Using reflection or a helper field to set 'ref' since it's private
            // (You can also make fields public in your DTOs for simplicity)
            return parseReference(obj.get("ref").getAsString());
        }

        // If it's not a reference, deserialize it as the inline type T
        if (typeOfT instanceof ParameterizedType) {
            Type innerType = ((ParameterizedType) typeOfT).getActualTypeArguments()[0];
            Object parsedValue = context.deserialize(json, innerType);
            rValue = setInlineValue(parsedValue);
        }

        return rValue;
    }

    // Helper to bypass private fields cleanly
    private RValue<?> parseReference(String refName) {
        RValue<Object> rv = new RValue<>();
        rv.ref = refName;
        return rv;
    }

    private <T> RValue<T> setInlineValue(T val) {
        RValue<T> rv = new RValue<>();
        rv.value = val;
        return rv;
    }
}