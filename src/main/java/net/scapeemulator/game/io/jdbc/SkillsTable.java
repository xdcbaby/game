package net.scapeemulator.game.io.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.scapeemulator.game.model.Player;

public class SkillsTable extends Table {
	private final PreparedStatement loadStatement;
	private final PreparedStatement saveStatement;
	
	public SkillsTable(Connection connection) throws SQLException {
		this.loadStatement = connection.prepareStatement("SELECT * FROM skills WHERE id = ?");
		this.saveStatement = connection.prepareStatement("REPLACE INTO skills (player_id, skill_id, experience) VALUES (?, ?, ?);");
	}
	
	@Override
	public void load(Player player) throws SQLException, IOException {
		loadStatement.setInt(1, player.getDatabaseId());

		try (ResultSet set = loadStatement.executeQuery()) {
			if (!set.first())
				throw new IOException();

			int id = set.getInt("skill_id");
			int xp = set.getInt("experience");
			player.getSkillSet().addExperience(id, xp);
		}
	}

	@Override
	public void save(Player player) throws SQLException {
		saveStatement.setInt(1, player.getDatabaseId());
		for (int i = 0; i < 24; i++) {
			saveStatement.setInt(2, i);
			saveStatement.setDouble(3, player.getSkillSet().getExperience(i));
		}
		saveStatement.execute();
	}

}
