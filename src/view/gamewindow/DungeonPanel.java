// Copyright (c) 2026 Bastian Rentzsch

package view.gamewindow;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import controller.GameController;
import controller.PlayerController;
import model.Game;
import model.entity.Player;
import java.awt.Color;

/**
 * A Swing panel responsible for rendering the dungeon view as a scaled
 * image inside the game window.
 * <p>
 * This panel uses {@link DungeonImage} to generate a first-person view
 * of the dungeon based on the player's position and facing direction.
 * The resulting image is scaled to fit the panel while preserving aspect
 * ratio and applying a margin in fullscreen mode.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class DungeonPanel extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The currently rendered dungeon image.
     */
    private BufferedImage currentDungeonImage;

    /**
     * Renderer responsible for generating dungeon images.
     */
    private DungeonImage dungeonImage;

    /**
     * Creates a new dungeon panel and initializes the first rendered view.
     *
     * @param game the game instance containing dungeon and player state
     */
	public DungeonPanel(Game game) {
		this.setBackground(new Color(41, 37, 36));
		this.dungeonImage = new DungeonImage(GameController.getDungeon(game));

		this.currentDungeonImage = this.dungeonImage.paintCurrentView(
				PlayerController.getX(GameController.getPlayer(game)),
				PlayerController.getY(GameController.getPlayer(game)),
				PlayerController.getFacing(GameController.getPlayer(game)));
	}
	
	/**
     * Returns the currently rendered dungeon image.
     *
     * @return the current dungeon view image
     */
	public BufferedImage getCurrentDungeonImage() {
		return this.currentDungeonImage;
	}

	/**
     * Paints the dungeon image scaled to fit the panel while preserving
     * aspect ratio.
     *
     * @param g the graphics context used for painting
     */
	@Override
	protected void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    if (this.currentDungeonImage == null) {
			return;
		}

	    double aspectRatio = (double) this.currentDungeonImage.getWidth() / this.currentDungeonImage.getHeight();

		int panelWidth = getWidth();
		int panelHeight = getHeight();

		boolean fullscreen = panelWidth > 1000 || panelHeight > 700;

		// When Full Screen 15% border
		double scaleFactor = fullscreen ? 0.85 : 1.0;

		int drawWidth = (int) (panelWidth * scaleFactor);
		int drawHeight = (int) (drawWidth / aspectRatio);

		if (drawHeight > panelHeight * scaleFactor) {
		    drawHeight = (int) (panelHeight * scaleFactor);
		    drawWidth = (int) (drawHeight * aspectRatio);
		}

		int x = (panelWidth - drawWidth) / 2;
		int y = (panelHeight - drawHeight) / 2;

		g.drawImage(this.currentDungeonImage, x, y, drawWidth, drawHeight, this);
	}

	/**
     * Updates the rendered dungeon view based on the player's new state.
     *
     * @param player the player whose position and direction determine the view
     */
	public void updateView(Player player) {
		this.currentDungeonImage = this.dungeonImage.paintCurrentView(
				PlayerController.getX(player),
				PlayerController.getY(player),
				PlayerController.getFacing(player));

		this.revalidate();
		this.repaint();
	}

}
