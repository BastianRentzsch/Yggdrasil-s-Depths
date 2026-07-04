// Copyright (c) 2026 Bastian Rentzsch

package view.battle;

import javax.swing.JButton;
import javax.swing.JPanel;

import controller.EncounterSystem;
import model.Game;
import model.entity.Enemy;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.Insets;
import java.util.List;

/**
 * The main view for a battle scene.
 * <p>
 * This panel combines the enemy display and the battle menu, and acts as the
 * central UI container for combat interactions. It is responsible for
 * initializing enemy encounters and delegating UI updates to its subcomponents.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class BattleView extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel responsible for displaying all enemies participating in the current battle.
	 */
	private EnemiesViewPanel enemiesViewPanel;

	/**
	 * Panel containing the battle command menu and player status information.
	 */
	private BattleMenuPanel battleMenuPanel;

	/**
	 * Creates a new battle view.
	 * <p>
	 * Initializes the enemy encounter, creates the enemy display and battle menu,
	 * and arranges both components within the battle layout.
	 * </p>
	 *
	 * @param game the active game instance containing the player and battle data
	 */
	public BattleView(Game game) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {50};
		gridBagLayout.rowHeights = new int[] {50, 50};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.3};
		this.setLayout(gridBagLayout);
		
		Enemy[] enemies = EncounterSystem.getEnemies(game.enemies);
		
		this.enemiesViewPanel = new EnemiesViewPanel(enemies);
		GridBagConstraints gbc_enemiesViewPanel = new GridBagConstraints();
		gbc_enemiesViewPanel.insets = new Insets(0, 0, 5, 0);
		gbc_enemiesViewPanel.fill = GridBagConstraints.BOTH;
		gbc_enemiesViewPanel.gridx = 0;
		gbc_enemiesViewPanel.gridy = 0;
		this.add(this.enemiesViewPanel, gbc_enemiesViewPanel);
		
		this.battleMenuPanel = new BattleMenuPanel(game);
		GridBagConstraints gbc_battleMenuPanel = new GridBagConstraints();
		gbc_battleMenuPanel.fill = GridBagConstraints.BOTH;
		gbc_battleMenuPanel.gridx = 0;
		gbc_battleMenuPanel.gridy = 1;
		this.add(this.battleMenuPanel, gbc_battleMenuPanel);
	}
	
	/**
	 * Returns the enemy panels displayed in the current battle.
	 *
	 * @return a list of {@link EnemyPanel} instances representing the active enemies
	 */
	public List<EnemyPanel> getEnemies() {
		return this.enemiesViewPanel.getEnemies();
	}
	
	/**
	 * Returns the action buttons displayed in the battle menu.
	 *
	 * @return an array containing the battle action buttons
	 */
	public JButton[] getButtons() {
		return this.battleMenuPanel.getButtons();
	}
	
	/**
	 * Updates the player's health display in the battle menu.
	 *
	 * @param health the current health value of the player
	 */
	public void updatePlayerHealth(int health) {
		this.battleMenuPanel.updatePlayerHealth(health);
	}
}
