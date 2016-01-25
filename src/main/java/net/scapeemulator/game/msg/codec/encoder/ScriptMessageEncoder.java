package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.ScriptMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrame.Type;
import net.scapeemulator.game.net.game.GameFrameBuilder;

import java.io.IOException;

public final class ScriptMessageEncoder extends MessageEncoder<ScriptMessage> {

	public ScriptMessageEncoder() {
		super(ScriptMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, ScriptMessage message) throws IOException {
		int id = message.getId();
		String types = message.getTypes();
		Object[] parameters = message.getParameters();

		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.SCRIPT_MESSAGE, Type.VARIABLE_SHORT); //70?
		builder.put(DataType.SHORT, 0);
		builder.putString(types);

		for (int i = types.length() - 1; i >= 0; i--) {
			if (types.charAt(i) == 's') {
				builder.putString((String) parameters[i]);
			} else {
				builder.put(DataType.INT, ((Number) parameters[i]).intValue());
			}
		}

		builder.put(DataType.INT, id);
		return builder.toGameFrame();
	}

}
