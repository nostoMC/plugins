package fr.nosto.tasks;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;

public class PluginListTask {

	public static void list(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				PluginManager pm = Bukkit.getServer().getPluginManager();
				Logger logger = Bukkit.getLogger();
				logger.log(Level.WARNING, "");
				logger.log(Level.WARNING, "");
				logger.log(Level.WARNING, "§bListe des plugins Nosto disponibles : ");
				logger.log(Level.WARNING, "");
				
				if(pm.getPlugin("Nosto-Nightclub") != null) {
					logger.log(Level.WARNING, "§bNightclub - §a✔");
				} else {
					logger.log(Level.WARNING, "§bNightclub - §c✖");
				}
				logger.log(Level.WARNING, "");
				if(pm.getPlugin("Nosto-Survival") != null) {
					logger.log(Level.WARNING, "§bSurvival - §a✔");
				} else {
					logger.log(Level.WARNING, "§bSurvival - §c✖");
				}
				logger.log(Level.WARNING, "");
				if(pm.getPlugin("Nosto-World") != null) {
					logger.log(Level.WARNING, "§bWorld - §a✔");
				} else {
					logger.log(Level.WARNING, "§bWorld - §c✖");
				}
				
				logger.log(Level.WARNING, "");
				logger.log(Level.WARNING, "");
			}
		}.runTaskLater(main, 60);
		
	}

}
