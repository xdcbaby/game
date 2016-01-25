package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Direction;
import net.scapeemulator.game.model.Npc;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.update.NpcDescriptor;

public class NPCSpawnCommandHandler extends CommandHandler {

	public NPCSpawnCommandHandler() {
		super("npc");
	}
	
	@Override
	public void handle(Player player, String[] arguments) {
		if (player.getRights() < 2) {
			return;
		}
		
		if (arguments.length != 1) {
			player.sendMessage("Syntax: ::npc [id]");
			return;
		}
		
		int npcId = Integer.parseInt(arguments[0]);
		Npc npc = new Npc(npcId);
		npc.setDirections(Direction.NONE, Direction.NONE);
		npc.setPosition(player.getPosition());
		NpcDescriptor.create(npc);
		World.getWorld().getNpcs().add(npc);
	}

}
