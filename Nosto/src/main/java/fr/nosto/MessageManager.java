package fr.nosto;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class MessageManager {

    private static Main main;

    public static void init(Main main) {
        MessageManager.main = main;
    }

    public static String getMessage(String basename) {
        final FileConfiguration config = main.getMessageConfig();
        if (config.isString(basename)) {
            return config.getString(basename);
        }
        Bukkit.getLogger().warning("Aucun message ne correspond à \"" + basename + "\"");
        return basename;
    }
}
