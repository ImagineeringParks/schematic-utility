# Schematic Utility
[![GitHub](https://img.shields.io/github/license/ImagineeringParks/schematic-utility?color=blue&label=License)](https://github.com/ImagineeringParks/schematic-utility/blob/main/LICENSE) [![GitHub release](https://img.shields.io/github/release/ImagineeringParks/schematic-utility.svg)](https://github.com/ImagineeringParks/schematic-utility/releases) ![GitHub All Releases](https://img.shields.io/github/downloads/ImagineeringParks/schematic-utility/total?color=bright-green&label=Downloads)

Schematic Utility is a basic Minecraft plugin that allows you to load and paste WorldEdit schematic files at a given location.

## Features
* Load schematics at a specific location.
* Load schematics in another world.
* Allows you to use command blocks or the console to paste schematics.

## What's New in 1.3-beta.1? 🚀
- Integrated WorldEdit's `-a` air flag.
- Load command is now:
  - `/su load <filename> <world> <x> <y> <z> [-a]`
- Help menu will only show commands that users have permissions for.

## Commands
* `/su help [page]`
  * Shows the help menu.
* `/su load <filename> <world> <x> <y> <z> [-a]`
  * Load and paste structure from the schematic file.
* `/su reload`
  * Reloads the config.

## Permissions
* schematics.load
  * Allows access to _/su load_ and _/su help_
* schematics.admin
  * Allows access to all commands.
 
## config.yml
```
messages:
    load: "%filename% has been loaded."
    
    # Setting this to false will disable the load message
    enabled: true
    
    # Placeholders
    # %filename% - Schematic file name
```

## Dependencies
 This plugin requires [WorldEdit](https://dev.bukkit.org/projects/worldedit) to be installed.

##
_Want to help this project and others like it in development?
[Consider buying me a coffee!](https://www.buymeacoffee.com/sarahmyerson)_
