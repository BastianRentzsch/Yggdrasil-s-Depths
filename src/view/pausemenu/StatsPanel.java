// Copyright (c) 2026 Bastian Rentzsch

package view.pausemenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import controller.GameController;
import controller.PlayerController;
import model.Game;
import model.entity.Player;

/**
 * A panel that displays the player's statistics inside the status screen.
 * <p>
 * It shows the player's name, health, physical attack, and physical defense.
 * The layout automatically scales when the component is resized.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class StatsPanel extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Label displaying the player's current and maximum health.
	 */
	private JLabel lblHealthAmount;

	/**
	 * Label displaying the player's current physical attack value.
	 */
	private JLabel lblAttackAmount;

	/**
	 * Label displaying the player's current physical defense value.
	 */
	private JLabel lblDefenceAmount;

	/**
     * Creates a new statistics panel for the given game instance.
     * <p>
     * The panel displays dynamic player information and updates its layout
     * based on the parent {@link StatusScreen} size.
     * </p>
     *
     * @param game the current game instance containing player data
     */
	public StatsPanel(Game game) {
		this.setBackground(new Color(41, 37, 36));

		GridBagLayout gbl_statsPanel = new GridBagLayout();
		gbl_statsPanel.columnWidths = new int[]{109, 0, 90, 0};
		gbl_statsPanel.rowHeights = new int[] {26, 30, 0, 30, 0, 0, 0, 0, 0, 0};
		gbl_statsPanel.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_statsPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_statsPanel);

		JLabel lblPlayername = new JLabel(PlayerController.getName(GameController.getPlayer(game)));
		lblPlayername.setHorizontalAlignment(SwingConstants.CENTER);
		lblPlayername.setForeground(Color.WHITE);
		lblPlayername.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblPlayername.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblPlayername = new GridBagConstraints();
		gbc_lblPlayername.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblPlayername.gridwidth = 3;
		gbc_lblPlayername.insets = new Insets(0, 0, 5, 5);
		gbc_lblPlayername.gridx = 0;
		gbc_lblPlayername.gridy = 0;
		this.add(lblPlayername, gbc_lblPlayername);

		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		GridBagConstraints gbc_separator = new GridBagConstraints();
		gbc_separator.fill = GridBagConstraints.HORIZONTAL;
		gbc_separator.gridwidth = 3;
		gbc_separator.insets = new Insets(0, 0, 5, 0);
		gbc_separator.gridx = 0;
		gbc_separator.gridy = 2;
		this.add(separator, gbc_separator);

		JLabel lblHealth = new JLabel("Health");
		lblHealth.setForeground(Color.WHITE);
		lblHealth.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblHealth.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblHealth = new GridBagConstraints();
		gbc_lblHealth.insets = new Insets(0, 0, 5, 5);
		gbc_lblHealth.gridx = 0;
		gbc_lblHealth.gridy = 4;
		this.add(lblHealth, gbc_lblHealth);

		this.lblHealthAmount = new JLabel(PlayerController.getHealth(GameController.getPlayer(game)) + " / "
				+ PlayerController.getMaxHealth(GameController.getPlayer(game)));
		this.lblHealthAmount.setHorizontalAlignment(SwingConstants.LEFT);
		this.lblHealthAmount.setForeground(Color.WHITE);
		this.lblHealthAmount.setFont(new Font("SansSerif", Font.BOLD, 15));
		GridBagConstraints gbc_lblHealthAmount = new GridBagConstraints();
		gbc_lblHealthAmount.anchor = GridBagConstraints.EAST;
		gbc_lblHealthAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblHealthAmount.gridx = 2;
		gbc_lblHealthAmount.gridy = 4;
		this.add(this.lblHealthAmount, gbc_lblHealthAmount);

		JLabel lblPhyAttack = new JLabel("Attack");
		lblPhyAttack.setForeground(Color.WHITE);
		lblPhyAttack.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPhyAttack.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblPhyAttack = new GridBagConstraints();
		gbc_lblPhyAttack.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhyAttack.gridx = 0;
		gbc_lblPhyAttack.gridy = 5;
		this.add(lblPhyAttack, gbc_lblPhyAttack);

		this.lblAttackAmount = new JLabel(Integer.toString(PlayerController.getDamage(GameController.getPlayer(game))));
		this.lblAttackAmount.setForeground(Color.WHITE);
		this.lblAttackAmount.setFont(new Font("SansSerif", Font.BOLD, 15));
		this.lblAttackAmount.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblPhyAttackAmount = new GridBagConstraints();
		gbc_lblPhyAttackAmount.anchor = GridBagConstraints.EAST;
		gbc_lblPhyAttackAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblPhyAttackAmount.gridx = 2;
		gbc_lblPhyAttackAmount.gridy = 5;
		this.add(this.lblAttackAmount, gbc_lblPhyAttackAmount);

		
		JLabel lblPhyDefence = new JLabel("Defence");
		lblPhyDefence.setForeground(Color.WHITE);
		lblPhyDefence.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblPhyDefence.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblPhyDefence = new GridBagConstraints();
		gbc_lblPhyDefence.insets = new Insets(0, 0, 5, 5);
		gbc_lblPhyDefence.gridx = 0;
		gbc_lblPhyDefence.gridy = 6;
		this.add(lblPhyDefence, gbc_lblPhyDefence);

		this.lblDefenceAmount = new JLabel(Integer.toString(PlayerController.getDefense(GameController.getPlayer(game))));
		this.lblDefenceAmount.setForeground(Color.WHITE);
		this.lblDefenceAmount.setFont(new Font("SansSerif", Font.BOLD, 15));
		this.lblDefenceAmount.setAlignmentX(0.5f);
		GridBagConstraints gbc_lblPhyDefenceAmount = new GridBagConstraints();
		gbc_lblPhyDefenceAmount.anchor = GridBagConstraints.EAST;
		gbc_lblPhyDefenceAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblPhyDefenceAmount.gridx = 2;
		gbc_lblPhyDefenceAmount.gridy = 6;
		this.add(this.lblDefenceAmount, gbc_lblPhyDefenceAmount);

		Font originalStatFont = lblHealth.getFont();
		Font originalPlayerFont = lblPlayername.getFont();

		JLabel[] statLabels = {
				lblHealth,
				this.lblHealthAmount,
				lblPhyAttack,
				this.lblAttackAmount,
				lblPhyDefence,
				this.lblDefenceAmount
		};

		this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
            		StatsPanel source = (StatsPanel) e.getSource();
            		StatusScreen statusScreen = (StatusScreen) source.getParent();

                float scale = statusScreen.getWidth() / 798f;
                float statSize = Math.max(15f, originalStatFont.getSize2D() * scale);
                float playernameSize = Math.max(20f, originalPlayerFont.getSize2D() * scale);

                for (JLabel label : statLabels) {
                    label.setFont(originalStatFont.deriveFont(statSize));
                }

                lblPlayername.setFont(originalPlayerFont.deriveFont(playernameSize));

                source.revalidate();
                source.repaint();
            }
        });
	}

	/**
     * Updates the displayed statistics based on the given player.
     *
     * @param player the player whose current stats should be displayed
     */
	public void updateStats(Player player) {
		this.lblHealthAmount.setText(PlayerController.getHealth(player) + " / " + PlayerController.getMaxHealth(player));
		this.lblAttackAmount.setText(Integer.toString(PlayerController.getDamage(player)));
		this.lblDefenceAmount.setText(Integer.toString(PlayerController.getDefense(player)));

		this.revalidate();
		this.repaint();
	}
}
