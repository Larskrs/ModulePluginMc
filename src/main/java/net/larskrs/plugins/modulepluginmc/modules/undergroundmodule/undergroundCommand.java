package net.larskrs.plugins.modulepluginmc.modules.undergroundmodule;

import net.larskrs.plugins.modulepluginmc.ModulePluginMC;
import net.larskrs.plugins.modulepluginmc.api.bukkit.Command;
import net.larskrs.plugins.modulepluginmc.api.bukkit.ConfigTool;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class undergroundCommand extends Command {

    public undergroundCommand() {
        super("underground", "underground.command", "Main Command", "underground", new String[]{"london"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("setRespawn")) {
            if (sender instanceof Player) {
                if (sender.hasPermission("underground.command.setrespawn")) {
                    Location location = ((Player) sender).getLocation();
                    FileConfiguration yml = ModulePluginMC.getInstance().getConfig();
                    yml.set("underground.respawn" + ".world", location.getWorld().getName());
                    yml.set("underground.respawn" + ".x", location.getX());
                    yml.set("underground.respawn" + ".y", location.getY());
                    yml.set("underground.respawn" + ".z", location.getZ());
                    yml.set("underground.respawn" + ".yaw", location.getYaw());
                    yml.set("underground.respawn" + ".pitch", location.getPitch());
                    ModulePluginMC.getInstance().saveConfig();
                    ModulePluginMC.getInstance().reloadConfig();

                    sender.sendMessage(ChatColor.GREEN + "You updated the respawn point! ");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Sorry, this command may only be ran by players. :(");
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        List<String> options = new ArrayList<>();

            if (args.length == 1) {
                options.add("setRespawn");
            }

        return options;
    }
}
