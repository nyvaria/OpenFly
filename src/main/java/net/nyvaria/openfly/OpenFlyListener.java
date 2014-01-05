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
