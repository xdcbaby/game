package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.ItemExamineMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class ItemExamineMessageDecoder extends MessageDecoder<ItemExamineMessage> {

	public ItemExamineMessageDecoder() {
		super(Opcode.EXAMINE_ITEM);
	}

	@Override
	public ItemExamineMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int id = (int) reader.getUnsigned(DataType.SHORT);
		return new ItemExamineMessage(id);
	}

}
