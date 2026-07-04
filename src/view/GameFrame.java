// Copyright (c) 2026 Bastian Rentzsch

package view;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import audio.MusicPlayer;
import view.gamewindow.GameWindow;
import view.utils.PlayerMenu;

/**
 * Main application window of the game.
 * <p>
 * This frame manages the entire UI using a {@link CardLayout}, allowing switching
 * between different screens such as the main menu, game view, inventory, status,
 * options, and battle view.
 * It also controls the pause menu bar and global input handling (e.g., ESC key).
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class GameFrame extends JFrame {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Indicates whether a game session is currently running.
     */
    private boolean gameIsRunning;

    /**
     * Indicates whether a battle is currently active.
     */
    private boolean isFighting = false;

    /**
     * Music player responsible for background music and menu themes.
     */
    private MusicPlayer musicPlayer = new MusicPlayer();
    
    /**
     * Reference to the active game window.
     */
    private GameWindow window;

    /**
     * Menu bar used for the in-game pause menu (status, inventory, options).
     */
    private JMenuBar menuBar;

    /**
     * Layout manager used to switch between different screens (cards).
     */
    private CardLayout cardLayout;

    /**
     * Container panel that holds all screens managed by the CardLayout.
     */
    private JPanel cards;

    /**
     * Identifier for the main menu screen.
     */
    public static final String MAIN_MENU = "main";

    /**
     * Identifier for the new save / character creation screen.
     */
    public static final String NEW_SAVE = "new";

    /**
     * Identifier for the main gameplay screen.
     */
    public static final String GAME = "game";

    /**
     * Identifier for the status screen.
     */
    public static final String STATUS = "status";

    /**
     * Identifier for the inventory screen.
     */
    public static final String INVENTORY = "inventory";

    /**
     * Identifier for the options screen.
     */
    public static final String OPTIONS = "options";

    /**
     * Identifier for the battle screen.
     */
    public static final String BATTLE = "battle";

    /**
     * Creates the main game frame and initializes all UI components.
     * <p>
     * This includes:
     * <ul>
     *   <li>Window configuration (size, close operation, etc.)</li>
     *   <li>Pause menu setup (status, inventory, options)</li>
     *   <li>CardLayout screen system</li>
     *   <li>Global ESC key handler for toggling the pause menu</li>
     * </ul>
     * </p>
     */
	public GameFrame() {
		GameFrame thisFrame = this;
		this.gameIsRunning = false;
		
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.setTitle("Yggdrasil’s Depths");
		
		this.setSize(798, 455);
		this.setMinimumSize(new Dimension(798, 455));
		this.setPreferredSize(new Dimension(798, 455));

		this.setResizable(false);
		this.setLocationRelativeTo(null);

		this.setPauseMenuBar(new JMenuBar());
		this.getPauseMenuBar().setLayout(new GridLayout(1,3));
		this.getPauseMenuBar().setVisible(false);
		this.setJMenuBar(this.getPauseMenuBar());

		PlayerMenu menuStatus = new PlayerMenu("Status", 1);
		menuStatus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisFrame.showCard(GameFrame.STATUS);
			}
		});
		this.getPauseMenuBar().add(menuStatus);

		PlayerMenu menuInventory = new PlayerMenu("Inventory", 2);
		menuInventory.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisFrame.showCard(GameFrame.INVENTORY);
			}
		});
		this.getPauseMenuBar().add(menuInventory);

		PlayerMenu menuOptions = new PlayerMenu("Options", 0);
		menuOptions.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				thisFrame.showCard(GameFrame.OPTIONS);
			}
		});
		this.getPauseMenuBar().add(menuOptions);

		this.cardLayout = new CardLayout();
		this.cards = new JPanel(this.cardLayout);
		this.cards.add(new MainMenu(this), GameFrame.MAIN_MENU);
		this.add(this.cards);

        this.setFocusTraversalKeysEnabled(false);

    		this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
					.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "toggleMenu");
    		this.getRootPane().getActionMap().put("toggleMenu", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (thisFrame.gameIsRunning && !thisFrame.isFighting) {
					if (thisFrame.getPauseMenuBar().isVisible()) {
						thisFrame.getPauseMenuBar().setVisible(false);

						thisFrame.showCard(GameFrame.GAME);
						thisFrame.getWindow().requestGameFocus();
						
						MusicPlayer.playExploration(thisFrame.musicPlayer);
					}
					else {
		            	thisFrame.getPauseMenuBar().setVisible(true);

		            	thisFrame.showCard(GameFrame.STATUS);
		            	
		            	MusicPlayer.playOtherMenus(thisFrame.musicPlayer);
					}
				}
			}
		});

		this.setVisible(true);
		
		MusicPlayer.playMainMenu(this.musicPlayer);
	}

	/**
     * Returns the pause menu bar.
     *
     * @return the JMenuBar used for pause menu navigation
     */
	public JMenuBar getPauseMenuBar() {
		return this.menuBar;
	}

	/**
     * Returns the active game window.
     *
     * @return the current GameWindow instance
     */
	public GameWindow getWindow() {
		return this.window;
	}
	
	/**
	 * Returns the music player used to manage the game's background music
	 * and audio playback.
	 *
	 * @return the active {@link MusicPlayer} instance
	 */
	public MusicPlayer getMusicPlayer() {
		return this.musicPlayer;
	}
	
	/**
     * Sets the pause menu bar.
     *
     * @param menuBar the menu bar to set
     */
	public void setPauseMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	/**
     * Sets the active game window.
     *
     * @param window the GameWindow to set
     */
	public void setWindow(GameWindow window) {
		this.window = window;
	}
	
	/**
     * Shows a specific screen (card) in the UI.
     *
     * @param name identifier of the screen to display
     */
	public void showCard(String name) {
		this.cardLayout.show(this.cards, name);
	}

	/**
     * Adds a new screen to the CardLayout container.
     *
     * @param panel the panel to add
     * @param name  identifier used to access this panel
     */
	public void addCard(JPanel panel, String name) {
		this.cards.add(panel, name);
	}

	/**
	 * Sets the active game window.
	 *
	 * @param gameWindow the GameWindow that represents the gameplay view
	 */
	public void setGameWindow(GameWindow gameWindow) {
		this.setWindow(gameWindow);
	}

	/**
	 * Marks whether a game session is currently running.
	 * <p>
	 * If the game is stopped, the pause menu is automatically hidden.
	 * </p>
	 *
	 * @return gameIsRunning {@code true} if a game session is active,
	 *                      {@code false} otherwise
	 */
	public boolean isGameRunning() {
		return this.gameIsRunning;
	}

	/**
     * Updates the game running state.
     * <p>
     * If the game is stopped, the pause menu is automatically hidden.
     * </p>
     *
     * @param gameIsRunning true to mark the game as running, false otherwise
     */
	public void setGameIsRunning(boolean gameIsRunning) {
		if (this.gameIsRunning != gameIsRunning) {
			this.gameIsRunning = gameIsRunning;
		}
		if (!this.gameIsRunning) {
			this.getPauseMenuBar().setVisible(false);
		}
	}

	/**
	 * Toggles the current battle state.
	 * <p>
	 * While a battle is active, the pause menu cannot be opened via
	 * the ESC key.
	 * </p>
	 */
	public void toggleIsFighting() {
		this.isFighting = !this.isFighting;
	}

	/**
	 * Requests keyboard focus after the frame has been added to the
	 * native window system.
	 * <p>
	 * This ensures that global key bindings, such as the ESC key for
	 * opening the pause menu, are immediately available.
	 * </p>
	 */
	@Override
	public void addNotify() {
	    super.addNotify();
	    this.requestFocusInWindow();
	}

}
