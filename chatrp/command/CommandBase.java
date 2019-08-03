package me.tylergrissom.chatrp.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public abstract class CommandBase
  implements CommandExecutor
{
  public CommandBase() {}
  
  abstract void execute(CommandSender paramCommandSender, Command paramCommand, String[] paramArrayOfString);
  
  public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings)
  {
    execute(commandSender, command, strings);
    
    return true;
  }
}
