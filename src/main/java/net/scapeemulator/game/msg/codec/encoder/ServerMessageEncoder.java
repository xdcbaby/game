package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.ServerMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrame.Type;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class ServerMessageEncoder extends MessageEncoder<ServerMessage> {

	public ServerMessageEncoder() {
		super(ServerMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ServerMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 59, Type.VARIABLE_BYTE);
		builder.putString(message.getText());
		return builder.toGameFrame();
	}

}
