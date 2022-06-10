package net.larskrs.plugins.modulepluginmc;

import net.larskrs.plugins.modulepluginmc.api.module.ModuleManager;
import net.larskrs.plugins.modulepluginmc.commands.ModulesCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class ModulePluginMC extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ModuleManager.SetUp();

        ModuleManager.loadModule(new ExampleModule());
        new ModulesCommand();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
