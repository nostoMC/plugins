package fr.nostoNC.tasks.effects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;

public class RandomParticleEffect {

	public RandomParticleEffect(Main main) {

		List<Bat> batList = new ArrayList<>();

		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {

				Bat bat = (Bat) Main.defaultWorld.spawnEntity(new Location(null, -2, 108, 146), EntityType.BAT);
				bat.setInvisible(true);
				bat.setSilent(true);
				
				bat.getWorld().playSound(bat.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 2, 1);

				batList.add(bat);

				i++;
				if (i == 20) this.cancel();
				
			}
		}.runTaskTimer(main, 0, 1);
		
		new BukkitRunnable() {
			
			int i = 0;
			
			@Override
			public void run() {
				
				for (Bat bat : batList) {
					Main.defaultWorld.spawnParticle(Particle.FLAME, bat.getLocation(), 1, 0, 0, 0, 0);
				}
				
				i++;
				if (i >= 200) {
					this.cancel();

					Firework firework = (Firework) Main.defaultWorld.spawnEntity(batList.get(0).getLocation(), EntityType.FIREWORK);

					FireworkMeta fwMeta = firework.getFireworkMeta();
					fwMeta.addEffect(FireworkEffect.builder()
							.with(FireworkEffect.Type.BALL)
							.withColor(Color.RED, Color.ORANGE, Color.YELLOW)
							.flicker(true).trail(true)
							.build());

					firework.setFireworkMeta(fwMeta);
					firework.detonate();

					batList.get(0).remove(); // kill la 1er bat
					batList.remove(0); // supprime le 1er élément de la list

					for (Bat bat : batList) {
						bat.remove();

						firework = (Firework) Main.defaultWorld.spawnEntity(bat.getLocation(), EntityType.FIREWORK);
						firework.setFireworkMeta(fwMeta);
						firework.detonate();
					}
				}
				
			}
		}.runTaskTimer(main, 0, 1);
		
	}

}
