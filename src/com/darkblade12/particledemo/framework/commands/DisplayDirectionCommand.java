package com.darkblade12.particledemo.framework.commands;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.darkblade12.particledemo.ParticleDemo;
import com.darkblade12.particledemo.framework.AbstractCommand;
import com.darkblade12.particledemo.framework.CommandHandler;
import com.darkblade12.particledemo.framework.Permission;
import com.darkblade12.particledemo.particle.ParticleEffect;
import com.darkblade12.particledemo.particle.ParticleEffect.BlockData;
import com.darkblade12.particledemo.particle.ParticleEffect.ItemData;
import com.darkblade12.particledemo.particle.ParticleEffect.ParticleData;
import com.darkblade12.particledemo.particle.ParticleEffect.ParticleProperty;

public final class DisplayDirectionCommand extends AbstractCommand<ParticleDemo> {
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
		if (!effect.hasProperty(ParticleProperty.DIRECTIONAL)) {
			plugin.displayMessage(sender, "§cThis particle effect is not directional!");
			return;
		}
		String parameterVectorX = parameters[1];
		float vectorX;
		try {
			vectorX = Float.parseFloat(parameterVectorX);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterVectorX + " §cisn't a valid parameter for §e<vectorX>§c!");
			return;
		}
		String parameterVectorY = parameters[2];
		float vectorY;
		try {
			vectorY = Float.parseFloat(parameterVectorY);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterVectorY + " §cisn't a valid parameter for §e<vectorY>§c!");
			return;
		}
		String parameterVectorZ = parameters[3];
		float vectorZ;
		try {
			vectorZ = Float.parseFloat(parameterVectorZ);
		} catch (Exception exception) {
			plugin.displayMessage(sender, "§6" + parameterVectorZ + " §cisn't a valid parameter for §e<vectorZ>§c!");
			return;
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
		String parameterRange = parameters[5];
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
		ParticleData particleData = null;
		boolean requiresData = effect.hasProperty(ParticleProperty.REQUIRES_DATA);
		if (parameters.length == 7) {
			if (!requiresData) {
				plugin.displayMessage(sender, "§cThis particle effect doesn't require additional data!");
				return;
			}
			String parameterData = parameters[6];
			int id;
			byte data;
			try {
				String[] splitData = parameterData.split(":");
				id = Integer.parseInt(splitData[0]);
				data = Byte.parseByte(splitData[1]);
			} catch (Exception exception) {
				plugin.displayMessage(sender, "§6" + parameterData + " §cisn't a valid parameter for §e[id:data]§c!");
				return;
			}
			@SuppressWarnings("deprecation")
			Material material = Material.getMaterial(id);
			if (material == null) {
				plugin.displayMessage(sender, "§cThe material id is invalid!");
				return;
			}
			boolean itemData = effect == ParticleEffect.ITEM_CRACK;
			if (!itemData && !material.isBlock()) {
				plugin.displayMessage(sender, "§cThe material is not a block!");
				return;
			}
			particleData = itemData ? new ItemData(material, data) : new BlockData(material, data);
		}
		if (requiresData && particleData == null) {
			plugin.displayMessage(sender, "§cThis particle effect requires additional data!");
			return;
		}
		Location center = ((Player) sender).getLocation().add(0, 1, 0);
		Material material = center.getBlock().getType();
		if (effect.hasProperty(ParticleProperty.REQUIRES_WATER) && material != Material.WATER && material != Material.STATIONARY_WATER) {
			plugin.displayMessage(sender, "§cThe specified particle effect requires water to display properly!");
			return;
		}
		if (requiresData) {
			effect.display(particleData, new Vector(vectorX, vectorY, vectorZ), speed, center, range);
		} else {
			effect.display(new Vector(vectorX, vectorY, vectorZ), speed, center, range);
		}
		plugin.displayMessage(sender, "§aA particle effect with the specified parameters was displayed.");
	}

	@Override
	public String getName() {
		return "displaydirection";
	}

	@Override
	public String[] getParameters() {
		return new String[] { "<name>", "<vectorX>", "<vectorY>", "<vectorZ>", "<speed>", "<range>", "[id:data]" };
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
		return "Displays a particle which flies into a certain direction (uses player as center)";
	}
}