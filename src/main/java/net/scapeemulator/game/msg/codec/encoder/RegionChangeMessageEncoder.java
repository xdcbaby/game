package net.scapeemulator.game.msg.codec.encoder;

import io.netty.buffer.ByteBufAllocator;
import net.scapeemulator.game.model.Position;
import net.scapeemulator.game.msg.RegionChangeMessage;
import net.scapeemulator.game.msg.codec.MessageEncoder;
import net.scapeemulator.game.net.game.*;
import net.scapeemulator.game.net.game.GameFrame.Type;
import net.scapeemulator.game.util.LandscapeKeyTable;

public final class RegionChangeMessageEncoder extends MessageEncoder<RegionChangeMessage> {

	private final LandscapeKeyTable table;

	public RegionChangeMessageEncoder(LandscapeKeyTable table) {
		super(RegionChangeMessage.class);
		this.table = table;
	}

	@Override
	public GameFrame encode(ByteBufAllocator alloc, RegionChangeMessage message) {
		GameFrameBuilder builder = new GameFrameBuilder(alloc, 49, Type.VARIABLE_SHORT);

		Position position = message.getPosition();
        builder.put(DataType.BYTE, DataTransformation.SUBTRACT, 1);
		builder.put(DataType.SHORT, position.getCentralRegionY());

		boolean force = true;
		int centralMapX = position.getCentralRegionX() / 8;
		int centralMapY = position.getCentralRegionY() / 8;

		if ((centralMapX == 48 || centralMapX == 49) && centralMapY == 48)
			force = false;

		if (centralMapX == 48 && centralMapY == 148)
			force = false;

		for (int mapX = ((position.getCentralRegionX() - 6) / 8); mapX <= ((position.getCentralRegionX() + 6) / 8); mapX++) {
			for (int mapY = ((position.getCentralRegionY() - 6) / 8); mapY <= ((position.getCentralRegionY() + 6) / 8); mapY++) {
				if (force || (mapY != 49 && mapY != 149 && mapY != 147 && mapX != 50 && (mapX != 49 || mapY != 47))) {
					int[] keys = table.getKeys(mapX, mapY);
					for (int i = 0; i < 4; i++)
						builder.put(DataType.INT, DataOrder.LITTLE, keys[i]);
				}
			}
		}

		builder.put(DataType.BYTE, DataTransformation.NEGATE, position.getHeight());
		builder.put(DataType.SHORT, DataTransformation.ADD, position.getLocalX(position.getCentralRegionX()));
		builder.put(DataType.SHORT, position.getLocalY(position.getCentralRegionY()));
        builder.put(DataType.SHORT, position.getCentralRegionX());
		return builder.toGameFrame();
	}

}
