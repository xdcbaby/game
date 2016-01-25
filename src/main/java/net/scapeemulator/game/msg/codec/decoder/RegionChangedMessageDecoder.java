package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.RegionChangedMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.GameFrame;

import java.io.IOException;

public final class RegionChangedMessageDecoder extends MessageDecoder<RegionChangedMessage> {

	private static final RegionChangedMessage REGION_CHANGED_MESSAGE = new RegionChangedMessage();

	public RegionChangedMessageDecoder() {
		super(113);
	}

	@Override
	public RegionChangedMessage decode(GameFrame frame) throws IOException {
		return REGION_CHANGED_MESSAGE;
	}

}
