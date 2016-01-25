package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.InterfaceTextMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.*;
import net.scapeemulator.game.net.game.GameFrame.Type;

public final class InterfaceTextMessageEncoder extends MessageEncoder<InterfaceTextMessage> {

	public InterfaceTextMessageEncoder() {
		super(InterfaceTextMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceTextMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_TEXT, Type.VARIABLE_SHORT);
        builder.put(DataType.SHORT, DataOrder.LITTLE, 0);
        builder.putString(message.getText());
		builder.put(DataType.INT, DataOrder.MIDDLE, (message.getId() << 16) | message.getSlot());
		return builder.toGameFrame();
	}

}
