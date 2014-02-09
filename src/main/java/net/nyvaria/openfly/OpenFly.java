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

import net.nyvaria.component.hook.MetricsHook;
import net.nyvaria.component.wrapper.NyvariaPlugin;
import net.nyvaria.openfly.commands.FlyCommand;
import net.nyvaria.openfly.flier.FlierList;
import org.bukkit.entity.Player;

/**
 * @author Paul Thompson
 */
public class OpenFly extends NyvariaPlugin {
    public static String PERM_FLY = "openfly.fly";

    // Listener and Commands and Lists (oh my)
    private OpenFlyListener listener = null;
    private FlyCommand flyCommand = null;
    private FlierList flierList = null;

    @Override
    public void onEnable() {
        // Initialise or update the configuration
        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        // Initialise optional hooks
        MetricsHook.enable(this);

        // Create an empty flier list and add all currently logged in players
        this.flierList = new FlierList(this);
        for (Player player : this.getServer().getOnlinePlayers()) {
            this.flierList.put(player);
        }

        // Create and register the listener
        this.listener = new OpenFlyListener(this);

        // Create and set the commands
        this.flyCommand = new FlyCommand(this);
        this.getCommand(FlyCommand.CMD).setExecutor(this.flyCommand);

        // Print a lovely message
        this.log("Enabling %1$s successful", this.getNameAndVersion());
    }

    @Override
    public void onDisable() {
        // Disable the hooks
        MetricsHook.disable();

        // Destroy the flier list
        this.flierList = null;

        // Print a lovely message
        this.log("Disabling %s successful", this.getNameAndVersion());
    }

    /**
     * Getters
     */

    public FlierList getFlierList() {
        return flierList;
    }
}
