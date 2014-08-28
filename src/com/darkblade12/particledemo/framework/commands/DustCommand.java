package com.darkblade12.particledemo.framework.commands;

import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.darkblade12.particledemo.ParticleDemo;
import com.darkblade12.particledemo.framework.AbstractCommand;
import com.darkblade12.particledemo.framework.CommandHandler;
import com.darkblade12.particledemo.framework.Permission;
import com.darkblade12.particledemo.particle.ParticleEffect;

public final class DustCommand extends AbstractCommand<ParticleDemo> {
	@SuppressWarnings("deprecation")
	@Override
	public void execute(ParticleDemo plugin, CommandHandler<ParticleDemo> handler, CommandSender sender, String label, String[] parameters) {
		String parameterId = parameters[0];
		int id;
		try {
			id = Integer.parseInt(parameterId);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterId + " §cisn't a valid parameter for §e<id>§c!");
			return;
		}
		if (id < 0) {
			plugin.displayMessage(sender, "§cThe value of parameter §e<id> §ccan't be negative!");
			return;
		}
		Material material = Material.getMaterial(id);
		if (material == null || !material.isBlock()) {
			plugin.displayMessage(sender, "§cThe value of parameter §e<id> §cisn't a block id!");
			return;
		}
		String parameterData = parameters[1];
		byte data;
		try {
			data = Byte.parseByte(parameterData);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterData + " §cisn't a valid parameter for §e<data>§c!");
			return;
		}
		if (data < 0) {
			plugin.displayMessage(sender, "§cThe value of parameter §e<data> §ccan't be negative!");
			return;
		}
		String parameterOffsetX = parameters[2];
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
		String parameterOffsetY = parameters[3];
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
		String parameterOffsetZ = parameters[4];
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
		String parameterSpeed = parameters[5];
		float speed;
		try {
			speed = Float.parseFloat(parameterSpeed);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterSpeed + " §cisn't a valid parameter for §e<speed>§c!");
			return;
		}
		if (speed < 0) {
			plugin.displayMessage(sender, "§cThe value of parameter §e<speed> §ccan't be negative!");
			return;
		}
		String parameterAmount = parameters[6];
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
		String parameterRange = parameters[7];
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
		ParticleEffect.displayBlockDust(id, data, offsetX, offsetY, offsetZ, speed, amount, ((Player) sender).getLocation().add(0, 1, 0), range);
		plugin.displayMessage(sender, "§aA block dust particle effect with the specified parameters was displayed.");
	}

	@Override
	public String getName() {
		return "dust";
	}

	@Override
	public String[] getParameters() {
		return new String[] { "<id>", "<data>", "<offsetX>", "<offsetY>", "<offsetZ>", "<speed>", "<amount>", "<range>" };
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
		return "Displays a block dust particle effect with the specified parameters (uses player as center)";
	}
}