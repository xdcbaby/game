package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.CameraMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.*;

import java.io.IOException;

public final class CameraMessageDecoder extends MessageDecoder<CameraMessage> {

	public CameraMessageDecoder() {
		super(Opcode.CAMERA);
	}

	@Override
	public CameraMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int pitch = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int yaw = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		return new CameraMessage(yaw, pitch);
	}

}
