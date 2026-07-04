// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import view.GameFrame;

/**
 * A lightweight popup dialog used to display short messages to the user.
 * <p>
 * The dialog is undecorated, centered relative to a parent {@link GameFrame},
 * and automatically closes after a fixed duration. It supports different
 * message types that influence the visual styling of the text.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class PopupDialog extends JDialog {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Message type for standard informational messages.
     */
    public static final String PLAIN = "plain";

    /**
     * Message type for error messages.
     */
    public static final String ERROR = "error";

    /**
     * Creates a new popup dialog with the specified message and type.
     *
     * @param frame the parent frame used for positioning the dialog
     * @param message the message text displayed inside the dialog
     * @param messageType the type of message (e.g. {@link #PLAIN}, {@link #ERROR})
     */
	public PopupDialog(GameFrame frame, String message, String messageType) {
		this.setUndecorated(true);
		this.getContentPane().setBackground(new Color(41, 37, 36));
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setAlwaysOnTop(true);
		this.setBounds(100, 100, 450, 100);
		this.setLocationRelativeTo(frame);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(155, 155, 155), 2));
		this.getContentPane().setLayout(new BorderLayout(0, 0));


		JLabel lblMessage = new JLabel(message);
		if (messageType.equals(PopupDialog.PLAIN)) {
			lblMessage.setForeground(new Color(255, 255, 255));
		}
		else if (messageType.equals(PopupDialog.ERROR)) {
			lblMessage.setForeground(new Color(255, 0, 0));
		}
		lblMessage.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		this.getContentPane().add(lblMessage, BorderLayout.CENTER);
	}

	/**
     * Displays a popup dialog that automatically closes after 1 second.
     *
     * @param frame the parent frame used for positioning the dialog
     * @param message the message text to display
     * @param messageType the type of message (e.g. {@link #PLAIN}, {@link #ERROR})
     */
	public static void showDialog(GameFrame frame, String message, String messageType) {
		PopupDialog dialog = new PopupDialog(frame, message, messageType);
	    dialog.setModal(true);

	    Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}

	    });
	    timer.setRepeats(false);
	    timer.start();

	    dialog.setVisible(true);
	}

}
