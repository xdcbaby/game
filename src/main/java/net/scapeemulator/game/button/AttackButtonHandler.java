package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.PlayerSettings;
import net.scapeemulator.game.model.Tab;

public final class AttackButtonHandler extends ButtonHandler {

	private final int tab;
	private final int[] styles;
	private final int autoRetaliate;

	public AttackButtonHandler(int tab, int[] styles, int autoRetaliate) {
		super(tab);
		this.tab = tab;
		this.styles = styles;
		this.autoRetaliate = autoRetaliate;
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (player.getInterfaceSet().getTab(Tab.ATTACK) != tab)
			return;

		PlayerSettings settings = player.getSettings();

		for (int style = 0; style < styles.length; style++) {
			if (styles[style] == slot) {
				settings.setAttackStyle(style);
				return;
			}
		}

		if (slot == autoRetaliate) {
			settings.toggleAutoRetaliating();
		}
	}

}
