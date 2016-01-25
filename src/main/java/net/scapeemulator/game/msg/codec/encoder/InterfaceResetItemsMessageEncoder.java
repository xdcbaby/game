package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.InterfaceResetItemsMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

import java.io.IOException;

public final class InterfaceResetItemsMessageEncoder extends MessageEncoder<InterfaceResetItemsMessage> {

	public InterfaceResetItemsMessageEncoder() {
		super(InterfaceResetItemsMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceResetItemsMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_RESET_ITEMS);
		builder.put(DataType.INT, DataOrder.MIDDLE, (message.getId() << 16) | message.getSlot());
		return builder.toGameFrame();
	}

}
