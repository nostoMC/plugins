package fr.djredstone.nosto.commands.mdp;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.djredstone.nosto.Main;

public class CommandMDP implements CommandExecutor {
	
	ArrayList<Player> menuPlayers = Main.getMenuPlayersList();

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("mdp")) {
        	
        	if(args.length == 0) {
        		player.sendMessage("");
        		player.sendMessage("�cUtilisation : /mdp reset");
        	} else {
        		if(args[0].equalsIgnoreCase("reset")) {
        			menuPlayers = Main.getMenuPlayersList();
        			if(menuPlayers.contains(player)) {
        				
        				player.sendMessage("");
        				player.sendMessage("�cVous devez vous connecter ! (/login <Mot de passe>)");
        				
        			} else {
        				
        				Main.getInstance().getConfig().set("password." + player.getUniqueId() + ".valide", null);
        				Main.getInstance().getConfig().set("password." + player.getUniqueId().toString() + ".password", null);
        				player.kickPlayer("�eMot de passe r�inisialis� !");
        				
        			}
        		} else {
        			player.sendMessage("");
            		player.sendMessage("�cUtilisation : /mdp reset");
        		}
        	}
        	
        }
        
        if(cmd.getName().equalsIgnoreCase("register")) {
			if(Main.getInstance().getConfig().contains("password." + player.getUniqueId().toString() + ".password")) {
				player.sendMessage("");
				player.sendMessage("�cVous avez d�j� mis un mot de passe !");
			} else {
				if(args.length < 1) {
					player.sendMessage("");
					player.sendMessage("�cUtilisation : /register <MotDePasse>");
				} else if(args.length > 1) {
					player.sendMessage("");
					player.sendMessage("�cUtilisation : /register <MotDePasse>");
				} else {
					Main.getInstance().getConfig().set("password." + player.getUniqueId().toString() + ".password", args[0]);
					player.sendMessage("");
					player.sendMessage("�aVous avez mis �2�l" + args[0] + " �aen tant que mot de passe ! V�rifier votre mot de passe avec �2�l/valide <MotDePasse>");
					Main.getInstance().saveConfig();
				}
			}
		}
        
        if(cmd.getName().equalsIgnoreCase("login")) {
        	if(menuPlayers.contains(player)) {
        		if(args.length == 1) {
        			if(!Main.getInstance().getConfig().contains("password." + player.getUniqueId().toString() + ".password")) {
        				player.sendMessage("");
        				player.sendMessage("�cVous n'avez pas mis de mot de passe ! (/register <Mot de passe>)");
        			} else {
        				if(Main.getInstance().getConfig().getBoolean("password." + player.getUniqueId().toString() + ".valide") == true) {
        					if(args[0].equals(Main.getInstance().getConfig().getString("password." + player.getUniqueId().toString() + ".password"))) {
        						player.sendMessage("");
        						player.sendMessage("�aMot de passe correct ! �eBienvenue sur le serveur !");
        						player.sendTitle("�6�lBienvenue sur le serveur !", "�eConnection sous le pseudonime �6�l" + player.getName(), 0, 60, 5);
        						ItemStack compassLobby = new ItemStack(Material.COMPASS, 1);
        						ItemMeta compassLobbyMeta = compassLobby.getItemMeta();
        						compassLobbyMeta.setDisplayName("�b�lClick pour ouvrire le menu de t�l�portation");
        						compassLobbyMeta.addEnchant(Enchantment.DAMAGE_ALL, 200, true);
        						compassLobbyMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        						compassLobby.setItemMeta(compassLobbyMeta);
        						player.getInventory().setItem(4, compassLobby);
        						player.updateInventory();
        						String[] messages = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        						Bukkit.broadcastMessage("");
        						if(messages[new Random().nextInt(messages.length)] == "1") {
        							Bukkit.broadcastMessage("�eSalut �6�l" + player.getName() + "�e ! Bienvenue sur le serveur !");
        						} else if(messages[new Random().nextInt(messages.length)] == "2") {
        							Bukkit.broadcastMessage("�eBon retour parmi nous �6�l" + player.getName() + "�e !");
        						} else if(messages[new Random().nextInt(messages.length)] == "3") {
        							Bukkit.broadcastMessage("�6�l" + player.getName() + "�e est de retour !");
        						} else if(messages[new Random().nextInt(messages.length)] == "4") {
        							Bukkit.broadcastMessage("�6�l" + player.getName() + "�e nous a rejoint !");
        						} else if(messages[new Random().nextInt(messages.length)] == "5") {
        							Bukkit.broadcastMessage("�eOh bas ! Qui vois l� je ? C'est �6�l" + player.getName() + "�e !");
        						} else if(messages[new Random().nextInt(messages.length)] == "6") {
        							Bukkit.broadcastMessage("�6�l" + player.getName() + "�e est l� ! J'AIME BIEN !");
        						} else if(messages[new Random().nextInt(messages.length)] == "7") {
        							Bukkit.broadcastMessage("�eOh.. It's you �6�l" + player.getName() + "�e .. It's been a looong time.. How have you been ?");
        						} else if(messages[new Random().nextInt(messages.length)] == "8") {
        							Bukkit.broadcastMessage("�eAh ! Un nouveau joueur de connecter ! Et il s'agit de... �6�l" + player.getName() + "�e !");
        						} else if(messages[new Random().nextInt(messages.length)] == "9") {
        							Bukkit.broadcastMessage("�eHello �6�l" + player.getName() + "�e ! Bon cube sur le serveur !");
        						}
        						if(player.getUniqueId().toString().equalsIgnoreCase("a9cf67ad-7b27-4ca3-a428-c6d5beb9b038") || player.getUniqueId().toString().equalsIgnoreCase("f2d404ac-19f2-4365-b11a-6f9d32d3db26") || player.getUniqueId().toString().equalsIgnoreCase("aff99f6d-a86b-447e-ac3f-0cb88a100145")) {
        							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add administrateur");
        							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "op " + player.getName());
        						} else if(player.getUniqueId().toString().equalsIgnoreCase("9a798aef-55fe-4c9b-b59b-ad7d190ae724")) {
        							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add buildeur");
        							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "op " + player.getName());
        						} else if(player.getUniqueId().toString().equalsIgnoreCase("428bd794-9832-46c1-8e44-3ec719eb7809")) {
        							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + player.getName() + " parent add dev");
        							Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "op " + player.getName());
        						}
        					} else {
        						player.sendMessage("");
        						player.sendMessage("�cLe mot de passe est incorect !");
        					}
        				} else {
        					player.sendMessage("");
        					player.sendMessage("�cVous n'avez pas valid� votre mot de passe ! (/valide <MotDePasse>)");
        				}
        			}
        		} else {
        			player.sendMessage("");
        			player.sendMessage("�cUtilisation : /login <MotDePasse>");
        		}
        	} else {
        		player.sendMessage("");
        		player.sendMessage("�cVous �tes d�j� connect� !");
        	}
        }
        
        if(cmd.getName().equalsIgnoreCase("valide")) {
        	if(Main.getInstance().getConfig().getBoolean("password." + player.getUniqueId().toString() + ".valide") == true || !Main.getInstance().getConfig().getBoolean("password." + player.getUniqueId().toString() + ".valide")) {
        		if(args.length == 1) {
        			if(!Main.getInstance().getConfig().contains("password." + player.getUniqueId().toString() + ".password")) {
        				player.sendMessage("");
        				player.sendMessage("�cVous n'avez pas mis de mot de passe ! (/register <Mot de passe>)");
        			} else {
        				if(args[0].equalsIgnoreCase(Main.getInstance().getConfig().getString("password." + player.getUniqueId().toString() + ".password"))) {
        					player.sendMessage("");
        					player.sendMessage("�aMot de passe valid� !");
        					Main.getInstance().getConfig().set("password." + player.getUniqueId() + ".valide", true);
        					Main.getInstance().saveConfig();
        				} else {
       						player.sendMessage("");
       						player.sendMessage("�cLe mot de passe est incorect !");
       					}
        			}
        		} else {
        			player.sendMessage("");
        			player.sendMessage("�cUtilisation : /valide <MotDePasse>");
        		}
        	} else {
        		player.sendMessage("");
        		player.sendMessage("�cVous avez d�j� valid� votre mot de passe !");
        	}
        }
		
		return false;
	}

}
