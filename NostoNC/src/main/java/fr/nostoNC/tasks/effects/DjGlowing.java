package fr.nostoNC.tasks.effects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class DjGlowing {

	public DjGlowing(Main main) {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				for(Player players : Bukkit.getOnlinePlayers()) {
					if(players.getWorld() == Main.defaultWorld) {
						if(Main.dj.contains(players)) {
							PotionEffect potion = new PotionEffect(PotionEffectType.GLOWING, 2, 1, true);
							players.addPotionEffect(potion);
						}
					}
				}
				
			}
		}.runTaskTimer(main, 0, 0);
	}

}
