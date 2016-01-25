package net.scapeemulator.game.model.item.itemonplayer;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.ItemOnPlayerMessage;

public interface ItemOnPlayer {
	public void handle(Player player, ItemOnPlayerMessage message);
}
