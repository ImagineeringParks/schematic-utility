package com.sm10259.su;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Utils extends JavaPlugin
{
	/*
	 * Given the argument, checks if the
	 * sender has sufficient permissions
	 */
	public boolean checkPerms(CommandSender sender, String arg0)
	{
		// Use load and list commands
		if(arg0.equalsIgnoreCase("load"))
			if(sender.isOp() || sender.hasPermission("schematics.admin") || sender.hasPermission("schematics.load"))
				return true;
		
		// Reload the config
		if(arg0.equalsIgnoreCase("reload"))
			if(sender.isOp() || sender.hasPermission("schematics.admin"))
				return true;
		
		// Use the help command
		if(arg0.equalsIgnoreCase("help"))
			if(sender.isOp() || sender.hasPermission("schematics.admin") || sender.hasPermission("schematics.load"))
				return true;
		
		return false;
	}
	
	/*
	 * Validates that the WorldEdit schematics folder
	 * exists and returns the location
	 */
	protected String getSchemDir()
	{
		Plugin worldEdit = getServer().getPluginManager().getPlugin("WorldEdit");
		String directory = worldEdit.getDataFolder() + File.separator + "schematics";
		
		File schemDir = new File(directory);
		
		// If schematic folder does not exist, create it
		if (!schemDir.exists())
			schemDir.mkdirs();
		return directory;
	}
	
	/*
	 * Check if a string is null or empty. 
	 * Returns false if it contains values.
	 */
	public static boolean isNullEmpty(String str)
	{
	    // Check if string is null
	    if (str == null)
	      return true;

	    // Check if string is empty
	    else if(str.isEmpty())
	      return true;
	    
	    // String may contain values
	    else
	      return false;
	  }
}
