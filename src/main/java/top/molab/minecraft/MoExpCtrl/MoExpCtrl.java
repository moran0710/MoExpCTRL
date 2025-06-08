package top.molab.minecraft.MoExpCtrl;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class MoExpCtrl extends JavaPlugin {

    private static MoExpCtrl instance;

    public static MoExpCtrl getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        // Plugin startup logic
        saveDefaultConfig();
        getLogger().info("------------------------------");
        getLogger().info("| MoExpCtrl  | OpenMoPlugins |");
        getLogger().info("| Author:Moran0710           |");
        getLogger().info("| A Simple exp ctrl plugin   |");
        getLogger().info("------------------------------");
        RuntimeDataManager.getInstance().init();
        Objects.requireNonNull(getCommand("MoExpCtrl")).setExecutor(new MainCommand());
        Objects.requireNonNull(getCommand("MoExpCtrl")).setTabCompleter(new MainCommand());
        getServer().getPluginManager().registerEvents(new PlayerExpChangeEventListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
