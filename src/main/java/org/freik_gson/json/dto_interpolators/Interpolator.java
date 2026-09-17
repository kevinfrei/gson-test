package org.freik_gson.json.dto_interpolators;

import com.google.gson.*;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

@JsonAdapter(Interpolator.AdapterFactory.class)
public abstract class Interpolator {
    public abstract String getType();

    static class AdapterFactory implements TypeAdapterFactory {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
            if (!Interpolator.class.isAssignableFrom(type.getRawType())) {
                return null;
            }

            // Delegate to runtime mapping or handle sub-typing logic here
            // This keeps your GsonBuilder completely clean of navigation rules.
            return (TypeAdapter<T>) new Adapter(gson);
        }
    }

    private static class Adapter extends TypeAdapter<Interpolator> {
        private final Gson gson;

        // Gson passes itself into the adapter factory, which we forward here
        public Adapter(Gson gson) {
            this.gson = gson;
        }

        @Override
        public void write(JsonWriter out, Interpolator value) throws IOException {
            if (value == null) {
                out.nullValue();
                return;
            }

            // 1. Let Gson convert the concrete class into a JsonElement tree
            JsonElement tree = gson.toJsonTree(value, value.getClass());
            JsonObject jsonObject = tree.getAsJsonObject();

            // 2. Inject the correct "type" discriminator based on the subclass
            if (value instanceof Tangent) {
                jsonObject.addProperty("type", "Tangent");
            } else if (value instanceof Linear) {
                jsonObject.addProperty("type", "Linear");
            } else if (value instanceof Constant) {
                jsonObject.addProperty("type", "Constant");
            } else if (value instanceof FacingPoint) {
                jsonObject.addProperty("type", "FacingPoint");
            } else if (value instanceof PieceWise) {
                jsonObject.addProperty("type", "PieceWise");
            }

            // 3. Write the modified tree out to the stream
            gson.toJson(jsonObject, out);
        }

        @Override
        public Interpolator read(JsonReader in) throws IOException {
            // 1. Parse the incoming stream into a JsonObject tree
            JsonObject jsonObject = JsonParser.parseReader(in).getAsJsonObject();

            if (!jsonObject.has("type")) {
                throw new JsonParseException("Missing 'type' discriminator field for Interpolator");
            }

            String type = jsonObject.get("type").getAsString();
            Class<? extends Interpolator> targetClass;

            // 2. Map the string type to the correct concrete Java class
            switch (type) {
                case "Tangent":
                    targetClass = Tangent.class;
                    break;
                case "Linear":
                    targetClass = Linear.class;
                    break;
                case "Constant":
                    targetClass = Constant.class;
                    break;
                case "FacingPoint":
                    targetClass = FacingPoint.class;
                    break;
                case "PieceWise":
                    targetClass = PieceWise.class;
                    break;
                default:
                    throw new JsonParseException("Unknown heading interpolation type: " + type);
            }

            // 3. Delegate the object mapping back to Gson for the specific subclass
            return gson.fromJson(jsonObject, targetClass);
        }
    }
}

