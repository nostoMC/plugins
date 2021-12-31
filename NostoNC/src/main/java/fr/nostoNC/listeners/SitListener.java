package fr.nostoNC.listeners;

import java.util.Collection;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.type.PistonHead;
import org.bukkit.block.data.type.Slab;
import org.bukkit.block.data.type.Stairs;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.scheduler.BukkitRunnable;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
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
        if (!Utils.isInClub(player)) return;

        Block block = event.getClickedBlock();
        if (block == null) return;
        if (!block.getRelative(BlockFace.UP).isPassable()) return;
        
        Collection<Entity> nearbyEntities = block.getLocation().add(.5, .5, .5).getNearbyEntities(.5, .5, .5);
        for (Entity entity : nearbyEntities) {
            if (entity.getScoreboardTags().contains("clubSeat")) return;
        }

        BlockData data = block.getBlockData();

        if (data instanceof Stairs stair) {

            if (stair.isWaterlogged()) return;
            if (stair.getHalf() != Bisected.Half.BOTTOM) return;

            Location loc = block.getLocation().add(.5, .3, .5);

            switch (stair.getShape()) {
                case STRAIGHT -> loc.setYaw(0);
                case INNER_LEFT, OUTER_LEFT -> loc.setYaw(-45);
                case INNER_RIGHT, OUTER_RIGHT -> loc.setYaw(45);
            }
            switch (stair.getFacing()) {
                case EAST -> loc.setYaw(loc.getYaw() + 90);
                case SOUTH -> loc.setYaw(loc.getYaw() + 180);
                case WEST -> loc.setYaw(loc.getYaw() + 270);
            }

            loc.setPitch(0);
            loc.add(loc.getDirection().multiply(.2));

            ArmorStand chair = createArmorStand(loc);
            chair.addPassenger(player);
        }

        else if (block.getType() == Material.PISTON_HEAD) {
            PistonHead piston = (PistonHead) data;

            if (piston.getFacing() != BlockFace.UP) return;

            Location loc = block.getLocation().add(.5, .4,.5);

            AreaEffectCloud chair = createArea(loc);
            chair.addPassenger(player);
        }

        else if (block.getType() == Material.SMOOTH_QUARTZ_SLAB || block.getType() == Material.BRICK_SLAB) {
            Slab slab = (Slab) data;

            if (slab.isWaterlogged()) return;
            if (slab.getType() != Slab.Type.BOTTOM) return;

            Location loc = block.getLocation().add(.5, -.1,.5);

            AreaEffectCloud chair = createArea(loc);
            chair.addPassenger(player);
        }

    }

    @EventHandler
    public void onDismount(EntityDismountEvent event) {

        if(!(event.getEntity() instanceof Player)) return;

        Entity entity = event.getDismounted();
        if(!(entity instanceof ArmorStand || entity instanceof AreaEffectCloud)) return;

        Set<String> tags = entity.getScoreboardTags();
        
        if (tags.contains("clubSeat")) {
            entity.remove();

            // Cette task est nécessaire pour exécuter le code APRÈS que l'event ait fini d'etre calculé
            // (sinon le tp n'a pas d'effet)
            new BukkitRunnable() {

                @Override
                public void run() {
                    Player player = (Player) event.getEntity();
                    if (tags.contains("lift_0.6")) player.teleport(player.getLocation().add(0, 0.6, 0));
                    if (tags.contains("lift_1.2")) player.teleport(player.getLocation().add(0, 1.2, 0));
                }
            }.runTask(Main.instance);
        }

    }

    private static AreaEffectCloud createArea(Location loc) {
        // l'area est summon en 0 0 pour qu'on ne voie pas les particules qu'elle émet lors du spawn
        Location spawnLoc = new Location(Main.defaultWorld, 0, 0, 0);
        AreaEffectCloud cloud = (AreaEffectCloud) Main.defaultWorld.spawnEntity(spawnLoc, EntityType.AREA_EFFECT_CLOUD);

        cloud.setParticle(Particle.BLOCK_CRACK, Material.AIR.createBlockData());
        cloud.setDuration(32000);
        cloud.setRadius(0);
        cloud.addScoreboardTag("clubSeat");
        cloud.addScoreboardTag("lift_0.6");

        cloud.teleport(loc);

        return cloud;
    }

    private static ArmorStand createArmorStand(Location loc) {
        // l'armor stand est summon en 0 0 pour qu'on ne le voie pas lors du spawn
        Location spawnLoc = new Location(Main.defaultWorld, 0, 0, 0, loc.getYaw(), loc.getPitch());
        ArmorStand armorStand = (ArmorStand) Main.defaultWorld.spawnEntity(spawnLoc, EntityType.ARMOR_STAND);

        armorStand.setVisible(false);
        armorStand.setMarker(true);
        armorStand.addScoreboardTag("clubSeat");
        armorStand.addScoreboardTag("lift_1.2");

        armorStand.teleport(loc);

        return armorStand;
    }
}
