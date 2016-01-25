package net.scapeemulator.game.msg.codec.encoder;

import java.io.IOException;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.GroundItemMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class GroundItemMessageEncoder extends MessageEncoder<GroundItemMessage> {

	public GroundItemMessageEncoder() {
		super(GroundItemMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, GroundItemMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 33);
		
		builder.put(DataType.BYTE, message.getPositon().getX());
		builder.put(DataType.BYTE, message.getPositon().getY());
		builder.put(DataType.BYTE, message.getId());
		
		return builder.toGameFrame();
	}


}
