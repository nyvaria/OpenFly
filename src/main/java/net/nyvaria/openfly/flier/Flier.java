/**
 * 
 */
package net.nyvaria.openfly.flier;

import org.bukkit.entity.Player;

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
		
		if (LocationUtil.isInMidAir(player.getLocation())) {
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
}
