// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * A panel that displays a sprite image.
 * <p>
 * The image is loaded from a file path during construction and rendered
 * inside the panel. It is automatically scaled depending on the panel size
 * and can be displayed in different layouts depending on whether it represents
 * the player or another entity.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class SpriteImagePanel extends JPanel {

	/**
	 *  Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Determines whether this sprite represents the player.
     */
	private boolean isPlayer;
	
	/**
     * The image displayed by this panel.
     */
	private BufferedImage spriteImage;

	/**
     * Creates a new {@code SpriteImagePanel} and loads the sprite image
     * from the specified file path.
     *
     * @param imagePath the path to the image file
     * @param isPlayer {@code true} if this sprite represents the player,
     *                 {@code false} otherwise
     */
	public SpriteImagePanel(String imagePath, boolean isPlayer) {
		this.isPlayer = isPlayer;
		try {
			this.spriteImage = ImageIO.read(new File(imagePath));
		}
		catch (IOException e) {
			System.err.println("Fehler beim Laden der Grafik");
		}

		this.setSize(this.spriteImage.getWidth(), this.spriteImage.getHeight());
		this.setMinimumSize(new Dimension(this.spriteImage.getWidth(), this.spriteImage.getHeight()));
		
		this.setOpaque(false);
	}


	/**
     * Paints the sprite image onto this panel.
     * <p>
     * If this panel represents the player, the image is drawn at full size.
     * Otherwise, the image is scaled down and positioned near the bottom center
     * of the panel.
     * </p>
     *
     * @param g the graphics context used for painting
     */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int width;
	    int height;
	    int x;
	    int y;
	    
	    if (this.isPlayer) {
	        width = getWidth();
	        height = getHeight();
	        
	        x = 0;
	        y = 0;
	    }
	    else {
	        width = (int) (getWidth() / 1.5);
	        height = (int) (getHeight() / 1.5);
	        
	        x = (getWidth() - width) / 2;
	        y = getHeight() - height;
	    }

	    g.drawImage(this.spriteImage, x, y, width, height, null);
    }
}
