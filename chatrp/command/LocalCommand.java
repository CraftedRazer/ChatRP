package me.tylergrissom.chatrp.command;

import java.util.List;
import java.util.UUID;
import me.tylergrissom.chatrp.ChatRPPlugin;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LocalCommand extends CommandBase
{
  private ChatRPPlugin plugin;
  
  public LocalCommand(ChatRPPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public ChatRPPlugin getPlugin() { return plugin; }
  

  void execute(CommandSender sender, Command command, String[] strings)
  {
    if ((sender instanceof Player)) {
      Player p = (Player)sender;
      List<UUID> local = getPlugin().getLocal();
      
      if (local.contains(p.getUniqueId())) {
        getPlugin().getLocal().remove(p.getUniqueId());
        
        p.sendMessage(ChatColor.GRAY + "You have left " + ChatColor.GOLD + "Local");
      } else {
        getPlugin().getLocal().add(p.getUniqueId());
        
        p.sendMessage(ChatColor.GRAY + "You have entered " + ChatColor.GOLD + "Local");
      }
    }
  }
}
