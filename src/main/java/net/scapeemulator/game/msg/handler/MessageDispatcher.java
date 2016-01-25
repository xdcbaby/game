package net.scapeemulator.game.msg.handler;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.scapeemulator.game.button.ButtonDispatcher;
import net.scapeemulator.game.command.CommandDispatcher;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.ButtonMessage;
import net.scapeemulator.game.msg.CameraMessage;
import net.scapeemulator.game.msg.ChatMessage;
import net.scapeemulator.game.msg.ClickChatBoxMessage;
import net.scapeemulator.game.msg.ClickItemMessage;
import net.scapeemulator.game.msg.ClickMessage;
import net.scapeemulator.game.msg.CommandMessage;
import net.scapeemulator.game.msg.DisplayMessage;
import net.scapeemulator.game.msg.DropItemMessage;
import net.scapeemulator.game.msg.EquipItemMessage;
import net.scapeemulator.game.msg.ExtendedButtonMessage;
import net.scapeemulator.game.msg.FlagsMessage;
import net.scapeemulator.game.msg.FocusMessage;
import net.scapeemulator.game.msg.IdleLogoutMessage;
import net.scapeemulator.game.msg.InterfaceClosedMessage;
import net.scapeemulator.game.msg.ItemExamineMessage;
import net.scapeemulator.game.msg.ItemOnItemMessage;
import net.scapeemulator.game.msg.ItemOnObjectMessage;
import net.scapeemulator.game.msg.ItemOnPlayerMessage;
import net.scapeemulator.game.msg.Message;
import net.scapeemulator.game.msg.MoveItemMessage;
import net.scapeemulator.game.msg.MusicMessage;
import net.scapeemulator.game.msg.NpcAttackMessage;
import net.scapeemulator.game.msg.NpcExamineMessage;
import net.scapeemulator.game.msg.ObjectExamineMessage;
import net.scapeemulator.game.msg.ObjectOptionOneMessage;
import net.scapeemulator.game.msg.OperateItemMessage;
import net.scapeemulator.game.msg.PingMessage;
import net.scapeemulator.game.msg.QuickChatMessage;
import net.scapeemulator.game.msg.RegionChangedMessage;
import net.scapeemulator.game.msg.RemoveItemMessage;
import net.scapeemulator.game.msg.SequenceNumberMessage;
import net.scapeemulator.game.msg.SwapItemsMessage;
import net.scapeemulator.game.msg.WalkMessage;
import net.scapeemulator.game.plugin.PluginContext;

public final class MessageDispatcher {

	private static final Logger logger = LoggerFactory.getLogger(MessageDispatcher.class);

	private final Map<Class<?>, MessageHandler<?>> handlers = new HashMap<>();
	private final ButtonDispatcher buttonDispatcher = new ButtonDispatcher();
    private final CommandDispatcher commandDispatcher = new CommandDispatcher();

	public MessageDispatcher() {
		bind(ClickChatBoxMessage.class, new ClickChatBoxMessageHandler());
		bind(ObjectExamineMessage.class, new ObjectExamineMessageHandler());
		bind(MusicMessage.class, new MusicDecoderMessageHandler());
		bind(ItemExamineMessage.class, new ItemExamineMessageHandler());
		bind(ItemOnPlayerMessage.class, new ItemOnPlayerMessageHandler());
		bind(ItemOnObjectMessage.class, new ItemOnObjectMessageHandler());
		bind(DropItemMessage.class, new DropItemMessageHandler());
		bind(OperateItemMessage.class, new OperateItemMessageHandler());
		bind(ClickItemMessage.class, new ClickItemMessageHandler());
		bind(QuickChatMessage.class, new QuickChatMessageHandler());
		bind(MoveItemMessage.class, new MoveItemMessageHandler());
		bind(ItemOnItemMessage.class, new ItemOnItemMessageHandler());
		bind(NpcExamineMessage.class, new NPCExamineMessageHandler());
		bind(NpcAttackMessage.class, new NpcAttackMessageHandler());
		bind(PingMessage.class, new PingMessageHandler());
		bind(IdleLogoutMessage.class, new IdleLogoutMessageHandler());
		bind(ButtonMessage.class, new ButtonMessageHandler(buttonDispatcher));
		bind(WalkMessage.class, new WalkMessageHandler());
		bind(ChatMessage.class, new ChatMessageHandler());
		bind(CommandMessage.class, new CommandMessageHandler(commandDispatcher));
		bind(ExtendedButtonMessage.class, new ExtendedButtonMessageHandler(buttonDispatcher));
		bind(SwapItemsMessage.class, new SwapItemsMessageHandler());
		bind(EquipItemMessage.class, new EquipItemMessageHandler());
		bind(DisplayMessage.class, new DisplayMessageHandler());
		bind(RemoveItemMessage.class, new RemoveItemMessageHandler());
		bind(RegionChangedMessage.class, new RegionChangedMessageHandler());
		bind(ClickMessage.class, new ClickMessageHandler());
		bind(FocusMessage.class, new FocusMessageHandler());
		bind(CameraMessage.class, new CameraMessageHandler());
		bind(FlagsMessage.class, new FlagsMessageHandler());
		bind(SequenceNumberMessage.class, new SequenceNumberMessageHandler());
		bind(InterfaceClosedMessage.class, new InterfaceClosedMessageHandler());
        bind(ObjectOptionOneMessage.class, new ObjectOptionOneHandler());
	}

	public <T extends Message> void bind(Class<T> clazz, MessageHandler<T> handler) {
		handlers.put(clazz, handler);
	}

	@SuppressWarnings("unchecked")
	public void dispatch(Player player, Message message) {
		MessageHandler<Message> handler = (MessageHandler<Message>) handlers.get(message.getClass());
		if (handler != null) {
			try {
				handler.handle(player, message);
			} catch (Throwable t) {
				logger.warn("Error processing packet.", t);
			}
		} else {
			logger.warn("Cannot dispatch message (no handler): " + message.getClass().getName() + ".");
		}
	}

    public void decorateDispatchers(PluginContext context) {
        context.decorateButtonDispatcher(buttonDispatcher);
        context.decorateCommandDispatcher(commandDispatcher);
    }
}
