package net.larskrs.plugins.modulepluginmc.modules.examplemodule;

import net.larskrs.plugins.modulepluginmc.ModulePluginMC;
import net.larskrs.plugins.modulepluginmc.api.bukkit.Command;
import net.larskrs.plugins.modulepluginmc.api.module.Module;
import net.larskrs.plugins.modulepluginmc.modules.undergroundmodule.undergroundCommand;
import net.larskrs.plugins.modulepluginmc.modules.undergroundmodule.undergroundModuleListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ExampleModule extends Module {

    public ExampleModule() {
        super("Example", "This is the example module", "1.0.1", "Larskrs");
    }

    @Override
    public void onModuleLoaded() {
        new ExampleCommand();
        new undergroundCommand();
        Bukkit.getConsoleSender().sendMessage("The max allowed height is: " +  ModulePluginMC.getInstance().getConfig().getDouble("underground.maxHeight"));
        Bukkit.getPluginManager().registerEvents(new undergroundModuleListener(), ModulePluginMC.getInstance());
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

    private class ExampleCommand extends Command {

        public ExampleCommand() {
            super("example",
                    "moduleplugin.commands.example",
                    "Example command.",
                    "example",
                    new String[]{"exampl"});
        }

        @Override
        public void execute(CommandSender sender, String[] args) {
            sender.sendMessage("You ran the example command.");
        }

        @Override
        public List<String> onTabComplete(CommandSender sender, String[] args) {
            return null;
        }
    }
}
