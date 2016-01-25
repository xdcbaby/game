package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.SkillMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.*;

public final class SkillMessageEncoder extends MessageEncoder<SkillMessage> {

	public SkillMessageEncoder() {
		super(SkillMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, SkillMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 164);
		builder.put(DataType.BYTE, message.getLevel());
		builder.put(DataType.INT, DataOrder.MIDDLE, message.getExperience());
		builder.put(DataType.BYTE, message.getSkill());
		return builder.toGameFrame();
	}

}
