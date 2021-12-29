package fr.codemc.portals.commands;

import fr.codemc.portals.utils.ChatUtil;
import fr.codemc.portals.utils.ConfigBuilder;
import fr.codemc.portals.utils.Translator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CurrentWorldCommands implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player){
            Player player =(Player)sender;
            if(player.hasPermission(ConfigBuilder.getInstance().getString(("permissions.currentworld"))))
                ChatUtil.sendMessage(player, Translator.translateMessage("commands.currentworld.get-world").replaceAll("%world%", player.getWorld().getName()));
            else
                ChatUtil.sendMessage(sender, Translator.translateMessage("commands.no-permission"));
        } else
            ChatUtil.sendMessage(sender, Translator.translateMessage("commands.not-player"));
        return false;
    }
}
