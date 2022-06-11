package net.larskrs.plugins.modulepluginmc;

import java.util.List;

public class Config {

    public static void addLoadedModuleToFile(Module m) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        loaded.add(m.getName());
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
    }
    public static void removeLoadedModuleToFile(Module m) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        loaded.remove(m.getName());
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
    }
    public static void removeLoadedModuleToFile(String name) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        loaded.remove(name);
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
    }
    public static void addLoadedModuleToFile(String name) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        loaded.remove(name);
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
    }
}
