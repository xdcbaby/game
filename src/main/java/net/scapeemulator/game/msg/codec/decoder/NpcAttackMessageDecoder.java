package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.model.Npc;
import net.scapeemulator.game.model.World;
import net.scapeemulator.game.msg.NpcAttackMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class NpcAttackMessageDecoder extends MessageDecoder<NpcAttackMessage> {

	public NpcAttackMessageDecoder() {
		super(Opcode.NPC_ATTACK_OPTION);
	}

	@Override
	public NpcAttackMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int npcId = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
		int unknown = (int) reader.getUnsigned(DataType.BYTE);
        final Npc npc = World.getWorld().getNpcs().get(npcId);
		return new NpcAttackMessage(npc);
	}

}
