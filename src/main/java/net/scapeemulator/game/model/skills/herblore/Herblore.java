package net.scapeemulator.game.model.skills.herblore;

import net.scapeemulator.game.model.Animation;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Skill;
import net.scapeemulator.game.model.item.Item;

public class Herblore {
	public static final int VIAL = 227;
	public static final int PESTLE_AND_MORTAR = 233;
	private static final int MAKE_POTION_ANIMATION = 363;
	
	public static boolean grind(Player player, int item) {
		for (Grindables g : Grindables.values()) {
			if (g.getItem() == item) {
				if (player.getInventory().contains(PESTLE_AND_MORTAR)) {
					Item input = player.getInventory().getItem(item);
					Item output = new Item(g.getOutput());
					if (input != null) {
						player.getInventory().remove(input);
						player.getInventory().add(output);
						player.sendMessage("You grind " + input.getDefinition().getName() + " into " + output.getDefinition().getName());
						return true;
					}
				} else {
					player.sendMessage("You need a Pestle and mortar to be able to do that.");
				}
				break;
			}
		}
		return false;
	}
	
	public static boolean cleanHerb(Player player, int slot) {
		Item item = player.getInventory().get(slot);
		if (item != null) {
			for (Leaf leaf : Leaf.values()) {
				if (leaf.getHerb().getId() == item.getId()) {
					if (player.getSkillSet().getCurrentLevel(Skill.HERBLORE) >= leaf.getLevelReq()) {
						player.getInventory().reset(slot);
						player.getInventory().add(leaf.getClean());
						player.getSkillSet().addExperience(Skill.HERBLORE, leaf.getXp());
						player.sendMessage("You clean the dirt off the leaf.");
						return true;
					} else {
						player.sendMessage("You need a herblore level of " + leaf.getLevelReq() + " to clean this herb.");
					}
					break;
				}
			}
		}
		return false;
	}
	
	public static boolean makeUnfinishedPotion(Player player, Item herb) {
		for (UnfinishedPotion up : UnfinishedPotion.values()) {
			if (up.getHerb().getId() == herb.getId()) {
				if (player.getSkillSet().getCurrentLevel(Skill.HERBLORE) >= up.getLevelReq()) {
					player.playAnimation(new Animation(MAKE_POTION_ANIMATION));
					for (Item item : player.getInventory().toArray()) {
						if (item.getId() == VIAL) {
							player.getInventory().remove(item);
							break;
						}
					}
					player.getInventory().remove(herb);
					player.getInventory().add(up.getUnfinished());
					player.getSkillSet().addExperience(Skill.HERBLORE, up.getXp());
					player.sendMessage("You put " + herb.getDefinition().getName() + " into  vial of water.");
					return true;
				} else {
					player.sendMessage("You need a herblore level of " + up.getLevelReq() + " to make this potion.");
				}
				break;
			}
		}
		return false;
	}
	
	public static boolean makePotion(Player player, Item itemUsed, Item usedWith) {
		for (Potions potion : Potions.values()) {
			if (potion.getUnfinished().getId() == itemUsed.getId() || potion.getUnfinished().getId() == usedWith.getId()) {
				if (player.getSkillSet().getCurrentLevel(Skill.HERBLORE) >= potion.getLevelReq()) {
					player.playAnimation(new Animation(MAKE_POTION_ANIMATION));
					player.getInventory().remove(itemUsed);
					player.getInventory().remove(usedWith);
					player.getInventory().add(potion.getPotion());
					player.getSkillSet().addExperience(Skill.HERBLORE, potion.getXp());
					player.sendMessage("You combine the ingredients to make a " + potion.getPotion().getDefinition().getName() + ".");
					return true;
				} else {
					player.sendMessage("You need a herblore level of " + potion.getLevelReq() + " to make this potion.");
				}
				break;
			}
		}
		System.out.println("Requirements not met for making potions...");
		return false;
	}
}
