package me.georgeawp.staffutils;

import me.georgeawp.staffutils.commands.StaffChatCommand;
import me.georgeawp.staffutils.commands.StaffChatToggleCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class StaffUtils extends JavaPlugin
{
    public static StaffUtils instance;
    public FileConfiguration config;

    public static StaffUtils getInstance() {
        return StaffUtils.instance;
    }

    public void reloadConfiguration() {
        this.reloadConfig();
        this.config = this.getConfig();
    }

    public void onEnable() {
        StaffUtils.instance = this;
        this.config = this.getConfig();
        this.config.options().copyDefaults(true);
        this.saveConfig();
        this.getCommand("staffchat").setExecutor(new StaffChatCommand());
        this.getCommand("staffchattoggle").setExecutor(new StaffChatToggleCommand());

    }

    public void onDisable() {
    }

    static {
        StaffUtils.instance = null;
    }
}
