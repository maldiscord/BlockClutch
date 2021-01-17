package lol.maltest.practiceknockback.cmds;

import lol.maltest.practiceknockback.PracticeKnockback;
import lol.maltest.practiceknockback.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleBuild implements CommandExecutor {

    public PracticeKnockback plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("mal.admin")) {
                if(!plugin.canBuildList.contains(player.getUniqueId())) {
                    player.sendMessage(ChatUtil.clr("&cYou can now build perm blocks!"));
                    plugin.canBuildList.add(player.getUniqueId());
                } else {
                    player.sendMessage(ChatUtil.clr("&cYou can no longer build perm blocks!"));
                    plugin.canBuildList.remove(player.getUniqueId());
                }
            }
        }
        return false;
    }
}
