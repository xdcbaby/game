/**
 * scape-emulator-final
 * Copyright (c) 2014 Justin Conner
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in  the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/license/>.
 */
package net.scapeemulator.game.action.impl;

import net.scapeemulator.game.action.Action;
import net.scapeemulator.game.model.Animation;
import net.scapeemulator.game.model.Player;
import net.scapeemulator.game.model.SpotAnimation;

public final class EmoteAction extends Action<Player> {
    public static final int YES = 855;
    public static final int NO = 856;
    public static final int BOW = 858;
    public static final int ANGRY = 859;
    public static final int THINK = 857;
    public static final int WAVE = 863;
    public static final int SHRUG = 2113;
    public static final int CHEER = 862;
    public static final int BECKON = 864;
    public static final int JUMP_FOR_JOY = 2109;
    public static final int LAUGH = 861;
    public static final int YAWN = 2111;
    public static final int DANCE = 866;
    public static final int JIG = 2106;
    public static final int SPIN = 2107;
    public static final int HEADBANG = 2108;
    public static final int CRY = 860;
    public static final int BLOW_KISS = 1368;
    public static final int BLOW_KISS_GFX = 574;
    public static final int PANIC = 2105;
    public static final int RASPBERRY = 2110;
    public static final int CLAP = 865;
    public static final int SALUTE = 2112;
    public static final int GOBLIN_BOW = 2127;
    public static final int GOBLIN_SALUTE = 2128;
    public static final int GLASS_BOX = 1131;
    public static final int CLIMB_ROPE = 1130;
    public static final int LEAN = 1129;
    public static final int GLASS_WALL = 1128;
    public static final int IDEA = 4275;
    public static final int STOMP = 1745;
    public static final int FLAP = 4280;
    public static final int SLAP_HEAD = 4276;
    public static final int ZOMBIE_WALK = 3544;
    public static final int ZOMBIE_DANCE = 3543;
    public static final int ZOMBIE_HAND = 7272;
    public static final int ZOMBIE_HAND_GFX = 1244;
    public static final int SCARED = 2836;
    public static final int BUNNY_HOP = 6111;
    public static final int SNOWMAN_DANCE = 7531;
    public static final int AIR_GUITAR = 2414; 
    public static final int AIR_GUITAR_GFX = 1537;
    public static final int SAFETY_FIRST = 8770;
    public static final int SAFETY_FIRST_GFX = 1553;
    public static final int EXPLORE = 9990;
    public static final int EXPLORE_GFX = 1734;
    public static final int TRICK = 10530;
    public static final int TRICK_GFX = 1864;
    public static final int FREEZE = 11044;
    public static final int FREEZE_GFX = 1973;
    public static final int AROUND_WORLD = 11542;
    public static final int AROUND_WORLD_GFX = 2037;
    
    public EmoteAction(Player player, int animation, int delay) {
        this(player, animation, -1, delay);
    }

    public EmoteAction(Player player, int animation, int spotAnimation, int delay) {
        super(delay, false, player);
        player.getWalkingQueue().reset();
        player.playAnimation(new Animation(animation));
        if (spotAnimation != -1)
            player.playSpotAnimation(new SpotAnimation(spotAnimation));
    }

    public void execute() {
        stop();
    }

}
