package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.ConfigMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

import java.io.IOException;

public final class ConfigMessageEncoder extends MessageEncoder<ConfigMessage> {

	public ConfigMessageEncoder() {
		super(ConfigMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ConfigMessage message) throws IOException {
		int id = message.getId();
		int value = message.getValue();

		if (value >= -128 && value <= 127) {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.CONFIG_MESSAGE);
			builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, id);
			builder.put(DataType.INT, DataOrder.INVERSED_MIDDLE, value);
			return builder.toGameFrame();
		} else {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, 226);
			builder.put(DataType.INT, DataOrder.INVERSED_MIDDLE, value);
			builder.put(DataType.SHORT, DataTransformation.ADD, id);
			return builder.toGameFrame();
		}
	}

}
