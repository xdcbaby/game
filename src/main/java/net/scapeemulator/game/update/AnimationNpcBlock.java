package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Animation;
import net.scapeemulator.game.model.Npc;
import net.scapeemulator.game.msg.NpcUpdateMessage;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class AnimationNpcBlock extends NpcBlock {

	private final Animation animation;

	public AnimationNpcBlock(Npc npc) {
		super(0x10);
		this.animation = npc.getAnimation();
	}

	@Override
	public void encode(NpcUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, animation.getId());
		builder.put(DataType.BYTE, animation.getDelay());
	}

}
