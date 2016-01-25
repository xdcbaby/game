package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.model.object.GameObject;
import net.scapeemulator.game.model.object.ObjectOrientation;
import net.scapeemulator.game.model.object.ObjectType;

public class ObjectSpawnCommandHandler extends CommandHandler {

	public ObjectSpawnCommandHandler() {
		super("obj");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2) {
			return;
		}
		if (arguments.length != 1) {
			player.sendMessage("Syntax: ::obj [id]");
			return;
		}
		
		int objectId = Integer.parseInt(arguments[0]);
		World.getWorld().getRegionManager().getRegion(player.getPosition()).addObject(new GameObject(objectId, ObjectType.TYPE_0.getId(), player.getPosition(), ObjectOrientation.NORTH));
	}

}
