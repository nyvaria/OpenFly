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
