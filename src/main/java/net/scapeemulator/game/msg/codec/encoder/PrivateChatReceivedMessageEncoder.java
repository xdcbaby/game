package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.PrivateChatReceivedMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrame.Type;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class PrivateChatReceivedMessageEncoder extends MessageEncoder<PrivateChatReceivedMessage> {

	public PrivateChatReceivedMessageEncoder() {
		super(PrivateChatReceivedMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, PrivateChatReceivedMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 0, Type.VARIABLE_BYTE);
		builder.put(DataType.LONG, message.getName());
		builder.put(DataType.SHORT, 0);
		int id = message.getMessageCounter();
		builder.putBytes(new byte[] { (byte) ((id << 16) & 0xFF), (byte) ((id << 8) & 0xFF), (byte) (id & 0xFF) });
		builder.put(DataType.BYTE, message.getRights());
		builder.putBytes(message.getPacked());
		return builder.toGameFrame();
	}
}