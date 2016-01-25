package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.NpcExamineMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public final class NpcExamineMessageDecoder extends MessageDecoder<NpcExamineMessage> {
    
    public NpcExamineMessageDecoder() {
        super(Opcode.NPC_EXAMINE);
    }

    @Override
    public NpcExamineMessage decode(GameFrame frame) throws IOException {
        GameFrameReader reader = new GameFrameReader(frame);
        int id = (int) reader.getUnsigned(DataType.SHORT, DataOrder.LITTLE);
        return new NpcExamineMessage(id);
    }
}
