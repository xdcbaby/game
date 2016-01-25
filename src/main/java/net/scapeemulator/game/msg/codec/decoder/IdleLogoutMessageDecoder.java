package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.IdleLogoutMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.GameFrame;

import java.io.IOException;

public final class IdleLogoutMessageDecoder extends MessageDecoder<IdleLogoutMessage> {

	private static final IdleLogoutMessage IDLE_LOGOUT_MESSAGE = new IdleLogoutMessage();

	public IdleLogoutMessageDecoder() {
		super(Opcode.IDLE_LOGOUT);
	}

	@Override
	public IdleLogoutMessage decode(GameFrame frame) throws IOException {
		return IDLE_LOGOUT_MESSAGE;
	}

}
