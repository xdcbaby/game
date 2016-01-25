package net.scapeemulator.game.msg.codec.decoder;

import net.scapeemulator.game.msg.ChatMessage;
import net.scapeemulator.game.msg.codec.MessageDecoder;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrame;
import net.scapeemulator.game.net.game.GameFrameReader;
import net.scapeemulator.util.ChatUtils;

import java.io.IOException;

public final class ChatMessageDecoder extends MessageDecoder<ChatMessage> {

	public ChatMessageDecoder() {
		super(Opcode.CHAT_OPCODE);
	}

	@Override
	public ChatMessage decode(GameFrame frame) throws IOException {
		GameFrameReader reader = new GameFrameReader(frame);
		int size = reader.getLength() - 2;

		int color = (int) reader.getUnsigned(DataType.BYTE);
		int effects = (int) reader.getUnsigned(DataType.BYTE);

		byte[] bytes = new byte[size];
		reader.getBytes(bytes);
		String text = ChatUtils.unpack(bytes);

		return new ChatMessage(color, effects, text);
	}

}
