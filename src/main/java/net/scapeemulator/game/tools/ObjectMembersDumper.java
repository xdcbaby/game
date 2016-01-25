package net.scapeemulator.game.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.scapeemulator.cache.def.ObjectDefinition;
import net.scapeemulator.game.model.definition.ObjectDefinitions;

public class ObjectMembersDumper {
	public static void dump() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/object-members.txt"));
		for(int i = 0; i <= 45000; i++) {
			ObjectDefinition def = ObjectDefinitions.forId(i);
			if (def != null) {
				String examine = getObjectExamine(def.getName());
				writer.write(i + " - " + examine);
				writer.flush();
				writer.newLine();
				System.out.println("Object: " + i + " - " + examine);
			}
		}
		writer.close();
	}

	public static String getObjectExamine(String name) throws IOException {
		try {
			String itemName = name.replace(" ", "_");
			URL url = new URL("http://runescape.wikia.com/wiki/" + itemName);
			String line;
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(10000);
			BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((line = stream.readLine()) != null) {
				
				if (line.contains("Members</a>") || line.contains("Members Only?")) {
					String nextLine = stream.readLine();
					String examine = nextLine.replace("</th><td>", "").replace("*", "").replace("#160;", "").replace("&lt;", "").replace("&gt;", "").replace("<br />", "");
					if (examine.endsWith("</small>")) {
						String toRemove = examine.substring(examine.indexOf("<small>"), examine.indexOf("</small>")+8);
						return examine.replace(toRemove, "");
					}
					return examine;
				}
				
			}
			stream.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "no";
	}
}
