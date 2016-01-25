package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.ResetMinimapFlagMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

import java.io.IOException;

public final class ResetMinimapFlagMessageEncoder extends MessageEncoder<ResetMinimapFlagMessage> {

	public ResetMinimapFlagMessageEncoder() {
		super(ResetMinimapFlagMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ResetMinimapFlagMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 33);
        builder.put(DataType.BYTE, 0xFF);
        builder.put(DataType.BYTE, 0);
        builder.put(DataType.BYTE, message.getFlagId());
		return builder.toGameFrame();
	}

}
