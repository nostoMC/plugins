package fr.nosto.tasks;

import fr.nosto.Main;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

public class PluginListTask {

	public static void list(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				
				PluginManager pm = Bukkit.getServer().getPluginManager();
				StringBuilder list = new StringBuilder("\n");
				list.append("\n╔══════╣ §bListe des plugins Nosto disponibles :\n║\n");
				
				if (pm.getPlugin("Nosto-Nightclub") != null) list.append("║   §bNightclub - §a✔");
				else list.append("║   §bNightclub - §c✖");
				list.append("\n║\n");
				if (pm.getPlugin("Nosto-Survival") != null) list.append("║   §bSurvival - §a✔");
				else list.append("║   §bSurvival - §c✖");
				list.append("\n║\n");
				if (pm.getPlugin("Nosto-Island") != null) list.append("║   §bIsland - §a✔");
				else list.append("║   §bIsland - §c✖");

				list.append("\n║\n╚═══════════════════\n");
				Bukkit.getLogger().info(list.toString());

			}
		}.runTaskLater(main, 60);
		
	}

}
