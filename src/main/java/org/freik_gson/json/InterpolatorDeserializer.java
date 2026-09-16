package org.freik_gson.json;

import com.google.gson.*;
import org.freik_gson.json.dto_interpolators.*;

import java.lang.reflect.Type;

public class InterpolatorDeserializer implements JsonDeserializer<Interpolator> {
    @Override
    public Interpolator deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        String type = jsonObject.get("type").getAsString();

        // Map the string type to the correct Pedro Pathing / custom implementation class
        switch (type.toLowerCase()) {
            case "Tangent":
                return context.deserialize(jsonObject, Tangent.class);
            case "Linear":
                return context.deserialize(jsonObject, Linear.class);
            case "Constant":
                return context.deserialize(jsonObject, Constant.class);
            case "FacingPoint":
                return context.deserialize(jsonObject, FacingPoint.class);
            case "PieceWise":
                return context.deserialize(jsonObject, PieceWise.class);
            default:
                throw new JsonParseException("Unknown heading interpolation type: " + type);
        }
    }
}