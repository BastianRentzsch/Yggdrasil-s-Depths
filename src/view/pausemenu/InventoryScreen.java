// Copyright (c) 2026 Bastian Rentzsch

package view.pausemenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.GameController;
import controller.ItemController;
import controller.PlayerController;
import model.Game;
import model.items.Item;
import model.items.Itemtype;
import view.utils.ScrollBarUI;

/**
 * InventoryScreen represents the graphical user interface for displaying the
 * player's inventory inside the pause menu.
 *
 * <p>
 * The screen is split into two main sections:
 * <ul>
 *   <li>A scrollable list of all inventory items (left side)</li>
 *   <li>A detailed view of the selected item (right side)</li>
 * </ul>
 * </p>
 *
 * <p>
 * Selecting an item updates the detail panel with its image, name, amount,
 * and description.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class InventoryScreen extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * Creates the inventory screen UI and binds it to the given game instance.
     *
     * <p>
     * This constructor initializes all Swing components, builds the split-pane layout,
     * populates the item list from the player's inventory, and registers a listener
     * to update the item detail view when the selection changes.
     * </p>
     *
     * @param game the current game instance containing the player's inventory
     */
	public InventoryScreen(Game game) {
		this.setLayout(new BorderLayout());

		JSplitPane splitPane = new JSplitPane();
		splitPane.setBackground(new Color(41, 37, 36));
		splitPane.setResizeWeight(0.1);
		splitPane.setDividerSize(0);
		splitPane.setBorder(null);
		splitPane.setEnabled(false);
		this.add(splitPane, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		splitPane.setLeftComponent(scrollPane);

		JPanel detailsPanel = new JPanel();
		detailsPanel.setForeground(new Color(255, 255, 255));
		detailsPanel.setBackground(new Color(41, 37, 36));
		splitPane.setRightComponent(detailsPanel);
		GridBagLayout gbl_detailsPanel = new GridBagLayout();
		gbl_detailsPanel.columnWidths = new int[]{156, 133, 80, 0};
		gbl_detailsPanel.rowHeights = new int[]{51, 224, 0};
		gbl_detailsPanel.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_detailsPanel.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		detailsPanel.setLayout(gbl_detailsPanel);

		JLabel lblItemImage = new JLabel("");
		GridBagConstraints gbc_lblItemImage = new GridBagConstraints();
		gbc_lblItemImage.fill = GridBagConstraints.BOTH;
		gbc_lblItemImage.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemImage.gridx = 0;
		gbc_lblItemImage.gridy = 0;
		detailsPanel.add(lblItemImage, gbc_lblItemImage);

		JLabel lblItemname = new JLabel("");
		lblItemname.setForeground(new Color(255, 255, 255));
		lblItemname.setBackground(new Color(255, 255, 255));
		lblItemname.setFont(new Font("SansSerif", Font.BOLD, 15));
		GridBagConstraints gbc_lblItemname = new GridBagConstraints();
		gbc_lblItemname.anchor = GridBagConstraints.NORTH;
		gbc_lblItemname.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblItemname.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemname.gridx = 1;
		gbc_lblItemname.gridy = 0;
		detailsPanel.add(lblItemname, gbc_lblItemname);

		JLabel lblItemAmount = new JLabel("");
		lblItemAmount.setFont(new Font("SansSerif", Font.PLAIN, 13));
		lblItemAmount.setForeground(new Color(255, 255, 255));
		GridBagConstraints gbc_lblItemAmount = new GridBagConstraints();
		gbc_lblItemAmount.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblItemAmount.insets = new Insets(0, 0, 5, 0);
		gbc_lblItemAmount.gridx = 2;
		gbc_lblItemAmount.gridy = 0;
		detailsPanel.add(lblItemAmount, gbc_lblItemAmount);

		JTextPane textDescription = new JTextPane();
		textDescription.setContentType("text/html");
		textDescription.setEnabled(false);
		textDescription.setFont(new Font("SansSerif", Font.PLAIN, 15));
		textDescription.setBackground(new Color(41, 37, 36));
		textDescription.setForeground(new Color(255, 255, 255));
		textDescription.setEditable(false);
		GridBagConstraints gbc_textDescription = new GridBagConstraints();
		gbc_textDescription.fill = GridBagConstraints.BOTH;
		gbc_textDescription.gridwidth = 3;
		gbc_textDescription.gridx = 0;
		gbc_textDescription.gridy = 1;
		detailsPanel.add(textDescription, gbc_textDescription);

		JList<String> list = new JList<>();
		list.setForeground(new Color(255, 255, 255));
		list.setBackground(new Color(41, 37, 36));
		list.setFont(new Font("SansSerif", Font.PLAIN, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		List<String> entries = new ArrayList<>();
		for (Map.Entry<Item, Integer> e : PlayerController.getInventory(GameController.getPlayer(game))) {
		    entries.add(e.getKey().getName());
		}

		list.setModel(new AbstractListModel<>() {
		    @Override
		    public int getSize() {
		        return entries.size();
		    }

		    @Override
		    public String getElementAt(int index) {
		        return entries.get(index);
		    }
		});
		list.addListSelectionListener(new ListSelectionListener() {
		    @Override
		    public void valueChanged(ListSelectionEvent e) {
		        if (!e.getValueIsAdjusting()) {
		            String selected = list.getSelectedValue();
		            if (selected != null) {
		            		Map.Entry<Item, Integer> result = PlayerController.getItemFromInventory(
									GameController.getPlayer(game), selected);

		            		if (result != null) {
		            			Item item = result.getKey();
		            			int amount = result.getValue();

		            			ImageIcon icon = new ImageIcon(ItemController.getImagePath(item));
		            			lblItemImage.setIcon(icon);

		            			lblItemname.setText(ItemController.getName(item));
			                lblItemAmount.setText("Amount:    " + amount + "x");
			                textDescription.setText(ItemController.getDescription(item));
		            		}
		            }
		        }
		    }
		});


		UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());

		list.setSelectionBackground(new Color(255, 120, 0));
		list.setSelectionForeground(Color.WHITE);

		if (entries.size() > 0) {
		    list.setSelectedIndex(0);
		}
	}
}
