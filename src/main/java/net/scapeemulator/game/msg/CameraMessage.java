package net.scapeemulator.game.msg;

public final class CameraMessage extends Message {

	private final int yaw, pitch;

	public CameraMessage(int yaw, int pitch) {
		this.yaw = yaw;
		this.pitch = pitch;
	}

	public int getYaw() {
		return yaw;
	}

	public int getPitch() {
		return pitch;
	}

}
