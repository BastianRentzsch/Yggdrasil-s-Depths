// Copyright (c) 2026 Bastian Rentzsch

package view.pausemenu;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import audio.MusicPlayer;
import controller.GameController;
import controller.SavedataHandler;
import model.Game;
import view.GameFrame;
import view.utils.PopupDialog;
import view.utils.ToggleButton;

/**
 * A pause menu screen that provides game options such as saving,
 * loading, exiting the game, and toggling fullscreen mode.
 * <p>
 * The layout and available actions depend on whether a game session
 * is currently running.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class OptionScreen extends JPanel {

	/**
     * Serial version UID for serialization.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Button used to save the current game progress.
     */
    private JButton btnSave = new JButton("Save");

    /**
     * Button used to load a previously saved game.
     */
    private JButton btnLoad = new JButton("Load");

    /**
     * Creates an options screen for an active game session.
     * <p>
     * In addition to the standard options, this version enables saving and
     * loading the current game state and provides actions for returning to
     * the main menu or exiting the application.
     * </p>
     *
     * @param frame the main application frame used for navigation and UI management
     * @param game the active game instance whose state can be saved or loaded
     */
	public OptionScreen(GameFrame frame, Game game) {
		this(frame);

		this.btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					SavedataHandler.save(GameController.getPlayer(game));
					PopupDialog.showDialog(frame, "The game was successfully saved.", PopupDialog.PLAIN);
				} catch (IOException ex) {
					PopupDialog.showDialog(frame, "The game could not be successfully saved.",
							PopupDialog.ERROR);
				}
			}
		});

		this.btnLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					GameController.setPlayer(game, SavedataHandler.load());
					frame.getWindow().getDungeonPanel().updateView(GameController.getPlayer(game));

					StatusScreen statusScreen = new StatusScreen(frame, game);
					frame.addCard(statusScreen, GameFrame.STATUS);

					InventoryScreen inventoryScreen = new InventoryScreen(game);
					frame.addCard(inventoryScreen, GameFrame.INVENTORY);

					PopupDialog.showDialog(frame, "The game has successfully loaded.", PopupDialog.PLAIN);
				} catch (IOException | ClassNotFoundException ex) {
					PopupDialog.showDialog(frame, "The game could not be successfully loaded.",
							PopupDialog.ERROR);
				}
			}
		});
	}

	/**
	 * Creates an options screen without an active game session.
	 * <p>
	 * This version provides general application options, such as returning
	 * to the main menu and toggling fullscreen mode, but does not include
	 * save or load functionality.
	 * </p>
	 *
	 * @param frame the main application frame used for navigation and UI management
	 */
	public OptionScreen(GameFrame frame) {
		boolean isMaximized = (frame.getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH;

		this.setBackground(new Color(41, 37, 36));

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {566};
		gridBagLayout.rowHeights = new int[] {20, 23, 23, 23, 23, 10, 26, 0, 40};
		gridBagLayout.columnWeights = new double[]{1.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0};
		this.setLayout(gridBagLayout);

		if (frame.isGameRunning()) {
			this.btnSave.setForeground(new Color(255, 255, 255));
			this.btnSave.setFocusPainted(false);
			this.btnSave.setContentAreaFilled(false);
			this.btnSave.setFont(new Font("SansSerif", Font.BOLD, 15));
			this.btnSave.setAlignmentY(Component.TOP_ALIGNMENT);
			this.btnSave.setAlignmentX(Component.CENTER_ALIGNMENT);

			GridBagConstraints gbc_btnSave = new GridBagConstraints();
			gbc_btnSave.anchor = GridBagConstraints.NORTH;
			gbc_btnSave.insets = new Insets(0, 0, 5, 0);
			gbc_btnSave.gridx = 0;
			gbc_btnSave.gridy = 1;
			this.add(this.btnSave, gbc_btnSave);

			this.btnLoad.setContentAreaFilled(false);
			this.btnLoad.setFocusPainted(false);
			this.btnLoad.setForeground(new Color(255, 255, 255));
			this.btnLoad.setFont(new Font("SansSerif", Font.BOLD, 15));
			this.btnLoad.setAlignmentY(Component.TOP_ALIGNMENT);
			this.btnLoad.setAlignmentX(Component.CENTER_ALIGNMENT);

			GridBagConstraints gbc_btnLoad = new GridBagConstraints();
			gbc_btnLoad.anchor = GridBagConstraints.NORTH;
			gbc_btnLoad.insets = new Insets(0, 0, 5, 0);
			gbc_btnLoad.gridx = 0;
			gbc_btnLoad.gridy = 2;
			this.add(this.btnLoad, gbc_btnLoad);

			JButton btnExitMainMenu = new JButton("Exit to Main Menu");
			btnExitMainMenu.setContentAreaFilled(false);
			btnExitMainMenu.setFocusPainted(false);
			btnExitMainMenu.setForeground(new Color(255, 255, 255));
			btnExitMainMenu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean choice = ExitConfirmationDialog.showDialog((OptionScreen) btnExitMainMenu.getParent(),
							true);

					if (choice) {
						frame.setGameIsRunning(false);
						frame.showCard(GameFrame.MAIN_MENU);
						
						MusicPlayer.playMainMenu(frame.getMusicPlayer());
					}
				}
			});
			btnExitMainMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
			btnExitMainMenu.setAlignmentY(Component.TOP_ALIGNMENT);
			btnExitMainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

			GridBagConstraints gbc_btnExitMainMenu = new GridBagConstraints();
			gbc_btnExitMainMenu.anchor = GridBagConstraints.NORTH;
			gbc_btnExitMainMenu.insets = new Insets(0, 0, 5, 0);
			gbc_btnExitMainMenu.gridx = 0;
			gbc_btnExitMainMenu.gridy = 3;
			this.add(btnExitMainMenu, gbc_btnExitMainMenu);

			JButton btnExitGame = new JButton("Exit Game");
			btnExitGame.setContentAreaFilled(false);
			btnExitGame.setForeground(new Color(255, 255, 255));
			btnExitGame.setFocusPainted(false);
			btnExitGame.setFont(new Font("SansSerif", Font.BOLD, 15));
			btnExitGame.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean choice = ExitConfirmationDialog.showDialog((OptionScreen) btnExitGame.getParent(),
							true);

					if (choice) {
						System.exit(0);
					}
				}
			});
			btnExitGame.setAlignmentY(Component.TOP_ALIGNMENT);
			btnExitGame.setAlignmentX(Component.CENTER_ALIGNMENT);

			GridBagConstraints gbc_btnExitGame = new GridBagConstraints();
			gbc_btnExitGame.anchor = GridBagConstraints.NORTH;
			gbc_btnExitGame.insets = new Insets(0, 0, 5, 0);
			gbc_btnExitGame.gridx = 0;
			gbc_btnExitGame.gridy = 4;
			this.add(btnExitGame, gbc_btnExitGame);

			JSeparator separator = new JSeparator();
			separator.setPreferredSize(new Dimension(0, 10));
			separator.setMaximumSize(new Dimension(32767, 10));
			separator.setAlignmentY(Component.TOP_ALIGNMENT);

			GridBagConstraints gbc_separator = new GridBagConstraints();
			gbc_separator.fill = GridBagConstraints.HORIZONTAL;
			gbc_separator.anchor = GridBagConstraints.NORTH;
			gbc_separator.insets = new Insets(0, 0, 5, 0);
			gbc_separator.gridx = 0;
			gbc_separator.gridy = 5;
			this.add(separator, gbc_separator);
		}
		else {
			JButton btnMainMenu = new JButton("Back to Main Menu");
			btnMainMenu.setContentAreaFilled(false);
			btnMainMenu.setForeground(new Color(255, 255, 255));
			btnMainMenu.setFocusPainted(false);
			btnMainMenu.setFont(new Font("SansSerif", Font.BOLD, 15));
			btnMainMenu.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					frame.showCard(GameFrame.MAIN_MENU);
					
					MusicPlayer.playMainMenu(frame.getMusicPlayer());
				}
			});
			btnMainMenu.setAlignmentY(Component.TOP_ALIGNMENT);
			btnMainMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			GridBagConstraints gbc_btnExitGame = new GridBagConstraints();
			gbc_btnExitGame.anchor = GridBagConstraints.NORTH;
			gbc_btnExitGame.insets = new Insets(0, 0, 5, 0);
			gbc_btnExitGame.gridx = 0;
			gbc_btnExitGame.gridy = 4;
			this.add(btnMainMenu, gbc_btnExitGame);
		}

		JLabel lblFullScreen = new JLabel("Full Screen");
		lblFullScreen.setAlignmentY(Component.TOP_ALIGNMENT);
		lblFullScreen.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblFullScreen.setFont(new Font("SansSerif", Font.BOLD, 20));
		lblFullScreen.setForeground(new Color(255, 255, 255));

		GridBagConstraints gbc_lblFullScreen = new GridBagConstraints();
		gbc_lblFullScreen.anchor = GridBagConstraints.NORTH;
		gbc_lblFullScreen.insets = new Insets(0, 0, 5, 0);
		if (frame.isGameRunning()) {
			gbc_lblFullScreen.gridx = 0;
			gbc_lblFullScreen.gridy = 6;
		}
		else {
			gbc_lblFullScreen.gridx = 0;
			gbc_lblFullScreen.gridy = 1;
		}
		this.add(lblFullScreen, gbc_lblFullScreen);

		ToggleButton tbFullScreen = new ToggleButton(isMaximized);
		tbFullScreen.setAlignmentY(Component.TOP_ALIGNMENT);
		tbFullScreen.setMinimumSize(new Dimension(80, 40));
		tbFullScreen.setMaximumSize(new Dimension(80, 40));
		tbFullScreen.setAlignmentX(Component.CENTER_ALIGNMENT);

		GridBagConstraints gbc_tbFullScreen = new GridBagConstraints();
		gbc_tbFullScreen.anchor = GridBagConstraints.NORTH;
		if (frame.isGameRunning()) {
			gbc_tbFullScreen.gridx = 0;
			gbc_tbFullScreen.gridy = 7;
		}
		else {
			gbc_tbFullScreen.gridx = 0;
			gbc_tbFullScreen.gridy = 2;
		}
		this.add(tbFullScreen, gbc_tbFullScreen);
	}
}
