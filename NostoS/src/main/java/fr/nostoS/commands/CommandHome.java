package fr.nostoS.commands;

import fr.nostoS.Utils;
import fr.nostoS.mysql.DbConnection;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.UUID;

public class CommandHome implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (Utils.getSurviesNames().contains(player.getWorld().getName())) {

            final DbConnection dbConnection = Utils.getDatabaseManager().getDbConnection();

            try {
                final Connection connection = dbConnection.getConnection();

                if (cmd.getName().equalsIgnoreCase("sethome")) {

                    if (args.length == 0) {
                        player.sendMessage("\n§cUtilisation : /sethome <nom>");
                        return false;
                    }

                    if (args.length == 1) {
                        setHome(connection, player.getUniqueId(), args[0], player.getWorld().getName(), (float) player.getLocation().getX(), (float) player.getLocation().getY(), (float) player.getLocation().getZ(), player.getEyeLocation().getPitch(), player.getEyeLocation().getYaw());
                        player.sendMessage("\n§bLe home §b§l" + args[0] + " §best maintenant mis en place en §a§l" + player.getLocation().getBlockX() + " §a§l" + player.getLocation().getBlockY() + " §a§l" + player.getLocation().getBlockZ() + " §b!");
                        return false;
                    }

                    player.sendMessage("\n§cUtilisation : /sethome <nom>");
                    return false;

                } else if (cmd.getName().equalsIgnoreCase("delhome")) {

                    if (args.length == 0) {
                        player.sendMessage("\n§cUtilisation : /delhome <nom>");
                        return false;
                    }

                    if (args.length == 1) {
                        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, name FROM survival_home WHERE (uuid = ? AND name = ?)");
                        preparedStatement.setString(1, player.getUniqueId().toString());
                        preparedStatement.setString(2, args[0]);
                        final ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            deleteHome(connection, player.getUniqueId(), args[0]);
                            player.sendMessage("\n§bHome supprimé !");
                            return false;
                        } else {
                            player.sendMessage("\n§cVous n'avez aucun home à ce nom");
                        }
                    }

                    player.sendMessage("\n§cUtilisation : /delhome <nom>");
                    return false;

                } else if (cmd.getName().equalsIgnoreCase("home")) {

                    if (args.length == 0) {
                        player.sendMessage("\n§cUtilisation : /home <nom>");
                        return false;
                    }

                    if (args.length == 1) {
                        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM survival_home WHERE (uuid = ? AND name = ?)");
                        preparedStatement.setString(1, player.getUniqueId().toString());
                        preparedStatement.setString(2, args[0]);
                        final ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            World w = Bukkit.getServer().getWorld(resultSet.getString("world"));
                            float x = resultSet.getFloat("x");
                            float y = resultSet.getFloat("y");
                            float z = resultSet.getFloat("z");
                            float pitch = resultSet.getFloat("pitch");
                            float yaw = resultSet.getFloat("yaw");
                            final Location loc = new Location(w, x, y, z, pitch, yaw);
                            player.sendMessage("\n§bTéléportation vers §b§l" + args[0] + " §b!");
                            player.teleport(loc);
                            player.teleport(loc);
                        } else {
                            player.sendMessage("\n§cVous n'avez aucun home à ce nom");
                        }
                        return false;
                    }

                    player.sendMessage("\n§cUtilisation : /home <nom>");
                    return false;

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else {
            player.sendMessage("\n§cLes homes sont seulement autorisés dans les mondes : §6§lSurvie");
        }

        return false;
    }

    private void setHome(Connection connection, UUID uuid, String name, String world, float x, float y, float z, float pitch, float yaw) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO survival_home VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, world);
            preparedStatement.setFloat(4, x);
            preparedStatement.setFloat(5, y);
            preparedStatement.setFloat(6, z);
            preparedStatement.setFloat(7, pitch);
            preparedStatement.setFloat(8, yaw);
            preparedStatement.setTimestamp(9, timestamp);
            preparedStatement.setTimestamp(10, timestamp);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteHome(Connection connection, UUID uuid, String name) {
        try {
            final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM survival_home WHERE (uuid = ? AND name = ?)");

            preparedStatement.setString(1, uuid.toString());
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}