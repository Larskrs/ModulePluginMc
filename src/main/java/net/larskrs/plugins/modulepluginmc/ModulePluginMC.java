package net.larskrs.plugins.modulepluginmc;

import net.larskrs.plugins.modulepluginmc.api.module.ModuleManager;
import net.larskrs.plugins.modulepluginmc.commands.ModulesCommand;
import net.larskrs.plugins.modulepluginmc.modules.examplemodule.ExampleModule;
import net.larskrs.plugins.modulepluginmc.modules.undergroundmodule.UndergroundModule;
import org.bukkit.plugin.java.JavaPlugin;

public final class ModulePluginMC extends JavaPlugin {

    private static ModulePluginMC instance;

    @Override
    public void onEnable() {
        // Plugin startup logic

        instance = this;

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        ModuleManager.SetUp();

        ModuleManager.registerModule(new ExampleModule());
        ModuleManager.registerModule(new UndergroundModule());

        ModuleManager.LoadModules();
        new ModulesCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ModulePluginMC getInstance() {
        return instance;
    }
}
