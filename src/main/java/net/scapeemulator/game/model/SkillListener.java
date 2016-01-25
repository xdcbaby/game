package net.scapeemulator.game.model;

public interface SkillListener {

	public void skillChanged(SkillSet set, int skill);

	public void skillLevelledUp(SkillSet set, int skill);

}
