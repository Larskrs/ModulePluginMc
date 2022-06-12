package net.larskrs.plugins.modulepluginmc.api.module;

import net.larskrs.plugins.modulepluginmc.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;

public class ModuleManager {

    public static ArrayList<Module> modules;
    public static ArrayList<Module> loadedModules;

    public static void SetUp() {
        modules = new ArrayList<>();
        loadedModules = new ArrayList<>();
    }

    public static void registerModule(Module module) {
        if (modules.contains(module.getClass())) {
            return;
        }
        modules.add(module);
    }

    /**
     * This loads all the modules previously loaded.
     */
    public static void LoadModules() {
        for (Module m : Config.getLoadableModules()
             ) {
            loadModule(m);
        }
    }
    public static void loadModule(Module module) {
        if (!modules.contains(module)) {
            registerModule(module);
        }
        module.load();
        loadedModules.add(module);
        Config.addLoadedModuleToFile(module);
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Loaded the " + ChatColor.GREEN + module.getName());
    }
    public static void unloadModule(Module module) {
        if (!modules.contains(module)) {
            registerModule(module);
        }
        module.unload();
        loadedModules.remove(module);
        Config.removeLoadedModuleToFile(module);
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Unoaded the " + ChatColor.GREEN + module.getName());
    }

    public static List<Module> getModules () {
        return modules;
    }
    public static List<Module> getLoadedModules () {
        return loadedModules;
    }

    public static boolean isLoaded(Module m) {
        return loadedModules.contains(m);
    }

    public static Module getModule(String name) {
        for (Module m : modules
             ) {
            if (Objects.equals(m.name, name)) {
                return m;
            }
        }
        return null;
    }
}
