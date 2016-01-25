package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.FocusMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

import java.io.IOException;

public final class FocusMessageDecoder extends MessageDecoder<FocusMessage> {

	private static final FocusMessage FOCUSED_MESSAGE = new FocusMessage(true);
	private static final FocusMessage NOT_FOCUSED_MESSAGE = new FocusMessage(false);

	public FocusMessageDecoder() {
		super(Opcode.FOCUS_MESSAGE);
	}

	@Override
	public FocusMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int focused = (int) reader.getUnsigned(DataType.BYTE);
		return focused != 0 ? FOCUSED_MESSAGE : NOT_FOCUSED_MESSAGE;
	}

}
