// Copyright (c) 2026 Bastian Rentzsch

package view.battle;

import javax.swing.JPanel;

import model.entity.Enemy;
import view.utils.HealthBar;
import view.utils.SpriteImagePanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * The {@code EnemyPanel} represents the visual UI component for an enemy in battle.
 * <p>
 * It displays the enemy sprite and a health bar reflecting the enemy's current health.
 * The panel automatically updates its display based on the enemy's state.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class EnemyPanel extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The health bar UI component showing the enemy's current health.
	 */
	private HealthBar healthBar;

	/**
	 * The enemy entity associated with this panel.
	 */
	private Enemy enemy;

	/**
	 * Creates an {@code EnemyPanel} for the specified enemy.
	 * <p>
	 * Initializes the enemy sprite and health bar UI components.
	 * </p>
	 *
	 * @param enemy the enemy entity to display
	 */
	public EnemyPanel(Enemy enemy) {
		this.enemy = enemy;
		
		this.setOpaque(false);
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0};
		gridBagLayout.rowHeights = new int[] {0, 0};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.1, 0.0};
		this.setLayout(gridBagLayout);
		
		SpriteImagePanel spriteImagePanel = new SpriteImagePanel("./res/images/sprites/enemies/"
				+ enemy.getImagename(), false);
		GridBagConstraints gbc_spriteImagePanel = new GridBagConstraints();
		gbc_spriteImagePanel.fill = GridBagConstraints.BOTH;
		gbc_spriteImagePanel.insets = new Insets(0, 0, 5, 5);
		gbc_spriteImagePanel.gridx = 0;
		gbc_spriteImagePanel.gridy = 0;
		this.add(spriteImagePanel, gbc_spriteImagePanel);
		
		this.healthBar = new HealthBar(enemy.getMaxHealth(), enemy.getHealth());
		GridBagConstraints gbc_healthBar = new GridBagConstraints();
		gbc_healthBar.insets = new Insets(0, 0, 5, 5);
		gbc_healthBar.fill = GridBagConstraints.BOTH;
		gbc_healthBar.gridx = 0;
		gbc_healthBar.gridy = 1;
		this.add(this.healthBar, gbc_healthBar);
	}

	/**
	 * Returns the enemy associated with this panel.
	 *
	 * @return the enemy entity
	 */
	public Enemy getEnemy() {
		return this.enemy;
	}
	
	/**
	 * Updates the visual display of the enemy.
	 * <p>
	 * If the enemy's health reaches zero, the panel is hidden.
	 * Otherwise, the health bar is updated to reflect the current health.
	 * </p>
	 */
	public void updateDisplay() {
		if (this.enemy.getHealth() == 0) {
			this.setVisible(false);
		}
		else {
			this.healthBar.setHealth(this.enemy.getHealth());
		}
	}

}
