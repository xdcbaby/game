package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.MoveItemMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class MoveItemMessageDecoder extends MessageDecoder<MoveItemMessage> {

	public MoveItemMessageDecoder() {
		super(Opcode.MOVE_ITEM);
	}

	@Override
	public MoveItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int newSlotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int unknown = (int) reader.getUnsigned(DataType.BYTE);
		int flag = (int) reader.getUnsigned(DataType.INT); 
		int interfaceId = ((flag >> 16) & 0xFFFF) / 256;
		int oldSlotId = (int) reader.getUnsigned(DataType.SHORT) / 256;
		return new MoveItemMessage(interfaceId, oldSlotId, newSlotId);
	}

}
