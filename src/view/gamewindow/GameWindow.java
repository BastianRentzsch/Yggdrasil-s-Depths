// Copyright (c) 2026 Bastian Rentzsch

package view.gamewindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import audio.MusicPlayer;
import controller.DungeonController;
import controller.EncounterSystem;
import controller.GameController;
import controller.PlayerController;
import model.Game;
import model.dungeon.Direction;
import view.GameFrame;
import view.battle.BattleView;
import view.pausemenu.InventoryScreen;
import view.pausemenu.OptionScreen;
import view.pausemenu.StatusScreen;
import view.utils.HealthBar;

/**
 * Main in-game UI container responsible for rendering the dungeon view,
 * player HUD (health bar), and handling real-time player input.
 * <p>
 * This panel connects the {@link model.Game} state with the visual
 * representation and controls gameplay flow such as movement, turning,
 * and triggering encounters.
 * </p>
 * <p>
 * It also initializes and manages additional screens such as inventory,
 * status, and options, and registers keyboard input bindings for gameplay
 * control.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class GameWindow extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Panel responsible for rendering the current dungeon room and player perspective.
	 */
    private DungeonPanel dungeonPanel;

    /**
     * Health bar displaying the player's current and maximum health.
     */
    private HealthBar healthBar;

    /**
     * Reference to the main application frame used for navigation and screen management.
     */
    private GameFrame frame;

    /**
     * Current game instance containing the player, dungeon, items, and game state.
     */
    private Game game;

    /**
     * Creates the main gameplay window.
     * <p>
     * Initializes the dungeon view, player HUD, pause menu screens,
     * and registers all keyboard controls required during gameplay.
     * </p>
     *
     * @param frame the main application frame
     * @param game the active game instance
     */
	public GameWindow(GameFrame frame, Game game) {
		this.game = game;
		this.frame = frame;

		this.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		topPanel.setBackground(new Color(41, 37, 36));

		FlowLayout fl_topPanel = new FlowLayout();
		fl_topPanel.setAlignment(FlowLayout.LEFT);
		topPanel.setLayout(fl_topPanel);
		this.add(topPanel, BorderLayout.NORTH);

		this.setHealthBar(new HealthBar(PlayerController.getMaxHealth(GameController.getPlayer(this.game)),
											PlayerController.getHealth(GameController.getPlayer(this.game))));
		topPanel.add(this.getHealthBar(), FlowLayout.LEFT);

		this.setDungeonPanel(new DungeonPanel(this.game));
		this.add(this.getDungeonPanel(), BorderLayout.CENTER);

        this.setupKeyBindings();

        StatusScreen statusScreen = new StatusScreen(this.frame, this.game);
        this.frame.addCard(statusScreen, GameFrame.STATUS);

        InventoryScreen inventoryScreen = new InventoryScreen(this.game);
        this.frame.addCard(inventoryScreen, GameFrame.INVENTORY);

        OptionScreen optionScreen = new OptionScreen(this.frame, this.game);
        this.frame.addCard(optionScreen, GameFrame.OPTIONS);
	}

	/**
	 * Returns the panel responsible for rendering the dungeon.
	 *
	 * @return the dungeon rendering panel
	 */
	public DungeonPanel getDungeonPanel() {
		return this.dungeonPanel;
	}
	
	/**
	 * Returns the player's health bar.
	 *
	 * @return the health bar component
	 */
	public HealthBar getHealthBar() {
		return this.healthBar;
	}

	/**
	 * Sets the dungeon rendering panel.
	 *
	 * @param dungeonPanel the dungeon panel to display
	 */
	public void setDungeonPanel(DungeonPanel dungeonPanel) {
		this.dungeonPanel = dungeonPanel;
	}
	
	/**
	 * Sets the player's health bar.
	 *
	 * @param healthBar the health bar component
	 */
	public void setHealthBar(HealthBar healthBar) {
		this.healthBar = healthBar;
	}
	
	/**
	 * Requests keyboard focus for the gameplay view.
	 * <p>
	 * This allows the game window to immediately receive keyboard input
	 * for movement and other controls.
	 * </p>
	 */
	public void requestGameFocus() {
		this.getDungeonPanel().requestFocusInWindow();
	}

	/**
	 * Registers the keyboard controls used during gameplay.
	 * <p>
	 * The following controls are available:
	 * <ul>
	 *   <li><b>W</b> – Move forward</li>
	 *   <li><b>S</b> – Move backward</li>
	 *   <li><b>A</b> – Strafe left</li>
	 *   <li><b>D</b> – Strafe right</li>
	 *   <li><b>Q</b> – Turn left</li>
	 *   <li><b>E</b> – Turn right</li>
	 * </ul>
	 * Movement actions automatically trigger a random encounter check.
	 * </p>
	 */
	private void setupKeyBindings() {
	    InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap actionMap = getActionMap();

	    inputMap.put(KeyStroke.getKeyStroke("W"), "moveForward");
	    inputMap.put(KeyStroke.getKeyStroke("S"), "moveBackward");
	    inputMap.put(KeyStroke.getKeyStroke("A"), "moveLeft");
	    inputMap.put(KeyStroke.getKeyStroke("D"), "moveRight");
	    inputMap.put(KeyStroke.getKeyStroke("Q"), "turnLeft");
	    inputMap.put(KeyStroke.getKeyStroke("E"), "turnRight");

	    actionMap.put("moveForward", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		moveForward();
	        		fight();
	        }
	    });

	    actionMap.put("moveBackward", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		moveBackward();
	        		fight();
	        }
	    });

	    actionMap.put("moveLeft", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		moveLeft();
	        		fight();
	        }
	    });

	    actionMap.put("moveRight", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		moveRight();
	        		fight();
	        }
	    });

	    actionMap.put("turnLeft", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		turnLeft();
	        }
	    });

	    actionMap.put("turnRight", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        		turnRight();
	        }
	    });
	}

	/**
	 * Moves the player one tile forward relative to their current facing direction.
	 * <p>
	 * The method checks dungeon boundaries before performing the movement and updates
	 * the dungeon view after a successful move.
	 * </p>
	 */
	private void moveForward() {
	    if ((PlayerController.getX(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.NORTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.NORTH);
	        this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	    else if ((PlayerController.getX(GameController.getPlayer(this.game)) < DungeonController.getXLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.SOUTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.SOUTH);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	    else if ((PlayerController.getY(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.WEST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.WEST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	    else if ((PlayerController.getY(GameController.getPlayer(this.game)) < DungeonController.getYLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.EAST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.EAST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	}

	/**
	 * Moves the player one tile backward relative to their current facing direction.
	 * <p>
	 * The method converts the backward movement into an opposite directional move,
	 * ensuring the player does not leave the dungeon boundaries. The view is updated
	 * after movement.
	 * </p>
	 */
	private void moveBackward() {
		if ((PlayerController.getX(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.SOUTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.NORTH);
	        this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getX(GameController.getPlayer(this.game)) < DungeonController.getXLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.NORTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.SOUTH);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getY(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.EAST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.WEST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getY(GameController.getPlayer(this.game)) < DungeonController.getYLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.WEST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.EAST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	}

	/**
	 * Strafes the player one tile to the left relative to their current facing direction.
	 * <p>
	 * This is a strafe movement that depends on the player's orientation. The dungeon
	 * view is updated after a successful move.
	 * </p>
	 */
	private void moveLeft() {
		if ((PlayerController.getX(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.EAST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.NORTH);
	        this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getX(GameController.getPlayer(this.game)) < DungeonController.getXLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.WEST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.SOUTH);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getY(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.NORTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.WEST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	    else if ((PlayerController.getY(GameController.getPlayer(this.game)) < DungeonController.getYLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.SOUTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.EAST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	}

	/**
	 * Strafes the player one tile to the right relative to their current facing direction.
	 * <p>
	 * This is a strafe movement that depends on the player's orientation. The dungeon
	 * view is updated after a successful move.
	 * </p>
	 */
	private void moveRight() {
		if ((PlayerController.getX(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.WEST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.NORTH);
	        this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getX(GameController.getPlayer(this.game)) < DungeonController.getXLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.EAST)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.SOUTH);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getY(GameController.getPlayer(this.game)) > 0
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.SOUTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.WEST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
		else if ((PlayerController.getY(GameController.getPlayer(this.game)) < DungeonController.getYLength(
						GameController.getDungeon(this.game)) - 1
	    		&& PlayerController.getFacing(GameController.getPlayer(this.game)) == Direction.NORTH)) {
	    	PlayerController.move(GameController.getPlayer(this.game), Direction.EAST);
	    	this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	    }
	}

	/**
	 * Rotates the player 90 degrees to the left and updates the dungeon view.
	 */
	private void turnLeft() {
		PlayerController.turnLeft(GameController.getPlayer(this.game));
		this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	}

	/**
	 * Rotates the player 90 degrees to the right and updates the dungeon view.
	 */
	private void turnRight() {
		PlayerController.turnRight(GameController.getPlayer(this.game));
		this.getDungeonPanel().updateView(GameController.getPlayer(this.game));
	}

	/**
	 * Checks whether a random encounter occurs and starts a battle if necessary.
	 * <p>
	 * When an encounter is triggered, a new {@link BattleView} is created,
	 * added to the main frame, displayed, and initialized by the
	 * {@link EncounterSystem}. The background music is then switched to
	 * the combat theme.
	 * </p>
	 */
	private void fight() {
		if (EncounterSystem.checkForEncounter()) {
			BattleView battleView = new BattleView(this.game);
			
			this.frame.addCard(battleView, GameFrame.BATTLE);
			this.frame.showCard(GameFrame.BATTLE);
			this.frame.toggleIsFighting();
			
			EncounterSystem.battle(this.frame, battleView, battleView.getEnemies(),
					GameController.getPlayer(this.game), battleView.getButtons());
			
			MusicPlayer.playCombat(this.frame.getMusicPlayer());
		}
	}
	
	/**
	 * Updates the health bar to match the player's current health value.
	 */
	public void updatePlayerHealth() {
		this.getHealthBar().setHealth(PlayerController.getHealth(GameController.getPlayer(this.game)));
	}
}
