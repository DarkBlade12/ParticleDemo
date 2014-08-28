package com.darkblade12.particledemo.framework.commands;

import org.bukkit.command.CommandSender;

import com.darkblade12.particledemo.ParticleDemo;
import com.darkblade12.particledemo.framework.AbstractCommand;
import com.darkblade12.particledemo.framework.CommandHandler;
import com.darkblade12.particledemo.framework.Permission;
import com.darkblade12.particledemo.particle.ParticleEffect;

public final class NamesCommand extends AbstractCommand<ParticleDemo> {
	private String list;

	@Override
	public void execute(ParticleDemo plugin, CommandHandler<ParticleDemo> handler, CommandSender sender, String label, String[] parameters) {
		if (list == null) {
			list = getListString();
		}
		plugin.displayMessage(sender, "§aAll particle effect names: " + list);
	}

	private String getListString() {
		StringBuilder builder = new StringBuilder();
		for (ParticleEffect effect : ParticleEffect.values()) {
			builder.append("\n§r §7\u25AB §2" + effect.getName());
		}
		return builder.append("\n§r§8(§eYou may have to scroll up to see the entire list§8)").toString();
	}

	@Override
	public String getName() {
		return "names";
	}

	@Override
	public Permission getPermission() {
		return Permission.USE;
	}

	@Override
	public boolean isExecutableAsConsole() {
		return true;
	}

	@Override
	public String getDescription() {
		return "Displays a list of all particle effect names";
	}
}