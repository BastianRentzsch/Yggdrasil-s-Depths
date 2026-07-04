// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.border.AbstractBorder;

/**
 * A custom border that paints vertical white lines on one or both sides
 * of a Swing component.
 * <p>
 * The side(s) of the border are determined by a numeric configuration value:
 * <ul>
 *   <li>{@code 0} - left side only</li>
 *   <li>{@code 1} - right side only</li>
 *   <li>{@code 2} - both left and right sides</li>
 * </ul>
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class SideBorder extends AbstractBorder {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Determines which side(s) of the border are rendered.
     */
	private final int bordersides;

	/**
     * Creates a new {@code SideBorder} with the specified side configuration.
     *
     * @param bordersides the border side configuration:
     *                    {@code 0} for left,
     *                    {@code 1} for right,
     *                    {@code 2} for both sides
     */
    public SideBorder(int bordersides) {
        this.bordersides = bordersides;
    }

    /**
     * Paints the border for the given component.
     *
     * @param c the component being painted
     * @param g the graphics context used for painting
     * @param x the x-coordinate of the border area
     * @param y the y-coordinate of the border area
     * @param width the width of the border area
     * @param height the height of the border area
     */
    @Override
    public void paintBorder(Component c, Graphics g,
                            int x, int y, int width, int height) {
    		g.setColor(Color.WHITE);
    		if (this.bordersides == 0) { // Left
    			g.drawLine(0, 0, 0, height - 1);
    		}
    		else if (this.bordersides == 1) { // Right
    			g.drawLine(width - 1, 0, width - 1, height - 1);
    		}
    		else if (this.bordersides == 2) { // Left and Right
    			g.drawLine(0, 0, 0, height - 1);
    			g.drawLine(width - 1, 0, width - 1, height - 1);
    		}
    }
}
