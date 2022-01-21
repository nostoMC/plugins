package fr.nostoNC.tasks;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import fr.nostoNC.Main;
import org.jetbrains.annotations.NotNull;

public class ElevatorTask {

    private static boolean inited = false;
    private static Main main;

    private static World mainLobby;

    private static final Vector LOBBY_ELEVATOR_CORNER = new Vector(-2.0, 99.0, -10.0);
    private static final Vector CLUB_ELEVATOR_CORNER = new Vector(-3.0, 101.0, 216.0);

    private static final BoundingBox LOBBY_ELEVATOR_BOX =
            BoundingBox.of(new Vector(-2.0, 99.0, -15.0), new Vector(3.0, 102.0, -10.0));
    private static final BoundingBox CLUB_ELEVATOR_BOX =
            BoundingBox.of(new Vector(-8.0, 101.0, 216.0), new Vector(-3.0, 104.0, 221.0));
    
    private static final Set<UUID> inLobbyElevator = new HashSet<>();
    private static final Set<UUID> inClubElevator = new HashSet<>();

    public enum Elevator {
        LOBBY, CLUB
    }

    public static void init(Main main) {

        if (inited) return;
        inited = true;

        ElevatorTask.main = main;

        mainLobby = Bukkit.getWorld("MainLobby");
        if (mainLobby == null) {
            Bukkit.getLogger().log(Level.SEVERE, "Unable to get world \"MainLobby\"");
            return;
        }

        new BukkitRunnable()  {

            @Override
            public void run() {

                for (Player player : mainLobby.getPlayers()) {
                    if (!inLobbyElevator.contains(player.getUniqueId()) && LOBBY_ELEVATOR_BOX.contains(player.getLocation().toVector())) {
                        startTimer(player, Elevator.LOBBY);
                    }
                }
                for (Player player : Main.defaultWorld.getPlayers()) {
                    if (!inClubElevator.contains(player.getUniqueId()) && CLUB_ELEVATOR_BOX.contains(player.getLocation().toVector())) {
                        startTimer(player, Elevator.CLUB);
                    }
                }

            }
        }.runTaskTimer(main, 10, 40);
    }

    private static void startTimer(Player player, @NotNull Elevator elevator) {
        BoundingBox elevatorBox;
        Set<UUID> inElevator;

        if (elevator == Elevator.LOBBY) {
            elevatorBox = LOBBY_ELEVATOR_BOX;
            inElevator = inLobbyElevator;
        } else {
            elevatorBox = CLUB_ELEVATOR_BOX;
            inElevator = inClubElevator;
        }

        UUID uuid = player.getUniqueId();

        inElevator.add(uuid);

        BossBar bar = Bukkit.createBossBar("§eL'ascenceur démarre bientot..", BarColor.YELLOW, BarStyle.SOLID);
        bar.setVisible(true);
        bar.setProgress(0.0);
        bar.addPlayer(player);

        if (elevator == Elevator.LOBBY) player.sendTitle("§c§c⚠ Epileptic Warning ⚠","§6• Optifine Recommandé •",10,200,10);

        new BukkitRunnable() {

            private double progress = 0.0;

            @Override
            public void run() {

                progress += 1f / 100; // 100 ticks = 5 sec

                if (progress >= 1) {
                    startElevator(player, elevator);

                    bar.removeAll();
                    this.cancel();
                    return;
                }
                else if (!elevatorBox.contains(player.getLocation().toVector())) {
                    inElevator.remove(uuid);

                    bar.removeAll();
                    this.cancel();
                    return;
                }

                bar.setProgress(progress);

            }
        }.runTaskTimer(main, 0, 1);

    }

    private static void startElevator(Player player, @NotNull Elevator startingElevator) {
        Location playerLoc = player.getLocation();

        double x = LOBBY_ELEVATOR_CORNER.getX() + CLUB_ELEVATOR_CORNER.getX() - playerLoc.getX();
        double z = LOBBY_ELEVATOR_CORNER.getZ() + CLUB_ELEVATOR_CORNER.getZ() - playerLoc.getZ();

        double y;
        World destinationWorld;

        if (startingElevator == Elevator.LOBBY) {
            y = playerLoc.getY() + CLUB_ELEVATOR_CORNER.getY() - LOBBY_ELEVATOR_CORNER.getY();
            destinationWorld = Main.defaultWorld;
        } else {
            y = playerLoc.getY() + LOBBY_ELEVATOR_CORNER.getY() - CLUB_ELEVATOR_CORNER.getY();
            destinationWorld = mainLobby;
        }

        float yaw = playerLoc.getYaw() + 180;

        Location destination = new Location(destinationWorld, x, y, z, yaw, playerLoc.getPitch());
        player.teleport(destination); // teleporting 2 times to prevent multiverse-inventories from changing destination
        player.teleport(destination);
        
        if (startingElevator == Elevator.LOBBY) player.setResourcePack("https://www.dropbox.com/sh/hwhqynt5xvhvyj1/AAAki-6eHwpMLNlsMS6p4ySfa?dl=1");

        BoundingBox destionationElevatorBox;
        Set<UUID> inStartingElevator;
        Set<UUID> inDestinationElevator;

        if (startingElevator == Elevator.LOBBY) {
            destionationElevatorBox = CLUB_ELEVATOR_BOX;
            inStartingElevator = inLobbyElevator;
            inDestinationElevator = inClubElevator;
        } else {
            destionationElevatorBox = LOBBY_ELEVATOR_BOX;
            inStartingElevator = inClubElevator;
            inDestinationElevator = inLobbyElevator;
        }

        UUID uuid = player.getUniqueId();

        inStartingElevator.remove(uuid);
        inDestinationElevator.add(uuid);

        new BukkitRunnable() {

            @Override
            public void run() {
                if (!destionationElevatorBox.contains(player.getLocation().toVector())) {
                    inDestinationElevator.remove(uuid);
                    this.cancel();
                }
            }
        }.runTaskTimer(main, 1, 1);

    }
}
