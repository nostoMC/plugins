package fr.nosto;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class MessageManager {

    public static String getMessage(String basename) {
        final FileConfiguration config = Main.getMessageConfig();
        if (config.isString(basename)) {
            return config.getString(basename);
        }
        Bukkit.getLogger().warning("Aucun message ne correspond à \"" + basename + "\"");
        return basename;
    }
}
