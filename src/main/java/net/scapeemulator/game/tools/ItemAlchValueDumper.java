package net.scapeemulator.game.tools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import net.scapeemulator.cache.def.ItemDefinition;
import net.scapeemulator.game.model.item.ItemDefinitions;

public class ItemAlchValueDumper {
	public static void dump() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("data/item-alch-values.txt"));
		for(int i = 0; i <= 20000; i++) {
			ItemDefinition def = ItemDefinitions.forId(i);
			if (def != null) {
				String lowAlch = getLowAlch(def.getName());
				String highAlch = getHighAlch(def.getName());
				writer.write(i + " - " + lowAlch + " - " + highAlch);
				writer.flush();
				writer.newLine();
				System.out.println("ITEM: " + i + " - " + lowAlch + " - " + highAlch);
			}
		}
		writer.close();
	}

	public static String getLowAlch(String name) throws IOException {
		try {
			String npcName = name.replace(" ", "_");
			URL url = new URL("http://runescape.wikia.com/wiki/" + npcName);
			String line;
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(10000);
			BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((line = stream.readLine()) != null) {
				if (line.contains("Low alch</a>")) {
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
		return "0 coins";
	}
	
	public static String getHighAlch(String name) throws IOException {
		try {
			String npcName = name.replace(" ", "_");
			URL url = new URL("http://runescape.wikia.com/wiki/" + npcName);
			String line;
			URLConnection urlConnection = url.openConnection();
			urlConnection.setReadTimeout(10000);
			BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			while ((line = stream.readLine()) != null) {
				if (line.contains("High alch</a>")) {
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
		return "0 coins";
	}
}
