package net.scapeemulator.game.update;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.ChatMessage;
import net.scapeemulator.game.msg.PlayerUpdateMessage;
import net.scapeemulator.game.net.game.DataOrder;
import net.scapeemulator.game.net.game.DataTransformation;
import net.scapeemulator.game.net.game.DataType;
import net.scapeemulator.game.net.game.GameFrameBuilder;
import net.scapeemulator.util.ChatUtils;

import java.util.Arrays;

public final class ChatPlayerBlock extends PlayerBlock {

	private final ChatMessage chatMessage;
	private final int rights;

	public ChatPlayerBlock(Player player) {
		super(0x20);
		this.chatMessage = player.getChatMessage();
		this.rights = player.getRights();
	}

	@Override
	public void encode(PlayerUpdateMessage message, GameFrameBuilder builder) {
		byte[] bytes = new byte[256];
		int size = ChatUtils.pack(chatMessage.getText(), bytes);

		builder.put(DataType.SHORT, DataTransformation.ADD, (chatMessage.getColor() << 8) | chatMessage.getEffects());
		builder.put(DataType.BYTE, DataTransformation.ADD, rights);
		builder.put(DataType.BYTE, DataTransformation.SUBTRACT, size);
		builder.putBytesReverse(Arrays.copyOf(bytes, size));
	}

}
