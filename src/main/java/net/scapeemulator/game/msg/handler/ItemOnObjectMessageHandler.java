package net.scapeemulator.game.msg.handler;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.model.object.GameObject;
import net.scapeemulator.game.msg.ItemOnObjectMessage;

public class ItemOnObjectMessageHandler extends MessageHandler<ItemOnObjectMessage> {

	@Override
	public void handle(Player player, ItemOnObjectMessage message) {
		if (message.getInterfaceId() == Interface.INVENTORY) {
			if (player.getInventory().get(message.getSlotId()).getId() == message.getItemId()) {
				GameObject object = World.getWorld().getRegionManager().getRegion(player.getPosition()).getObject(new Position(message.getObjectX(), message.getObjectY()));
				if (object.getId() == message.getObjectId()) {
					//TODO: logic
					return;
				}
			}
		}
	}

}
