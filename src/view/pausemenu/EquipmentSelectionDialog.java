// Copyright (c) 2026 Bastian Rentzsch

package view.pausemenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractListModel;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import model.Game;
import model.items.Item;
import model.items.Itemtype;
import model.items.equipments.Accessory;
import model.items.equipments.Armor;
import model.items.equipments.Headwear;
import model.items.equipments.Weapon;
import view.utils.ScrollBarUI;

/**
 * The {@code EquipmentSelectionDialog} is a modal popup dialog that allows the player
 * to equip or unequip items from a specific {@link model.items.Itemtype}.
 * <p>
 * It displays all available items of the selected type from the player's inventory
 * and lets the user choose one to equip or remove currently equipped items.
 * </p>
 * <p>
 * The dialog returns both the selected item and whether the user chose to unequip.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class EquipmentSelectionDialog extends JDialog {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
     * The item selected by the user for equipping.
     * <p>
     * This value is {@code null} if the user chose to unequip instead.
     * </p>
     */
    private Item selectedValue;

    /**
     * Indicates whether the user selected the unequip action.
     * <p>
     * If {@code true}, the dialog result indicates that the currently equipped item
     * should be removed.
     * </p>
     */
    private boolean unequipping;

    /**
     * Creates a new equipment selection dialog.
     * <p>
     * The dialog shows all items of the specified type from the player's inventory
     * and allows the user to either equip an item, unequip the current item, or cancel.
     * </p>
     *
     * @param parent the parent {@code StatusScreen} used for positioning the dialog
     * @param game the current game instance containing the player inventory
     * @param type the {@link model.items.Itemtype} to filter items by
     */
	private EquipmentSelectionDialog(StatusScreen parent, Game game, Itemtype type) {
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setType(Type.POPUP);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(155, 155, 155), 2));
		this.setLocationRelativeTo(parent);
		this.getContentPane().setLayout(new BorderLayout());

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(null);
		contentPanel.setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.getVerticalScrollBar().setUI(new ScrollBarUI());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		JList<Item> list = new JList<>();
		list.setForeground(new Color(255, 255, 255));
		list.setBackground(new Color(41, 37, 36));
		list.setFont(new Font("SansSerif", Font.PLAIN, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		List<Item> entries =  game.player.getInventory().getEquipments(type);
		list.setModel(new AbstractListModel<>() {
			@Override
		    public int getSize() {
		        return entries.size();
		    }

		    @Override
		    public Item getElementAt(int index) {
		        return entries.get(index);
		    }
		});

		list.setCellRenderer(new DefaultListCellRenderer() {
		    @Override
		    public Component getListCellRendererComponent(
		            JList<?> list,
		            Object value,
		            int index,
		            boolean isSelected,
		            boolean cellHasFocus) {

		        JLabel label = (JLabel) super.getListCellRendererComponent(
		                list, value, index, isSelected, cellHasFocus);

		        if (value instanceof Item item) {
					if (item instanceof Weapon weapon) {
						label.setText("<html>" + weapon.getName() +"<br>Attack: +" + weapon.getDamage() + "</html>");
					}
					else {
						if (item instanceof Headwear headwear) {
							label.setText("<html>" + headwear.getName() + "<br>Attack: +" + headwear.getDefense()
									+ "</html>");
						}
						else if (item instanceof Armor armor) {
							label.setText("<html>" + armor.getName() + "<br>Attack: +" + armor.getDefense()
									+ "</html>");
						}
						else if (item instanceof Accessory accessory) {
							label.setText("<html>" + accessory.getName() + "<br>Attack: +" + accessory.getDefense()
									+ "</html>");
						}
					}
		        }

		        return label;
		    }
		});

		UIManager.put("List.focusCellHighlightBorder", BorderFactory.createEmptyBorder());

		list.setSelectionBackground(new Color(255, 120, 0));
		list.setSelectionForeground(Color.WHITE);

		if (entries.size() > 0) {
		    list.setSelectedIndex(0);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(41, 37, 36));
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton btnEquip = new JButton("Equip");
		btnEquip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedValue = list.getSelectedValue();
				unequipping = false;
				dispose();
			}
		});
		btnEquip.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnEquip.setFocusPainted(false);
		btnEquip.setContentAreaFilled(false);
		btnEquip.setForeground(new Color(255, 255, 255));
		buttonPane.add(btnEquip);
		this.getRootPane().setDefaultButton(btnEquip);

		JButton btnUnequip = new JButton("Unequip");
		btnUnequip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedValue = null;
				unequipping = true;
				dispose();
			}
		});
		btnUnequip.setForeground(Color.WHITE);
		btnUnequip.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnUnequip.setFocusPainted(false);
		btnUnequip.setContentAreaFilled(false);
		buttonPane.add(btnUnequip);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnCancel.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setFocusPainted(false);
		buttonPane.add(btnCancel);
	}

	/**
	 * Displays the equipment selection dialog and waits for user input.
	 * <p>
	 * The returned entry contains:
	 * <ul>
	 *   <li>key: the selected {@link model.items.Item} (or {@code null} if unequipping)</li>
	 *   <li>value: {@code true} if the user chose to unequip, otherwise {@code false}</li>
	 * </ul>
	 * </p>
	 *
	 * @param parent the parent {@code StatusScreen} used for positioning
	 * @param game the current game instance containing inventory data
	 * @param type the {@link model.items.Itemtype} to filter equipment by
	 * @return a map entry containing the selected item and unequip flag
	 */
	public static Map.Entry<Item, Boolean> showDialog(StatusScreen parent, Game game, Itemtype type) {
	    EquipmentSelectionDialog dialog = new EquipmentSelectionDialog(parent, game, type);
	    dialog.setModal(true);
	    dialog.setVisible(true);

	    return new AbstractMap.SimpleEntry<>(dialog.selectedValue, dialog.unequipping);
	}
}
