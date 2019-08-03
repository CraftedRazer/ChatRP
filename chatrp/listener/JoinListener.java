package me.tylergrissom.chatrp.listener;

import me.tylergrissom.chatrp.ChatRPPlugin;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener
{
  private ChatRPPlugin plugin;
  
  public JoinListener(ChatRPPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public ChatRPPlugin getPlugin() { return plugin; }
  
  @org.bukkit.event.EventHandler
  public void onJoin(PlayerJoinEvent event)
  {
    Player p = event.getPlayer();
    
    getPlugin().getGlobal().add(p.getUniqueId());
  }
}
