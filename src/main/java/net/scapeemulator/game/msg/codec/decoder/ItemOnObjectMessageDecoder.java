package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.ItemOnObjectMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class ItemOnObjectMessageDecoder extends MessageDecoder<ItemOnObjectMessage> {

	public ItemOnObjectMessageDecoder() {
		super(Opcode.ITEM_ON_OBJECT);
	}

	@Override
	public ItemOnObjectMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int slotId = (int) reader.getUnsigned(DataType.SHORT);
		int objectX = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int objectId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int interfaceId = (int) reader.getSigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int unknown = (int) reader.getUnsigned(DataType.BYTE, DataTransformation.NEGATE);
		int objectY = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		return new ItemOnObjectMessage(objectId, objectX, objectY, itemId, slotId, interfaceId);
	}

}
