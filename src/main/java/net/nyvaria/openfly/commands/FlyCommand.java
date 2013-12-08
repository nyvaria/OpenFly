/**
 * 
 */
package net.nyvaria.openfly.commands;

import net.nyvaria.openfly.OpenFly;
import net.nyvaria.openfly.flier.Flier;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * @author Paul Thompson
 *
 */
public class FlyCommand implements CommandExecutor {
	private final OpenFly plugin;
	
	public FlyCommand(OpenFly plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Make sure we have a Player
		if ( !(sender instanceof Player) ) {
			sender.sendMessage("You must be a player to use /fly");
			return false;
		}
		
		// Attempt to get the flier
		Flier flier = this.plugin.flierList.get((Player) sender);
		
		// Make sure our Player is online
		if (flier == null) {
			sender.sendMessage("You must be online to use /fly");
			return false;
		}
		
		// For now, no permission checks. Just toggle fly.
		if (flier.getAllowFlight()) {
			sender.sendMessage(ChatColor.YELLOW + "Time to drop like a rock!");
			flier.setAllowFlight(false);
		} else {
			sender.sendMessage(ChatColor.YELLOW + "Fly away, little birdie!");
			flier.setAllowFlight(true);
		}
		
		// Return this command as process
		return true;
	}

}
