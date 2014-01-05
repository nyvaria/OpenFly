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
		
		if (this.hasFlyPermission()) {
			if (LocationUtil.isInMidAir(player.getLocation())) {
				this.setFlying(true);
			}
		} else {
			if (this.isFlying()) {
				this.setFlying(false);
			}
		}
			
	}
	
	public boolean isFlying() {
		return this.player.getAllowFlight();
	}
	
	public void setFlying(boolean flyEnabled) {
		this.player.setAllowFlight(flyEnabled);
	}
	
	public void toggleFlyEnabled() {
		if (this.isFlying()) {
			this.setFlying(false);
		} else {
			this.setFlying(true);
		}
	}
	
	public boolean hasFlyPermission() {
		return this.player.hasPermission(OpenFly.PERM_FLY);
	}
}
