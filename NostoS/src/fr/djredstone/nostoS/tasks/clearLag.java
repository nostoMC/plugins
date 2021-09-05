package fr.djredstone.nostoS.tasks;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoS.Main;

public class clearLag {

	public clearLag(Main main) {

		new BukkitRunnable() {
			int i = 0;
			@Override
			public void run() {

				i++;
				
				if(i == 180) {
					Main.sendMessageInSurvivalWorld("§4Attention ! §cLes items à terre vont êtres supprimés dans 60 §csecondes!");
				}
				if(i == 220) {
					Main.sendMessageInSurvivalWorld("§4Attention ! §cLes items à terre vont êtres supprimés dans 20 §csecondes!");
				}
				if(i == 240) {
					
					int nbEntity = 0;
					for(String name : Main.survivalWorld) {
						for(Entity entity : Bukkit.getWorld(name).getEntities()) {
							if(entity instanceof Item) {
								nbEntity ++;
								entity.remove();
							}
						}
					}
					
					Main.sendMessageInSurvivalWorld("§a" + nbEntity + " entitiés ont été supprimés !");
					i = 0;
				}
				
			}
		}.runTaskTimer(main, 0, 20);
		
	}

}
