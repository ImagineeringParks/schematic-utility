package com.sm10259.su;

import java.util.logging.Logger;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.sm10259.su.commands.Commands;

public class SchematicUtility extends JavaPlugin implements Listener
{
	public static SchematicUtility plugin;
	public final Logger logger = Logger.getLogger("SchematicUtility");
	Commands commands;
	
	public void onEnable()
	{
		plugin = this;
		plugin.getServer().getPluginManager().registerEvents((Listener) this, this);
		
		commands = new Commands(this);
		getCommand("su").setExecutor(commands);
	}
	
	public void onDisable()
	{
		
	}
}
