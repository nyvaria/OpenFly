/**
 * Copyright (c) 2013-2014
 * Paul Thompson <captbunzo@gmail.com> / Nyvaria <geeks@nyvaria.net>
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
