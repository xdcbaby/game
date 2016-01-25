package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.model.item.Item;
import net.scapeemulator.game.msg.InterfaceItemsMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrame.Type;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public final class InterfaceItemsMessageEncoder extends MessageEncoder<InterfaceItemsMessage> {

	public InterfaceItemsMessageEncoder() {
		super(InterfaceItemsMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, InterfaceItemsMessage message) {
		Item[] items = message.getItems();

		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.INTERFACE_ITEMS, Type.VARIABLE_SHORT);
		builder.put(DataType.INT, (message.getId() << 16) | message.getSlot());
		builder.put(DataType.SHORT, message.getType());
		builder.put(DataType.SHORT, items.length);

		for (Item item : items) {
			if (item == null) {
                builder.put(DataType.SHORT, 0);
				builder.put(DataType.BYTE, DataTransformation.NEGATE, 0);
			} else {
                builder.put(DataType.SHORT, item.getId() + 1);

				int amount = item.getAmount();
				if (amount >= 255) {
					builder.put(DataType.BYTE, DataTransformation.NEGATE, 255);
					builder.put(DataType.INT, amount);
				} else {
					builder.put(DataType.BYTE, DataTransformation.NEGATE, amount);
				}
			}
		}
		return builder.toGameFrame();
	}

}
