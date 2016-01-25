package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.ObjectOptionOneMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.*;

public final class ObjectOptionOneMessageDecoder extends MessageDecoder<ObjectOptionOneMessage> {

	public ObjectOptionOneMessageDecoder() {
		super(92);
	}

	@Override
	public ObjectOptionOneMessage decode(GameFrame frame) {
		GameFrameReader reader = new GameFrameReader(frame);

        boolean forceRun = reader.getUnsigned(DataType.BYTE, DataTransformation.SUBTRACT) == 1;
        int id = (int) reader.getSigned(DataType.SHORT, DataOrder.LITTLE);
		int x = (int) reader.getSigned(DataType.SHORT, DataTransformation.ADD);
		int y = (int) reader.getSigned(DataType.SHORT, DataTransformation.ADD);

		return new ObjectOptionOneMessage(x, y, id, forceRun);
	}

}
