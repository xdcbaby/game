package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Direction;
import net.scapeemulator.game.model.Npc;
import net.scapeemulator.game.msg.NpcUpdateMessage;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class RunNpcDescriptor extends NpcDescriptor {

	private final Direction firstDirection, secondDirection;

	public RunNpcDescriptor(Npc npc) {
		super(npc);
		this.firstDirection = npc.getFirstDirection();
		this.secondDirection = npc.getSecondDirection();
	}

	@Override
	public void encodeDescriptor(NpcUpdateMessage message, GameFrameBuilder builder, GameFrameBuilder blockBuilder) {
		builder.putBits(1, 1);
		builder.putBits(2, 2);
		builder.putBits(1, 1);
		builder.putBits(3, firstDirection.toInteger());
		builder.putBits(3, secondDirection.toInteger());
		builder.putBits(1, isBlockUpdatedRequired() ? 1 : 0);
	}

}
