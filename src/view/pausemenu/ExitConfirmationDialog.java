// Copyright (c) 2026 Bastian Rentzsch

package view.pausemenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The {@code ExitConfirmationDialog} is a modal confirmation window used to
 * ask the player whether they really want to exit the game or return to the
 * main menu.
 * <p>
 * It blocks interaction with the rest of the application until the user
 * explicitly confirms or cancels the action.
 * </p>
 * <p>
 * Depending on the context, the dialog shows a warning message about losing
 * unsaved progress.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class ExitConfirmationDialog extends JDialog {

	/**
	 *  Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Indicates whether the user confirmed the exit action.
	 * <p>
	 * This value is set to {@code true} when the user clicks "OK"
	 * and {@code false} when the user cancels the dialog.
	 * </p>
	 */
	private boolean exit;

	/**
	 * Creates a modal exit confirmation dialog.
	 * <p>
	 * The dialog asks the user to confirm either exiting the game entirely
	 * or returning to the main menu. It displays a warning about unsaved progress
	 * and provides "OK" and "Cancel" buttons.
	 * </p>
	 *
	 * @param parent the parent {@code OptionScreen} used to center the dialog
	 * @param exitGame if {@code true}, the dialog warns about exiting the game;
	 *                 if {@code false}, it warns about returning to the main menu
	 */
	public ExitConfirmationDialog(OptionScreen parent, boolean exitGame) {
		this.setType(Type.POPUP);
		this.setUndecorated(true);
		this.setResizable(false);
		this.setAlwaysOnTop(true);
		this.setBounds(100, 100, 476, 108);
		this.setLocationRelativeTo(parent);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(155, 155, 155), 2));
		getContentPane().setLayout(new BorderLayout());

		JPanel panel = new JPanel();
		panel.setBackground(new Color(41, 37, 36));
		panel.setForeground(new Color(255, 255, 255));
		getContentPane().add(panel, BorderLayout.CENTER);

		JLabel lblWarning = new JLabel();
		lblWarning.setForeground(new Color(255, 255, 255));
		lblWarning.setFont(new Font("SansSerif", Font.BOLD, 20));
		if (exitGame) {
			lblWarning.setText(
					"<html>Are you sure you want to exit the game? <br>All unsaved progress will be lost.<html>"
			);
		}
		else {
			lblWarning.setText(
					"<html>Are you sure you want to exit to the main menu? <br>All unsaved progress will be lost.<html>"
			);
		}
		panel.add(lblWarning);

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(41, 37, 36));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit = true;
				dispose();
			}
		});
		btnOk.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnOk.setFocusPainted(false);
		btnOk.setContentAreaFilled(false);
		btnOk.setForeground(new Color(255, 255, 255));
		btnOk.setActionCommand("OK");
		buttonPane.add(btnOk);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFocusPainted(false);
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				exit = false;
				dispose();
			}
		});
		btnCancel.setActionCommand("Cancel");
		buttonPane.add(btnCancel);
	}

	/**
	 * Returns whether the user confirmed the exit action.
	 *
	 * @return {@code true} if the user confirmed exiting, {@code false} otherwise
	 */
	private boolean getExit() {
		return this.exit;
	}

	/**
	 * Displays the exit confirmation dialog and waits for user input.
	 *
	 * @param parent the parent {@code OptionScreen} used for positioning the dialog
	 * @param exitGame if {@code true}, the dialog warns about exiting the game;
	 *                 if {@code false}, it warns about returning to the main menu
	 * @return {@code true} if the user confirmed the exit action, {@code false} otherwise
	 */
	public static boolean showDialog(OptionScreen parent, boolean exitGame) {
		ExitConfirmationDialog dialog = new ExitConfirmationDialog(parent, exitGame);
	    dialog.setModal(true);
	    dialog.setVisible(true);
	    return dialog.getExit();
	}

}
