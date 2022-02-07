package fr.nosto.menus;

import fr.nosto.Main;
import fr.nosto.Utils;
import fr.nosto.menus.mainmenu.ShopMenu;
import fr.nosto.menus.mainmenu.TpMenu;
import fr.nosto.menus.mainmenu.TrailsMenu;
import fr.nosto.mysql.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainMenu implements Listener {

	public static final String title = "§2§lMenu";

	public static void openMenu(Player player) {

		if (!player.getWorld().getName().endsWith("Lobby")) return;

		float playerMoney = 0;
		try {
			playerMoney = getPlayerMoney(player);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		Inventory inv = Bukkit.createInventory(null, 54, title);
	
		ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
		SkullMeta meta = (SkullMeta) skull.getItemMeta();
		assert meta != null;
		meta.setOwningPlayer(player);
		meta.setDisplayName("§6§l" + player.getName());
		
		ArrayList<String> lore = new ArrayList<>();
		lore.add("§eMoney : §6§l" + playerMoney);
		
		meta.setLore(lore);
		
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		skull.setItemMeta(meta);
		
		inv.setItem(13, skull);

		ItemStack particleItem;
		if (player.getWorld().getName().endsWith("Lobby")) {
			particleItem = Utils.createItem(Material.BLAZE_POWDER, "§6§lParticules");
		} else {
			particleItem = Utils.createItem(Material.BLAZE_POWDER, "§8§lParticules",
					"§cCe menu n'est accessible que dans les lobbys !");
		}
		inv.setItem(37, particleItem);
		inv.setItem(31, Utils.createItem(Material.COMPASS , "§2§lTP"));
		inv.setItem(43, Utils.createItem(Material.GOLD_INGOT , "§e§lBoutique"));
		
		Utils.fillEmptyItem(inv);
	
		player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
		player.openInventory(inv);
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event) {
		if (!event.getView().getTitle().equals(title)) return;
		event.setCancelled(true);

		Player player = (Player) event.getWhoClicked();
		ItemStack current = event.getCurrentItem();
		if (current == null) return;
		
		switch (current.getType()) {

			case COMPASS:
				TpMenu.openMenu(player);
				break;

			case BLAZE_POWDER:
				if (player.getWorld().getName().endsWith("Lobby"))
					TrailsMenu.openMenu(player);
				else player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_NO, 100, 1);
				break;

			case GOLD_INGOT:
				ShopMenu.openMenu(player);
				break;

			default:
				break;
		}
	}

	private static float getPlayerMoney(Player player) throws SQLException {

		final DbConnection dbConnection = Utils.databaseManager.getDbConnection();

		final Connection connection = dbConnection.getConnection();

		final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, money FROM player_money WHERE uuid = ?");
		preparedStatement.setString(1, player.getUniqueId().toString());
		final ResultSet resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) return resultSet.getFloat("money");
		return 0;
	}

}
