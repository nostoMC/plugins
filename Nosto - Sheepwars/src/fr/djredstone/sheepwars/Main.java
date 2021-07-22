package fr.djredstone.sheepwars;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Main extends JavaPlugin implements Listener, CommandExecutor {
	
	List<String> arguments = new ArrayList<String>();
	
	Location hub = new Location(Bukkit.getWorld("swHub"), 0, 5, 0);
	
	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable() {
		
	}
	
	@EventHandler
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		Player player = null;
		if(sender instanceof Player) {
			player = (Player) sender;
		}
		
		if(player.getWorld() == hub.getWorld()) {
		
			if(args.length >= 2) {
			
				player.sendMessage("");
				player.sendMessage("§c§lUtilisation : /sheepwars play");
				
			
			} else {
		
				if(cmd.getName().equalsIgnoreCase("sheepwars") || cmd.getName().equalsIgnoreCase("sw")) {
			
					if(args[0].equalsIgnoreCase("sethub")) {
					
						if(player.hasPermission("nosto.sheepwars.admin")) {
				
							hub = player.getLocation();
							player.sendMessage("");
							player.sendMessage("§a§lCoordonées missent à jour !");
					
						} else {
						
							player.sendMessage("");
							player.sendMessage("§c§lVous n'avez pas les permissions !");
						
						}
					
					} else if(args[0].equalsIgnoreCase("play")) {
						
						openMapMenu(player);
						
					}
			
				}
			}
		
		} else {
			
			player.sendMessage("");
			player.sendMessage("§cCette commande est seulement autorisés dans les mondes : §6§lSheepwars");
			
		}
		
		return false;
		
	}
	
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args){

		Player player = (Player) sender;
		
		if(arguments.isEmpty()) {
			arguments.add("play");
			if(player.hasPermission("nosto.sheepwars.admin")) {
				arguments.add("sethub");
			}
		}
		
		List<String> result = new ArrayList<String>();
		if(args.length == 1) {
			for (String a : arguments) {
				if(a.toLowerCase().startsWith(args[0].toLowerCase())) {
					result.add(a);
				}
			}
			return result;
		}
		
		return null;
	}
	
	public ItemStack getItem(Material material, String customName) {
		ItemStack it = new ItemStack(material, 1);
		ItemMeta itM = it.getItemMeta();
		if(customName != null) itM.setDisplayName(customName);
		itM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		it.setItemMeta(itM);
		return it;
	}
	
	public void openMapMenu(Player player) {
		
		Inventory inv;
		inv = Bukkit.createInventory(null, 45, "§f§lSheep§c§lwars");
		
		inv.setItem(10, getItem(Material.OAK_BOAT , "§f§lNaval"));
		inv.setItem(12, getItem(Material.WHITE_WOOL , "§f§lAérien"));
		inv.setItem(14, getItem(Material.SMOOTH_STONE , "§f§lGrate ciel"));
		inv.setItem(16, getItem(Material.MAGMA_CREAM , "§f§lSpacial"));
		inv.setItem(31, getItem(Material.FIREWORK_ROCKET, "§f§lMap Aléatoire"));
		inv.setItem(36, getItem(Material.BARRIER, "§c§lFermer le menu"));
		
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
		
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {

		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();

		if(event.getView().getTitle().equalsIgnoreCase("§f§lSheep§c§lwars")) {
			event.setCancelled(true);
			
			switch(current.getType()){
			
			case OAK_BOAT:
				event.getView().close();
				mapNaval(player);
				break;
			
			case BARRIER:
				event.getView().close();
				break;
			
			default:
				break;
			}
		}
	}
	
	public boolean deleteWorld(File path) {
	      if(path.exists()) {
	          File files[] = path.listFiles();
	          for(int i=0; i<files.length; i++) {
	              if(files[i].isDirectory()) {
	                  deleteWorld(files[i]);
	              } else {
	                  files[i].delete();
	              }
	          }
	      }
	      return(path.delete());
	}
	
	public void mapNaval(Player player) {
		if (Bukkit.getWorld("sw_naval") == null) {
            
        }
		Location world = new Location(Bukkit.getWorld("sw_naval"), 0, 5, 0);
		player.teleport(world);
	}
	
	public void createMapNaval() {
		Bukkit.createWorld(new WorldCreator("sw_naval").type(WorldType.FLAT));
        Bukkit.getWorld("sw_naval").setDifficulty(Difficulty.PEACEFUL);
        Bukkit.getWorld("sw_naval").setAnimalSpawnLimit(0);
        Bukkit.getWorld("sw_naval").setMonsterSpawnLimit(0);
        
        BukkitTask navalMapTask1 = new BukkitRunnable() {

			@Override
			public void run() {
				if(Bukkit.getWorld("sw_naval").getPlayers().isEmpty()) {
					deleteWorld(Bukkit.getWorld("sw_naval").getWorldFolder());
				}
			}
        	
        }.runTaskTimer(this, 0, 0);
	}
}
