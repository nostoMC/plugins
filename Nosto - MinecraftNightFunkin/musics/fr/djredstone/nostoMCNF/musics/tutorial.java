package fr.djredstone.nostoMCNF.musics;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.djredstone.nostoMCNF.Main;
import fr.djredstone.nostoMCNF.fleches.bleu;
import fr.djredstone.nostoMCNF.fleches.red;
import fr.djredstone.nostoMCNF.fleches.rose;
import fr.djredstone.nostoMCNF.fleches.vert;

public class tutorial {

	public static void start(String string, Location arrowRose1, Location arrowBleu1, Location arrowVert1, Location arrowRouge1, Location arrowRose2, Location arrowBleu2, Location arrowVert2, Location arrowRouge2) {

		for(Player players : Bukkit.getOnlinePlayers()) {
			if(players.getWorld() == Bukkit.getWorld("McNightFunkin")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "jukebox music " + players.getName() + " https://cdn.discordapp.com/attachments/749967838512676906/862676944235921418/tutorial-friday-night-funkin-ost.mp3");
			}
		}
		
		HashMap<Integer, String> musicNormal = new HashMap<Integer, String>();
		
		if(musicNormal.isEmpty()) {
			musicNormal.put(184, "P1_rose_simple");
			musicNormal.put(205, "P1_red_simple");
			musicNormal.put(240, "P1_rose_simple");
			musicNormal.put(261, "P1_red_simple");
			musicNormal.put(283, "P2_rose_simple");
			musicNormal.put(304, "P2_red_simple");
			musicNormal.put(325, "P2_rose_simple");
			musicNormal.put(360, "P2_red_simple");
			musicNormal.put(381, "P1_vert_simple");
			musicNormal.put(402, "P1_bleu_simple");
			musicNormal.put(424, "P1_vert_simple");
			musicNormal.put(444, "P1_bleu_simple");
			musicNormal.put(480, "P2_vert_simple");
			musicNormal.put(501, "P2_bleu_simple");
			musicNormal.put(522, "P2_vert_simple");
			musicNormal.put(544, "P2_bleu_simple");
		}
		
		for(int i = 0; i != musicNormal.size(); i++) {
			if(musicNormal.get(i) == null) {
				musicNormal.put(i, "");
			}
		}
		
		new BukkitRunnable() {
			
			int i = 40;
			
			@Override
			public void run() {
				
				if(musicNormal.get(i) != null) {
					
					if(musicNormal.get(i).startsWith("P1_")) {
						if(musicNormal.get(i).endsWith("red_simple")) {
							red.simple(arrowRouge1);
						} else if(musicNormal.get(i).endsWith("rose_simple")) {
							rose.simple(arrowRose1);
						} else if(musicNormal.get(i).endsWith("bleu_simple")) {
							bleu.simple(arrowBleu1);
						} else if(musicNormal.get(i).endsWith("vert_simple")) {
							vert.simple(arrowVert1);
						}
					} else {
						if(musicNormal.get(i).endsWith("red_simple")) {
							red.simple(arrowRouge2);
						} else if(musicNormal.get(i).endsWith("rose_simple")) {
							rose.simple(arrowRose2);
						} else if(musicNormal.get(i).endsWith("bleu_simple")) {
							bleu.simple(arrowBleu2);
						} else if(musicNormal.get(i).endsWith("vert_simple")) {
							vert.simple(arrowVert2);
						}
					}
				} else {
					new BukkitRunnable() {
						
						@Override
						public void run() {
							for(Player players : Bukkit.getOnlinePlayers()) {
								if(players.getWorld() == Bukkit.getWorld("McNightFunkin")) {
									Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "jukebox stop " + players.getName());
								}
							}
							this.cancel();
							
						}
					}.runTaskLater(Main.getInstance(), 100);
					
					this.cancel();
					
				}
				
				i++;
				
			}
		}.runTaskTimer(Main.getInstance(), 0, 0);
		
	}

}
