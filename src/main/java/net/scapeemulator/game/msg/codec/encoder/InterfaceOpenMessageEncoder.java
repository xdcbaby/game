package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.InterfaceOpenMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.*;

public final class InterfaceOpenMessageEncoder extends MessageEncoder<InterfaceOpenMessage> {

	public InterfaceOpenMessageEncoder() {
		super(InterfaceOpenMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceOpenMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 35);
		builder.put(DataType.BYTE, DataTransformation.NEGATE, message.getType());
        builder.put(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD, 0);
		builder.put(DataType.INT, DataOrder.LITTLE, (message.getId() << 16) | message.getSlot());
		builder.put(DataType.SHORT, DataOrder.LITTLE, message.getChildId());
		return builder.toGameFrame();
	}

}
