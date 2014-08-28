package com.darkblade12.particledemo.framework;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

public enum Permission {
	NONE("none") {
		@Override
		public boolean has(CommandSender sender) {
			return true;
		}
	},
	USE("particledemo.use");

	private static final Map<String, Permission> NAME_MAP = new HashMap<String, Permission>();
	private static final Map<String, Permission> NODE_MAP = new HashMap<String, Permission>();
	private final String node;
	private final List<Permission> inherited;

	static {
		for (Permission permission : values()) {
			NAME_MAP.put(permission.name(), permission);
			if (permission == NONE) {
				continue;
			}
			NODE_MAP.put(permission.node, permission);
		}
	}

	private Permission(String node, Permission... inherited) {
		this.node = node;
		this.inherited = Arrays.asList(inherited);
	}

	public String getNode() {
		return node;
	}

	public List<Permission> getInherited() {
		return inherited;
	}

	public boolean has(CommandSender sender) {
		return sender.hasPermission(node) || hasParent(sender);
	}

	public boolean hasParent(CommandSender sender) {
		for (Permission permission : values()) {
			if (!permission.getInherited().contains(this) || !permission.has(sender)) {
				continue;
			}
			return true;
		}
		return false;
	}

	public static Permission fromName(String name) {
		return NAME_MAP.get(name.toUpperCase());
	}

	public static Permission fromNode(String node) {
		return NAME_MAP.get(node.toLowerCase());
	}
}