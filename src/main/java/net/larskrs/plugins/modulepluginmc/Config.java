package net.larskrs.plugins.modulepluginmc;

import net.larskrs.plugins.modulepluginmc.api.module.Module;
import net.larskrs.plugins.modulepluginmc.api.module.ModuleManager;

import java.util.ArrayList;
import java.util.List;

public class Config {

    public static void addLoadedModuleToFile(Module m) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        if (loaded.contains(m.getName()))
            return;

        loaded.add(m.getName());
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
        refreshConfig();
    }
    public static void removeLoadedModuleToFile(Module m) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        if (!loaded.contains(m.getName()))
            return;

        loaded.remove(m.getName());
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
        refreshConfig();
    }
    public static void removeLoadedModuleToFile(String name) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        if (!loaded.contains(name))
            return;

        loaded.remove(name);
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
        refreshConfig();
    }
    public static void addLoadedModuleToFile(String name) {
        List<String> loaded = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        if (loaded.contains(name))
            return;

        loaded.remove(name);
        ModulePluginMC.getInstance().getConfig().set("loadedmodules", loaded);
        refreshConfig();
    }
    public static List<Module> getLoadableModules() {
        List<String> moduleNameList = ModulePluginMC.getInstance().getConfig().getStringList("loadedmodules");
        List<Module> modules = new ArrayList<>();
        for (String s: moduleNameList
             ) {
            Module m = ModuleManager.getModule(s);
            if (m != null) {
                modules.add(m);
            }
        }
        return modules;
    }
    public static void refreshConfig() {
        ModulePluginMC.getInstance().saveConfig();
        ModulePluginMC.getInstance().reloadConfig();
    }
}
