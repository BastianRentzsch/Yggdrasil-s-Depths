// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

/**
 * A custom health bar component that visually represents an entity's
 * current and maximum health.
 * <p>
 * The health bar is rendered with a rounded background and a red fill
 * indicating the current health percentage. It also displays textual
 * information ("HP" and current/maximum health) centered and aligned
 * within the bar.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class HealthBar extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The maximum health value represented by this bar.
     */
    private int maxHealth;

    /**
     * The current health value displayed by this bar.
     */
    private int health;

    /**
     * Creates a new {@code HealthBar} with the specified maximum and current health.
     *
     * @param maxHealth the maximum health value
     * @param health the initial current health value
     */
    public HealthBar(int maxHealth, int health) {
    		this.setBorder(new RoundedCornerBorder());
    		this.setPreferredSize(new Dimension(300, 25));
    		this.setOpaque(false);
    		this.maxHealth = maxHealth;
    		this.health = health;
    }

    /**
     * Updates the current health value and repaints the component.
     *
     * @param health the new health value (automatically clamped to valid range)
     */
    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(this.maxHealth, health));
        repaint();
    }

    /**
     * Updates the maximum health value and repaints the component.
     *
     * @param maxHealth the new maximum health value
     */
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        repaint();
    }

    /**
     * Paints the health bar component.
     * <p>
     * The bar consists of a rounded background, a red fill representing
     * current health, and overlayed text showing "HP" and the numeric
     * health values.
     * </p>
     *
     * @param g the graphics context used for painting
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        Shape clip = new RoundRectangle2D.Float(
                0, 0, width - 1, height - 1, height, height);

        g2.setClip(clip);

        // Background
        g2.setColor(Color.GRAY);
        g2.fillRoundRect(0, 0, width, height, 7, 7);

        // Health fill
        int hpWidth = width * this.health / this.maxHealth;
        g2.setColor(Color.RED);
        g2.fillRoundRect(0, 0, hpWidth, height, 7, 7);

        g2.dispose();

        // Left label ("HP")
        String text_HP = "HP";

        int textX = 5; // Distance from left edge
        int textY = (height + g.getFontMetrics().getAscent() - g.getFontMetrics().getDescent()) / 2;

        g.setColor(Color.BLACK);
        g.drawString(text_HP, textX + 1, textY + 1);

        g.setColor(Color.WHITE);
        g.drawString(text_HP, textX, textY);

        // Centered health text
        String text_amount = this.health + "/" + this.maxHealth;

        textX = (width - g.getFontMetrics().stringWidth(text_amount)) / 2;
        textY = (height + g.getFontMetrics().getAscent() - g.getFontMetrics().getDescent()) / 2;

        g.setColor(Color.BLACK);
        g.drawString(text_amount, textX + 1, textY + 1);

        g.setColor(Color.WHITE);
        g.drawString(text_amount, textX, textY);
    }
}