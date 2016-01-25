package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.ClickMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.*;

import java.io.IOException;

public final class ClickMessageDecoder extends MessageDecoder<ClickMessage> {

	public ClickMessageDecoder() {
		super(Opcode.CLICK);
	}

	@Override
	public ClickMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
        int pos = (int) reader.getUnsigned(DataType.INT, DataOrder.INVERSED_MIDDLE);
		int flags = (int) reader.getUnsigned(DataType.SHORT, DataTransformation.ADD);

		int time = flags & 0x7fff;
		boolean rightClick = ((flags >> 15) & 0x1) != 0;

		int x = pos & 0xffff;
		int y = (pos >> 16) & 0xffff;

		return new ClickMessage(time, x, y, rightClick);
	}

}
