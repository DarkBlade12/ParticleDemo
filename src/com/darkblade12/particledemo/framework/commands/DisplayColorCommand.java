package com.darkblade12.particledemo.framework.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.darkblade12.particledemo.ParticleDemo;
import com.darkblade12.particledemo.framework.AbstractCommand;
import com.darkblade12.particledemo.framework.CommandHandler;
import com.darkblade12.particledemo.framework.Permission;
import com.darkblade12.particledemo.particle.ParticleEffect;
import com.darkblade12.particledemo.particle.ParticleEffect.NoteColor;
import com.darkblade12.particledemo.particle.ParticleEffect.OrdinaryColor;
import com.darkblade12.particledemo.particle.ParticleEffect.ParticleColor;
import com.darkblade12.particledemo.particle.ParticleEffect.ParticleProperty;

public final class DisplayColorCommand extends AbstractCommand<ParticleDemo> {
	private static final String RGB_FORMAT = "\\d{1,3}:\\d{1,3}:\\d{1,3}";

	@Override
	public void execute(ParticleDemo plugin, CommandHandler<ParticleDemo> handler, CommandSender sender, String label, String[] parameters) {
		ParticleEffect effect = ParticleEffect.fromName(parameters[0]);
		if (effect == null) {
			plugin.displayMessage(sender, "§cA particle effect with this name doesn't exist!");
			return;
		}
		if (!effect.isSupported()) {
			plugin.displayMessage(sender, "§cThis particle effect is not supported by your server version!");
			return;
		}
		if (!effect.hasProperty(ParticleProperty.COLORABLE)) {
			plugin.displayMessage(sender, "§cThis particle effect is not colorable!");
			return;
		}
		String parameterColor = parameters[1];
		ParticleColor color;
		if (effect == ParticleEffect.NOTE) {
			int note;
			try {
				note = Integer.parseInt(parameterColor);
			} catch (Exception exception) {
				plugin.displayMessage(sender, "§6" + parameterColor + " §cisn't a valid parameter for §e<note>§c!");
				return;
			}
			if (note < 0) {
				plugin.displayMessage(sender, "§cThe value of parameter §e<note> §ccan't be lower than 0!");
				return;
			}
			if (note > 24) {
				plugin.displayMessage(sender, "§cThe value of parameter §e<note> §ccan't be higher than 24!");
				return;
			}
			color = new NoteColor(note);
		} else {
			if (!parameterColor.matches(RGB_FORMAT)) {
				plugin.displayMessage(sender, "§6" + parameterColor + " §cisn't a valid parameter for §e<red:green:blue>§c!");
				return;
			}
			String[] rgbString = parameterColor.split(":");
			int red = Integer.parseInt(rgbString[0]);
			if (red < 0) {
				plugin.displayMessage(sender, "§cThe value of parameter §ered §ccan't be lower than 0!");
				return;
			}
			if (red > 255) {
				plugin.displayMessage(sender, "§cThe value of parameter §ered §ccan't be higher than 255!");
				return;
			}
			int green = Integer.parseInt(rgbString[1]);
			if (green < 0) {
				plugin.displayMessage(sender, "§cThe value of parameter §egreen §ccan't be lower than 0!");
				return;
			}
			if (green > 255) {
				plugin.displayMessage(sender, "§cThe value of parameter §egreen §ccan't be higher than 255!");
				return;
			}
			int blue = Integer.parseInt(rgbString[2]);
			if (blue < 0) {
				plugin.displayMessage(sender, "§cThe value of parameter §eblue §ccan't be lower than 0!");
				return;
			}
			if (blue > 255) {
				plugin.displayMessage(sender, "§cThe value of parameter §eblue §ccan't be higher than 255!");
				return;
			}
			color = new OrdinaryColor(red, green, blue);
		}
		String parameterRange = parameters[2];
		double range;
		try {
			range = Double.parseDouble(parameterRange);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterRange + " §cisn't a valid parameter for §e<range>§c!");
			return;
		}
		if (range < 1) {
			plugin.displayMessage(sender, "§cThe value of parameter §e<range> §ccan't be lower than 1!");
			return;
		}
		effect.display(color, ((Player) sender).getLocation().add(0, 2, 0), range);
		plugin.displayMessage(sender, "§aA particle effect with the specified parameters was displayed.");
	}

	@Override
	public String getName() {
		return "displaycolor";
	}

	@Override
	public String[] getParameters() {
		return new String[] { "<name>", "<red:green:blue/note>", "<range>" };
	}

	@Override
	public Permission getPermission() {
		return Permission.USE;
	}

	@Override
	public boolean isExecutableAsConsole() {
		return false;
	}

	@Override
	public String getDescription() {
		return "Displays a specific colored particle (uses players head as center)";
	}
}