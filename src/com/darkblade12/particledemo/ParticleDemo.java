package com.darkblade12.particledemo;

import com.darkblade12.particledemo.framework.AbstractPlugin;
import com.darkblade12.particledemo.framework.CommandHandler;
import com.darkblade12.particledemo.framework.commands.DisplayCommand;
import com.darkblade12.particledemo.framework.commands.NamesCommand;

public final class ParticleDemo extends AbstractPlugin {
	private final CommandHandler<ParticleDemo> commandHandler;

	public ParticleDemo() {
		super();
		commandHandler = new CommandHandler<ParticleDemo>(this, "particle", 4, new NamesCommand(), new DisplayCommand());
	}

	@Override
	public void onEnable() {
		long time = System.currentTimeMillis();
		try {
			commandHandler.register();
		} catch (Exception exception) {
			logWarning("An error occurred while registering the command handler, plugin will disable! Cause: " + exception.getMessage());
			disable();
			return;
		}
		logInfo("Plugin version " + getDescription().getVersion() + " activated! (" + (System.currentTimeMillis() - time) + " ms)");
	}

	@Override
	public void onDisable() {
		logInfo("Plugin deactivated!");
	}

	@Override
	public boolean onReload() {
		return true;
	}

	@Override
	public String getPrefix() {
		return "§8[§cParticle§7Demo§8]§r";
	}
}