// Copyright (c) 2026 Bastian Rentzsch

package view.battle;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;

import controller.GameController;
import controller.PlayerController;
import model.Game;
import view.utils.HealthBar;

/**
 * UI panel representing the battle action menu.
 * <p>
 * This panel contains the player's health bar and the main combat action
 * buttons (attack, use item, flee). It is displayed in the lower section
 * of the battle screen and provides interaction controls for the player.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class BattleMenuPanel extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Health bar displaying the player's current health.
	 */
	private HealthBar healthBar;

	/**
	 * Array containing all battle action buttons.
	 */
	private JButton[] buttons;

	/**
	 * Constructs the battle menu panel and initializes UI components.
	 *
	 * @param game the current game instance used to access player state
	 */
	public BattleMenuPanel(Game game) {
		this.setBackground(new Color(41, 37, 36));
		this.setBorder(BorderFactory.createLineBorder(new Color(155, 155, 155), 2));
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 0, 0};
		gridBagLayout.rowHeights = new int[] {30, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{0.2, 1.0};
		this.setLayout(gridBagLayout);
		
		this.healthBar = new HealthBar(PlayerController.getMaxHealth(GameController.getPlayer(game)),
				PlayerController.getHealth(GameController.getPlayer(game)));
		GridBagConstraints gbc_healthBar = new GridBagConstraints();
		gbc_healthBar.gridwidth = 3;
		gbc_healthBar.insets = new Insets(0, 0, 5, 5);
		gbc_healthBar.gridx = 0;
		gbc_healthBar.gridy = 0;
		add(this.healthBar, gbc_healthBar);
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnAttack.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAttack.setFocusPainted(false);
		btnAttack.setContentAreaFilled(false);
		btnAttack.setForeground(new Color(255, 255, 255));
		
		GridBagConstraints gbc_btnAttack = new GridBagConstraints();
		gbc_btnAttack.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAttack.insets = new Insets(0, 0, 0, 5);
		gbc_btnAttack.gridx = 0;
		gbc_btnAttack.gridy = 1;
		this.add(btnAttack, gbc_btnAttack);
		
		JButton btnUseItem = new JButton("Use Item");
		btnUseItem.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnUseItem.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnUseItem.setFocusPainted(false);
		btnUseItem.setContentAreaFilled(false);
		btnUseItem.setForeground(new Color(255, 255, 255));
		
		GridBagConstraints gbc_btnUseItem = new GridBagConstraints();
		gbc_btnUseItem.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnUseItem.insets = new Insets(0, 0, 0, 5);
		gbc_btnUseItem.gridx = 1;
		gbc_btnUseItem.gridy = 1;
		this.add(btnUseItem, gbc_btnUseItem);
		
		JButton btnFlee = new JButton("Flee");
		btnFlee.setBorder(new LineBorder(new Color(255, 255, 255), 2));
		btnFlee.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnFlee.setFocusPainted(false);
		btnFlee.setContentAreaFilled(false);
		btnFlee.setForeground(new Color(255, 255, 255));
		
		GridBagConstraints gbc_btnFlee = new GridBagConstraints();
		gbc_btnFlee.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnFlee.gridx = 2;
		gbc_btnFlee.gridy = 1;
		this.add(btnFlee, gbc_btnFlee);
		
		this.buttons = new JButton[3];
		this.buttons[0] = btnAttack;
		this.buttons[1] = btnUseItem;
		this.buttons[2] = btnFlee;
	}
	
	/**
	 * Returns all action buttons of the battle menu.
	 *
	 * @return an array of JButton objects representing battle actions
	 */
	public JButton[] getButtons() {
		return this.buttons;
	}
	
	/**
	 * Updates the player's health bar display.
	 *
	 * @param health the current health value of the player
	 */
	public void updatePlayerHealth(int health) {
		this.healthBar.setHealth(health);
	}
}
