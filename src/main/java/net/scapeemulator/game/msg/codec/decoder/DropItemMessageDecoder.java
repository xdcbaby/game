package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.DropItemMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class DropItemMessageDecoder extends MessageDecoder<DropItemMessage> {

	public DropItemMessageDecoder() {
		super(Opcode.DROP_ITEM);
	}

	@Override
	public DropItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int interfaceId = (int) reader.getUnsigned(DataType.INT, DataOrder.INVERSED_MIDDLE) / 256;
		int slotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		return new DropItemMessage(itemId, interfaceId, slotId);
	}

}
