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
package net.scapeemulator.game.plugin.impl;

import net.scapeemulator.game.plugin.PluginContext;
import net.scapeemulator.game.plugin.PluginEnvironment;
import net.scapeemulator.game.plugin.Script;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hadyn Richard
 */
public final class RubyEnvironment implements PluginEnvironment {

    /**
     * The script engine manager for all the script environments.
     */
    private static final ScriptEngineManager manager = new ScriptEngineManager();

    /**
     * The collection of scripts that have been compiled.
     */
    private Map<String, CompiledScript> scripts;

    /**
     * The script engine for this environment.
     */
    private ScriptEngine engine;

    /**
     * Constructs a new {@link RubyEnvironment};
     */
    public RubyEnvironment() {
        scripts = new HashMap<>();
        engine = manager.getEngineByName("jruby");
        if(engine == null) {
            throw new NullPointerException();
        }
    }

    /**
     * Sets the context of the script environment.
     *
     * @param context   The environment context.
     */
    public void setContext(PluginContext context) {
        engine.getBindings(javax.script.ScriptContext.GLOBAL_SCOPE).put("ctx", context);
    }

    /**
     * Evalulates a script, the script must have been previously compiled.
     *
     * @param name              The name of the script to evaluate.
     * @throws ScriptException  A script exception was thrown while evaluating the script.
     */
    public void eval(String name) throws ScriptException {
        CompiledScript compiledScript = scripts.get(name);
        if(compiledScript == null) {
            throw new RuntimeException("script does not exist");
        }
        compiledScript.eval();
    }

    /**
     * Loads a script.
     *
     * @param script            The script to load.
     * @throws ScriptException  A script exception was thrown while evaluating the script.
     */
    public void load(Script script) throws ScriptException {
        Compilable compilable = (Compilable) engine;
        CompiledScript compiledScript = compilable.compile(script.getSource());
        scripts.put(script.getName(), compiledScript);
    }

    /**
     * Evaluates a script.
     *
     * @param script            The script to evaluate.
     * @throws ScriptException  A script exception was thrown while evaluating the script.
     */
    public void eval(Script script) throws ScriptException {
        if(scripts.containsKey(script.getName())) {
            eval(script.getName());
        } else {
            Compilable compilable = (Compilable) engine;
            CompiledScript compiledScript = compilable.compile(script.getSource());
            scripts.put(script.getName(), compiledScript);
            compiledScript.eval();
        }
    }
}
