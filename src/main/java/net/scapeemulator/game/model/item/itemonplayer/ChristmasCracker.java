package net.scapeemulator.game.model.item.itemonplayer;

import java.util.Random;

import net.scapeemulator.game.model.Animation;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.ItemOnPlayerMessage;

public class ChristmasCracker implements ItemOnPlayer {
	private int[] PARTY_HATS = {1038, 1040, 1042, 1044, 1046};
	private int[] SECONDARY = {1973, 1897, 1635, 441, 1217, 563, 1969, 950, 1718, 2355};

	public ChristmasCracker() {}
	
	@Override
	public void handle(Player player, ItemOnPlayerMessage message) {
		int localPlayerId = (message.getLocalPlayerId() - 2);
		int slotId = message.getSlotId();
		int itemId = message.getItemId();
		Player other = player.getLocalPlayers().get(localPlayerId);
		if (other != null) {
			Item item = player.getInventory().get(slotId);
			if (item != null) {
				if (item.getId() == itemId) {
					if (itemId == 962) {
						player.getInventory().remove(item);
						player.sendMessage("You pull the cracker...");
						final Random random = new Random();
						boolean chance = random.nextInt(100) < 50;
						int hat = random.nextInt(PARTY_HATS.length);
						int second = random.nextInt(SECONDARY.length);
						player.playAnimation(new Animation(892));
						if (chance) {
							player.getInventory().add(new Item(PARTY_HATS[hat]));
							player.getInventory().add(new Item(SECONDARY[second]));
							player.sendMessage("You win the prize!");
							other.sendMessage("The person with whom you pull the cracker gets the prize.");
						} else {
							other.getInventory().add(new Item(PARTY_HATS[hat]));
							other.getInventory().add(new Item(SECONDARY[second]));
							other.sendMessage("You win the prize!");
							player.sendMessage("The person with whom you pull the cracker gets the prize.");
						}
					}
				}
			}
		}
	}
}
