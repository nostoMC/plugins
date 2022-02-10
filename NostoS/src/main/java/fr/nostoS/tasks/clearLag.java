package fr.nostoS.tasks;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoS.Main;
import fr.nostoS.Utils;

public class clearLag {

	public clearLag(Main main) {

		new BukkitRunnable() {
			int i = 0;
			@Override
			public void run() {

				i++;

				if(i == 180) {
					Utils.sendMessageToSurvival("\n§4Attention ! §cLes items à terre vont êtres supprimés dans 60 §csecondes!");
				}
				if(i == 220) {
					Utils.sendMessageToSurvival("\n§4Attention ! §cLes items à terre vont êtres supprimés dans 20 §csecondes!");
				}
				if(i == 240) {
					
					int nbEntity = 0;
					for(World world : Utils.getSurviesWorlds()) {

						for(Entity entity : world.getEntities()) {
							if(entity instanceof Item) {
								nbEntity ++;
								entity.remove();
							}
						}
					}
					
					Utils.sendMessageToSurvival("\n§a" + nbEntity + " entitiés ont été supprimés !");
					i = 0;
				}
				
			}
		}.runTaskTimer(main, 0, 20);
		
	}

}
