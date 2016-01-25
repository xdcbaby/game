package net.scapeemulator.game.button;

import java.util.HashMap;
import java.util.Map;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public final class ButtonDispatcher {

	private final Map<Integer, ButtonHandler> handlers = new HashMap<>();

	public ButtonDispatcher() {
		bind(new TradeButtonHandler(Interface.TRADE));
		bind(new TradeButtonHandler(334));
		bind(new ReportAbuseButtonHandler());
		bind(new FixedButtonHandler());
		bind(new ResizableButtonHandler());
		bind(new LogoutButtonHandler());
		bind(new EmoteButtonHandler());
		bind(new AttackButtonHandler(Interface.ATTACK_AXE,          new int[] { 2, 5, 4, 3 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_MAUL,         new int[] { 2, 4, 3 },    24));
		bind(new AttackButtonHandler(Interface.ATTACK_BOW,          new int[] { 2, 4, 3 },    27));
		bind(new AttackButtonHandler(Interface.ATTACK_CLAWS,        new int[] { 2, 5, 4, 3 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_LONGBOW,      new int[] { 2, 4, 3 },    24));
		bind(new AttackButtonHandler(Interface.ATTACK_FIXED_DEVICE, new int[] { 2, 3 },       7 ));
		bind(new AttackButtonHandler(Interface.ATTACK_GODSWORD,     new int[] { 2, 3, 4, 5 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_SWORD,        new int[] { 2, 3, 4, 5 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_PICKAXE,      new int[] { 2, 3, 4, 5 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_HALBERD,      new int[] { 2, 3, 4, 5 }, 24));
		bind(new AttackButtonHandler(Interface.ATTACK_STAFF,        new int[] { 2, 3, 4 },    24));
		bind(new AttackButtonHandler(Interface.ATTACK_SCYTHE,       new int[] { 2, 3, 4, 5 }, 11));
		bind(new AttackButtonHandler(Interface.ATTACK_SPEAR,        new int[] { 2, 3, 4, 5 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_MACE,         new int[] { 2, 3, 4, 5 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_DAGGER,       new int[] { 2, 3, 4, 5 }, 26));
		bind(new AttackButtonHandler(Interface.ATTACK_MAGIC_STAFF,  new int[] { 1, 2, 3 },    9 ));
		bind(new AttackButtonHandler(Interface.ATTACK_THROWN,       new int[] { 2, 3, 4 },    24));
		bind(new AttackButtonHandler(Interface.ATTACK_UNARMED,      new int[] { 2, 3, 4, 5 }, 24));
		bind(new AttackButtonHandler(Interface.ATTACK_WHIP,         new int[] { 2, 3, 4 },    24));
		bind(new EnergyOrbButtonHandler());
		bind(new SettingsButtonHandler());
		bind(new WorldMapButtonHandler());
		bind(new EquipmentButtonHandler());
		bind(new SpellBookButtonHandler());
		bind(new SkillsButtonHandler());
	}

	public void bind(ButtonHandler handler) {
		handlers.put(handler.getId(), handler);
	}

	public void handle(Player player, int id, int slot, int parameter) {
		ButtonHandler handler = handlers.get(id);
		if (handler != null)
			handler.handle(player, slot, parameter);
	}

}
