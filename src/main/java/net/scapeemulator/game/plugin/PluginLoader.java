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

import net.scapeemulator.game.plugin.impl.RubyEnvironment;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Hadyn Richard
 */
public final class PluginLoader {

    /**
     * The logger for this class.
     */
    private static final Logger logger = LoggerFactory.getLogger(PluginLoader.class);

    /**
     * The JSON factory to create new JSON parsers with.
     */
    private final JsonFactory factory = new JsonFactory();

    /**
     * The ruby script environment for the plugin loader.
     */
    private RubyEnvironment scriptEnvironment = new RubyEnvironment();

    /**
     * The map that contains all the parsed plugin data.
     */
    private final Map<String, Plugin> parsedPluginData = new HashMap<>();

    /**
     * The set of plugins that have had their scripts loaded.
     */
    private Set<String> loadedPlugins = new HashSet<>();

    /**
     * Constructs a new {@link PluginLoader};
     */
    public PluginLoader(PluginContext context) {
        scriptEnvironment.setContext(context);
    }

    /**
     * Loads all the plugins from the specified directory.
     */
    public void load(String dir) throws IOException, ScriptException {
        load(new File(dir));
    }

    /**
     * Loads all the plugins from the specified file directory.
     */
    public void load(File dir) throws IOException, ScriptException {
        scriptEnvironment.eval(new Script(new File(dir, "bootstrap.rb")));
        for(File file : dir.listFiles()) {

            /* Skip over non-directory files */
            if(!file.isDirectory()) {
                continue;
            }

            File dataFile = new File(file, "plugin.json");

            /* Check to see if the json data file exists */
            if(!dataFile.exists()) {
                logger.warn("missing plugin.json file from '" + file.getName() + "' plugin");
                return;
            }

            JsonParser parser = factory.createJsonParser(dataFile);

            /* Check to see if the JSON structure start is correct */
            if(parser.nextToken() != JsonToken.START_OBJECT) {
                throw new IOException();
            }

            /* Load the plugin data from the JSON file */
            Plugin plugin = new Plugin();
            while(parser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = parser.getCurrentName();
                switch(currentName.toLowerCase()) {
                    case "scripts":
                        /* Check to see if the next token is valid */
                        if(parser.nextToken() != JsonToken.START_ARRAY) {
                            throw new IOException();
                        }

                        while(parser.nextToken() != JsonToken.END_ARRAY) {
                            plugin.addScript(parser.getText());
                        }
                        break;

                    case "dependencies":
                        /* Check to see if the next token is valid */
                        if(parser.nextToken() != JsonToken.START_ARRAY) {
                            throw new IOException();
                        }

                        while(parser.nextToken() != JsonToken.END_ARRAY) {
                            plugin.addDependency(parser.getText());
                        }
                        break;
                }
            }
            parsedPluginData.put(file.getName(), plugin);
        }

        /* Load each of the plugins from its data */
        for(Map.Entry<String, Plugin> entry : parsedPluginData.entrySet()) {
            /* Check if the plugin has already been loaded */
            if(loadedPlugins.contains(entry.getKey())) {
                continue;
            }

            loadPlugin(dir, entry.getKey(), entry.getValue());
        }

        logger.info("PluginLoader loaded " + loadedPlugins.size() + " plugins...");
    }

    /**
     * Loads a plugin from the specified root directory, name, and plugin data.
     */
    private void loadPlugin(File dir, String name, Plugin data) throws IOException, ScriptException {
        /* Check if the plugin has already been loaded */
        if(loadedPlugins.contains(name)) {
            return;
        }

        /* Load all of the dependencies first */
        for(String pluginName : data.getDependencies()) {

            /* Check if the dependency is valid */
            if(!parsedPluginData.containsKey(pluginName)) {
                continue;
            }

            loadPlugin(dir, pluginName, parsedPluginData.get(pluginName));
        }

        File scriptDir = new File(dir, name);

        /* Evaluate each of the scripts */
        for(String scriptName : data.getScripts()) {
            scriptEnvironment.eval(new Script(new File(scriptDir, scriptName)));
        }

        /* Note that the plugin has been loaded */
        loadedPlugins.add(name);
    }
}
