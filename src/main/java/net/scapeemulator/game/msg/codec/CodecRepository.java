package net.scapeemulator.game.msg.codec;

import java.util.HashMap;
import java.util.Map;

import net.scapeemulator.game.msg.Message;
import net.scapeemulator.game.msg.codec.decoder.ButtonMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.CameraMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ChatMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ClickChatBoxMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ClickItemMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ClickMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.CommandMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.DisplayMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.DropItemMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.EquipItemMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ExtendedButtonMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.FlagsMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.FocusMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.IdleLogoutMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.InterfaceClosedMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ItemExamineMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ItemOnItemMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ItemOnObjectMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ItemOnPlayerMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.MoveItemMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.MusicDecoderMessage;
import net.scapeemulator.game.msg.codec.decoder.NpcAttackMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.NpcExamineMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ObjectExamineMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ObjectOptionOneMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.ObjectOptionTwoMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.Opcode;
import net.scapeemulator.game.msg.codec.decoder.OperateItemMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.PingMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.QuickChatMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.RegionChangedMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.RemoveItemMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.SequenceNumberMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.SwapItemsMessageDecoder;
import net.scapeemulator.game.msg.codec.decoder.WalkMessageDecoder;
import net.scapeemulator.game.msg.codec.encoder.ConfigMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.EnergyMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.GroundItemMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceCloseMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceItemsMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceOpenMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceResetItemsMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceRootMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceSlottedItemsMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceTextMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.InterfaceVisibleMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.LogoutMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.MusicMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.NpcAnimationMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.NpcUpdateMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.PlayerUpdateMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.PrivateChatReceivedMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.RegionChangeMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.ResetMinimapFlagMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.ScriptIntMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.ScriptMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.ScriptStringMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.ServerMessageEncoder;
import net.scapeemulator.game.msg.codec.encoder.SkillMessageEncoder;
import net.scapeemulator.game.util.LandscapeKeyTable;

public final class CodecRepository {

	private final MessageDecoder<?>[] inCodecs = new MessageDecoder<?>[256];
	private final Map<Class<?>, MessageEncoder<?>> outCodecs = new HashMap<>();

	public CodecRepository(LandscapeKeyTable table) {
		/* decoders */
		bind(new PingMessageDecoder());
		bind(new IdleLogoutMessageDecoder());
		bind(new ButtonMessageDecoder());
		bind(new WalkMessageDecoder(Opcode.WALK));
		bind(new WalkMessageDecoder(207));
		bind(new WalkMessageDecoder(215)); //TODO: check
		bind(new ChatMessageDecoder());
		bind(new CommandMessageDecoder());
		bind(new ExtendedButtonMessageDecoder());
		bind(new SwapItemsMessageDecoder());
		bind(new EquipItemMessageDecoder());
		bind(new DisplayMessageDecoder());
		bind(new RemoveItemMessageDecoder());
		bind(new RegionChangedMessageDecoder());
		bind(new ClickMessageDecoder());
		bind(new FocusMessageDecoder());
		bind(new CameraMessageDecoder());
		bind(new FlagsMessageDecoder());
		bind(new SequenceNumberMessageDecoder());
		bind(new InterfaceClosedMessageDecoder());
		bind(new ObjectOptionOneMessageDecoder());
		bind(new ObjectOptionTwoMessageDecoder());
		bind(new ClickItemMessageDecoder());
		bind(new NpcAttackMessageDecoder());
		bind(new NpcExamineMessageDecoder());
		bind(new ItemOnItemMessageDecoder());
		bind(new MoveItemMessageDecoder());
		bind(new QuickChatMessageDecoder());
		bind(new OperateItemMessageDecoder());
		bind(new DropItemMessageDecoder());
		bind(new ItemOnObjectMessageDecoder());
		bind(new ItemOnPlayerMessageDecoder());
		bind(new ItemExamineMessageDecoder());
		bind(new MusicDecoderMessage());
		bind(new ObjectExamineMessageDecoder());
		bind(new ClickChatBoxMessageDecoder(Opcode.CLICK_CHAT_BOX));
	//	bind(new ClickChatBoxMessageDecoder(156));
		
		/* encoders */
		bind(new NpcAnimationMessageEncoder());
		bind(new PrivateChatReceivedMessageEncoder());
		bind(new GroundItemMessageEncoder());
		bind(new RegionChangeMessageEncoder(table));
		bind(new InterfaceRootMessageEncoder());
		bind(new InterfaceOpenMessageEncoder());
		bind(new InterfaceCloseMessageEncoder());
		bind(new InterfaceVisibleMessageEncoder());
		bind(new InterfaceTextMessageEncoder());
		bind(new ServerMessageEncoder());
		bind(new LogoutMessageEncoder());
		bind(new PlayerUpdateMessageEncoder());
		bind(new SkillMessageEncoder());
		bind(new EnergyMessageEncoder());
		bind(new InterfaceItemsMessageEncoder());
		bind(new MusicMessageEncoder());
		bind(new InterfaceSlottedItemsMessageEncoder());
		bind(new InterfaceResetItemsMessageEncoder());
		bind(new ResetMinimapFlagMessageEncoder());
		bind(new ConfigMessageEncoder());
		bind(new ScriptMessageEncoder());
		bind(new NpcUpdateMessageEncoder());
		bind(new ScriptStringMessageEncoder());
		bind(new ScriptIntMessageEncoder());
	}

	public MessageDecoder<?> get(int opcode) {
		return inCodecs[opcode];
	}

	@SuppressWarnings("unchecked")
	public <T extends Message> MessageEncoder<T> get(Class<T> clazz) {
		return (MessageEncoder<T>) outCodecs.get(clazz);
	}

	public void bind(MessageDecoder<?> decoder) {
		inCodecs[decoder.opcode] = decoder;
	}

	public void bind(MessageEncoder<?> encoder) {
		outCodecs.put(encoder.clazz, encoder);
	}

}
