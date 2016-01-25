package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.InterfaceCloseMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class InterfaceCloseMessageEncoder extends MessageEncoder<InterfaceCloseMessage> {

	public InterfaceCloseMessageEncoder() {
		super(InterfaceCloseMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceCloseMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_CLOSE);
		builder.put(DataType.SHORT, 0);
		//builder.put(DataType.SHORT, message.getId());
		//builder.put(DataType.SHORT, message.getSlot());
		builder.put(DataType.INT, DataOrder.INVERSED_MIDDLE, (message.getId() << 16) | message.getSlot());
		return builder.toGameFrame();
	}

}
