package net.scapeemulator.game.button;

import net.scapeemulator.game.action.impl.EmoteAction;
import net.scapeemulator.game.model.Interface;
import net.scapeemulator.game.model.Player;

public final class EmoteButtonHandler extends ButtonHandler {

	public EmoteButtonHandler() {
		super(Interface.EMOTES);
	}

	@Override
	public void handle(Player player, int slot, int parameter) {
		if (slot == 2) {
			player.startAction(new EmoteAction(player, EmoteAction.YES, 4));
		} else if (slot == 3) {
			player.startAction(new EmoteAction(player, EmoteAction.NO, 4));
		} else if (slot == 4) {
			player.startAction(new EmoteAction(player, EmoteAction.BOW, 4));
		} else if (slot == 5) {
			player.startAction(new EmoteAction(player, EmoteAction.ANGRY, 4));
		} else if (slot == 6) {
			player.startAction(new EmoteAction(player, EmoteAction.THINK, 4));
		} else if (slot == 7) {
			player.startAction(new EmoteAction(player, EmoteAction.WAVE, 4));
		} else if (slot == 8) {
			player.startAction(new EmoteAction(player, EmoteAction.SHRUG, 3));
		} else if (slot == 9) {
			player.startAction(new EmoteAction(player, EmoteAction.CHEER, 5));
		} else if (slot == 10) {
			player.startAction(new EmoteAction(player, EmoteAction.BECKON, 3));
		} else if (slot == 12) {
			player.startAction(new EmoteAction(player, EmoteAction.LAUGH, 4));
		} else if (slot == 11) {
			player.startAction(new EmoteAction(player, EmoteAction.JUMP_FOR_JOY, 3));
		} else if (slot == 13) {
			player.startAction(new EmoteAction(player, EmoteAction.YAWN, 6));
		} else if (slot == 14) {
			player.startAction(new EmoteAction(player, EmoteAction.DANCE, 8));
		} else if (slot == 15) {
			player.startAction(new EmoteAction(player, EmoteAction.JIG, 7));
		} else if (slot == 16) {
			player.startAction(new EmoteAction(player, EmoteAction.SPIN, 3));
		} else if (slot == 17) {
			player.startAction(new EmoteAction(player, EmoteAction.HEADBANG, 7));
		} else if (slot == 18) {
			player.startAction(new EmoteAction(player, EmoteAction.CRY, 5));
		} else if (slot == 19) {
			player.startAction(new EmoteAction(player, EmoteAction.BLOW_KISS, EmoteAction.BLOW_KISS_GFX, 11));
		} else if (slot == 20) {
			player.startAction(new EmoteAction(player, EmoteAction.PANIC, 4));
		} else if (slot == 21) {
			player.startAction(new EmoteAction(player, EmoteAction.RASPBERRY, 5));
		} else if (slot == 22) {
			player.startAction(new EmoteAction(player, EmoteAction.CLAP, 6));
		} else if (slot == 23) {
			player.startAction(new EmoteAction(player, EmoteAction.SALUTE, 3));
		} else if (slot == 24) {
			player.startAction(new EmoteAction(player, EmoteAction.GOBLIN_BOW, 3));
		} else if (slot == 25) {
			player.startAction(new EmoteAction(player, EmoteAction.GOBLIN_SALUTE, 3));
		} else if (slot == 26) {
			player.startAction(new EmoteAction(player, EmoteAction.GLASS_BOX, 3));
		} else if (slot == 27) {
			player.startAction(new EmoteAction(player, EmoteAction.CLIMB_ROPE, 3));
		} else if (slot == 28) {
			player.startAction(new EmoteAction(player, EmoteAction.LEAN, 3));
		} else if (slot == 29) {
			player.startAction(new EmoteAction(player, EmoteAction.GLASS_WALL, 3));
		} else if (slot == 30) {
			player.startAction(new EmoteAction(player, EmoteAction.IDEA, 3));
		} else if (slot == 31) {
			player.startAction(new EmoteAction(player, EmoteAction.STOMP, 3));
		} else if (slot == 32) {
			player.startAction(new EmoteAction(player, EmoteAction.FLAP, 3));
		} else if (slot == 33) {
			player.startAction(new EmoteAction(player, EmoteAction.SLAP_HEAD, 3));
		} else if (slot == 34) {
			player.startAction(new EmoteAction(player, EmoteAction.ZOMBIE_WALK, 3));
		} else if (slot == 35) {
			player.startAction(new EmoteAction(player, EmoteAction.ZOMBIE_DANCE, 3));
		} else if (slot == 36) {
			player.startAction(new EmoteAction(player, EmoteAction.ZOMBIE_HAND, EmoteAction.ZOMBIE_HAND_GFX, 3));
		} else if (slot == 37) {
			player.startAction(new EmoteAction(player, EmoteAction.SCARED, 3));
		} else if (slot == 38) {
			player.startAction(new EmoteAction(player, EmoteAction.BUNNY_HOP, 3));
		} else if (slot == 39) {
			//TODO: skillcape
			player.sendMessage("You need to be wearing a skillcape to perform this emote.");
		} else if (slot == 40) {
			player.startAction(new EmoteAction(player, EmoteAction.SNOWMAN_DANCE, 3));
		} else if (slot == 41) {
			player.startAction(new EmoteAction(player, EmoteAction.AIR_GUITAR, EmoteAction.AIR_GUITAR_GFX, 3));
		} else if (slot == 42) {
			player.startAction(new EmoteAction(player, EmoteAction.SAFETY_FIRST, EmoteAction.SAFETY_FIRST_GFX, 3));
		} else if (slot == 43) {
			player.startAction(new EmoteAction(player, EmoteAction.EXPLORE, EmoteAction.EXPLORE_GFX, 3));
		} else if (slot == 44) {
			player.startAction(new EmoteAction(player, EmoteAction.TRICK, EmoteAction.TRICK_GFX, 3));
		} else if (slot == 45) {
			player.startAction(new EmoteAction(player, EmoteAction.FREEZE, EmoteAction.FREEZE_GFX, 3));
		} else if (slot == 46) {
			//TODO: Fix give thanks, gfx is wrong and it does not transform into turkey?
			player.startAction(new EmoteAction(player, 10996, 1714, 3));
		} else if (slot == 47) {
			player.startAction(new EmoteAction(player, EmoteAction.AROUND_WORLD, EmoteAction.AROUND_WORLD_GFX, 3));
		}
	}

}
