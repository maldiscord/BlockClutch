package lol.maltest.practiceknockback;

import lol.maltest.practiceknockback.arena.ArenaManager;
import lol.maltest.practiceknockback.cmds.Setup;
import lol.maltest.practiceknockback.cmds.ToggleBuild;
import lol.maltest.practiceknockback.events.Game;
import lol.maltest.practiceknockback.methods.DataConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.UUID;

public final class PracticeKnockback extends JavaPlugin {

    private ArenaManager arenaManager;
    public static PracticeKnockback plugin;
    public ArrayList<UUID> canBuildList = new ArrayList<>();
    public boolean enabled = true;
    
    @Override
    public void onEnable() {
       plugin = this;
       arenaManager = new ArenaManager(this);
       arenaManager.deserialise();
       this.getCommand("setup").setExecutor(new Setup(this));
       this.getCommand("togglebuild").setExecutor(new ToggleBuild());
       this.getServer().getPluginManager().registerEvents(new Game(), this);
       this.getConfig().options().copyDefaults(true);
       this.saveConfig();
        DataConfig.setup();
        DataConfig.getDataFile().options().copyDefaults();
        DataConfig.save();
    }

    public void onDisable() {
        arenaManager.serialise();
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }
}

