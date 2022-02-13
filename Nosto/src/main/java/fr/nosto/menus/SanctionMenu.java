package fr.nosto.menus;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Objects;

import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import fr.nosto.Utils;
import fr.nosto.mysql.prepareStatement.mute;

public class SanctionMenu implements Listener {

    private static final String title = "§c§lSanctions contre ";

    @SuppressWarnings("deprecation")
    public static void openMenu(Player player, OfflinePlayer target) throws SQLException {

        Inventory inv = Bukkit.createInventory(null, 45, title + target.getName());

        ItemStack skull = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta meta = (SkullMeta) skull.getItemMeta();
        assert meta != null;
        meta.setOwningPlayer(target);
        meta.setDisplayName("§c§l" + target.getName());

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        skull.setItemMeta(meta);

        inv.setItem(13, skull);

        inv.setItem(29, Utils.createItem(Material.TRIPWIRE_HOOK, "§cMute", mute.check(target).next() ? "§7Le joueur est mute" : "§8Le joueur n'est pas mute"));
        inv.setItem(31, Utils.createItem(Material.STICK, "§cKick"));
        inv.setItem(33, Utils.createItem(Material.WOODEN_SWORD, "§cBan", target.isBanned() ? "§7Le joueur est banni" : "§8Le joueur n'est pas banni"));

        Utils.fillEmptyItem(inv);

        player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 100, 1);
        player.openInventory(inv);

    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getView().getTitle().startsWith(title)) return;
        event.setCancelled(true);

        Player player = (Player) event.getWhoClicked();
        ItemStack current = event.getCurrentItem();
        if (current == null) return;

        String targetName = event.getView().getTitle().replace(title, "");
        try {
            OfflinePlayer target = Bukkit.getOfflinePlayer(targetName);
            switch (current.getType()) {

                case TRIPWIRE_HOOK -> {
                    event.getView().close();
                    ResultSet resultSet = mute.check(target);
                    if (resultSet.next()) {
                        mute.remove(target, resultSet.getTimestamp("end_date"));
                        if (target.isOnline()) Objects.requireNonNull(target.getPlayer()).sendMessage("\n§aVous avais été dé-mute par §2" + player.getName() + "§a !");
                        player.sendMessage("\n§9" + targetName + "§b a correctement été dé-mute !");
                    } else {
                        mute.add(target, player, "Une raison", new Timestamp(System.currentTimeMillis() + 60*1000));
                        if (target.isOnline()) Objects.requireNonNull(target.getPlayer()).sendMessage("\n§cVous avais été mute par §4" + player.getName() + "§c !");
                        Bukkit.broadcastMessage("\n§9" + targetName + "§b a été mute par §9" + player.getName() + "§b pendant §960s §bpour la raison suivante :\n§9(WIP)");
                    }
                }

                case STICK -> {
                    event.getView().close();
                    if (target.isOnline()) {
                        Objects.requireNonNull(target.getPlayer()).kickPlayer("\n§cVous avais été kick par §4" + player.getName() + "§c pour la raison suivante :" +
                                "\n§4(WIP)");
                        Bukkit.broadcastMessage("\n§9" + targetName + "§b a été kick par §9" + player.getName() + "§b pour la raison suivante :" +
                                "\n§9(WIP)");
                    } else {
                        player.sendMessage("\n§9" + targetName + "§b ne peut pas être kick");
                    }
                }

                case WOODEN_SWORD -> {
                    event.getView().close();
                    if (target.isBanned()) {
                        Bukkit.getBanList(BanList.Type.NAME).pardon(targetName);
                        player.sendMessage("\n§9" + targetName + "§b a correctement été dé-banni !");
                    } else {
                        target.banPlayer("\n§cVous avais été banni par §4" + player.getName() + "§c pour la raison suivante :" +
                                "\n§4(WIP)");
                        Bukkit.broadcastMessage("\n§9" + targetName + "§b a été banni par §9" + player.getName() + "§b pour la raison suivante :" +
                                "\n§9(WIP)");
                    }
                }

            }
        } catch (NullPointerException ignored) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
