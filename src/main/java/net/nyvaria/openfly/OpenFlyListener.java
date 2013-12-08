/**
 * 
 */
package net.nyvaria.openfly;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * @author Paul
 *
 */
public class OpenFlyListener implements Listener {
	private final OpenFly plugin;
	
	public OpenFlyListener(OpenFly plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerLoginEvent(PlayerLoginEvent event) {
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
	
}
