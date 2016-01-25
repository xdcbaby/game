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
package net.scapeemulator.game.model.definition;

import net.scapeemulator.cache.Archive;
import net.scapeemulator.cache.Cache;
import net.scapeemulator.cache.Container;
import net.scapeemulator.cache.ReferenceTable;
import net.scapeemulator.cache.def.ObjectDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ObjectDefinitions {

    private static final Logger logger = LoggerFactory.getLogger(ObjectDefinitions.class);
    private static ObjectDefinition[] definitions;

    public static void init(Cache cache) throws IOException {
        int count = 0;

        Container tableContainer = Container.decode(cache.getStore().read(255, 16));
        ReferenceTable table = ReferenceTable.decode(tableContainer.getData());

        int files = table.capacity();
        definitions = new ObjectDefinition[files * 256];

        for (int file = 0; file < files; file++) {
            ReferenceTable.Entry entry = table.getEntry(file);
            if (entry == null)
                continue;

            Archive archive = Archive.decode(cache.read(16, file).getData(), entry.size());
            int nonSparseMember = 0;
            for (int member = 0; member < entry.capacity(); member++) {
                ReferenceTable.ChildEntry childEntry = entry.getEntry(member);
                if (childEntry == null)
                    continue;

                int id = file * 256 + member;
                ObjectDefinition definition = ObjectDefinition.decode(archive.getEntry(nonSparseMember++));
                definitions[id] = definition;
                count++;
            }
        }

        logger.info("Loaded " + count + " object definitions.");
        parseObjectExamination();
        parseObjectMembers();
    }
    
    private static void parseObjectMembers() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/object-members.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int objectId = Integer.parseInt(parsed[0].trim());
			try {
				String examine = parsed[2].trim();
				if (objectId < count()) {
					if (definitions[objectId] != null) {
						definitions[objectId].setMembers(examine.equalsIgnoreCase("yes"));
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " object membership type.");
	}
    
    private static void parseObjectExamination() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("data/object-examine.txt"));
		String read;
		int count = 0;
		while ((read = reader.readLine()) != null && count < count()) {
			String[] parsed = read.split("-");
			int objectId = Integer.parseInt(parsed[0].trim());
			try {
				String examine = parsed[2].trim();
				if (objectId < count()) {
					if (definitions[objectId] != null) {
						definitions[objectId].setDescription(examine);
					}
				}
				count++;
			} catch (IndexOutOfBoundsException e) {
				//Ignore, will skip
			}
		}
		reader.close();
		logger.info("Loaded " + count + " object examinations.");
	}

    public static int count() {
        return definitions.length;
    }

    public static ObjectDefinition forId(int id) {
        return definitions[id];
    }

}

