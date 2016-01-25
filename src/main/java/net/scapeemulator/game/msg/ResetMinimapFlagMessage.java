package net.scapeemulator.game.msg;

public final class ResetMinimapFlagMessage extends Message {
    private int flagId;

    public ResetMinimapFlagMessage(int flagId) {
        this.flagId = flagId;
    }

    public int getFlagId() {
        return flagId;
    }
}
