package net.larskrs.plugins.modulepluginmc.api.module;

import jdk.jfr.Description;
import org.bukkit.Bukkit;

import java.util.logging.Level;

public abstract class Module {

    public String name, description;
    private String version = "1.0.0";
    private String author = "Ken Scarborough";

    public Module (String _name, String _description, String _version, String _author) {
        this.name = _name;
        this.description = _description;
        this.version = _version;
        this.author = _author;
    }

    /**
     * This is called once the module is loaded.
     */
    public abstract void onModuleLoaded();
    public abstract void onModuleUnload();
    public abstract void onModuleStart();
    public abstract void onModuleUpdate();

    public void load() {
        onModuleLoaded();
        Bukkit.getLogger().log(Level.FINE, "Loaded the [" + name + "] Module.");
    }
    public void unload() {
        onModuleUnload();
    }
    public void start() {
        onModuleStart();
    }
    public void update() {
        onModuleUpdate();
    }

    public String GetVersion() {
        return version;
    }

}
