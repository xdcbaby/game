package net.scapeemulator.game.msg.codec.decoder;

import java.io.IOException;

import net.scapeemulator.game.msg.ObjectExamineMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;

public class ObjectExamineMessageDecoder extends MessageDecoder<ObjectExamineMessage> {
    
    public ObjectExamineMessageDecoder() {
        super(Opcode.EXAMINE_OBJECT);
    }

    @Override
    public ObjectExamineMessage decode(GameFrame frame) throws IOException {
        GameFrameReader reader = new GameFrameReader(frame);
        int id = (int) reader.getUnsigned(DataType.SHORT);
        return new ObjectExamineMessage(id);
    }
}
