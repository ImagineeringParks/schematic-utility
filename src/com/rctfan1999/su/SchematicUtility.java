package com.rctfan1999.su;

import java.io.File;

import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import commands.Commands;

public class SchematicUtility extends JavaPlugin implements Listener
{
	public static SchematicUtility plugin;
	Commands commands;
	
	public void onEnable()
	{
		plugin = this;
		plugin.getServer().getPluginManager().registerEvents((Listener) this, this);
		
		commands = new Commands(this);
		getCommand("schematic").setExecutor(commands);
		getCommand("schem").setExecutor(commands);
		getCommand("su").setExecutor(commands);
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
