package net.scapeemulator.game.model.item;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Skill;

//TODO: Range items and mage items - most of melee is done, skillcapes?
public class ItemRequirements {
	public static final int BRONZE_LEVEL_REQ = 1;
	public static final int IRON_LEVEL_REQ = 1;
	public static final int STEEL_LEVEL_REQ = 5;
	public static final int MITH_LEVEL_REQ = 20;
	public static final int ADDY_LEVEL_REQ = 40;
	public static final int RUNE_LEVEL_REQ = 40;
	public static final int GRANITE_LEVEL_REQ = 50;
	public static final int DRAGON_LEVEL_REQ = 60;
	public static final int BARROWS_LEVEL_REQ = 70;
	public static final int OP_LEVEL_REQ = 75;
	public static final int SKILL_CAPE_LEVEL_REQ = 99;

	/**
	 * Checks if the Player has the requirements to wear an item
	 * 
	 * @param player
	 *            The Player trying to wear an item
	 * @param item
	 *            The Item a Player is trying to wear
	 * @return If the Player has the requirements to wear the item
	 */
	public static boolean checkRequirements(Player player, Item item) {
		EquipmentDefinition def = item.getEquipmentDefinition();
		if (def == null) {
			return false;
		}

		String name = item.getDefinition().getName().toLowerCase();
		int slot = def.getSlot();

		if (name.contains("bronze")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= BRONZE_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + BRONZE_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= BRONZE_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + BRONZE_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("iron")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= IRON_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + IRON_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= IRON_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + IRON_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("steel")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= STEEL_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + STEEL_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= STEEL_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + STEEL_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("mith")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= MITH_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + MITH_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= MITH_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + MITH_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("adamant")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= ADDY_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + ADDY_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= ADDY_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + ADDY_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("rune") || name.contains("d'hide body")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= RUNE_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + RUNE_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= RUNE_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + RUNE_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("granite")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= GRANITE_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + GRANITE_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= GRANITE_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + GRANITE_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("dragon")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= DRAGON_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + DRAGON_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= DRAGON_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + DRAGON_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("verac") || name.contains("dharok") || name.contains("ahrim") || name.contains("torag") || name.contains("karil") || name.contains("guthan")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= BARROWS_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + BARROWS_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD || slot == Equipment.HANDS
					|| slot == Equipment.BODY || slot == Equipment.LEGS
					|| slot == Equipment.FEET) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= BARROWS_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + BARROWS_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		if (name.contains("whip")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= 70) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + 70 + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		
		if (name.contains("godsword") || name.contains("dragonfire")) {
			if (slot == Equipment.WEAPON) {
				if (player.getSkillSet().getCurrentLevel(Skill.ATTACK) >= OP_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a attack level of " + OP_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			} else if (slot == Equipment.SHIELD) {
				if (player.getSkillSet().getCurrentLevel(Skill.DEFENCE) >= OP_LEVEL_REQ) {
					return true;
				} else {
					player.sendMessage("You must have a defence level of " + OP_LEVEL_REQ + " to wear " + item.getDefinition().getName() + ".");
					return false;
				}
			}
		}
		
		return true;
	}

}
