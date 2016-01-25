package net.scapeemulator.game.button;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.msg.RegionChangeMessage;
import net.scapeemulator.game.msg.ScriptMessage;

public final class WorldMapButtonHandler extends ButtonHandler {

	public WorldMapButtonHandler() {
		super(Interface.WORLD_MAP);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 3) {
			/*
			 * TODO: this is probably the incorrect script to run, and
			 * definitely incorrect arguments!
			 * 
			 * Vastico dumped 140 as the script to run upon closing the world
			 * map. This script does not seem to (directly) end up using opcode
			 * 6002 anywhere, which sets a flag allowing the map region to be
			 * re-loaded even though your region X/Y have not changed.
			 * 
			 * Scripts 1187 and 1132 both contain some code that uses this
			 * opcode, and seem to base the value they pass it off a weird
			 * interface-related calculation. Passing 0 and 0 seem to make the
			 * flag get set so this code works, but this is a nasty hack that
			 * needs fixing!!! It is likely that there are some unintended side
			 * effects.
			 */
			player.send(new ScriptMessage(1187, "ii", 0, 0));

			Position position = player.getPosition();
			player.setLastKnownRegion(position);
			player.send(new RegionChangeMessage(position));

			player.getInterfaceSet().closeFullscreen();
		}
	}

}
