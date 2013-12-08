/**
 * 
 */
package net.nyvaria.openfly.flier;

import org.bukkit.entity.Player;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import net.nyvaria.openfly.OpenFly;
import net.nyvaria.openfly.LocationUtil;

/**
 * @author Paul Thompson
 *
 */
public class Flier {
	@SuppressWarnings("unused")
	private final OpenFly plugin;
	private final Player  player;
	
	public Flier(OpenFly plugin, Player player) {
		this.plugin = plugin;
		this.player = player;
		
		if (this.isInMidAir()) {
			this.setAllowFlight(true);
		}
	}
	
	public boolean getAllowFlight() {
		return this.player.getAllowFlight();
	}
	
	public void setAllowFlight(boolean allowFlight) {
		this.player.setAllowFlight(allowFlight);
	}
	
	public void toggleAllowFlight() {
		if (this.getAllowFlight()) {
			this.setAllowFlight(false);
		} else {
			this.setAllowFlight(true);
		}
	}

	private boolean isInMidAir() {
		Location location = player.getLocation();
		World    world    = location.getWorld();
		
		int x = location.getBlockX();
		int y = (int) Math.round(location.getY());
		int z = location.getBlockZ();
		
		while (LocationUtil.isBlockAboveAir(world, x, y, z) && (y > -1)) {
			--y;
		}
		
		if ( ((location.getBlockY() - y) > 1) || (y < 0) ) {
			return true;
		}
		
		return false;
	}
	
}
