package net.larskrs.plugins.modulepluginmc.api.bukkit;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigTool {
    public static Location getLocation(FileConfiguration yml, String address) {
        return new Location(
                Bukkit.getWorld(yml.getString(address + ".world")),
                yml.getDouble(address + ".x"),
                yml.getDouble(address + ".y"),
                yml.getDouble(address + ".z"),
                (float) yml.getDouble(address + ".yaw"),
                (float) yml.getDouble(address + ".pitch")
        );
    }
    public static void setLocation(FileConfiguration yml, String address, Location location) {
        yml.set(address + ".world", location.getWorld().getName());
        yml.set(address + ".x", location.getX());
        yml.set(address + ".y", location.getY());
        yml.set(address + ".z", location.getZ());
        yml.set(address + ".yaw", location.getYaw());
        yml.set(address + ".pitch", location.getPitch());
    }
}
