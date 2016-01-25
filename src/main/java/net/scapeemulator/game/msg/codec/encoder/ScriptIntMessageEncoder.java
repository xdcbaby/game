package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.ScriptIntMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.*;

import java.io.IOException;

public final class ScriptIntMessageEncoder extends MessageEncoder<ScriptIntMessage> {

	public ScriptIntMessageEncoder() {
		super(ScriptIntMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ScriptIntMessage message) throws IOException {
		int id = message.getId();
		int value = message.getValue();

		if (value >= -128 && value <= 127) {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.SCRIPT_INT);
			builder.put(DataType.SHORT, DataOrder.LITTLE, 0);
			builder.put(DataType.BYTE, DataTransformation.NEGATE, value);
			builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, id);
			return builder.toGameFrame();
		} else {
			GameFrameBuilder builder = new GameFrameBuilder(alloc, 69);
			builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, 0);
			builder.put(DataType.INT, value);
			builder.put(DataType.SHORT, DataTransformation.ADD, id);
			return builder.toGameFrame();
		}
	}

}
