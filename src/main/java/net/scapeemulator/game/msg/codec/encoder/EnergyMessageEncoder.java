package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.msg.EnergyMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameBuilder;

import java.io.IOException;

public final class EnergyMessageEncoder extends MessageEncoder<EnergyMessage> {

	public EnergyMessageEncoder() {
		super(EnergyMessage.class);
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, EnergyMessage message) throws IOException {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, Opcode.ENERGY);
		builder.put(DataType.BYTE, message.getEnergy());
		return builder.toGameFrame();
	}

}
