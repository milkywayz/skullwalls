package net.porillo.skullwalls.commands;

import net.porillo.skullwalls.SkullWalls;
import net.porillo.skullwalls.walls.Wall;
import net.porillo.skullwalls.walls.WallType;
import org.bukkit.command.CommandSender;

import java.util.List;

import static org.apache.commons.lang.WordUtils.capitalize;
import static org.bukkit.ChatColor.*;

public class CreateCommand extends BaseCommand {

    public CreateCommand(SkullWalls plugin) {
        super(plugin);
        super.setName("create");
        super.addUsage("[name]", "[type]", "Creates a new skull wall");
        super.setPermission("skullwalls.create");
        super.setConsoleOnly(true);
    }

    public void runCommand(CommandSender sender, List<String> args) {
        if (args.size() == 0) {
            sender.sendMessage(RED + "Specify the name of the wall");
        } else if (args.size() == 1) {
            sender.sendMessage(RED + "Specify the wall type: {" + WallType.getTypes() + "}");
        } else if (args.size() > 1) {
            Wall wall;
            WallType type;

            try {
                type = WallType.valueOf(args.get(1).toUpperCase());
                wall = SkullWalls.getCuboidHandler().create(sender.getName(), type, capitalize(args.get(0)));

                if(!SkullWalls.getWallHandler().exists(wall)) {
                    SkullWalls.getWallHandler().add(wall);
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
                sender.sendMessage(RED + "Error: Your cuboid is not complete!");
                return;
            } catch (IllegalArgumentException exx) {
                sender.sendMessage(RED + "Specify the wall type: {" + WallType.getTypes() + "}");
                return;
            }

            sender.sendMessage(GREEN + "Successfully created a Wall!");
            sender.sendMessage(BLUE + "Name: " + WHITE + capitalize(wall.getName()));
            sender.sendMessage(BLUE + "Type: " + WHITE + capitalize(wall.getType().toString().toLowerCase()));
            sender.sendMessage(BLUE + "Size: " + WHITE + wall.getSlots().size() + " slots");
        }
    }
}
