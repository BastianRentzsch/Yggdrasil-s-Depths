// Copyright (c) 2026 Bastian Rentzsch

package view.pausemenu;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import model.Game;
import view.GameFrame;
import view.utils.SpriteImagePanel;

/**
 * A UI panel that represents the player's status screen.
 * <p>
 * The status screen is part of the pause menu and displays the player's
 * statistics, character sprite, and equipped items in a three-column layout.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class StatusScreen extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new status screen displaying the player's stats, character image,
     * and equipment information.
     *
     * @param frame the main game frame used for navigation and UI interaction
     * @param game the current game instance containing player and game state
     */
	public StatusScreen(GameFrame frame, Game game) {
		this.setForeground(new Color(255, 255, 255));
		this.setBackground(new Color(41, 37, 36));
		this.setLayout(new GridLayout(0, 3, 0, 0));

		StatsPanel statsPanel = new StatsPanel(game);
		this.add(statsPanel);

		SpriteImagePanel characterImage = new SpriteImagePanel("./res/images/sprites/player/"
				+ game.player.getGender() + ".png", true);
		this.add(characterImage);

		EquipmentPanel equipmentPanel = new EquipmentPanel(frame, statsPanel, game);
		this.add(equipmentPanel);
	}
}
