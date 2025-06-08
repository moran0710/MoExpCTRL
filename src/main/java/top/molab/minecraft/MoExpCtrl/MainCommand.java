package top.molab.minecraft.MoExpCtrl;

import cc.carm.lib.easyplugin.utils.ColorParser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if (args.length >= 1 && args[0].equals("reload")){
            RuntimeDataManager.getInstance().init();
            sender.sendMessage(ColorParser.parse("&a插件已重载"));
        }else{
            sender.sendMessage(ColorParser.parse("&c重载插件方法: /MoExpCtrl reload"));
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args){
        return List.of("reload");
    }
}
