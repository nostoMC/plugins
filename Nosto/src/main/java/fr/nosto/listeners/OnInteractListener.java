package fr.nosto.listeners;

import fr.nosto.Main;
import fr.nosto.Utils;
import fr.nosto.menus.mainmenu.TpMenu;
import fr.nosto.mysql.DbConnection;
import org.bukkit.Chunk;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OnInteractListener implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		
		Player player = event.getPlayer();
		
		if (Utils.getSurviesNames().contains(player.getWorld().getName())) {
			
			if (event.getClickedBlock() != null) {

				final DbConnection dbConnection = Main.databaseManager.getDbConnection();
				try {
					Chunk chunk = event.getClickedBlock().getChunk();
					String chunkID = chunk.getX() + "_" + chunk.getZ();

					final Connection connection = dbConnection.getConnection();
					final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, chunkID FROM survival_claim WHERE (uuid = ? AND chunkID = ?)");
					preparedStatement.setString(1, player.getUniqueId().toString());
					preparedStatement.setString(2, chunkID);
					final ResultSet resultSet = preparedStatement.executeQuery();

					if (!resultSet.next()) {
						if (!player.isOp()) {
							event.setCancelled(true);
							player.sendMessage("");
							player.sendMessage("§cCe chunk a été claim. Tu ne peut donc rien faire dans cette zone.");
						}

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
        
        if (player.getWorld().getName().endsWith("Lobby")) {

			if (player.getOpenInventory().getTitle().equals("Crafting")
					&& event.getMaterial() == Material.COMPASS) {
				TpMenu.openMenu(player);
			}

			if (denyInteract(player)) event.setCancelled(true);

			Block block = event.getClickedBlock();
			if (block != null) {
				if (block.getType() == Material.OAK_WALL_SIGN || block.getType() == Material.DARK_OAK_DOOR) event.setCancelled(false);
			}
        }
	}
	
	@EventHandler
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
		if (event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract(event.getPlayer())) event.setCancelled(true);
		}
	}
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if (event.getDamager().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract((Player) event.getDamager())) event.setCancelled(true);
			if (event.getEntity() instanceof ArmorStand) event.setCancelled(true);
		}
	}
	
	@EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
		if (event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract(event.getPlayer())) event.setCancelled(true);
		}
    }
	
	@EventHandler
	public void onArmorStandInteract(PlayerArmorStandManipulateEvent event) {
		if (event.getPlayer().getWorld().getName().endsWith("Lobby")) {
			if (denyInteract(event.getPlayer())) event.setCancelled(true);
		}
	}

	private boolean denyInteract(Player player) {
		return player.getGameMode() != GameMode.CREATIVE || !player.hasPermission("*");
	}

}
