package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.ItemOnPlayerMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class ItemOnPlayerMessageDecoder extends MessageDecoder<ItemOnPlayerMessage> {

	public ItemOnPlayerMessageDecoder() {
		super(Opcode.ITEM_ON_PLAYER);
	}



	@Override
	public ItemOnPlayerMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int localPlayerId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		int unknown1 = (int) reader.getSigned(DataType.INT);
		int slotId = (int) reader.getUnsigned(DataType.SHORT, DataTransformation.ADD);
		int unknown2 = (int) reader.getUnsigned(DataType.BYTE, DataTransformation.NEGATE);
		int itemId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE, DataTransformation.ADD);
		
		
	/*	System.out.println("localPlayerId:" + localPlayerId);
		System.out.println("unknown1:" + unknown1);
		System.out.println("slotId:" + slotId);
		System.out.println("unknown2:" + unknown2);
		System.out.println("itemId:" + itemId); */
		
		return new ItemOnPlayerMessage(localPlayerId, slotId, itemId);
	}}
