package net.larskrs.plugins.modulepluginmc.modules.undergroundmodule;

import net.larskrs.plugins.modulepluginmc.ModulePluginMC;
import net.larskrs.plugins.modulepluginmc.api.bukkit.Command;
import net.larskrs.plugins.modulepluginmc.api.bukkit.ConfigTool;
import net.larskrs.plugins.modulepluginmc.api.module.Module;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import java.util.ArrayList;
import java.util.List;

public class UndergroundModule extends Module implements Listener {

    public UndergroundModule() {
        super("Underground", "Players may only live underground.", "1.0.2", "Larskrs");
    }

    @Override
    public void onModuleLoaded() {
        new undergroundCommand();
        Bukkit.getConsoleSender().sendMessage("The max allowed height is: " +  ModulePluginMC.getInstance().getConfig().getDouble("underground.maxHeight"));
        Bukkit.getPluginManager().registerEvents(this, ModulePluginMC.getInstance());
    }

    @Override
    public void onModuleUnload() {
        Bukkit.getConsoleSender().sendMessage("Unloaded the example module.");
    }

    @Override
    public void onModuleStart() {

    }

    @Override
    public void onModuleUpdate() {

    }

    @EventHandler
    public void onPlayerMove (PlayerMoveEvent e) {

        if (e.getPlayer().getGameMode().equals(GameMode.SPECTATOR)) {
            return; // stops right here.
        }


        double max =  ModulePluginMC.getInstance().getConfig().getDouble("underground.maxHeight");
        if (e.getTo().getY() >= max) {
            if (e.getFrom().getY() >= max) {
                // Player was not underground before-
            } else {
                e.getPlayer().sendTitle(ChatColor.RED + "please return to the underground.", ChatColor.GRAY + "This server is not supposed to be played that way!");
                e.setCancelled(true);
            }

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");

    }
    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {

        if (e.getPlayer().getBedSpawnLocation() != null) {
            return;
        }


        Location respawn = ConfigTool.getLocation(ModulePluginMC.getInstance().getConfig(), "underground.respawn");
        System.out.println(respawn.getWorld().getName());
        if (respawn != null) {
            e.setRespawnLocation(respawn);
        }
    }
    @EventHandler
    public void onFirstJoin (PlayerSpawnLocationEvent e) {

        if (e.getPlayer().hasPlayedBefore()) {
            return;
        }

        Location spawn = ConfigTool.getLocation(ModulePluginMC.getInstance().getConfig(), "underground.respawn");
        if (spawn != null) {
            e.setSpawnLocation(spawn);
        }
    }


}

class undergroundCommand extends Command {

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
