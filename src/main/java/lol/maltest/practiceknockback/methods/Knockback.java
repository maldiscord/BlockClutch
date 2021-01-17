package lol.maltest.practiceknockback.methods;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.lang.reflect.InvocationTargetException;

public class Knockback {
    public static void knockbackPlayerRight(Player player){

        Location loc = player.getLocation();
        loc.setX(player.getLocation().getX() + 1);
        loc.setY(player.getLocation().getY() + 1);
        loc.setYaw(player.getLocation().getYaw() + 90);
        Location loc2 = loc.clone();
        loc2.setYaw(loc2.getYaw() - player.getLocation().getYaw());
        loc2.setPitch(loc2.getPitch() - player.getLocation().getPitch());
        Arrow arrow = Bukkit.getWorld("world").spawnArrow(loc, loc2.getDirection(), 10f, 1f);
        arrow.spigot().setDamage(0);

    }

    public static void knockbackPlayerLeft(Player player){
        Location loc = player.getLocation();
        loc.setX(player.getLocation().getX() - 1);
        loc.setY(player.getLocation().getY() + 1);
        loc.setYaw(player.getLocation().getYaw() + 270);
        Location loc2 = loc.clone();
        loc2.setYaw(loc2.getYaw() - player.getLocation().getYaw());
        loc2.setPitch(loc2.getPitch() - player.getLocation().getPitch());
        Arrow arrow = Bukkit.getWorld("world").spawnArrow(loc, loc2.getDirection(), 10f, 1f);
        arrow.spigot().setDamage(0);
    }
}
