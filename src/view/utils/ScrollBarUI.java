// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * A custom implementation of {@link BasicScrollBarUI} that provides a
 * styled appearance for Swing scroll bars.
 * <p>
 * This UI customization changes the scrollbar colors, sets a fixed width,
 * and replaces the default increase/decrease arrow buttons with custom
 * image-based buttons.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class ScrollBarUI extends BasicScrollBarUI {

	/**
     * Creates a custom arrow button using an image icon.
     *
     * @param imagePath the file path of the image used as the button icon
     * @return a styled {@link JButton} used as a scrollbar arrow button
     */
	private static JButton createArrowButton(String imagePath) {
	    JButton button = new JButton(new ImageIcon(imagePath));

	    button.setBackground(new Color(41, 37, 36));
	    button.setBorderPainted(false);
	    button.setFocusPainted(false);
	    button.setContentAreaFilled(false);
	    button.setOpaque(true);

	    return button;
	}

	/**
     * Configures the appearance of the scrollbar, including colors and width.
     */
	@Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color(255, 120, 0);
        this.trackColor = new Color(41, 37, 36);
        this.scrollBarWidth = 25;
    }

	/**
     * Creates the decrease (up/left) button for the scrollbar.
     *
     * @param orientation the orientation of the scrollbar
     * @return a customized decrease button with an image icon
     */
	 @Override
	protected JButton createDecreaseButton(int orientation) {
	    return ScrollBarUI.createArrowButton("./res/images/ui/arrowUp.png");
	}

	 /**
     * Creates the increase (down/right) button for the scrollbar.
     *
     * @param orientation the orientation of the scrollbar
     * @return a customized increase button with an image icon
     */
	@Override
	protected JButton createIncreaseButton(int orientation) {
	    return ScrollBarUI.createArrowButton("./res/images/ui/arrowDown.png");
	}
}