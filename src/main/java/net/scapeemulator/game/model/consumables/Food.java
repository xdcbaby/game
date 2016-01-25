package net.scapeemulator.game.model.consumables;

import net.scapeemulator.game.model.Animation;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Skill;
import net.scapeemulator.game.model.item.Item;

public enum Food {
	ANCHOVIES(319, 1), CABBAGE(1965, 1), ONION(1957, 1), POTATO(1942, 1), CHEESE(1985, 2), DWELL_BERRIES(2126,
			2), JANGER_BERRIES(247, 2), LIME(2120, 2), LEMON(2102, 2), ORANGE(2108, 2), SPINACH_ROLL(1969, 2), TOMATO(
					1982,
					2), CHICKEN(2140, 3), CHOCOLATE_BAR(1973, 3), MEAT(4293, 3), SHRIMP(315, 3), BAKED_POTATO(6701,
							4), SARDINE(325, 4), BREAD(2309, 5), HERRING(347, 5), MACKEREL(355, 6), COD(339, 7), TROUT(
									333, 7), PIKE(351, 8), SALMON(329, 9), TUNA(361, 10), LOBSTER(379, 12), BASS(365,
											13), SWORDFISH(373, 14), SHARK(385, 20), TUNA_POTATO(7060, 22), SEA_TURTLE(
													397,
													21), MANTARAY(391, 22), PURPLE_SWEETS(10476, 25), PINEAPPLE_CHUNKS(
															2116, 2), WEB_SNIPPER(18169, 15), BOULDABASS(18171,
																	17), BLUE_CRAB(18175, 22), CAVE_MORAY(18177,
																			25), DUSK_EEL(18163, 7), GIANT_FLATFISH(
																					18165, 10), SHORT_FINNED_EEL(18167,
																							12), RED_EYE(18161,
																									5), HEIM_CRAB(18159,
																											2), SALVE_EEL(
																													18173,
																													20);

	private int id, health;

	Food(int id, int health) {
		this.id = id;
		this.health = health;
	}

	private static final int EAT_ANIMATION = 829;

	public static boolean consume(Player player, int slot) {
		Item item = player.getInventory().get(slot);
		if (item != null) {
			// TODO: Eating delay
			// TODO: Append health to player

			Object lastEat = player.getAttribute("food-delay");
			boolean canEat = false;
			if (lastEat == null) {
				canEat = true;
			} else if (System.currentTimeMillis() - (long)lastEat >= 1800) {
				canEat = true;
			}
			if (canEat) {
				int temp = player.getSkillSet().getCurrentLevel(Skill.HITPOINTS);
				player.getInventory().reset(slot);
				player.playAnimation(new Animation(EAT_ANIMATION));
				player.sendMessage("You eat " + item.getDefinition().getName() + ".");
				player.setAttribute("food-delay", System.currentTimeMillis());
				return true;
			}
			
		}
		return false;
	}

	public int getId() {
		return id;
	}

	public int getHealth() {
		return health;
	}
}