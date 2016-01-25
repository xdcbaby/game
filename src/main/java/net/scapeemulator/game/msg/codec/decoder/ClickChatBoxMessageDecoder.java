package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.ClickChatBoxMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class ClickChatBoxMessageDecoder extends MessageDecoder<ClickChatBoxMessage> {

	public ClickChatBoxMessageDecoder(int opcode) {
		super(opcode);
	}

	@Override
	public ClickChatBoxMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int localPlayerId = (int) reader.getUnsigned(DataType.SHORT);
		int type = (int) reader.getUnsigned(DataType.BYTE);
		return new ClickChatBoxMessage(type, localPlayerId);
	}

}
