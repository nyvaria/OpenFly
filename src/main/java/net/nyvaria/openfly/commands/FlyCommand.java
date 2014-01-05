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
	public static String CMD = "fly";
	
	private final OpenFly plugin;
	
	public FlyCommand(OpenFly plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		// Make sure we have a Player
		if ( !(sender instanceof Player) ) {
			sender.sendMessage("You must be a player to use /" + FlyCommand.CMD);
			return true;
		}
		
		// Attempt to get the flier
		Flier flier = this.plugin.flierList.get((Player) sender);
		
		// Make sure our Player is online
		if (flier == null) {
			sender.sendMessage("You must be online to use /" + FlyCommand.CMD);
			return true;
		}
		
		// Check if this player is not allowed to fly
		if (!flier.hasFlyPermission()) {
			sender.sendMessage(ChatColor.YELLOW + "Sorry, but you are not allowed to use /" + FlyCommand.CMD + " here");
			return true;
		}
		
		// Otherwise, toggle fly.
		if (flier.isFlying()) {
			sender.sendMessage(ChatColor.YELLOW + "Time to drop like a rock!");
			flier.setFlying(false);
		} else {
			sender.sendMessage(ChatColor.YELLOW + "Fly away, little birdie!");
			flier.setFlying(true);
		}
		 
		// Return this command as processed
		return true;
	}

}
