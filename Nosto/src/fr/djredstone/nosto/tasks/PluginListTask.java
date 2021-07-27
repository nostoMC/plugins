package fr.djredstone.nosto.tasks;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nosto.Main;

public class PluginListTask {

	public PluginListTask(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				PluginManager pm = Bukkit.getServer().getPluginManager();
				System.out.println("");
				System.out.println("");
				System.out.println("�bListe des plugins Nosto disponibles : ");
				System.out.println("");
				System.out.println("�bMini jeux : ");
				if(pm.getPlugin("Nosto-Duel") != null) System.out.println("�b - Duel");
				if(pm.getPlugin("Nosto-Sheepwars") != null) System.out.println("�b - Sheepwars");
				if(pm.getPlugin("Nosto-Dungeon") != null) System.out.println("�b - Dungeon");
				if(pm.getPlugin("Nosto-MinecraftNightFunkin") != null) System.out.println("�b - Minecraft Night Funkin");
				System.out.println("");
				System.out.println("�bEvents : ");
				if(pm.getPlugin("Nosto-Nightclub") != null) System.out.println("�b - Nightclub");
				if(pm.getPlugin("Nosto-SpeedRunnerVSHunter") != null) System.out.println("�b - SpeedRunnerVSHunter");
				System.out.println("");
				System.out.println("�bAutres : ");
				if(pm.getPlugin("Nosto-Sanctions") != null) System.out.println("�b - Sanctions");
				System.out.println("");
				System.out.println("");
			}
		}.runTaskLater(main, 60);
		
	}

}
