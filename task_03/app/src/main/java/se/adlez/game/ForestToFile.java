package se.adlez.game;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Utility class for saving and loading Forest objects to/from files.
 * Supports both Java serialization and JSON formats.
 */
public class ForestToFile {
    /**
     * Saves a Forest object to a file using Java serialization.
     *
     * @param forest The Forest object to save
     * @param filename The name of the file to save to
     * @throws IOException If there is an error writing to the file
     */
    public static void save(Forest forest, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(forest);
            System.out.println("Saved serialized forest to file '" + filename + "'.");
        } catch (IOException e) {
            System.out.println("Error saving serialized forest to file '" + filename + "'");
        }
    }

    /**
     * Loads a Forest object from a file using Java serialization.
     *
     * @param filename The name of the file to load from
     * @return The loaded Forest object, or null if loading fails
     * @throws IOException If there is an error reading from the file
     * @throws ClassNotFoundException If the Forest class cannot be found
     */
    public static Forest load(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            Forest forest = (Forest) ois.readObject();
            System.out.println("Loaded serialized forest to file '" + filename + "'.");
            return forest;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading serialized forest from file '" + filename + "'");
            return null;
        }
    }

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
