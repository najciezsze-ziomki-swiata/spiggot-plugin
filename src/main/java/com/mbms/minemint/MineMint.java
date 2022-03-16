package com.mbms.minemint;

import com.mbms.minemint.commands.GreetingCommand;
import com.mbms.minemint.commands.IgniteCommand;
import com.mbms.minemint.commands.MintingCommand;
import com.mbms.minemint.commands.NasaCommand;
import com.mbms.minemint.commands.home.HomeCommands;
import com.mbms.minemint.commands.home.HomeTabCompletion;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class MineMint extends JavaPlugin {
    private static MineMint plugin;

    @Override
    public void onEnable() {
        plugin = this;

        getLogger().info("onEnable has been invoked!");

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        registerCommand("ignite", new IgniteCommand(), null);
        registerCommand("home", new HomeCommands(), new HomeTabCompletion());
        registerCommand("mint", new MintingCommand(), null);
        registerCommand("nasa", new NasaCommand(), null);
        registerCommand("greet", new GreetingCommand(), null);
    }

    @Override
    public void onDisable() {
        getLogger().info("onDisable has been invoked!");
    }

    private void registerCommand(String name, CommandExecutor commandExecutor, TabCompleter tabCompletion) {
        PluginCommand command = getCommand(name);
        assert command != null;
        command.setExecutor(commandExecutor);
        command.setTabCompleter(tabCompletion);
    }

    private void registerEvent(Listener listener) {
        getServer().getPluginManager().registerEvents(listener, this);
    }

    public static MineMint getPlugin() {
        return plugin;
    }
}

