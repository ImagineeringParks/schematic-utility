package com.sm10259.su;

import java.io.File;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Utils extends JavaPlugin
{
	public boolean checkPerms(CommandSender sender, String label)
	{
		return false;
	}
	
	/*
	 * Validates that the WorldEdit schematics folder
	 * exists and returns the location
	 */
	private String getSchemDir()
	{
		Plugin worldEdit = this.getServer().getPluginManager().getPlugin("WorldEdit");
        String directory = worldEdit.getDataFolder() + File.separator + "schematics";
        
        File schemDir = new File(directory);
        
        // If schematic folder does not exist, create it
        if (!schemDir.exists())
        	schemDir.mkdirs();
        return directory;
    }
}
