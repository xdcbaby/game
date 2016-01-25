package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.msg.OperateItemMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class OperateItemMessageDecoder extends MessageDecoder<OperateItemMessage> {

	public OperateItemMessageDecoder() {
		super(Opcode.OPERATE_ITEM);
	}

	@Override
	public OperateItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		
		int slot = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int unknown = (int) reader.getSigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		
		return new OperateItemMessage(slot, itemId);
	}

}
