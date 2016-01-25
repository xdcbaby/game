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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by Hadyn Richard
 */
public final class Script {

    /**
     * The name of the script.
     */
    private String name;

    /**
     * The source of the script.
     */
    private Reader source;

    /**
     * Constructs a new {@link Script};
     * @param file The file of the script to create.
     * @throws IOException An I/O exception was thrown while creating the script.
     */
    public Script(File file) throws IOException {
        this(file.getCanonicalPath(), new FileReader(file));
    }

    /**
     * Constructs a new {@link Script};
     * @param name      The name of the script.
     * @param source    The source of the script.
     */
    public Script(String name, Reader source) {
        this.name = name;
        this.source = source;
    }

    /**
     * Gets the source of the script.
     *
     * @return  The source.
     */
    public Reader getSource() {
        return source;
    }

    /**
     * Gets the name of the script.
     *
     * @return  The name of the script.
     */
    public String getName() {
        return name;
    }
}
