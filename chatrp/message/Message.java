package me.tylergrissom.chatrp.message;

import java.util.Map;
import java.util.Map.Entry;
import me.tylergrissom.chatrp.ChatRPPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;




public enum Message
{
  ENTERED, 
  
  EXITED, 
  
  ACTION, 
  
  OOC, 
  
  SHOUT, 
  
  WHISPER, 
  
  THOUGHT, 
  
  TALK;
  
  private Message() {}
  public String get() { FileConfiguration c = ChatRPPlugin.getInstance().getConfig();
    String node = "messages." + toString().toLowerCase();
    
    return ChatColor.translateAlternateColorCodes('&', c.getString(node));
  }
  
  public String get(String from, String to) {
    return get().replace("$" + from, to);
  }
  
  public String get(Map<String, String> replaceSet) {
    String str = get();
    
    for (Map.Entry<String, String> entry : replaceSet.entrySet()) {
      str = str.replace("$" + (String)entry.getKey(), (CharSequence)entry.getValue());
    }
    
    return str;
  }
}
