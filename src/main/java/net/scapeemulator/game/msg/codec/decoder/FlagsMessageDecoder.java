package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.FlagsMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

import java.io.IOException;

public final class FlagsMessageDecoder extends MessageDecoder<FlagsMessage> {

	public FlagsMessageDecoder() {
		super(98);
	}

	@Override
	public FlagsMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int flags = (int) reader.getUnsigned(DataType.INT);
		return new FlagsMessage(flags);
	}

}
