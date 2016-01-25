package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.ButtonMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public final class ButtonMessageDecoder extends MessageDecoder<ButtonMessage> {

	public ButtonMessageDecoder() {
		super(Opcode.BUTTON);
	}

	@Override
	public ButtonMessage decode(GameFrame frame) {
		GameFrameReader reader = new GameFrameReader(frame);
		int button = (int) reader.getSigned(DataType.INT);
		int id = (button >> 16) & 0xFFFF;
		int slot = button & 0xFFFF;
		return new ButtonMessage(id, slot);
	}

}
