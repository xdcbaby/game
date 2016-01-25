package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.DisplayMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

import java.io.IOException;

public final class DisplayMessageDecoder extends MessageDecoder<DisplayMessage> {

	public DisplayMessageDecoder() {
		super(Opcode.DISPLAY_MESSAGE);
	}

	@Override
	public DisplayMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int mode = (int) reader.getUnsigned(DataType.BYTE);
		int width = (int) reader.getUnsigned(DataType.SHORT);
		int height = (int) reader.getUnsigned(DataType.SHORT);
		reader.getUnsigned(DataType.BYTE); // TODO identify this
		return new DisplayMessage(mode, width, height);
	}

}
