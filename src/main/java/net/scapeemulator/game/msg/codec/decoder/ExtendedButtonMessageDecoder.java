package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.ExtendedButtonMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

import java.io.IOException;

public final class ExtendedButtonMessageDecoder extends MessageDecoder<ExtendedButtonMessage> {

	public ExtendedButtonMessageDecoder() {
		super(Opcode.EXTENDED_BUTTON);
	}

	@Override
	public ExtendedButtonMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int button = (int) reader.getSigned(DataType.INT);
		int id = (button >> 16) & 0xFFFF;
		int slot = button & 0xFFFF;
		int parameter = (int) reader.getUnsigned(DataType.SHORT);
		
		System.out.println("id:" + id + " slot:" + slot + " params:" + parameter);
		
		return new ExtendedButtonMessage(id, slot, parameter);
	}

}
