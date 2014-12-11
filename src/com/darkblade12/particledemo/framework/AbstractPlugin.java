package com.darkblade12.particledemo.framework;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class AbstractPlugin extends JavaPlugin {
	private Logger logger;

	public AbstractPlugin() {
		logger = getLogger();
	}

	public abstract void onEnable();

	public abstract void onDisable();

	public abstract boolean onReload();

	public final void logInfo(String message) {
		logger.info(message);
	}

	public final void logWarning(String message) {
		logger.warning(message);
	}

	public final void displayMessage(CommandSender sender, String message) {
		sender.sendMessage(getPrefix() + " " + message);
	}

	public final void disable() {
		getServer().getPluginManager().disablePlugin(this);
	}

	@Override
	public FileConfiguration getConfig() {
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		return super.getConfig();
	}

	public abstract String getPrefix();
}