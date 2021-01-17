package lol.maltest.practiceknockback.events;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import fr.minuskube.netherboard.Netherboard;
import fr.minuskube.netherboard.bukkit.BPlayerBoard;
import lol.maltest.practiceknockback.PracticeKnockback;
import lol.maltest.practiceknockback.util.ChatUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static com.comphenix.protocol.PacketType.Play.Server.BLOCK_BREAK_ANIMATION;
import static com.comphenix.protocol.ProtocolLibrary.getProtocolManager;

public class Game implements Listener {

    private PracticeKnockback plugin = PracticeKnockback.plugin;

    @EventHandler
    public void finishCourse(PlayerMoveEvent e) {
        Location loc = e.getPlayer().getLocation().clone().subtract(0, 1, 0);
        Block b = loc.getBlock();
        if(b.getType() == Material.GOLD_BLOCK){
            e.getPlayer().sendMessage(ChatUtil.clr("&cYou completed the course gg! Now do it again! :)"));
        }
        if(b.getType() == Material.BARRIER) {
            e.getPlayer().sendMessage(ChatUtil.clr("&cOut of bounds!"));
        }
    }

    List<String> titles = plugin.getConfig().getStringList("scoreboard.titles");

    @EventHandler
    public void onJoinSB(PlayerJoinEvent e) {

        Player player = e.getPlayer();
        BPlayerBoard board = Netherboard.instance().createBoard(player, ChatUtil.clr("&e&lKnockback Clutch"));
        board.set(ChatUtil.clr("&8&m---------------"), 4);
        board.set(ChatUtil.clr("&f&lTime Eplapsed:"), 3);
        board.set(ChatUtil.clr("0:12.124"), 2);
        board.set(ChatUtil.clr("&8&m----------------"), 1);
        board.set(ChatUtil.clr("BWP Stereotype"), 0);

    }
    @EventHandler
    public void blockPlace(BlockPlaceEvent e) {
        Block block = e.getBlock();
        if(!plugin.canBuildList.contains(e.getPlayer().getUniqueId())) {
            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                @Override
                public void run() {
                    block.setType(Material.WOOL);
                    block.setData(DyeColor.LIME.getWoolData());
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                        @Override
                        public void run() {
                            block.setType(Material.WOOL);
                            block.setData(DyeColor.YELLOW.getWoolData());
                            Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    block.setType(Material.WOOL);
                                    block.setData(DyeColor.RED.getWoolData());
                                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
                                        @Override
                                        public void run() {
                                            block.setType(Material.AIR);
                                        }
                                    }, 20L);
                                }
                            }, 20L);
                        }
                    }, 20L);
                }
            }, 20L);
        }
    }


}
