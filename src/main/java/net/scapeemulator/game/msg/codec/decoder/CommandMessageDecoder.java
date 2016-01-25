package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.CommandMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

import java.io.IOException;

public final class CommandMessageDecoder extends MessageDecoder<CommandMessage> {

	public CommandMessageDecoder() {
		super(Opcode.COMMAND);
	}

	@Override
	public CommandMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		String command = reader.getString();
		return new CommandMessage(command);
	}

}
