package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.MusicMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

/**
 * Client sends the music id of the current song to client
 * Packet: 250
 */
public class MusicDecoderMessage extends MessageDecoder<MusicMessage> {

	public MusicDecoderMessage() {
		super(250);
	}

	@Override
	public MusicMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int soundId = (int)reader.getSigned(DataType.INT);
		return new MusicMessage(soundId);
	}

}
