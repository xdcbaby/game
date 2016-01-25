package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.RemoveItemMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.*;

import java.io.IOException;

public final class RemoveItemMessageDecoder extends MessageDecoder<RemoveItemMessage> {

	public RemoveItemMessageDecoder() {
		super(Opcode.REMOVE_ITEM);
	}

	@Override
	public RemoveItemMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int itemSlot = (int) reader.getUnsigned(DataType.SHORT) / 256;
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int flag = (int) reader.getSigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int id = (flag >> 16) & 0xFFFF;
		int slot = (flag & 0xFFFF);
		return new RemoveItemMessage(slot, id, itemSlot, itemId);
	}

}
