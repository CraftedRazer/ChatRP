package me.tylergrissom.chatrp.listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import me.tylergrissom.chatrp.ChatRPPlugin;
import me.tylergrissom.chatrp.message.Message;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements org.bukkit.event.Listener
{
  private ChatRPPlugin plugin;
  
  public ChatListener(ChatRPPlugin plugin)
  {
    this.plugin = plugin;
  }
  
  public ChatRPPlugin getPlugin() { return plugin; }
  
  private void router(AsyncPlayerChatEvent event, Map<Character, Message> map)
  {
    Player p = event.getPlayer();
    String msg = event.getMessage();
    boolean isInGlobal = getPlugin().getGlobal().contains(p.getUniqueId());
    boolean isInLocal = getPlugin().getLocal().contains(p.getUniqueId());
    


    boolean crossWorld = getPlugin().getConfig().getBoolean("cross_world", true);
    double localRange = getPlugin().getConfig().getDouble("local_range", 60.0D);
    Iterator localIterator;
    if ((isInLocal) && (!isInGlobal)) {
      Player op;
      for (localIterator = org.bukkit.Bukkit.getOnlinePlayers().iterator(); localIterator.hasNext(); 
          event.getRecipients().remove(op))
      {
        op = (Player)localIterator.next();
        if (((op.getWorld() == p.getWorld()) || (crossWorld)) && (op.getLocation().distance(p.getLocation()) <= localRange)) {}
      }
    }
    
    if (((!isInGlobal) && (!isInLocal)) || (isInGlobal)) { return;
    }
    for (Map.Entry<Character, Message> entry : map.entrySet()) {
      if (msg.startsWith(String.valueOf(entry.getKey()))) {
        Map<String, String> replace = new HashMap();
        
        replace.put("player", p.getDisplayName());
        replace.put("message", msg.substring(1));
        
        String format = ((Message)entry.getValue()).get(replace);
        
        event.setFormat(format);
        
        break;
      }
    }
  }
  
  @org.bukkit.event.EventHandler
  public void onChat(AsyncPlayerChatEvent event) {
    Map<Character, Message> map = new HashMap();
    
    map.put(Character.valueOf('+'), Message.ACTION);
    map.put(Character.valueOf('-'), Message.OOC);
    map.put(Character.valueOf('*'), Message.WHISPER);
    map.put(Character.valueOf('='), Message.THOUGHT);
    map.put(Character.valueOf('"'), Message.TALK);
    map.put(Character.valueOf('!'), Message.SHOUT);
    
    router(event, map);
  }
}
