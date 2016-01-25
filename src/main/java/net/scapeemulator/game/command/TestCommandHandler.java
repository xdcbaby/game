package net.scapeemulator.game.command;

import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.msg.ServerMessage;

public class TestCommandHandler extends CommandHandler {
	byte[] b = { 1, 2, 3, 5 };

	public TestCommandHandler() {
		super("test");
	}

	@Override
	public void handle(Player player, String[] arguments) {
		player.send(new ServerMessage("<shad=2><trans=50><col=493>Hello World"));
		//player.getInterfaceSet().openWindow(Interface.MUSIC); // 334
		//for (int i = 0; i < 5; i++) {
		//	player.send(new InterfaceTextMessage(Interface.MUSIC, i, "" + i));
		//}

		// player.getInterfaceSet().openFullscreen(Interface.WORLD_MAP);
		// player.send(new ScriptIntMessage(622,
		// player.getPosition().toPackedInt())); // map center
		// player.send(new ScriptIntMessage(674,
		// player.getPosition().toPackedInt())); // player position

		/*
		 * (if (arguments.length != 1) { player.getTrade().sendRequest(player);
		 * player.getTrade().openTrade(); } else {
		 * player.getTrade().sendRequest(player); player.getTrade().openTrade();
		 * player.getTrade().flashIcon(player, 1); }
		 */

		// player.send(new InterfaceOpenMessage(752, 1, 94, 0));

		// player.getActionSender().sendInventoryInterface(336);
		// player.getInterfaceSet().openTab(Tab.INVENTORY, 336);
		// player.send(new InterfaceItemsMessage(336, 0, 93,
		// player.getInventory().toArray()));

		// player.send(new InterfaceVisibleMessage(745, 6, false));
		// player.send(new InterfaceVisibleMessage(745, 3, true));
		// actionSender.sendInterfaceConfig(745, 6, false);
		// actionSender.sendInterfaceConfig(745, 3, true);

		/*
		 * if (arguments.length == 1) { Player other =
		 * World.getWorld().getPlayerByName(arguments[0]);
		 * player.getTrade().sendRequest(other); } else if (arguments.length ==
		 * 2) { player.getTrade().addItem(new
		 * Item(Integer.parseInt(arguments[0]))); }
		 */

		// player.getTrade().acceptRequest(player);
		// player.getTrade().setTrading(true);
		// player.getTrade().setState(State.PRE_ACCEPT);
		// player.getInterfaceSet().openWindow(334); //334
		// for (int i = 0; i < 48; i++) {
		// player.send(new InterfaceTextMessage(334, i, "" + i));
		// }

		// player.getInterfaceSet().openOverlay(745);
		// player.send(new ConfigMessage(300, 100));
		// player.send(new ConfigMessage(301, 0));

		// int id = Integer.parseInt(arguments[0]);
		// int anim = Integer.parseInt(arguments[1]);
		// int vol = Integer.parseInt(arguments[2]);

		// player.send(new NpcAnimationMessage(id, anim, 0));

		// player.playAnimation(new Animation(436, 0));

		// player.send(new PrivateChatReceivedMessage(123455674, (byte)0, b,
		// 0));
		// player.getInterfaceSet().openBank();
		// player.getInterfaceSet().openTrade();
		// player.getInterfaceSet().setDisplayMode(DisplayMode.RESIZABLE);

		// player.getInterfaceSet().openWindow(312);

		// System.out.println("Sending sound");
		// player.getSession().send(new MusicMessage(id));

		// player.getInterfaceSet().openWindow(Integer.parseInt(arguments[0]));
	}

}
