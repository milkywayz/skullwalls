package net.porillo.skullwalls.commands;

import net.porillo.skullwalls.SkullWalls;
import net.porillo.skullwalls.walls.SkullWall;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.Set;

public class ReloadCommand extends BaseCommand {

    public ReloadCommand(SkullWalls plugin) {
        super(plugin);
        super.setName("reload");
        super.addUsage(null, null, "Reloads plugin");
        super.setPermission("skullwalls.reload");
        super.setConsoleOnly(false);
    }

    public void runCommand(CommandSender sender, List<String> args) {
        if (args.size() == 0) {
            sender.sendMessage(ChatColor.BLUE + "Reloading configuration and walls!");
            this.plugin.getConfiguration().reload();
            this.plugin.reload();

            for (SkullWall w : SkullWalls.getWallHandler().getReadOnlyWalls())
                w.updateWall(SkullWalls.getOnlinePlayers());
        }
    }
}
