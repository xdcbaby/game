package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.ClickItemMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class ClickItemMessageDecoder extends MessageDecoder<ClickItemMessage> {

	public ClickItemMessageDecoder() {
		super(Opcode.CLICK_ITEM);
	}

	@Override
	public ClickItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int slotId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE,  DataTransformation.ADD);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataTransformation.ADD);
		int flag = (int) reader.getSigned(DataType.INT);
		int interfaceId = (flag >> 16) & 0xFFFF;
		return new ClickItemMessage(itemId, interfaceId, slotId);
	}

}
