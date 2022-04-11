package fr.nostoNC.tasks;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import fr.nostoNC.Utils;
import fr.nostoNC.tasks.effects.LaserUpDown;
import fr.nostoNC.tasks.effects.RandomParticleEffect;
import fr.nostoNC.tasks.effects.StrobeEffect;
import fr.nostoNC.tasks.effects.TopLaser;
import fr.nostoNC.tasks.effects.WallLaser;

public class EffectsManager {

    private static final World world = Utils.getDefaultWorld();

    /**
     * Activate or disable floor smoke effect
     * @param player The player who activate / disable the effect
     */
    public static void floorSmoke(Player player) {
        Utils.checkActiveEffectItem(player, "floorSmoke");
    }

    /**
     * Change the speed of the strobe
     * @param player The player who activate / disable the effect
     * @param type The ClickType of the item
     */
    public static void strobeTimingChange(Player player, ClickType type) {
        if (type == ClickType.RIGHT) {
            if (StrobeEffect.timing >= 20) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            StrobeEffect.timing++;
        }
        else if (type == ClickType.LEFT) {
            if (StrobeEffect.timing <= 1) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            StrobeEffect.timing--;
        }
    }

    /**
     * Activate or disable strobe effect
     * @param player The player who activate / disable the effect
     */
    public static void strobe(Player player) {
        Utils.checkActiveEffectItem(player, "strobe");
    }

    /**
     * Launch firework on the stage
     */
    public static void firework() {
        List<Firework> fireworks = new ArrayList<>();

        fireworks.add((Firework) world.spawnEntity(new Location(world, 4.5, 103.4, 148.5), EntityType.FIREWORK));
        fireworks.add((Firework) world.spawnEntity(new Location(world, 2.5, 103.4, 149.5), EntityType.FIREWORK));
        fireworks.add((Firework) world.spawnEntity(new Location(world, -0.5, 103.4, 150.5), EntityType.FIREWORK));
        fireworks.add((Firework) world.spawnEntity(new Location(world, -3.5, 103.4, 150.5), EntityType.FIREWORK));
        fireworks.add((Firework) world.spawnEntity(new Location(world, -6.5, 103.4, 149.5), EntityType.FIREWORK));
        fireworks.add((Firework) world.spawnEntity(new Location(world, -8.5, 103.4, 148.5), EntityType.FIREWORK));

        FireworkMeta fwm = fireworks.get(0).getFireworkMeta();

        fwm.setPower(2);
        fwm.addEffect(FireworkEffect.builder()
                .withColor(Color.WHITE)
                .withFade(Color.AQUA, Color.ORANGE)
                .with(FireworkEffect.Type.BURST)
                .flicker(true).trail(true)
                .build());

        for (Firework fw : fireworks) {
            fw.setVelocity(new Vector(0, 1, -0.2));
            fw.setFireworkMeta(fwm);
            fw.detonate();
        }
    }

    /**
     * Spawn many fire particle that going everywhere
     */
    public static void randomParticle() { new RandomParticleEffect(Main.getInstance()); }

    /**
     * Show all the top lasers
     */
    public static void topLaserFull() { TopLaser.showOrHideAll(false); }

    /**
     * Activate alternance on top lights
     */
    public static void topLaserSemi() { TopLaser.alternance(); }

    /**
     * Change the speed of the top laser
     * @param player The player who activate / disable the effect
     * @param type The ClickType of the item
     */
    public static void topLaserTimingChange(Player player, ClickType type) {
        if (type == ClickType.RIGHT) {
            if (TopLaser.timing >= 20) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            TopLaser.timing++;
        }
        else if (type == ClickType.LEFT) {
            if (TopLaser.timing <= 1) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            TopLaser.timing--;
        }
    }

    /**
     * Hide all the top lasers
     */
    public static void topLaserBlack() { TopLaser.showOrHideAll(true); }

    /***
     * Move top laser to down
     */
    public static void topLaserDown() { TopLaser.moveDown(); }

    /**
     * Move top laser to random
     */
    public static void topLaserRandom() { TopLaser.moveRandom(); }

    /**
     * Change the speed of the top laser moving
     * @param player The player who activate / disable the effect
     * @param type The ClickType of the item
     */
    public static void topLaserMoveTimingChange(Player player, ClickType type) {
        if (type == ClickType.RIGHT) {
            if (TopLaser.moveTiming >= 20) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            TopLaser.moveTiming++;
        }
        else if (type == ClickType.LEFT) {
            if (TopLaser.moveTiming <= 1) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            TopLaser.moveTiming--;
        }
    }

    /**
     * Move wall laser to front
     */
    public static void wallLaserFront() { WallLaser.moveFront(); }

    /**
     * Move wall laser to wave
     */
    public static void wallLaserWave() { WallLaser.moveWave(); }

    /**
     * Move wall laser to edge
     */
    public static void wallLaserEdge() { WallLaser.moveEdge(); }

    /**
     * Show wall laser
     */
    public static void wallLaserFull() { WallLaser.showOrHideAll(false); }

    /**
     * Alterne wall laser
     */
    public static void wallLaserSemi() { WallLaser.alternance(); }

    /**
     * Change the speed of the wall laser
     * @param player The player who activate / disable the effect
     * @param type The ClickType of the item
     */
    public static void wallLaserTimingChange(Player player, ClickType type) {
        if (type == ClickType.RIGHT) {
            if (WallLaser.timing >= 20) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            WallLaser.timing++;
        }
        else if (type == ClickType.LEFT) {
            if (WallLaser.timing <= 1) {
                player.playSound(player.getLocation(), Sound.BLOCK_LEVER_CLICK, 100, 1);
            }
            WallLaser.timing--;
        }
    }

    /**
     * Hide wall laser
     */
    public static void wallLaserBlack() { WallLaser.showOrHideAll(true); }

    /**
     * Activate or disable up/down laser
     */
    public static void laserUpDown() {
        if (LaserUpDown.isStarted()) LaserUpDown.stopLaser();
        else LaserUpDown.startLaser(Main.getInstance());
    }

    /**
     * Activate or disable top lights
     * @param player The player who activate / disable the effect
     */
    public static void topLights(Player player) { Utils.checkActiveEffectItem(player, "topLights"); }

}
