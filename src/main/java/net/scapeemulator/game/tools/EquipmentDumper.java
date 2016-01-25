package net.scapeemulator.game.tools;

import net.scapeemulator.cache.Cache;
import net.scapeemulator.cache.FileStore;
import net.scapeemulator.cache.def.ItemDefinition;
import net.scapeemulator.game.model.item.Equipment;
import net.scapeemulator.game.model.item.EquipmentDefinition;
import net.scapeemulator.game.model.item.EquipmentDefinition.WeaponClass;
import net.scapeemulator.game.model.item.ItemDefinitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public final class EquipmentDumper {

	private static final Logger logger = LoggerFactory.getLogger(EquipmentDumper.class);

	public static void main(String[] args) throws IOException {
		logger.info("Dumping equipment data...");

		Cache cache = new Cache(FileStore.open("data/cache"));
		ItemDefinitions.init(cache);

		try (DataOutputStream output = new DataOutputStream(new FileOutputStream("data/equipment.dat"))) {
			for (int id = 0; id < ItemDefinitions.count(); id++) {
				ItemDefinition def = ItemDefinitions.forId(id);
				if (def != null) {
					if (isEquipment(def)) {
						output.writeShort(id);
						int flags = 0, slot = getSlot(def);
						if (isTwoHanded(def)) flags |= EquipmentDefinition.FLAG_TWO_HANDED;
						if (isFullHelm(def)) flags |= EquipmentDefinition.FLAG_FULL_HELM;
						if (isFullMask(def)) flags |= EquipmentDefinition.FLAG_FULL_MASK;
						if (isFullBody(def)) flags |= EquipmentDefinition.FLAG_FULL_BODY;
						output.writeByte(flags);
						output.writeByte(slot);

						if (slot == Equipment.WEAPON) {
							output.writeShort(getStance(id, def));
							output.writeByte(getWeaponClass(def).ordinal());
						}
					}
				}
			}
			output.writeShort(-1);
		}

		logger.info("Successfully dumped equipment data.");
	}

	private static boolean isEquipment(ItemDefinition definition) {
		return definition.getMaleWearModel1() >= 0;
	}

	private static int getSlot(ItemDefinition definition) {
		if (definition.getName() == null) return Equipment.WEAPON;
		String name = definition.getName().toLowerCase();

		if (name.contains("claws")) return Equipment.WEAPON;
		if (name.contains("sword")) return Equipment.WEAPON;
		if (name.contains("dagger")) return Equipment.WEAPON;
		if (name.contains("mace")) return Equipment.WEAPON;
		if (name.contains("whip")) return Equipment.WEAPON;
		if (name.contains("bow")) return Equipment.WEAPON;
		if (name.contains("staff")) return Equipment.WEAPON;
		if (name.contains("dart")) return Equipment.WEAPON;

		if (name.contains("glove")) return Equipment.HANDS;
		if (name.contains("vamb")) return Equipment.HANDS;
		if (name.contains("gaunt")) return Equipment.HANDS;

		if (name.contains("ring")) return Equipment.RING;
		if (name.contains("bracelet")) return Equipment.RING;

		if (name.contains("amulet")) return Equipment.NECK;
		if (name.contains("necklace")) return Equipment.NECK;
		if (name.contains("scarf")) return Equipment.NECK;

		if (name.contains("leg")) return Equipment.LEGS;
		if (name.contains("bottom")) return Equipment.LEGS;
		if (name.contains("skirt")) return Equipment.LEGS;

		if (name.contains("body")) return Equipment.BODY;
		if (name.contains("top")) return Equipment.BODY;
		if (name.contains("chest")) return Equipment.BODY;
		if (name.contains("chainmail")) return Equipment.BODY;

		if (name.contains("arrow")) return Equipment.AMMO;
		if (name.contains("bolt")) return Equipment.AMMO;

		if (name.contains("shield")) return Equipment.SHIELD;
		if (name.contains("defender")) return Equipment.SHIELD;
		if (name.contains("book")) return Equipment.SHIELD;

		if (name.contains("cape")) return Equipment.CAPE;
		if (name.contains("cloak")) return Equipment.CAPE;

		if (name.contains("boot")) return Equipment.FEET;

		if (name.contains("hat")) return Equipment.HEAD;
		if (name.contains("helm")) return Equipment.HEAD;
		if (name.contains("mask")) return Equipment.HEAD;
		if (name.contains("hood")) return Equipment.HEAD;
		if (name.contains("coif")) return Equipment.HEAD;

		return Equipment.WEAPON;
	}

	private static boolean isTwoHanded(ItemDefinition def) {
		if (def.getName() == null) return false;
		String name = def.getName().toLowerCase();
		if (name.contains("2h")) return true;
		if (name.contains(" bow")) return true;
		if (name.contains("godsword")) return true;
		if (name.contains("claws")) return true;
		if (name.contains("spear")) return true;
		if (name.contains("maul")) return true;
		return false;
	}

	private static boolean isFullBody(ItemDefinition def) {
		if (def.getName() == null) return false;

		if (getSlot(def) != Equipment.BODY) return false;

		String name = def.getName().toLowerCase();
		if (name.contains("platebody")) return true;
		if (name.contains("robe")) return true;
		return false;
	}

	private static boolean isFullHelm(ItemDefinition def) {
		if (def.getName() == null) return false;

		if (getSlot(def) != Equipment.HEAD) return false;

		String name = def.getName().toLowerCase();
		if (name.contains("full")) return true;
		return false;
	}

	private static boolean isFullMask(ItemDefinition def) {
		if (def.getName() == null) return false;

		if (getSlot(def) != Equipment.HEAD) return false;

		if (isFullHelm(def)) return true;
		//String name = def.getName().toLowerCase();
		//if (name.contains("full")) return true;
		return false;
	}

	private static WeaponClass getWeaponClass(ItemDefinition def) {
		if (def.getName() == null) return WeaponClass.SWORD;

		String name = def.getName().toLowerCase();
		if (name.contains("scythe")) return WeaponClass.SCYTHE;
		if (name.contains("pickaxe")) return WeaponClass.PICKAXE;
		if (name.contains("axe")) return WeaponClass.AXE;
		if (name.contains("godsword")) return WeaponClass.GODSWORD;
		if (name.contains("claws")) return WeaponClass.CLAWS;
		if (name.contains("longsword")) return WeaponClass.SWORD;
		if (name.contains("scimitar")) return WeaponClass.SWORD;
		if (name.contains("2h sword")) return WeaponClass.SWORD;
		if (name.contains("sword")) return WeaponClass.DAGGER;
		if (name.contains("dagger")) return WeaponClass.DAGGER;
		if (name.contains("mace")) return WeaponClass.MACE;
		if (name.contains("maul")) return WeaponClass.MAUL;
		if (name.contains("whip")) return WeaponClass.WHIP;
		if (name.contains("longbow")) return WeaponClass.LONGBOW;
		if (name.contains("bow")) return WeaponClass.BOW;
		if (name.contains("staff")) return WeaponClass.STAFF;
		if (name.contains("spear")) return WeaponClass.SPEAR;
		if (name.contains("dart")) return WeaponClass.THROWN;

		return WeaponClass.SWORD;
	}

	private static int getStance(int id, ItemDefinition def) {
		if (def.getName() == null) return 1426;

		String name = def.getName().toLowerCase();
		if (name.contains("scimitar") || name.contains("sword"))
			return 1381;
		if (name.contains("whip"))
			return 620;
		if (name.contains("maul"))
			return 27;

		return 1426;
	}

}
