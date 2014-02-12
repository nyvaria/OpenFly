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
    private FlierList flierList = null;

    @Override
    public void onEnable() {
        // Initialise or update the configuration
        saveDefaultConfig();
        getConfig().options().copyDefaults(true);

        // Initialise optional hooks
        MetricsHook.enable(this);

        // Create an empty flier list and add all currently logged in players
        flierList = new FlierList();
        for (Player player : getServer().getOnlinePlayers()) {
            flierList.put(player);
        }

        // Create and register the listener
        listener = new OpenFlyListener(this);

        // Create and set the commands
        FlyCommand flyCommand = new FlyCommand(this);
        getCommand(FlyCommand.CMD).setExecutor(flyCommand);

        // Print a lovely message
        log("Enabling %1$s successful", getNameAndVersion());
    }

    @Override
    public void onDisable() {
        // Disable the hooks
        MetricsHook.disable();

        // Destroy the listener and flier list
        listener  = null;
        flierList = null;

        // Print a lovely message
        log("Disabling %s successful", getNameAndVersion());
    }

    /**
     * Getters
     */

    public FlierList getFlierList() {
        return flierList;
    }
}
