package se.adlez.game;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Utility class for saving and converting Forest objects as JSON.
 */
public class ForestToJson {
    /**
     * Converts a Forest object to a JSON string.
     *
     * @param forest The Forest object to convert
     * @return A pretty-printed JSON string representation of the Forest
     */
    public static String toJson(Forest forest) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = gson.toJson(forest);
        return prettyJson;
    }

    /**
     * Saves a Forest object to a file in JSON format.
     *
     * @param forest The Forest object to save
     * @param filename The name of the file to save to
     */
    public static void saveAsJson(Forest forest, String filename) {
        String json = toJson(forest);
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(json);
        } catch (IOException e) {
            System.out.println("Error saving serialized forest to file '" + filename + "'");
        }
    }
}
