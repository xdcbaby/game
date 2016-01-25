package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Player;

public class TradeButtonHandler extends ButtonHandler {

	public TradeButtonHandler(int id) {
		super(id);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (player.getTrade().getSession().isTrading()) {
			if (slot == 16) {
				player.getTrade().getSession().preAccept();
			} else if (slot == 18 || slot == 21 || slot == 8) {
				player.getTrade().getSession().declineTrade();
			} else if (slot == 20) {
				player.getTrade().getSession().acceptTrade();
			}
			return;
		}
	}

}
