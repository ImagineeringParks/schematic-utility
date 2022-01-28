package com.sm10259.su;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateChecker
{
	private final int resourceId;
	
	public UpdateChecker(int resourceId)
	{
		this.resourceId = resourceId;
    }
	
	public int getVersion() throws IOException
	{
		URL url = new URL("https://api.spigotmc.org/legacy/update.php?resource="+ resourceId);
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
		    for (String line; (line = reader.readLine()) != null;)
		    {
		        return Integer.parseInt(line);
		    }
		}
		return -1;
	}
}