package fr.nosto.tasks;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nosto.Main;

public class RandomBroadcastTask {

	public RandomBroadcastTask(Main main) {
		
		String[] messages = {"1", "2", "3", "4"};
    	
		new BukkitRunnable() {
		    @Override
		    public void run() {
		    	if(Bukkit.getOnlinePlayers().isEmpty() == false) {
		    		for(Player players : Bukkit.getOnlinePlayers()) {
		    			Set<String> survies_names = new HashSet<String>();
		    			survies_names.add("survie");
		    			survies_names.add("survie_the_end");
		    			survies_names.add("survie_nether");
		    			if(survies_names.contains(players.getWorld().getName())) {
		    				if(messages[new Random().nextInt(messages.length)] == "1") {
				    			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + players.getName() + " [\"\",{\"text\":\"\\n\"},{\"text\":\"Si vous rencontrez des bugs, veuillez nous les signaler sur le discord :\",\"bold\":true,\"color\":\"yellow\"},{\"text\":\" \",\"bold\":true,\"color\":\"gold\"},{\"text\":\"https://discord.io/Nosto\",\"bold\":true,\"underlined\":true,\"color\":\"gold\",\"clickEvent\":{\"action\":\"open_url\",\"value\":\"https://discord.io/Nosto\"},\"hoverEvent\":{\"action\":\"show_text\",\"contents\":\"Cliquez pour accéder au serveur discord\"}}]");
				    		} else if(messages[new Random().nextInt(messages.length)] == "2") {
				    			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "tellraw " + players.getName() + " [\"\",{\"text\":\"\\n\"},{\"text\":\"Les machines à lag sont \",\"bold\":true,\"color\":\"red\"},{\"text\":\"strictement interdites\",\"bold\":true,\"color\":\"dark_red\"},{\"text\":\" en tous mondes.\",\"bold\":true,\"color\":\"red\"}]");
				    		}
		    			}
		    		}
		    	}
		    }
		          
		}.runTaskTimer(main, 0, 1000);
		
	}

}
