// Copyright (c) 2026 Bastian Rentzsch

package view.battle;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import model.entity.Enemy;

import java.awt.GridBagLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code EnemiesViewPanel} represents the battle UI container for multiple enemies.
 * <p>
 * It arranges up to three {@link EnemyPanel} instances depending on the number of enemies
 * in the encounter and displays a shared battle background.
 * </p>
 * <p>
 * The panel is responsible for positioning enemy visuals and providing access
 * to all active enemy panels for further interaction (e.g., combat updates).
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class EnemiesViewPanel extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * UI panel for the first enemy.
	 */
	private EnemyPanel enemyOnePanel;

	/**
	 * UI panel for the second enemy (if present).
	 */
	private EnemyPanel enemyTwoPanel;

	/**
	 * UI panel for the third enemy (if present).
	 */
	private EnemyPanel enemyThreePanel;

	/**
	 * Creates an {@code EnemiesViewPanel} based on the provided enemies array.
	 * <p>
	 * Supports displaying 1 to 3 enemies and dynamically positions them
	 * in the battle scene layout.
	 * </p>
	 *
	 * @param enemies array of enemies participating in the battle
	 */
	public EnemiesViewPanel(Enemy[] enemies) {
		int enemiesCount = enemies.length;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0};
		gridBagLayout.columnWidths = new int[]{51, 232, 232, 232, 51};
		gridBagLayout.rowHeights = new int[]{0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0};
		this.setLayout(gridBagLayout);
		
		this.enemyOnePanel = new EnemyPanel(enemies[0]);
		GridBagConstraints gbc_enemyOnePanel = new GridBagConstraints();
		gbc_enemyOnePanel.insets = new Insets(0, 0, 5, 5);
		gbc_enemyOnePanel.fill = GridBagConstraints.BOTH;
		gbc_enemyOnePanel.gridy = 1;
		
		this.enemyTwoPanel = null;
		GridBagConstraints gbc_enemyTwoPanel = new GridBagConstraints();
		gbc_enemyTwoPanel.insets = new Insets(0, 0, 5, 5);
		gbc_enemyTwoPanel.fill = GridBagConstraints.BOTH;
		gbc_enemyTwoPanel.gridy = 1;
		
		this.enemyThreePanel = null;
		GridBagConstraints gbc_enemyThreePanel = new GridBagConstraints();
		gbc_enemyThreePanel.insets = new Insets(0, 0, 5, 5);
		gbc_enemyThreePanel.fill = GridBagConstraints.BOTH;
		gbc_enemyThreePanel.gridy = 1;
		
		if (enemiesCount > 1) {
			this.enemyTwoPanel = new EnemyPanel(enemies[1]);
		}
		if (enemiesCount > 2) {
			this.enemyThreePanel = new EnemyPanel(enemies[2]);
		}
		
		if (enemiesCount == 1) {
			gbc_enemyOnePanel.gridx = 2;
			this.add(this.enemyOnePanel, gbc_enemyOnePanel);
		}
		else if (enemiesCount == 2) {
			gbc_enemyOnePanel.gridx = 1;
			this.add(this.enemyOnePanel, gbc_enemyOnePanel);
			
			gbc_enemyTwoPanel.gridx = 3;
			this.add(this.enemyTwoPanel, gbc_enemyTwoPanel);
		}
		else if (enemiesCount == 3) {
			gbc_enemyOnePanel.gridx = 1;
			this.add(this.enemyOnePanel, gbc_enemyOnePanel);
			
			gbc_enemyTwoPanel.gridx = 2;
			this.add(this.enemyTwoPanel, gbc_enemyTwoPanel);
			
			gbc_enemyThreePanel.gridx = 3;
			this.add(this.enemyThreePanel, gbc_enemyThreePanel);
		}
	}

	/**
	 * Paints the battle background image behind all enemy panels.
	 *
	 * @param g the {@link Graphics} context used for rendering
	 * @throws RuntimeException if the background image cannot be loaded
	 */
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	   
	    try {
		    BufferedImage background = ImageIO.read(new File("./res/images/dungeon/battleBackground.png"));
		    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		} catch (IOException e) {
			throw new RuntimeException("Error loading dungeon graphics", e);
		}
	}

	/**
	 * Returns a list of all active {@link EnemyPanel} instances in this view.
	 *
	 * @return list of enemy panels currently displayed (1–3 entries)
	 */
	public List<EnemyPanel> getEnemies() {
		List<EnemyPanel> enemies = new ArrayList<>();
		enemies.add(this.enemyOnePanel);
		
		if (this.enemyTwoPanel != null) {
			enemies.add(this.enemyTwoPanel);
		}
		if (this.enemyThreePanel != null) {
			enemies.add(this.enemyThreePanel);
		}
		
		return enemies;
	}
}
