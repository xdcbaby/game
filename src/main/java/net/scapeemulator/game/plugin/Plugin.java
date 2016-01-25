/**
 * scape-emulator-final
 * Copyright (c) 2014 Justin Conner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in  the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/license/>.
 */
package net.scapeemulator.game.plugin;

import java.util.LinkedList;
import java.util.List;

public final class Plugin {

    /**
     * The list of scripts for the plugin.
     */
    private List<String> scripts = new LinkedList<>();

    /**
     * The list of dependencies for the plugin.
     */
    private List<String> dependencies = new LinkedList<>();

    /**
     * Constructs a new {@link Plugin};
     */
    public Plugin() {}

    /**
     * Adds a script to the scripts list.
     * @param file The name of the script.
     */
    public void addScript(String file) {
        scripts.add(file);
    }

    /**
     * Adds a dependency to the dependency list.
     * @param dependency The name of the dependency.
     */
    public void addDependency(String dependency) {
        scripts.add(dependency);
    }

    /**
     * Gets the list of scripts.
     * @return The scripts.
     */
    public List<String> getScripts() {
        return scripts;
    }

    /**
     * Gets the list of plugin dependencies.
     * @return The plugin dependencies.
     */
    public List<String> getDependencies() {
        return dependencies;
    }
}
