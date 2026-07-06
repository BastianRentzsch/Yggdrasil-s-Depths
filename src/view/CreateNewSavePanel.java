// Copyright (c) 2026 Bastian Rentzsch

package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import audio.MusicPlayer;
import controller.PlayerController;
import model.Game;
import view.gamewindow.GameWindow;
import view.utils.ArrowPanel;
import view.utils.PopupDialog;
import view.utils.SpriteImagePanel;

/**
 * A panel used for creating a new game save.
 * <p>
 * The panel allows the player to enter a character name, select a gender,
 * and start a new game session. It also supports keyboard navigation
 * for gender selection using the A and D keys.
 * </p>
 *
 * <p>
 * Once the player confirms the creation, the game is initialized,
 * a new player object is created, and the game window is opened.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class CreateNewSavePanel extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Left arrow panel used for gender selection (female option).
     */
    private ArrowPanel arrowLeftPanel;

    /**
     * Right arrow panel used for gender selection (male option).
     */
    private ArrowPanel arrowRightPanel;

    /**
     * Text field used to enter the player's name.
     */
    private JTextField textFieldName;

    /**
	 * Creates a new character creation panel.
	 * <p>
	 * Initializes all UI components required for creating a new game character,
	 * including name input, gender selection, character preview images,
	 * and a confirmation button that starts the game.
	 * </p>
	 *
	 * @param frame the main application frame used for navigation and state control
	 * @param game the game instance that will store the newly created player
	 */
	public CreateNewSavePanel(GameFrame frame, Game game) {
		this.setBackground(new Color(41, 37, 36));
		this.setForeground(new Color(41, 37, 36));
		this.setFocusable(true);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {85, 85, 85, 85, 85, 85, 85, 85, 85};
		gridBagLayout.rowHeights = new int[] {54, 54, 54, 54, 54};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0};
		this.setLayout(gridBagLayout);

		SpriteImagePanel femaleCharacterImagePanel = new SpriteImagePanel(
				"./res/images/sprites/player/female.png", true);
		GridBagConstraints gbc_femaleCharacterImagePanel = new GridBagConstraints();
		gbc_femaleCharacterImagePanel.gridwidth = 3;
		gbc_femaleCharacterImagePanel.gridheight = 5;
		gbc_femaleCharacterImagePanel.insets = new Insets(0, 0, 0, 5);
		gbc_femaleCharacterImagePanel.fill = GridBagConstraints.BOTH;
		gbc_femaleCharacterImagePanel.gridx = 0;
		gbc_femaleCharacterImagePanel.gridy = 0;
		this.add(femaleCharacterImagePanel, gbc_femaleCharacterImagePanel);

		JLabel lblText = new JLabel(
				"<html>Please enter your name and choose your gender, oh brave Adventurer!</html>"
		);
		lblText.setHorizontalTextPosition(SwingConstants.CENTER);
		lblText.setHorizontalAlignment(SwingConstants.CENTER);

		GridBagConstraints gbc_lblText = new GridBagConstraints();
		gbc_lblText.anchor = GridBagConstraints.SOUTH;
		gbc_lblText.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblText.gridwidth = 3;
		gbc_lblText.insets = new Insets(0, 0, 5, 5);
		gbc_lblText.gridx = 3;
		gbc_lblText.gridy = 0;
		this.add(lblText, gbc_lblText);
		lblText.setForeground(new Color(255, 255, 255));
		lblText.setFont(new Font("SansSerif", Font.BOLD, 15));

		SpriteImagePanel characterImagePanel = new SpriteImagePanel("./res/images/sprites/player/male.png",
				true);

		GridBagConstraints gbc_characterImagePanel = new GridBagConstraints();
		gbc_characterImagePanel.gridwidth = 3;
		gbc_characterImagePanel.gridheight = 5;
		gbc_characterImagePanel.fill = GridBagConstraints.BOTH;
		gbc_characterImagePanel.gridx = 6;
		gbc_characterImagePanel.gridy = 0;
		this.add(characterImagePanel, gbc_characterImagePanel);

		this.textFieldName = new JTextField();

		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridwidth = 3;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.gridx = 3;
		gbc_textFieldName.gridy = 1;
		this.add(this.textFieldName, gbc_textFieldName);
		this.textFieldName.setFont(new Font("SansSerif", Font.PLAIN, 15));
		this.textFieldName.setColumns(1);

		this.arrowLeftPanel = new ArrowPanel("arrowLeftOn.png", true);

		GridBagConstraints gbc_lblArrowLeft = new GridBagConstraints();
		gbc_lblArrowLeft.fill = GridBagConstraints.BOTH;
		gbc_lblArrowLeft.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrowLeft.gridx = 3;
		gbc_lblArrowLeft.gridy = 2;
		this.add(this.arrowLeftPanel, gbc_lblArrowLeft);

		JButton btnOk = new JButton("OK");
		btnOk.setContentAreaFilled(false);
		btnOk.setFocusPainted(false);
		btnOk.setForeground(new Color(255, 255, 255));
		btnOk.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = textFieldName.getText().trim();

				if (name.equals("")) {
					PopupDialog.showDialog(frame, "Please enter your name, Adventurer.", PopupDialog.PLAIN);
					return;
				}
				if (name.length() > 20) {
					PopupDialog.showDialog(frame, "Your name can only be 20 letters long, Adventurer.",
							PopupDialog.PLAIN);
					return;
				}

				String gender = "";
				if (arrowLeftPanel.isSelected() && !arrowRightPanel.isSelected()) {
					gender = "female";
				}
				else if (!arrowLeftPanel.isSelected() && arrowRightPanel.isSelected()) {
					gender = "male";
				}

				frame.setGameIsRunning(true);
				
				controller.GameController.setPlayer(game, PlayerController.createNewPlayer(name, gender, game));
				
				GameWindow gameWindow = new GameWindow(frame, game);
				frame.setGameWindow(gameWindow);

				frame.addCard(gameWindow, GameFrame.GAME);
				frame.showCard(GameFrame.GAME);
				
				MusicPlayer.playExploration(frame.getMusicPlayer());
				
				TutorialDialog.showTutorial(frame);
			}
		});

		GridBagConstraints gbc_btnOk = new GridBagConstraints();
		gbc_btnOk.fill = GridBagConstraints.BOTH;
		gbc_btnOk.insets = new Insets(0, 0, 5, 5);
		gbc_btnOk.gridx = 4;
		gbc_btnOk.gridy = 2;
		this.add(btnOk, gbc_btnOk);

		this.arrowRightPanel = new ArrowPanel("arrowRightOff.png", false);

		GridBagConstraints gbc_lblArrowRight = new GridBagConstraints();
		gbc_lblArrowRight.fill = GridBagConstraints.BOTH;
		gbc_lblArrowRight.insets = new Insets(0, 0, 5, 5);
		gbc_lblArrowRight.gridx = 5;
		gbc_lblArrowRight.gridy = 2;
		this.add(this.arrowRightPanel, gbc_lblArrowRight);

		this.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mousePressed(MouseEvent e) {
		        requestFocusInWindow();
		    }
		});

		this.setupKeyBindings();
	}

	/**
	 * Configures the keyboard shortcuts for gender selection.
	 * <p>
	 * Pressing the {@code A} key selects the female character, while pressing
	 * the {@code D} key selects the male character. Keyboard input is ignored
	 * while the player name text field has focus.
	 * </p>
	 */
	private void setupKeyBindings() {
	    InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
	    ActionMap actionMap = getActionMap();

	    inputMap.put(KeyStroke.getKeyStroke("A"), "left");
	    inputMap.put(KeyStroke.getKeyStroke("D"), "right");


	    actionMap.put("left", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
		   if (!arrowLeftPanel.isSelected() && arrowRightPanel.isSelected() && !textFieldName.isFocusOwner()) {
			   arrowLeftPanel.update("arrowLeftOn.png");
			   arrowLeftPanel.repaint();

			   arrowRightPanel.update("arrowRightOff.png");
			   arrowRightPanel.repaint();
		   }
	        }
	    });

	    actionMap.put("right", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
			if (arrowLeftPanel.isSelected() && !arrowRightPanel.isSelected() && !textFieldName.isFocusOwner()) {
			   arrowLeftPanel.update("arrowLeftOff.png");
			   arrowLeftPanel.repaint();

			   arrowRightPanel.update("arrowRightOn.png");
			   arrowRightPanel.repaint();
		   }
	        }
	    });
	}

}
