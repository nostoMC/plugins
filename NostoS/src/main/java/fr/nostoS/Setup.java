package fr.nostoS;

import fr.nostoS.listeners.AFKListeners;
import fr.nostoS.commands.CommandAFK;
import fr.nostoS.commands.CommandClaim;
import fr.nostoS.commands.CommandHome;
import fr.nostoS.commands.TabHome;
import fr.nostoS.listeners.OnInteractListener;
import fr.nostoS.mysql.DatabaseManager;
import fr.nostoS.tasks.clearLag;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Objects;

public class Setup {

    public Setup(Main main) {

        // MySQL (Database)
        FileConfiguration fc = Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("Plugin-Custom-Nosto")).getConfig();
        if (!fc.contains("SQL.host")) {
            fc.set("SQL.host", "HOST HERE");
        }

        if (!fc.contains("SQL.user")) {
            fc.set("SQL.user", "USER HERE");
        }

        if (!fc.contains("SQL.password")) {
            fc.set("SQL.password", "PASSWORD HERE");
        }

        if (!fc.contains("SQL.dbName")) {
            fc.set("SQL.dbName", "DATABASE NAME HERE");
        }

        main.saveConfig();

        Utils.setDatabaseManager(new DatabaseManager(fc.getString("SQL.host"), fc.getString("SQL.user"), fc.getString("SQL.password"), fc.getString("SQL.dbName")));

        // Commands
        registerCommands(main);

        // Listeners
        Bukkit.getPluginManager().registerEvents(new AFKListeners(), main);
        Bukkit.getPluginManager().registerEvents(new OnInteractListener(), main);

        // Tasks
        new clearLag(main);

        AFKListeners.initAFKLoop(main);

    }

    @SuppressWarnings("ConstantConditions")
    private void registerCommands(Main main) {

        main.getCommand("afk").setExecutor(new CommandAFK());

        main.getCommand("sethome").setExecutor(new CommandHome());
        main.getCommand("sethome").setTabCompleter(new TabHome());
        main.getCommand("home").setExecutor(new CommandHome());
        main.getCommand("home").setTabCompleter(new TabHome());
        main.getCommand("delhome").setExecutor(new CommandHome());
        main.getCommand("delhome").setTabCompleter(new TabHome());

        main.getCommand("claim").setExecutor(new CommandClaim());
        main.getCommand("unclaim").setExecutor(new CommandClaim());
    }

}
