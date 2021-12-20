package com.sm10259.su;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sm10259.su.commands.Commands;

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
}
