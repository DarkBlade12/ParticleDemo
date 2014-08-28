package com.darkblade12.particledemo.framework.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.darkblade12.particledemo.ParticleDemo;
import com.darkblade12.particledemo.framework.AbstractCommand;
import com.darkblade12.particledemo.framework.CommandHandler;
import com.darkblade12.particledemo.framework.Permission;
import com.darkblade12.particledemo.particle.ParticleEffect;

public final class NormalCommand extends AbstractCommand<ParticleDemo> {
	@Override
	public void execute(ParticleDemo plugin, CommandHandler<ParticleDemo> handler, CommandSender sender, String label, String[] parameters) {
		ParticleEffect effect = ParticleEffect.fromName(parameters[0]);
		if (effect == null) {
			plugin.displayMessage(sender, "§cA particle effect with this name doesn't exist!");
			return;
		}
		String parameterOffsetX = parameters[1];
		float offsetX;
		if (parameterOffsetX.equalsIgnoreCase("random")) {
			offsetX = (float) Math.random();
		} else {
			try {
				offsetX = Float.parseFloat(parameterOffsetX);
			} catch (Exception exception) {
				plugin.displayMessage(sender, "§6" + parameterOffsetX + " §cisn't a valid parameter for §e<offsetX>§c!");
				return;
			}
		}
		String parameterOffsetY = parameters[2];
		float offsetY;
		if (parameterOffsetY.equalsIgnoreCase("random")) {
			offsetY = (float) Math.random();
		} else {
			try {
				offsetY = Float.parseFloat(parameterOffsetY);
			} catch (Exception exception) {
				plugin.displayMessage(sender, "§6" + parameterOffsetY + " §cisn't a valid parameter for §e<offsetY>§c!");
				return;
			}
		}
		String parameterOffsetZ = parameters[3];
		float offsetZ;
		if (parameterOffsetZ.equalsIgnoreCase("random")) {
			offsetZ = (float) Math.random();
		} else {
			try {
				offsetZ = Float.parseFloat(parameterOffsetZ);
			} catch (Exception exception) {
				plugin.displayMessage(sender, "§6" + parameterOffsetZ + " §cisn't a valid parameter for §e<offsetZ>§c!");
				return;
			}
		}
		String parameterSpeed = parameters[4];
		float speed;
		try {
			speed = Float.parseFloat(parameterSpeed);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterSpeed + " §cisn't a valid parameter for §e<speed>§c!");
			return;
		}
		if (speed < 0) {
			plugin.displayMessage(sender, "§cThe value of parameter §e<speed> §ccan't be lower than 0!");
			return;
		}
		String parameterAmount = parameters[5];
		int amount;
		try {
			amount = Integer.parseInt(parameterAmount);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterAmount + " §cisn't a valid parameter for §e<amount>§c!");
			return;
		}
		if (amount < 1) {
			plugin.displayMessage(sender, "§cThe value of parameter §e<amount> §ccan't be lower than 1!");
			return;
		}
		String parameterRange = parameters[6];
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
		Location center = ((Player) sender).getLocation().add(0, 1, 0);
		Material material = center.getBlock().getType();
		if (effect.getRequiresWater() && material != Material.WATER && material != Material.STATIONARY_WATER) {
			plugin.displayMessage(sender, "§cThe specified particle effect requires water to display properly!");
			return;
		}
		effect.display(offsetX, offsetY, offsetZ, speed, amount, center, range);
		plugin.displayMessage(sender, "§aA particle effect with the specified parameters was displayed.");
	}

	@Override
	public String getName() {
		return "normal";
	}

	@Override
	public String[] getParameters() {
		return new String[] { "<name>", "<offsetX>", "<offsetY>", "<offsetZ>", "<speed>", "<amount>", "<range>" };
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
		return "Displays a normal particle effect with the specified parameters (uses player as center)";
	}
}