// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A custom panel that displays an arrow image.
 * <p>
 * The image is loaded from a file path and rendered to fill the entire panel
 * area. The arrow can visually represent a selected or unselected state,
 * which can be toggled by updating its state.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class ArrowPanel extends JPanel {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	/**
     * Relative file name of the arrow image.
     */
    private String path;

    /**
     * Indicates whether this arrow is currently selected.
     */
    private boolean isSelected;

    /**
     * Creates a new {@code ArrowPanel} with the specified image path and selection state.
     *
     * @param path the relative path of the image inside the resources folder
     * @param isSelected whether the arrow is initially selected
     */
	public ArrowPanel(String path, boolean isSelected) {
		this.path = path;
		this.isSelected = isSelected;

		this.setOpaque(false);
	}

	/**
     * Paints the arrow image onto this panel.
     * <p>
     * The image is loaded from disk every time the component is repainted
     * and scaled to fill the panel bounds.
     * </p>
     *
     * @param g the graphics context used for painting
     */
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    try {
			BufferedImage arrow = ImageIO.read(new File("./res/images/ui/" + this.path));

			g.drawImage(arrow, 0, 0, this.getWidth(), this.getHeight(), this);
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
     * Updates the arrow image and toggles the selection state.
     *
     * @param path the new image path
     */
	public void update(String path) {
		this.path = path;
		this.isSelected = !this.isSelected;
	}

	/**
     * Returns whether this arrow is currently selected.
     *
     * @return {@code true} if the arrow is selected, otherwise {@code false}
     */
	public boolean isSelected() {
		return this.isSelected;
	}
}
