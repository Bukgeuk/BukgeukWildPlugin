package kr.kro.bukgeukwild;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import org.apache.logging.log4j.core.jackson.Log4jJsonObjectMapper;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.UUID;

public class FileIO {
    public static void WriteAll() {
        if (Map.Data != null && !Map.Data.isEmpty()) {
            for (UUID key : Map.Data.keySet()) {
                String filename = BukgeukWild.Dir + File.separator + "Users" + File.separator + Map.Data.get(key).getUUID().toString() + ".udat";
                File file = new File(filename);

                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                        break;
                    }
                }

                Gson gson = new Gson();
                String jsonStr = gson.toJson(Map.Data.get(key));
                FileWriter writer = null;

                try {
                    writer = new FileWriter(file, false);
                    writer.write(jsonStr);
                    writer.flush();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (writer != null) writer.close();
                    } catch (IOException err) {
                        err.printStackTrace();
                    }
                }
            }
        }
    }

    public static void Write(UserData data) {
        String filename = BukgeukWild.Dir + File.separator + "Users" + File.separator + data.getUUID().toString() + ".udat";
        File file = new File(filename);
        boolean error = false;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                error = true;
            }
        }

        if (!error) {
            Gson gson = new Gson();
            String jsonStr = gson.toJson(data);
            FileWriter writer = null;

            try {
                writer = new FileWriter(file, false);
                writer.write(jsonStr);
                writer.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) writer.close();
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        }
    }

    public static HashMap<UUID, UserData> ReadAll() {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        File dir = new File(BukgeukWild.Dir + File.separator + "Users");
        File[] fileList = dir.listFiles();
        HashMap<UUID, UserData> Map = new HashMap<UUID, UserData>();
        Gson gson = new Gson();

        for (File file : fileList) {
            if (file.isFile()) {
                try {
                    JsonReader jsonReader = new JsonReader(new FileReader(BukgeukWild.Dir + File.separator + "Users" + File.separator + file.getName()));
                    UserData data = gson.fromJson(jsonReader, UserData.class);
                    Map.put(data.getUUID(), data);
                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }
        }

        return Map;
    }

    public static UserData Read(UUID UUID) {
        String filename = BukgeukWild.Dir + File.separator + "Users" + File.separator + UUID.toString() + ".udat";
        File file = new File(filename);
        Gson gson = new Gson();

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("IOException");
                e.printStackTrace();
            }
        } else {
            try {
                JsonReader jsonReader = new JsonReader(new FileReader(filename));
                return gson.fromJson(jsonReader, UserData.class);
            } catch (Exception e) {
                System.out.println("Exception");
                e.printStackTrace();
            }
        }
        return null;
    }
}
