package com.sm10259.su;

import java.io.IOException;
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
		
		// Check for updates
		double releaseVersion = 0.0;
		double currentVersion = Double.parseDouble(plugin.getDescription().getVersion());
		
		try {
			releaseVersion = new UpdateChecker(98658).getVersion();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(releaseVersion == currentVersion)
		{
			this.logger.info("You are running the latest version!");
		}
		
		else if(releaseVersion < currentVersion)
		{
			this.logger.info("You are running an unreleased version.");
		}
		
		else
		{
			plugin.getLogger().info("Version " + releaseVersion + " is now available.");
			plugin.getLogger().info("Download it here: https://r.spiget.org/98658");
		}
		
		commands = new Commands(this);
		getCommand("su").setExecutor(commands);
		
		// Load config and queue storage files
		this.logger.info("Loading config.yml");
		saveDefaultConfig();
		this.logger.info("Successfully loaded config.yml");
	}
	
	public void onDisable()
	{
		
	}
}
