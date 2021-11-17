package fr.nostoNC.listeners;

import java.util.Objects;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import org.spigotmc.event.entity.EntityDismountEvent;

public class SitListener implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (event.getHand() != EquipmentSlot.HAND
                || event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.isBlockInHand())
            return;

        Player player = event.getPlayer();
        if (player.isSneaking()) return;
        if (player.getWorld() != Main.defaultWorld) return;

        Block block = event.getClickedBlock();
        if (block == null) return;
        if (!block.getRelative(BlockFace.UP).isPassable()) return;

        BlockData data = block.getBlockData();
        World world = player.getWorld();

        if (data instanceof Stairs) {
            Stairs stair = (Stairs) data;

            if (stair.getHalf() != Bisected.Half.BOTTOM) return;

            Location loc = block.getLocation().add(.5, .3, .5);

            switch (stair.getShape()) {
                case STRAIGHT:
                    loc.setYaw(0);
                    break;
                case INNER_LEFT:
                case OUTER_LEFT:
                    loc.setYaw(-45);
                    break;
                case INNER_RIGHT:
                case OUTER_RIGHT:
                    loc.setYaw(45);
                    break;
            }

            switch (stair.getFacing()) {
                case EAST:
                    loc.setYaw(loc.getYaw() + 90);
                    break;
                case SOUTH:
                    loc.setYaw(loc.getYaw() + 180);
                    break;
                case WEST:
                    loc.setYaw(loc.getYaw() + 270);
                    break;
            }

            loc.setPitch(0);
            loc.add(loc.getDirection().multiply(.2));

            ArmorStand chair = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);

            chair.setVisible(false);
            chair.setMarker(true);
            chair.setCustomName("ClubStairSeat");
            chair.addPassenger(player);
        }

        else if (block.getType() == Material.PISTON_HEAD) {
            PistonHead piston = (PistonHead) data;

            if (piston.getFacing() != BlockFace.UP) return;

            Location loc = block.getLocation().add(.5, .8,.5);
            loc.setYaw(player.getLocation().getYaw());

            ArmorStand chair = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);

            chair.setVisible(false);
            chair.setMarker(true);
            chair.addPassenger(player);
        }

        else if (block.getType() == Material.SMOOTH_QUARTZ_SLAB) {
            Slab slab = (Slab) data;

            if (slab.getType() != Slab.Type.BOTTOM) return;

            Location loc = block.getLocation().add(.5, .3,.5);
            loc.setDirection(new Vector(14.0, 5.0, 179.0).subtract(loc.toVector())); // le joueur est assis vers la table

            ArmorStand chair = (ArmorStand) world.spawnEntity(loc, EntityType.ARMOR_STAND);

            chair.setVisible(false);
            chair.setMarker(true);
            chair.setCustomName("ClubStairSeat");
            chair.addPassenger(player);
        }

    }

    @EventHandler
    public void onDismount(EntityDismountEvent event) {

        if(!(event.getEntity() instanceof Player)) return;
        if(!(event.getDismounted() instanceof ArmorStand)) return;

        ArmorStand as = (ArmorStand) event.getDismounted();

        if (Objects.equals(as.getCustomName(), "ClubStairSeat")) {

            // Cette task est nécessaire pour exécuter le code APRÈS que l'event ait fini d'etre calculé
            // (sinon le tp n'a pas d'effet)
            new BukkitRunnable() {

                @Override
                public void run() {
                    Player player = (Player) event.getEntity();
                    player.teleport(player.getLocation().add(0, 1, 0));
                }
            }.runTask(Main.getPlugin(Main.class));
        }

    }
}
