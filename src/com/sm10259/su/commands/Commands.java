package com.sm10259.su.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sm10259.su.*;

public class Commands extends Utils implements CommandExecutor
{
	private SchematicUtility plugin;

    public Commands(SchematicUtility plugin) {
        this.plugin = plugin;
    }
    
    @Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
	{
    	/*
    	 * Base command: schematic
    	 * Alias: [schem, su]
    	 */
    	
    	// No arguments given
    	if(alias.equalsIgnoreCase("su") && args.length < 1)
    	{
    		sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "Too few arguments. View commands: /su help");
    		return false;
    	}
    	
    	if(checkPerms(sender, args[0]))
    	{
    		// Load a schematic
    		if(args[0].equalsIgnoreCase("load"))
    		{
    			if(args.length < 6)
    			{
    				sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "/su load <filename> <world> <x> <y> <z> [ignore air]");
    				return false;
    			}
    			
    			// User did not specify ignore air blocks
    			if(args.length == 6)
    			{
					new Loader(plugin);
					return Loader.executeCmd(sender, args[1], args[2], args[3], args[4], args[5], "false");
				}
    			else if(args.length == 7)
    			{
					new Loader(plugin);
					return Loader.executeCmd(sender, args[1], args[2], args[3], args[4], args[5], args[6]);
				}
    		}
    		
    		// Reload the config
    		else if(args[0].equalsIgnoreCase("reload"))
    		{
    			return true;
    		}
    		
    		// View help menu
    		else if(args[0].equalsIgnoreCase("help"))
    		{
    			if(args.length > 1)
    				return printHelpMenu(sender, args[0]);
    			else
    				return printHelpMenu(sender, "1");
    		}
    		
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
    		ChatColor.GREEN + "/su help" + ChatColor.RED + " <page>\n" +
    			ChatColor.GRAY + "Shows the help page.\n" +
    				
    		ChatColor.GREEN + "/su load " + ChatColor.RED + "<filename> <world> <x> <y> <z> [ignore air]\n" +
				ChatColor.GRAY + "Load a schematic.\n" +
    		
			ChatColor.GREEN + "/su reload\n" +
				ChatColor.GRAY + "Reloads the config.\n"
			);
    	}
    	return true;
    }
}
