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

public class ItemBonusDumper {

	public static void dump() throws IOException {
		//BufferedWriter writer = new BufferedWriter(new FileWriter("data/item-bonuses.txt"));
		for(int i = 0; i <= 20000; i++) {
			ItemDefinition def = ItemDefinitions.forId(i);
			if (def != null) {
				String[] attack = getAttackBonus(def.getName());
				String[] defence = getDefenceBonus(def.getName()); //stab, slash, crush, magic, ranged - 5
				String[] other = {}; //strength, ranged strength, magic, prayer - 4
				
				//writer.flush();
				//writer.newLine();
			}
		}
		//writer.close();
	}
	
	public static String[] getAttackBonus(String name) throws IOException {
		String itemId = name.replace(" ", "_");
		URL url = new URL("http://runescape.wikia.com/wiki/" + itemId);
		String line;
		URLConnection urlConnection = url.openConnection();
		urlConnection.setReadTimeout(10000);
		BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String stab = "0";
		String slash = "0";
		String crush = "0";
		String magic = "0";
		String ranged = "0";
		
		while ((line = stream.readLine()) != null) {
			if (line.startsWith("Attack bonus</a>")) {
				String nextLine = stream.readLine();
				String data = nextLine.replace("</th><td>", "").replace("*", "").replace("#160;", "").replace("&lt;", "").replace("&gt;", "").replace("<br />", "");
				
				System.out.println(line);
			
			}
		}
		stream.close();
		return new String[] {"0", "0", "0", "0", "0"};
	}
	
	public static String[] getDefenceBonus(String name) throws IOException {
		String itemId = name.replace(" ", "_");
		URL url = new URL("http://runescape.wikia.com/wiki/" + itemId);
		String line;
		URLConnection urlConnection = url.openConnection();
		urlConnection.setReadTimeout(10000);
		BufferedReader stream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String stab = "0";
		String slash = "0";
		String crush = "0";
		String magic = "0";
		String ranged = "0";
		
		while ((line = stream.readLine()) != null) {
			if (line.startsWith("Attack bonus</a>")) {
				String nextLine = stream.readLine();
				String data = nextLine.replace("</th><td>", "").replace("*", "").replace("#160;", "").replace("&lt;", "").replace("&gt;", "").replace("<br />", "");
				
				System.out.println(line);
			
			}
		}
		stream.close();
		return new String[] {"0", "0", "0", "0", "0"};
	}
	
	public static String[] getOtherBonus(String name) throws IOException {
		return new String[] {"0", "0", "0", "0"};
	}
}
