package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.WalkMessage;
import net.scapeemulator.game.msg.WalkMessage.Step;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

import java.io.IOException;

public final class WalkMessageDecoder extends MessageDecoder<WalkMessage> {

	public WalkMessageDecoder(int opcode) {
		super(opcode);
	}

	@Override
	public WalkMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);

        int minimapFlagId = (int) reader.getUnsigned(DataType.BYTE, DataTransformation.SUBTRACT);
        boolean running = reader.getUnsigned(DataType.BYTE) == 1;
        int y = (int) reader.getUnsigned(DataType.SHORT);
        int x = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
        return new WalkMessage(new Step(x, y), running, minimapFlagId);
	}
}
