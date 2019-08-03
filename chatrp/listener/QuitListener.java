package me.tylergrissom.chatrp.listener;

import java.util.List;
import me.tylergrissom.chatrp.ChatRPPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements org.bukkit.event.Listener
{
  private ChatRPPlugin plugin;
  
  public QuitListener(ChatRPPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public ChatRPPlugin getPlugin() { return plugin; }
  
  @org.bukkit.event.EventHandler
  public void onQuit(PlayerQuitEvent event)
  {
    Player p = event.getPlayer();
    
    getPlugin().getGlobal().remove(p.getUniqueId());
    getPlugin().getLocal().remove(p.getUniqueId());
  }
}
