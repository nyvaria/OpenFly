/**
 * 
 */
package net.nyvaria.openfly.flier;

import java.util.HashMap;

import net.nyvaria.openfly.OpenFly;

import org.bukkit.entity.Player;

/**
 * @author Paul Thompson
 *
 */
public class FlierList {
	private final OpenFly plugin;
	private HashMap<Player, Flier> list;
	
	public FlierList(OpenFly plugin) {
		this.plugin = plugin;
		this.list = new HashMap<Player, Flier>();
	}
	
	public void put(Player player) {
		if (!this.list.containsKey(player)) {
			Flier flier = new Flier(this.plugin, player);
			this.list.put(player, flier);
		}
	}
	
	public void remove(Player player) {
		if (this.list.containsKey(player)) {
			this.list.remove(player);
		}
	}
	
	public Flier get(Player player) {
		Flier flier = null;
		
		if (this.list.containsKey(player)) {
			flier = this.list.get(player);
		}
		
		return flier;
	}
	
	public void clear() {
		this.list.clear();
	}
}
