package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.InterfaceRootMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.*;
import net.scapeemulator.game.net.game.GameFrame.Type;

public final class InterfaceRootMessageEncoder extends MessageEncoder<InterfaceRootMessage> {

	public InterfaceRootMessageEncoder() {
		super(InterfaceRootMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceRootMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_ROOT_MESSAGE, Type.FIXED);
		builder.put(DataType.SHORT, message.getId());
        builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, 0);
		builder.put(DataType.BYTE, DataTransformation.SUBTRACT, 0);
		return builder.toGameFrame();
	}

}
