// Copyright (c) 2026 Bastian Rentzsch

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import audio.MusicPlayer;
import controller.GameController;
import model.Game;
import view.gamewindow.GameWindow;
import view.pausemenu.OptionScreen;
import view.utils.PopupDialog;

/**
 * Main menu panel of the game.
 * <p>
 * This screen serves as the entry point of the application and allows the user to:
 * <ul>
 *   <li>Start a new game</li>
 *   <li>Load a previously saved game</li>
 *   <li>Open the options menu</li>
 *   <li>Exit the game</li>
 * </ul>
 * It also handles loading required game resources such as items and enemies
 * when starting or loading a game.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class MainMenu extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates the main menu UI and initializes all menu buttons.
     * <p>
     * The constructor sets up the layout, styling, and behavior of all menu buttons:
     * <ul>
     *   <li><b>New Game:</b> Initializes a new dungeon, loads game data,
     *   and transitions to the save creation screen.</li>
     *   <li><b>Load:</b> Loads a saved player state and starts the game.</li>
     *   <li><b>Options:</b> Opens the settings/options screen.</li>
     *   <li><b>Exit:</b> Closes the application.</li>
     * </ul>
     * </p>
     *
     * @param frame the main application window used for screen navigation and state management
     */
	public MainMenu(GameFrame frame) {
		this.setBackground(new Color(41, 37, 36));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {50, 50, 50};
		gridBagLayout.rowHeights = new int[] {240, 40, 40, 40, 40};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0};
		this.setLayout(gridBagLayout);

		JButton btnNewGame = new JButton("New Game");
		btnNewGame.setContentAreaFilled(false);
		btnNewGame.setFocusPainted(false);
		btnNewGame.setForeground(new Color(255, 255, 255));
		btnNewGame.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game game;
				
				try {
					game = GameController.createNewGame();
				} catch (IOException | ClassNotFoundException ex) {
					PopupDialog.showDialog(frame, "The game could not be successfully loaded.",
							PopupDialog.ERROR);
					System.err.println(ex);
					return;
				}

				CreateNewSavePanel createNewSavePanel = new CreateNewSavePanel(frame, game);
				frame.addCard(createNewSavePanel, GameFrame.NEW_SAVE);
				frame.showCard(GameFrame.NEW_SAVE);
			}
		});

		GridBagConstraints gbc_btnNewGame = new GridBagConstraints();
		gbc_btnNewGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewGame.gridx = 1;
		gbc_btnNewGame.gridy = 1;
		this.add(btnNewGame, gbc_btnNewGame);

		JButton btnLoadGame = new JButton("Load");
		btnLoadGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setGameIsRunning(true);

				Game game;

				try {
					game = GameController.loadGame();
				} catch (IOException | ClassNotFoundException ex) {
					PopupDialog.showDialog(frame, "The game could not be successfully loaded.",
							PopupDialog.ERROR);
					return;
				}

				GameWindow gameWindow = new GameWindow(frame, game);
				frame.setGameWindow(gameWindow);

				frame.addCard(gameWindow, GameFrame.GAME);
				frame.showCard(GameFrame.GAME);
				
				MusicPlayer.playExploration(frame.getMusicPlayer());
			}
		});
		btnLoadGame.setContentAreaFilled(false);
		btnLoadGame.setFocusPainted(false);
		btnLoadGame.setForeground(new Color(255, 255, 255));
		btnLoadGame.setFont(new Font("SansSerif", Font.BOLD, 15));

		GridBagConstraints gbc_btnLoadGame = new GridBagConstraints();
		gbc_btnLoadGame.insets = new Insets(0, 0, 5, 5);
		gbc_btnLoadGame.gridx = 1;
		gbc_btnLoadGame.gridy = 2;
		this.add(btnLoadGame, gbc_btnLoadGame);

		JButton btnOptions = new JButton("options");
		btnOptions.setForeground(new Color(255, 255, 255));
		btnOptions.setFocusPainted(false);
		btnOptions.setContentAreaFilled(false);
		btnOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.addCard(new OptionScreen(frame), GameFrame.OPTIONS);

				frame.showCard(GameFrame.OPTIONS);
				
				MusicPlayer.playOtherMenus(frame.getMusicPlayer());
			}
		});
		btnOptions.setFont(new Font("SansSerif", Font.BOLD, 15));

		GridBagConstraints gbc_btnOptions = new GridBagConstraints();
		gbc_btnOptions.insets = new Insets(0, 0, 5, 5);
		gbc_btnOptions.gridx = 1;
		gbc_btnOptions.gridy = 3;
		this.add(btnOptions, gbc_btnOptions);

		JButton btnExit = new JButton("Exit Game");
		btnExit.setContentAreaFilled(false);
		btnExit.setFocusPainted(false);
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("SansSerif", Font.BOLD, 15));

		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.insets = new Insets(0, 0, 5, 5);
		gbc_btnExit.gridx = 1;
		gbc_btnExit.gridy = 4;
		this.add(btnExit, gbc_btnExit);
	}
	
	/**
     * Paints the background image of the main menu.
     * <p>
     * The image is loaded from the resource folder and scaled to fit the panel size.
     * If the image cannot be loaded, a runtime exception is thrown.
     * </p>
     *
     * @param g the graphics context used for rendering
     * @throws RuntimeException if the background image cannot be loaded
     */
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);
	   
	    try {
		    BufferedImage background = ImageIO.read(new File("./res/images/ui/titlescreen.png"));
		    g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);
		} catch (IOException e) {
			throw new RuntimeException("Error loading main menu background image", e);
		}
	}
}
