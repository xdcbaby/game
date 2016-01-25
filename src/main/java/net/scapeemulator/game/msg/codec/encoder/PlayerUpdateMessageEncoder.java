package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.PlayerUpdateMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;
import net.scapeemulator.game.update.PlayerDescriptor;

import java.io.IOException;

public final class PlayerUpdateMessageEncoder extends MessageEncoder<PlayerUpdateMessage> {

	public PlayerUpdateMessageEncoder() {
		super(PlayerUpdateMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, PlayerUpdateMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 25, GameFrame.Type.VARIABLE_SHORT);
		GameFrameBuilder blockBuilder = new GameFrameBuilder(alloc);
		builder.switchToBitAccess();

		message.getSelfDescriptor().encode(message, builder, blockBuilder);
		builder.putBits(8, message.getLocalPlayerCount());

		for (PlayerDescriptor descriptor : message.getDescriptors())
			descriptor.encode(message, builder, blockBuilder);

		if (blockBuilder.getLength() > 0) {
			builder.putBits(11, 2047);
			builder.switchToByteAccess();
			builder.putRawBuilder(blockBuilder);
		} else {
			builder.switchToByteAccess();
		}

		return builder.toGameFrame();
	}

}
