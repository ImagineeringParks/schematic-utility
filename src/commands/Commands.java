package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.rctfan1999.su.SchematicUtility;

public class Commands implements CommandExecutor
{
	SchematicUtility plugin;

    public Commands(SchematicUtility SchematicUtility) {
        plugin = SchematicUtility;
    }
    
    @Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
	{
    	label = label.toLowerCase();
    	
    	/*
    	 * Base command: schematic
    	 * Alias: [schem, su]
    	 */
    	if(args.length > 0 && checkPerms(sender, args[0]))
    	{
    		
    	}
    	return false;
	}
}
