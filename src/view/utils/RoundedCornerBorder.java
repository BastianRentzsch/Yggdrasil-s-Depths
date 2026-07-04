// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

/**
 * A custom Swing border that draws a rounded rectangle around a component.
 * <p>
 * The border uses anti-aliasing to create smooth edges and rounded corners.
 * It also adjusts the component insets to ensure proper spacing between the
 * border and the component's content.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class RoundedCornerBorder extends AbstractBorder {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;


	/**
     * Paints the rounded border around the specified component.
     *
     * @param c the component for which this border is being painted
     * @param g the graphics context used for painting
     * @param x the x-coordinate of the border
     * @param y the y-coordinate of the border
     * @param width the width of the border area
     * @param height the height of the border area
     */
	@Override
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		Graphics2D g2 = (Graphics2D)g.create();
		g2.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		RoundRectangle2D round = new RoundRectangle2D.Float(x, y, width - 1, height - 1, height, height);
		Container parent = c.getParent();

		if(parent!=null) {
			g2.setColor(new Color(213, 208, 209, 0));
			Area corner = new Area(new Rectangle2D.Float(x, y, width, height));
			corner.subtract(new Area(round));
			g2.fill(corner);
		}

		g2.setColor(Color.BLACK);
		g2.draw(round);
		g2.dispose();
	}

	/**
     * Returns the default border insets for this rounded border.
     *
     * @param c the component for which insets are requested
     * @return the insets defining the border padding
     */
	@Override
	public Insets getBorderInsets(Component c) {
		return new Insets(3, 8, 2, 8);
	}

	/**
     * Updates and returns the provided {@code Insets} object with the border insets.
     *
     * @param c the component for which insets are requested
     * @param insets the insets object to be modified
     * @return the modified insets object
     */
	@Override
	public Insets getBorderInsets(Component c, Insets insets) {
		insets.left = insets.right = 8;
		insets.top = insets.bottom = 2;
		return insets;
	}
}
