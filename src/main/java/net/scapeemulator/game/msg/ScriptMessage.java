package net.scapeemulator.game.msg;

public final class ScriptMessage extends Message {

	private final int id;
	private final String types;
	private final Object[] parameters;

	public ScriptMessage(int id, String types, Object... parameters) {
		this.id = id;
		this.types = types;
		this.parameters = parameters;
	}

	public int getId() {
		return id;
	}

	public String getTypes() {
		return types;
	}

	public Object[] getParameters() {
		return parameters;
	}

}
