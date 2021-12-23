package com.sm10259.su;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.CommandSender;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sm10259.su.commands.Commands;

public class Loader extends Commands
{
	public Loader(SchematicUtility SchematicUtility)
	{
		super(SchematicUtility);
	}
	
	@SuppressWarnings("deprecation")
	public static boolean executeCmd(CommandSender sender, String filename, String worldName, String xStr, String yStr, String zStr)
	{
		World world = Bukkit.getWorld(worldName);
		if(world == null)
		{
			sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "World not found.");
			return false;
		}
		
		int x = Integer.parseInt(xStr);
		int y = Integer.parseInt(yStr);
		int z = Integer.parseInt(zStr);
		
		String directory = new Utils().getSchemDir();
		File file = new File(directory + File.separator + filename + ".schem");
		
		// Load in the schematic
		com.sk89q.worldedit.world.World adaptedWorld = BukkitAdapter.adapt(world);

        ClipboardFormat format = ClipboardFormats.findByFile(file);

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {

            Clipboard clipboard = reader.read();

            try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(adaptedWorld,
                    -1))
            {
            	Operation operation = new ClipboardHolder(clipboard)
            			.createPaste(editSession)
                        .to(BlockVector3.at(x, y, z))
                        .ignoreAirBlocks(false)
                        .build();

                try
                {
                    Operations.complete(operation);
                    editSession.flushSession();
                }
                catch (WorldEditException e)
                {
                    sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "An internal error occured.");
                    e.printStackTrace();
                    return false;
                }
            }


        } catch (FileNotFoundException e) {
        	sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "'"+ filename +".schem' was not found.");
            e.printStackTrace();
            return false;
        } catch (IOException e) {
        	sender.sendMessage(ChatColor.GRAY + "- " + ChatColor.RED + "An internal error occured.");
            e.printStackTrace();
            return false;
        }
		
		return true;
	}
}
