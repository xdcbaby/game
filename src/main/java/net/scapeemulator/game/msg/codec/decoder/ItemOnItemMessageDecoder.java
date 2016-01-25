package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.ItemOnItemMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class ItemOnItemMessageDecoder extends MessageDecoder<ItemOnItemMessage> {
	
	public ItemOnItemMessageDecoder() {
		super(Opcode.ITEM_ON_ITEM);
	}

	@Override
	public ItemOnItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int itemUsed = (int) reader.getUnsigned(DataType.SHORT);
		int inter = (int) reader.getUnsigned(DataType.INT);
		int interfaceId = ((inter >> 16) & 0xFFFF) / 256;
		int usedSlot = (int) reader.getUnsigned(DataType.SHORT);
		int usedWithSlot = (int) reader.getUnsigned(DataType.SHORT);
		int usedOn = (int) reader.getUnsigned(DataType.SHORT, DataTransformation.ADD);
		int flag = (int) reader.getUnsigned(DataType.INT);
		int unused = ((flag >> 16) & 0xFFFF) / 256;
		return new ItemOnItemMessage(interfaceId, itemUsed, usedSlot, usedOn, usedWithSlot);
	}

}
