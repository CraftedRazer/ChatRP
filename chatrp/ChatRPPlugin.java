package me.tylergrissom.chatrp;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import me.tylergrissom.chatrp.command.ChatRPCommand;
import me.tylergrissom.chatrp.listener.ChatListener;
import me.tylergrissom.chatrp.listener.QuitListener;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;

public class ChatRPPlugin extends org.bukkit.plugin.java.JavaPlugin
{
  private ChatRPPlugin plugin;
  private static ChatRPPlugin instance;
  private List<UUID> local;
  private List<UUID> global;
  
  public ChatRPPlugin getPlugin()
  {
    return plugin;
  }
  
  public static ChatRPPlugin getInstance() { return instance; }
  

  public List<UUID> getLocal() { return local; } public List<UUID> getGlobal() { return global; }
  

  public void onEnable()
  {
    plugin = this;
    instance = this;
    
    local = new ArrayList();
    global = new ArrayList();
    
    getConfig().options().copyDefaults(true);
    saveConfig();
    
    getCommand("global").setExecutor(new me.tylergrissom.chatrp.command.GlobalCommand(this));
    getCommand("local").setExecutor(new me.tylergrissom.chatrp.command.LocalCommand(this));
    getCommand("chatrp").setExecutor(new ChatRPCommand(this));
    
    Bukkit.getPluginManager().registerEvents(new ChatListener(this), this);
    Bukkit.getPluginManager().registerEvents(new me.tylergrissom.chatrp.listener.JoinListener(this), this);
    Bukkit.getPluginManager().registerEvents(new QuitListener(this), this);
  }
  
  public void onDisable()
  {
    org.bukkit.event.HandlerList.unregisterAll(this);
  }
  
  public ChatRPPlugin() {}
}
