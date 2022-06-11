package net.larskrs.plugins.modulepluginmc.modules.undergroundmodule;

import net.larskrs.plugins.modulepluginmc.ModulePluginMC;
import net.larskrs.plugins.modulepluginmc.api.bukkit.ConfigTool;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class undergroundModuleListener implements Listener {

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
        Location respawn = ConfigTool.getLocation(ModulePluginMC.getInstance().getConfig(), "underground.respawn");
        System.out.println(respawn.getWorld().getName());
        if (respawn != null) {
            e.setRespawnLocation(respawn);
        }
    }
    @EventHandler
    public void onFirstJoin (PlayerSpawnLocationEvent e) {
        Location spawn = ConfigTool.getLocation(ModulePluginMC.getInstance().getConfig(), "underground.respawn");
        if (spawn != null) {
            e.setSpawnLocation(spawn);
        }
    }

}
