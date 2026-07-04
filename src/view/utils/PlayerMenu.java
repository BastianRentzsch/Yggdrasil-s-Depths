// Copyright (c) 2026 Bastian Rentzsch

package view.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonModel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicMenuUI;

/**
 * A customized {@link JMenu} with centered text rendering, custom colors,
 * and hover-based visual feedback.
 * <p>
 * This menu overrides the default Swing UI to provide a custom look and feel.
 * The background color changes when the menu is hovered or selected, and the
 * menu text is always centered horizontally. Additionally, a {@link SideBorder}
 * can be applied to one or both sides of the menu.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class PlayerMenu extends JMenu {

	/**
     * Serial version UID for serialization.
     */
	private static final long serialVersionUID = 1L;

	 /**
     * Creates a new {@code PlayerMenu} with the specified label and border configuration.
     *
     * @param text the text displayed by the menu
     * @param bodersides specifies which border side(s) should be painted:
     *                   {@code 0} for left side,
     *                   {@code 1} for right side,
     *                   {@code 2} for both sides
     */
	public PlayerMenu(String text, int bodersides) {
		super(text);

		this.setFont(new Font("SansSerif", Font.BOLD, 22));

		this.setUI(new BasicMenuUI() {
		    @Override
		    protected void paintText(Graphics g, JMenuItem menuItem,
		                             Rectangle textRect, String text) {
		    		FontMetrics fm = g.getFontMetrics();
		        int x = (menuItem.getWidth() - fm.stringWidth(text)) / 2;
		        int y = textRect.y + fm.getAscent();

		        g.setColor(Color.WHITE);
		        g.drawString(text, x, y);
		    }

		    @Override
		    protected void paintBackground(Graphics g, JMenuItem menuItem,
		                                   Color bgColor) {
		        ButtonModel model = menuItem.getModel();

		        if (model.isSelected() || model.isArmed()) {
		            g.setColor(new Color(255, 120, 0)); // Selected-Farbe
		        } else {
		            g.setColor(new Color(41, 37, 36));
		        }

		        g.fillRect(0, 0, menuItem.getWidth(), menuItem.getHeight());
		    }
		});

		this.setBorder(new SideBorder(bodersides));
		this.setHorizontalAlignment(SwingConstants.CENTER);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				((PlayerMenu)e.getSource()).setSelected(true);
			}

			@Override
            public void mouseExited(MouseEvent e) {
				((PlayerMenu)e.getSource()).setSelected(false);
			 }
		});
	}
}
