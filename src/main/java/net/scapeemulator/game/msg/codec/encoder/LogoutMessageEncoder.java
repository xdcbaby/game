package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.LogoutMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class LogoutMessageEncoder extends MessageEncoder<LogoutMessage> {

	public LogoutMessageEncoder() {
		super(LogoutMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, LogoutMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.LOGOUT);
		return builder.toGameFrame();
	}

}
