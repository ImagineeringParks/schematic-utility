package com.sm10259.su.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.sm10259.su.*;

public class Commands extends Utils implements CommandExecutor
{
	private SchematicUtility plugin;
	
	public Commands(SchematicUtility plugin)
	{
		this.plugin = plugin;
	}
    
    @Override
	public boolean onCommand(CommandSender sender, Command command, String alias, String[] args)
	{
    	/*
    	 * Base command: schematic
    	 * Alias: [schem, su]
    	 */
    	
    	boolean ignoreAir = false;
    	
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
    				sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "/su load <filename> <world> <x> <y> <z> [-a]");
    				return false;
    			}
    			
    			// User wants to ignore air blocks
    			if(args.length == 6)
    				ignoreAir = false;
    			
    			// User wants to ignore air blocks
    			else if(args.length == 7 && args[6].equalsIgnoreCase("-a"))
    				ignoreAir = true;
    			else
    			{
    				sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED +
    						"Unexpected flag "+ ChatColor.RESET + args[6] +
    						ChatColor.RED + "Expected -a.");
    				return false;
    			}
    				
    			
    			new Loader(plugin);
				return Loader.executeCmd(sender, args[1], args[2], args[3], args[4], args[5], ignoreAir);
    		}
    		
    		// Reload the config
    		else if(args[0].equalsIgnoreCase("reload"))
    		{
    			plugin.reloadConfig();
    			sender.sendMessage(ChatColor.AQUA + "[SchematicUtility]" + ChatColor.GREEN + " has been reloaded!");
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
    	
    	// No permissions
		else
			sender.sendMessage("You do not have permission to access this command.");
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
    		sender.sendMessage(ChatColor.GREEN + "/su help" + ChatColor.RED + " <page>\n" +
    			ChatColor.GRAY + "Shows the help page.\n");
    		
    		if(checkPerms(sender, "load"))
    		{
    			sender.sendMessage(ChatColor.GREEN + "/su load " + ChatColor.RED + "<filename> <world> <x> <y> <z> [-a]\n" +
    					ChatColor.GRAY + "Load a schematic.\n");
    		}
    		
    		if(checkPerms(sender, "reload"))
    		{
    			sender.sendMessage(ChatColor.GREEN + "/su reload\n" +
    					ChatColor.GRAY + "Reloads the config.\n");
    		}
    	}
    	else
    	{
    		sender.sendMessage(ChatColor.RED + "Page "+ ChatColor.GRAY + page +" does not exist.");
    	}
    	return true;
    }
}
