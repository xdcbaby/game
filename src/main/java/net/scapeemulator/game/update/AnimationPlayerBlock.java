package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Animation;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.PlayerUpdateMessage;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class AnimationPlayerBlock extends PlayerBlock {

	private final Animation animation;

	public AnimationPlayerBlock(Player player) {
		super(0x2);
		this.animation = player.getAnimation();
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		builder.put(DataType.SHORT, animation.getId());
		builder.put(DataType.BYTE, animation.getDelay());
	}

}
