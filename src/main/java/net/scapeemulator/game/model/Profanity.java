package net.scapeemulator.game.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Profanity {
	private static final Logger logger = LoggerFactory.getLogger(Profanity.class);
	private static List<String> profanity = new ArrayList<>();

	private Profanity() {
		throw new AssertionError();
	}
	
	public static String replace(String s) {
		profanity.parallelStream().forEach(p -> {
			String stars = "";
			int len = p.length();
			
			for (int i = 0; i < len; i++) {
				stars += "*";
			}
			
			s.replaceAll(p, stars);
		});
		return s;
	}
	
	public static void load() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader("data/profanity.txt"));
			String read;
			int count = 0;
			while ((read = reader.readLine()) != null) {
				profanity.add(read.trim());
				count++;
			}
			reader.close();
			logger.info("Loaded " + count + " profanitity words.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String[] getProfanity() {
		return (String[]) profanity.toArray();
	}
}
