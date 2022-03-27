package fr.nosto.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import org.jetbrains.annotations.NotNull;

public class TabWhitelist implements TabCompleter {

    private static final List<String> arguments = new ArrayList<>();
    static {
        arguments.add("on");
        arguments.add("off");
        arguments.add("reload");
        arguments.add("list");
        arguments.add("add");
        arguments.add("remove");
    }

    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, String[] args) {

        List<String> result = new ArrayList<>();

        if (args.length == 1) {
            for (String arg : arguments) {
                if (arg.contains(args[0])) {
                    result.add(arg);
                }
            }
        }

        return result;
    }

}
