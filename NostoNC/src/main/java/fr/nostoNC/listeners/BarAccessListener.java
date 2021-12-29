package fr.nostoNC.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Openable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;

public class BarAccessListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND
                || event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.isBlockInHand())
            return;

        Player player = event.getPlayer();
        if (!player.hasPermission("nosto.nightclub.staff") && !player.hasPermission("nosto.nightclub.interact")) return;
        if (player.isSneaking()) return;
        if (!Utils.isInClub(player)) return;

        Block block = event.getClickedBlock();
        if (block == null) return;
        if (block.getType() != Material.IRON_TRAPDOOR) return;

        Bisected half = (Bisected) block.getBlockData();
        if (half.getHalf() != Bisected.Half.TOP) return;

        Openable openable = (Openable) block.getBlockData();
        if (openable.isOpen()) return;

        block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, 1, 1);
        openable.setOpen(true);
        block.setBlockData(openable);

        new BukkitRunnable() {
            @Override
            public void run() {
                block.getWorld().playSound(block.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_CLOSE, 1, 1);
                openable.setOpen(false);
                block.setBlockData(openable);
            }
        }.runTaskLater(Main.instance, 20);

    }

}
