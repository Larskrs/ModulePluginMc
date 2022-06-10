package net.larskrs.plugins.modulepluginmc.commands;

import net.larskrs.plugins.modulepluginmc.api.bukkit.Command;
import net.larskrs.plugins.modulepluginmc.api.module.Module;
import net.larskrs.plugins.modulepluginmc.api.module.ModuleManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;

public class ModulesCommand extends Command {
    public ModulesCommand() {
        super(
                "modules",
                "moduleplugin.commands.modules",
                "This is the main command for the plugin.",
                "modules",
                new String[]{"modules", "modulemanagement"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (args.length >= 1 && args[0].equalsIgnoreCase("list")) {
            listModules(sender);
        } if (args.length >= 1 && args[0].equalsIgnoreCase("disable")) {
            disableModule(args[1]);
        } if (args.length >= 1 && args[0].equalsIgnoreCase("enable")) {
            enableModule(args[1]);
        }
    }

    private void enableModule(String arg) {
        Module module = ModuleManager.getModule(arg);
        if (module != null) {
            ModuleManager.loadModule(module);
        }
    }

    private void disableModule(String arg) {
        Module module = ModuleManager.getModule(arg);
        if (module != null) {
            ModuleManager.unloadModule(module);
        }
    }

    private void listModules(CommandSender sender) {
        if (ModuleManager.getModules().isEmpty()) {
            sender.sendMessage("Could not list...");
        }
        for (Module m : ModuleManager.getModules()
             ) {
            sender.sendMessage(ChatColor.WHITE + " > " + (ModuleManager.isLoaded(m) ? ChatColor.GREEN + " [Enabled]" : ChatColor.RED + " [Disabled]") + " " + ChatColor.WHITE + m.name + ChatColor.YELLOW + " - Version ( "+ m.GetVersion() +" )");
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
