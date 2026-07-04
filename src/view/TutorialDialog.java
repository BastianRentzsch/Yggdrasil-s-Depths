// Copyright (c) 2026 Bastian Rentzsch

package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * A modal tutorial dialog that displays basic game controls and instructions
 * to the player before starting the game.
 * <p>
 * The dialog is undecorated, always on top, and styled to match the game's UI theme.
 * It provides a simple HTML-based text area and an OK button to close the window.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class TutorialDialog extends JDialog {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new tutorial dialog.
	 * <p>
	 * The dialog is centered relative to the parent {@link GameFrame},
	 * displays game controls, and blocks input to other windows while open.
	 * </p>
	 *
	 * @param parent the parent game frame used for positioning the dialog
	 */
	private TutorialDialog(GameFrame parent) {
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setType(Type.POPUP);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 370);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(155, 155, 155), 2));
		this.setLocationRelativeTo(parent);
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBackground(new Color(41, 37, 36));
		contentPanel.setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JTextPane textPane = new JTextPane();
		textPane.setContentType("text/html");
		textPane.setEditable(false);
		textPane.setBackground(new Color(41, 37, 36));
		textPane.setForeground(Color.WHITE);
		textPane.setBorder(new EmptyBorder(10, 10, 10, 10));

		textPane.setText("""
		<html>
		  <body style='font-family:SansSerif; font-size:12px; color:white;'>
		    <p>Welcome!</p>
		    <p>Before you begin, here are the basic controls:</p>
		    <ul>
		      <li><b>W</b> – Move Forward</li>
		      <li><b>A</b> – Move Left</li>
		      <li><b>S</b> – Move Backward</li>
		      <li><b>D</b> – Move Right</li>
		      <li><b>Q</b> – Turn Left</li>
		      <li><b>E</b> – Turn Right</li>
		      <li><b>ESC</b> – Open the Pause Menu</li>
		    </ul>
		    <p>
		      Use these controls to move around the game world and press <b>ESC</b>
		      at any time to access the pause menu.
		    </p>
		    <p>Have fun!</p>
		  </body>
		</html>
		""");

		contentPanel.add(textPane, BorderLayout.CENTER);
		
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(41, 37, 36));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		okButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
		okButton.setFocusPainted(false);
		okButton.setContentAreaFilled(false);
		okButton.setForeground(new Color(255, 255, 255));
		buttonPane.add(okButton);
	}

	/**
	 * Displays the tutorial dialog modally.
	 * <p>
	 * This method creates a new instance of {@code TutorialDialog}
	 * and blocks user interaction with other windows until the dialog is closed.
	 * </p>
	 *
	 * @param parent the parent {@link GameFrame} used for positioning the dialog
	 */
	public static void showTutorial(GameFrame parent) {
		TutorialDialog dialog = new TutorialDialog(parent);
		dialog.setModal(true);
	    dialog.setVisible(true);
	}
}
