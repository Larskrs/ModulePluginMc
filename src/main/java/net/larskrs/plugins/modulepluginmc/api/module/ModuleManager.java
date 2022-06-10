package net.larskrs.plugins.modulepluginmc.api.module;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.List;
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
    public static void loadModule(Module module) {
        if (!modules.contains(module)) {
            registerModule(module);
        }
        module.load();
        loadedModules.add(module);
        System.out.println("loaded a module.");
    }
    public static void unloadModule(Module module) {
        if (!modules.contains(module)) {
            registerModule(module);
        }
        module.unload();
        loadedModules.remove(module);
        System.out.println("unloaded a module.");
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
            if ( m.name == name ) {
                return m;
            }
        }
        return null;
    }
}
