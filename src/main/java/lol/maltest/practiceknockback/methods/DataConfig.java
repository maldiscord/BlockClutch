package lol.maltest.practiceknockback.methods;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class DataConfig {
    private static File file;
    private static FileConfiguration dataFile;

    public ArrayList<UUID> playersInGame = new ArrayList<>();

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("PracticeKnockback").getDataFolder(), "data.yml");

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e){
                System.out.println("Oopsie ran into a error making the cagedata.yml " + e);
            }
        }
        dataFile = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration getDataFile() {
        return dataFile;
    }

    public static void save() {
        try {
            dataFile.save(file);
        } catch (IOException e) {
            System.out.println("Failed to save the file" + e);
        }

    }

    public static void reload() {
        dataFile = YamlConfiguration.loadConfiguration(file);
    }
}
