package com.sm10259.su.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sm10259.su.*;

public class Commands extends Utils implements CommandExecutor
{
	SchematicUtility plugin;

    public Commands(SchematicUtility SchematicUtility) {
        plugin = SchematicUtility;
    }
    
    @Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
	{
    	/*
    	 * Base command: schematic
    	 * Alias: [schem, su]
    	 */
    	
    	// No arguments given
    	if(alias.equalsIgnoreCase("schematic") && args.length < 1)
    	{
    		sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "Too few arguments. View commands: /schematic help");
    		return false;
    	}
    	
    	if(checkPerms(sender, args[0]))
    	{
    		// Load a schematic
    		if(args[0].equalsIgnoreCase("load"))
    		{
    			if(args.length < 6)
    			{
    				sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "/schematic load <filename> <world> <x> <y> <z>");
    				return false;
    			}
    			
    			return Loader.executeCmd(sender, args[1], args[2], args[3], args[4], args[5]);
    		}
    		
    		// Reload the config
    		else if(args[0].equalsIgnoreCase("reload"))
    		{
    			return true;
    		}
    		
    		// View help menu
    		else if(args[0].equalsIgnoreCase("help"))
    			return printHelpMenu(sender, args[1]);
    		
    		// Unexpected argument
    		else
    			sender.sendMessage("Command not found");
    			
    	}
    	return false;
	}
    
    /*
     * Displays the help menu
     */
    public boolean printHelpMenu(CommandSender sender, String pageStr)
    {
    	int page = 1;
    	
    	// If user specifies a page number,
    	// otherwise page = 1;
    	if(isNullEmpty(pageStr))
    		page = Integer.parseInt(pageStr);
    	
    	// Send header message
    	sender.sendMessage(ChatColor.GRAY + "-------- [ " + ChatColor.GOLD + "SchematicUtility" + ChatColor.GRAY + " ] --------\n"+
    			ChatColor.AQUA + "Help Page " + ChatColor.GOLD + page + "/1\n");
    	
    	// Help Page 1
    	if(page == 1)
    	{
    		sender.sendMessage(
    		ChatColor.GREEN + "/schematic help" + ChatColor.RED + " <page>\n" +
    			ChatColor.GRAY + "Shows the help page.\n" +
    				
    		ChatColor.GREEN + "/schematic load " + ChatColor.RED + "<filename> <world> <x> <y> <z>\n" +
				ChatColor.GRAY + "Load a schematic.\n" +
    		
			ChatColor.GREEN + "/schematic reload\n" +
				ChatColor.GRAY + "Reloads the config.\n"
			);
    	}
    	return true;
    }
}
