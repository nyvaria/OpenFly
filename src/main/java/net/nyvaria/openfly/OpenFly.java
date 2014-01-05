/**
 * 
 */
package net.nyvaria.openfly;

import java.util.logging.Level;

import net.nyvaria.openfly.commands.FlyCommand;
import net.nyvaria.openfly.flier.FlierList;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author Paul Thompson
 *
 */
public class OpenFly extends JavaPlugin {
	public static String PERM_FLY = "openfly.fly";

	public   FlierList       flierList  = null;
	private  OpenFlyListener listener   = null;
	private  FlyCommand      flyCommand = null;
	
	@Override
	public void onEnable() {
		this.getLogger().setLevel(Level.INFO);
		
		// Create an empty flier list
		this.flierList = new FlierList(this);
		
		// Create and register the listener
		this.listener = new OpenFlyListener(this);
		this.getServer().getPluginManager().registerEvents(listener, this);
		
		// Add all currently logged in players to the flier list
		for (Player player : this.getServer().getOnlinePlayers()) {
			this.flierList.put(player);
		}

		// Create and set the command
		this.flyCommand = new FlyCommand(this);
		this.getCommand(FlyCommand.CMD).setExecutor(this.flyCommand);
		
		this.log("Enabling " + this.getNameVersion() + " successful");
	}
	
	@Override
	public void onDisable() {
		// Empty and destroy the flier list
		this.flierList.clear();
		this.flierList = null;
		
		this.log("Disabling " + this.getNameVersion() + " successful");
	}
	
	public void reload() {
		this.log("Reloading " + this.getNameVersion());
		this.onDisable();
		this.onEnable();
		this.log("Reloading " + this.getNameVersion() + " successful");
	}

	private String getNameVersion() {
		return this.getName() + " " + this.getVersion();
	}
	
	private String getVersion() {
		return "v" + this.getDescription().getVersion();
	}
	
	public void log(String msg) {
		this.log(Level.INFO, msg);
	}
	
	public void log(Level level, String msg) {
		this.getLogger().log(level, msg);
	}
}
