package kz.dosyakitarov.nomads_delight.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class JsonReader {
    private final JsonObject rootJson;

    public JsonReader(String resourcePath) {
        try (InputStream is = JsonReader.class.getClassLoader().getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IllegalArgumentException("Key not found: '" + resourcePath + "'");
            }
            try (Reader reader = new InputStreamReader(is)) {
                this.rootJson = JsonParser.parseReader(reader).getAsJsonObject();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getInt(String itemName, String itemKey) {
        return getChildObject(itemName).get(itemKey).getAsInt();
    }

    public String getString(String itemName, String itemKey) {
        return getChildObject(itemName).get(itemKey).getAsString();
    }

    public boolean getBoolean(String itemName, String itemKey) {
        return getChildObject(itemName).get(itemKey).getAsBoolean();
    }

    public double getDouble(String itemName, String itemKey) {
        return getChildObject(itemName).get(itemKey).getAsDouble();
    }

    public float getFloat(String itemName, String itemKey) {
        return getChildObject(itemName).get(itemKey).getAsFloat();
    }

    public int getNutrition(String itemName) {
        return getInt(itemName, "nutrition");
    }

    public float getSaturation(String itemName) {
        return (float) getDouble(itemName, "saturation");
    }

    private JsonObject getChildObject(String itemName) {
        if (!this.rootJson.has(itemName) || this.rootJson.get(itemName).isJsonNull()) {
            throw new IllegalArgumentException("Key not found: '" + itemName + "'");
        }
        return this.rootJson.getAsJsonObject(itemName);
    }
}