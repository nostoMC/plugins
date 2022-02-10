package fr.nostoS.commands;

import fr.nostoS.Utils;
import fr.nostoS.mysql.DbConnection;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TabHome implements TabCompleter {

    List<String> arguments = new ArrayList<>();

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        Player player = (Player) sender;

        arguments.clear();
        final DbConnection dbConnection = Utils.getDatabaseManager().getDbConnection();
        try {
            final Connection connection = dbConnection.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement("SELECT uuid, name FROM survival_home");
            final ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getString("uuid").equals(player.getUniqueId().toString())) {
                    arguments.add(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> result = new ArrayList<>();

        if (cmd.getName().equalsIgnoreCase("home") || cmd.getName().equalsIgnoreCase("delhome")) {
            if (args.length == 1) {
                for (String a : arguments) {
                    if (a.toLowerCase().startsWith(args[0].toLowerCase())) {
                        result.add(a);
                    }
                }
                return result;
            }
        } else {
            return  result;
        }

        return null;
    }

}