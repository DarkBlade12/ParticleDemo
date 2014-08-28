package com.darkblade12.particledemo;

import com.darkblade12.particledemo.framework.AbstractPlugin;
import com.darkblade12.particledemo.framework.CommandHandler;
import com.darkblade12.particledemo.framework.commands.BlockCommand;
import com.darkblade12.particledemo.framework.commands.DustCommand;
import com.darkblade12.particledemo.framework.commands.IconCommand;
import com.darkblade12.particledemo.framework.commands.NamesCommand;
import com.darkblade12.particledemo.framework.commands.NormalCommand;

public final class ParticleDemo extends AbstractPlugin {
	private final CommandHandler<ParticleDemo> commandHandler;

	public ParticleDemo() {
		commandHandler = new CommandHandler<ParticleDemo>(this, "particle", 4, new NamesCommand(), new NormalCommand(), new IconCommand(), new BlockCommand(), new DustCommand());
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