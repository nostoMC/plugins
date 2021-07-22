package fr.djredstone.nosto.claim;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.djredstone.nosto.Main;

public class ClaimCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            
            if(player.getWorld() == Bukkit.getWorld("survie")) {
            
            if(cmd.getName().equalsIgnoreCase("claim")) {

            	Chunk chunk = player.getLocation().getChunk();

            	String chunkID = chunk.getX() + "_" + chunk.getZ();

            	if (Main.getInstance().getConfig().contains("claim." + chunkID)) {
            		player.sendMessage("");
                	player.sendMessage("§cCe chunk est déjà claim !");

            	} else {
            		Main.getInstance().getConfig().set("claim." + chunkID , player.getUniqueId().toString());
            		Main.getInstance().saveConfig();
            		player.sendMessage("");
            		player.sendMessage("§eVous avez claim ce chunk !");
            	}
            } else if(cmd.getName().equalsIgnoreCase("unclaim")) {
            	
            	Chunk chunk = player.getLocation().getChunk();

            	String chunkID = chunk.getX() + "_" + chunk.getZ();
            	
            	if (Main.getInstance().getConfig().getString("claim." + chunkID).equals(player.getUniqueId().toString())) {

            		if (Main.getInstance().getConfig().contains("claim." + chunkID)) {
            			Main.getInstance().getConfig().set("claim." + chunkID, null);;
            			Main.getInstance().saveConfig();
            			player.sendMessage("");
                		player.sendMessage("§eVous avez unclaim ce chunk !");
            		} else {
            			player.sendMessage("");
            			player.sendMessage("§cCe chunk n'est pas claim !");
            		}
            	} else {
            		player.sendMessage("");
            		player.sendMessage("§cVous ne possédez pas ce chunk !");
            	}
            }
            } else {
            	player.sendMessage("");
            	player.sendMessage("§cLes claim sont seulement autorisés dans les mondes : §6§lSurvie");
            }
        }
        return true;
    }
}
