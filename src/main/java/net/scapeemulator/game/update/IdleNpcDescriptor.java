package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Npc;
import net.scapeemulator.game.msg.NpcUpdateMessage;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class IdleNpcDescriptor extends NpcDescriptor {

	public IdleNpcDescriptor(Npc npc) {
		super(npc);
	}

	@Override
	public void encodeDescriptor(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		if (isBlockUpdatedRequired()) {
			builder.putBits(1, 1);
			builder.putBits(2, 0);
		} else {
			builder.putBits(1, 0);
		}
	}

}
