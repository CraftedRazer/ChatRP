package me.tylergrissom.chatrp.command;

import me.tylergrissom.chatrp.ChatRPPlugin;
import org.bukkit.command.CommandSender;

public class ChatRPCommand extends CommandBase
{
  private ChatRPPlugin plugin;
  
  public ChatRPCommand(ChatRPPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public ChatRPPlugin getPlugin() { return plugin; }
  

  void execute(CommandSender sender, org.bukkit.command.Command command, String[] strings)
  {
    if ((sender.hasPermission("chatrp.reload")) && (strings.length > 0) && (strings[0].equalsIgnoreCase("reload"))) {
      getPlugin().reloadConfig();
      
      sender.sendMessage("Reloaded ChatRP configuration");
    }
  }
}
