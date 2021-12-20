package com.sm10259.su;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.sm10259.su.commands.Commands;

public class Loader extends Commands
{
	public Loader(SchematicUtility SchematicUtility)
	{
		super(SchematicUtility);
	}
	
	public static boolean executeCmd(CommandSender sender, String filename, String worldName, String xStr, String yStr, String zStr)
	{
		World world = Bukkit.getWorld(worldName);
		if(world == null)
		{
			sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "Error: World not found.");
			return false;
		}
		
		int x = Integer.parseInt(xStr);
		int y = Integer.parseInt(yStr);
		int z = Integer.parseInt(zStr);
		
		String directory = new Utils().getSchemDir();
		File schemFile = new File(directory + File.separator + filename + ".schem");
		
		return true;
	}
}
