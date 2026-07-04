// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;

/**
 * A custom toggle button that switches a {@link JFrame} between
 * windowed and fullscreen mode.
 * <p>
 * The button is rendered with a custom UI consisting of a colored background
 * and a sliding white indicator. When selected, the associated frame is
 * switched to fullscreen mode. When deselected, it returns to windowed mode.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class ToggleButton extends JToggleButton {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Creates a new toggle button with the specified initial state.
     *
     * @param selected {@code true} if the button should be initially selected;
     *                 {@code false} otherwise
     */
	public ToggleButton(boolean selected) {
        this.setSelected(selected);
        this.setPreferredSize(new Dimension(80, 40));

        this.setContentAreaFilled(false);
    		this.setBorderPainted(false);
    		this.setFocusPainted(false);

    		this.addItemListener(new ItemListener() {
    			@Override
    			public void itemStateChanged(ItemEvent e) {
    				Component source = (Component) e.getSource();
    				JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(source);

    				if (e.getStateChange() == ItemEvent.SELECTED) {
    					frame.dispose();

    					frame.setExtendedState(Frame.MAXIMIZED_BOTH);
    					frame.setUndecorated(true);

    					frame.setVisible(true);
    				}
    				else {
    					frame.dispose();

    					frame.setExtendedState(Frame.NORMAL);
    					frame.setUndecorated(false);
    					frame.setSize(frame.getPreferredSize());
    					frame.setLocationRelativeTo(null);

    					frame.setVisible(true);
    				}
    			}
    		});
	}

	/**
     * Returns whether this toggle button is currently enabled.
     *
     * @return {@code true} if the toggle is active, otherwise {@code false}
     */
	public boolean isOn() {
		return this.isSelected();
    }

	/**
     * Sets the state of this toggle button.
     *
     * @param selected {@code true} to enable fullscreen mode,
     *                 {@code false} to disable it
     */
	public void setOn(boolean selected) {
		this.setSelected(selected);
        this.repaint();
    }

	/**
     * Paints the custom toggle button component.
     * <p>
     * The background color reflects the current state, and a sliding indicator
     * visually represents the toggle position.
     * </p>
     *
     * @param g the graphics context used for rendering
     */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Background
        g2.setColor(this.isSelected() ? new Color(76, 175, 80) : Color.LIGHT_GRAY);
        g2.fillRect(0, 0, width, height);

        // Slider
        int boxSize = height - 4;
        int boxX = this.isSelected() ? width - boxSize - 2 : 2;

        g2.setColor(Color.WHITE);
        g2.fillRect(boxX, 2, boxSize, boxSize);

        g2.dispose();
    }
}
