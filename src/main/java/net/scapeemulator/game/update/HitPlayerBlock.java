package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.PlayerUpdateMessage;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrameBuilder;

public class HitPlayerBlock extends PlayerBlock {
	private Player player;
	
	public HitPlayerBlock(Player player) {
		super(0x1);
		this.player = player;
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
	//	builder.put(DataType.BYTE, 1);
	//	builder.put(DataType.BYTE, 99);
	//	builder.put(DataType.BYTE, 10);
	}

}
