package fr.nostoS;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import fr.nostoS.mysql.DatabaseManager;
import net.md_5.bungee.api.chat.TextComponent;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Utils {

    private static DatabaseManager databaseManager;

    private static final Set<String> survies_names = new HashSet<>();
    static {
        survies_names.add("survie");
        survies_names.add("survie_the_end");
        survies_names.add("survie_nether");
    }

    public static void sendMessageToSurvival(String message) {
        for(String name : survies_names) {
            World world = Bukkit.getWorld(name);
            if (world == null) continue;
            
            sendMessageToWorld(world, message);
        }
    }

    public static void sendMessageToWorld(World world, String message) {
        for(Player player : world.getPlayers()) {
            player.sendMessage(message);
        }
    }

    public static void sendTextComponentToSurvival(TextComponent textComponent) {
        for (String name : survies_names) {
            sendTextComponentToWorld(Objects.requireNonNull(Bukkit.getWorld(name)), textComponent);
        }
    }

    public static void sendTextComponentToWorld(World world, TextComponent textComponent) {
        for (Player player : world.getPlayers()) {
            player.sendMessage(textComponent);
        }
    }

    public static Set<String> getSurviesNames() {
        return survies_names;
    }

    public static Set<World> getSurviesWorlds() {
        Set<World> worlds = new HashSet<>();

        for (String name : survies_names) {
            World world = Bukkit.getWorld(name);
            if (world == null) continue;

            worlds.add(world);
        }

        return worlds;
    }

    public static DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public static void setDatabaseManager(DatabaseManager databaseManager) {
        Utils.databaseManager = databaseManager;
    }
}
