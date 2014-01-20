/**
 * Copyright (c) 2013-2014 -- Paul Thompson / Nyvaria
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
package net.nyvaria.openfly;

import net.nyvaria.openfly.commands.FlyCommand;
import net.nyvaria.openfly.flier.Flier;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerKickEvent;

/**
 * @author Paul Thompson
 *
 */
public class OpenFlyListener implements Listener {
	private final OpenFly plugin;
	
	public OpenFlyListener(OpenFly plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		this.plugin.flierList.put(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		plugin.flierList.put(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		this.plugin.flierList.put(event.getPlayer());
	}
	
	@EventHandler
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event) {
		Flier flier = this.plugin.flierList.get(event.getPlayer());
		
		if (flier.isFlying() && !flier.hasFlyPermission()) {
			event.getPlayer().sendMessage(ChatColor.YELLOW + "Sorry, but you are not allowed to fly here");
			flier.setFlying(false);
		}
	}
	
}
