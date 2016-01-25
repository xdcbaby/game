package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.PingMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.GameFrame;

import java.io.IOException;

public final class PingMessageDecoder extends MessageDecoder<PingMessage> {

	private static final PingMessage PING_MESSAGE = new PingMessage();

	public PingMessageDecoder() {
		super(Opcode.PING);
	}

	@Override
	public PingMessage decode(GameFrame frame) throws IOException {
		return PING_MESSAGE;
	}

}
