package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.QuickChatMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class QuickChatMessageDecoder extends MessageDecoder<QuickChatMessage> {

	public QuickChatMessageDecoder() {
		super(Opcode.QUICK_CHAT);
	}

	@Override
	public QuickChatMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int id = (int) reader.getUnsigned(DataType.BYTE);
		int i2 = (int) reader.getUnsigned(DataType.BYTE);
		int i3 = (int) reader.getUnsigned(DataType.SHORT);
		
		System.out.println("i1: " + id);
		System.out.println("i2: " + i2);
		System.out.println("i3: " + i3);
		
		return new QuickChatMessage();
	}

}
