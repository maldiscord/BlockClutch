package lol.maltest.practiceknockback.cmds;

import lol.maltest.practiceknockback.PracticeKnockback;
import lol.maltest.practiceknockback.arena.Arena;
import lol.maltest.practiceknockback.arena.ArenaManager;
import lol.maltest.practiceknockback.methods.DataConfig;
import lol.maltest.practiceknockback.methods.Knockback;
import lol.maltest.practiceknockback.util.ChatUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Setup implements CommandExecutor {

    private ArenaManager arenaManager;

    public Setup(PracticeKnockback plugin) {
        this.arenaManager = plugin.getArenaManager();
    }

    private PracticeKnockback plugin = PracticeKnockback.plugin;
    private boolean enabled = plugin.getConfig().getBoolean("settings.enabled");

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission(plugin.getConfig().getString("strings.permission"))) {
                    if(args.length == 0) {
                        player.sendMessage(ChatUtil.clr("&c--------&c&lSetup&c--------"));
                        player.sendMessage(ChatUtil.clr("&7/setup addspawn <id>"));
                        player.sendMessage(ChatUtil.clr("&7/setup delspawn <id>"));
                        player.sendMessage(ChatUtil.clr("&7/setup tpspawn <id>"));
                        player.sendMessage(ChatUtil.clr("&7/setup enablegame"));
                        player.sendMessage(ChatUtil.clr("&7/setup disablegame"));
                        player.sendMessage(ChatUtil.clr("&7/setup reload"));
                        return false;
                    }

                    if(args[0].equalsIgnoreCase("addspawn")) {
                        if (args.length < 2) {
                            player.sendMessage(ChatUtil.clr("&cNot enough arguments"));
                        } else {
                            try {
                                String n = args[0];
                                Arena arena = new Arena(n, player.getLocation());
                                arenaManager.addArena(arena);
                                player.sendMessage(ChatUtil.clr("&aDone, created arena " + n));
                            } catch (NumberFormatException e) {
                                player.sendMessage(ChatUtil.clr("&cThat is not a number!"));
                            }
                        }
                    }
                    if(args[0].equalsIgnoreCase("delspawn")){
                        if(args.length < 2) {
                            player.sendMessage(ChatUtil.clr("&cNot enough arguments"));
                        } else {
                            int id = Integer.parseInt(args[1]);
                            if(DataConfig.getDataFile().contains("spawns." + id)) {
                                DataConfig.getDataFile().set("spawns." + id, null);
                                DataConfig.save();
                                DataConfig.reload();
                                player.sendMessage(ChatUtil.clr("&cDeleted the spawnpoint with the id &a" + id));
                            }
                        }
                    }
                if(args[0].equalsIgnoreCase("reload")){
                    plugin.reloadConfig();
                    player.sendMessage(ChatUtil.clr("&cReloaded the config."));
                }
                List<String> titles = plugin.getConfig().getStringList("scoreboard.titles");
                if(args[0].equalsIgnoreCase("right")){
                    Knockback.knockbackPlayerRight(player);
                }
                if(args[0].equalsIgnoreCase("left")){
                    Knockback.knockbackPlayerLeft(player);
                }
                    if(args[0].equalsIgnoreCase("tpspawn")){
                        if(args.length < 2) {
                            player.sendMessage(ChatUtil.clr("&cNot enough arguments"));
                        }else {
                            int id = Integer.parseInt(args[1]);
                            if(DataConfig.getDataFile().contains("spawns." + id)) {


                                player.sendMessage(ChatUtil.clr("&cTeleported to the spawn point of the id &a"+id));
                            } else {
                                player.sendMessage(ChatUtil.clr("&cThat spawnpoint does not exist"));
                            }
                        }
                    }
                    if(args[0].equalsIgnoreCase("enablegame")){
                        if(enabled) {
                            player.sendMessage(ChatUtil.clr("&cThe game is already enabled"));
                        } else {
                            enableGame();
                            player.sendMessage(ChatUtil.clr("&aEnabled the game!"));
                        }
                    }
                    if(args[0].equalsIgnoreCase("disablegame")){
                        if(!enabled) {
                            player.sendMessage(ChatUtil.clr("&cThe game is already disabled"));
                        } else {
                            disableGame();
                            player.sendMessage(ChatUtil.clr("&aDisabled the game!"));
                        }
                    }

            } else {
                player.sendMessage(ChatUtil.clr(plugin.getConfig().getString("strings.noperm").replaceAll("%p%", player.getName())));
            }
        }
        return false;
    }

    public void disableGame() {
        plugin.getConfig().set("settings.enabled", false);
        plugin.saveConfig();
        plugin.reloadConfig();
    }

    public void enableGame() {
        plugin.getConfig().set("settings.enabled", true);
        plugin.saveConfig();
        plugin.reloadConfig();
    }


    public void addSpawn(Player player, int name) {
        DataConfig.getDataFile().set("spawns." + name + ".world", player.getLocation().getWorld().getName());
        DataConfig.getDataFile().set("spawns." + name + ".x", Double.valueOf(player.getLocation().getX()));
        DataConfig.getDataFile().set("spawns." + name + ".y", Double.valueOf(player.getLocation().getY()));
        DataConfig.getDataFile().set("spawns." + name + ".z", Double.valueOf(player.getLocation().getZ()));
        DataConfig.getDataFile().set("spawns." + name + ".pitch", Float.valueOf(player.getLocation().getPitch()));
        DataConfig.getDataFile().set("spawns." + name + ".yaw", Float.valueOf(player.getLocation().getYaw()));
    }

}
