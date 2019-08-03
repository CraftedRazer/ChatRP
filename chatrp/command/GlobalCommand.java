package me.tylergrissom.chatrp.command;

import java.util.List;
import java.util.UUID;
import me.tylergrissom.chatrp.ChatRPPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GlobalCommand extends CommandBase
{
  private ChatRPPlugin plugin;
  
  public GlobalCommand(ChatRPPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public ChatRPPlugin getPlugin() { return plugin; }
  

  void execute(CommandSender sender, Command command, String[] strings)
  {
    if ((sender instanceof Player)) {
      Player p = (Player)sender;
      List<UUID> global = getPlugin().getGlobal();
      
      if (global.contains(p.getUniqueId())) {
        getPlugin().getGlobal().remove(p.getUniqueId());
        
        p.sendMessage(ChatColor.GRAY + "You have left " + ChatColor.GOLD + "Global");
      } else {
        getPlugin().getGlobal().add(p.getUniqueId());
        
        p.sendMessage(ChatColor.GRAY + "You have entered " + ChatColor.GOLD + "Global");
      }
    }
  }
}
