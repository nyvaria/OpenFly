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
package net.nyvaria.openfly;

import java.util.logging.Level;

import net.nyvaria.openfly.commands.FlyCommand;
import net.nyvaria.openfly.flier.FlierList;
import net.nyvaria.openfly.metrics.MetricsHandler;

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
	private  MetricsHandler  metrics    = null;
	private  FlyCommand      flyCommand = null;
	
	@Override
	public void onEnable() {
		// Create an empty flier list
		this.flierList = new FlierList(this);
		
		// Create and register the listener
		this.listener = new OpenFlyListener(this);
		this.getServer().getPluginManager().registerEvents(listener, this);
		
		// Add all currently logged in players to the flier list
		for (Player player : this.getServer().getOnlinePlayers()) {
			this.flierList.put(player);
		}

		// Initialise or update the configuration
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		
		// Initialise metrics
		boolean useMetrics = this.getConfig().getBoolean("use-metrics");
		if (useMetrics) {
            this.metrics = new MetricsHandler(this);
            metrics.run();
		} else {
            this.log("Skipping metrics");
		}
		
		// Create and set the commands
		this.flyCommand = new FlyCommand(this);
		this.getCommand(FlyCommand.CMD).setExecutor(this.flyCommand);
		
		// Print a lovely message
		this.log("Enabling " + this.getNameVersion() + " successful");
	}
	
	@Override
	public void onDisable() {
		// Empty and destroy the flier list
		this.flierList.clear();
		this.flierList = null;
		
		// Destroy the metrics handler
		this.metrics = null;
		
		this.log("Disabling " + this.getNameVersion() + " successful");
	}
	
	public void log(String msg) {
		this.log(Level.INFO, msg);
	}
	
	public void log(Level level, String msg) {
		this.getLogger().log(level, msg);
	}
	private String getNameVersion() {
		return this.getName() + " v" + this.getDescription().getVersion();
	}
	
}
