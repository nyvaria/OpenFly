/**
 * 
 */
package net.nyvaria.openfly.metrics;

import java.io.IOException;
import java.util.logging.Level;

import org.mcstats.Metrics;

import net.nyvaria.openfly.OpenFly;

/**
 * @author Paul Thompson
 * 
 * This was initially based on a version of such from PlugMan by rylinaux
 * https://github.com/rylinaux/PlugMan
 */
public class MetricsHandler {
    private static final String METRICS_URL = "http://mcstats.org/plugin/OpenFly";
	private final OpenFly plugin;
	
	public MetricsHandler(OpenFly plugin) {
		this.plugin = plugin;
		
		try {
			Metrics metrics = new Metrics(plugin);
			metrics.start();
			this.plugin.log("Metrics started: " + METRICS_URL);
		} catch (IOException e) {
			this.plugin.log(Level.WARNING, "Failed to start metrics");
			e.printStackTrace();
		}
	}
}
