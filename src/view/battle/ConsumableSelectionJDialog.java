// Copyright (c) 2026 Bastian Rentzsch

package view.battle;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

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

import model.entity.Player;
import model.items.Consumable;
import model.items.Item;
import model.items.Itemtype;
import view.utils.ScrollBarUI;

/**
 * The {@code ConsumableSelectionJDialog} provides a popup dialog that allows the player
 * to select a consumable item during battle.
 * <p>
 * It displays all consumable items in the player's inventory and returns the selected
 * item when confirmed. The dialog is modal and blocks interaction with the battle view
 * until closed.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class ConsumableSelectionJDialog extends JDialog {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The consumable item selected by the player.
	 */
	private Item selectedConsumable;

	/**
	 * Creates a new consumable selection dialog for battle usage.
	 * <p>
	 * Displays all consumable items from the player's inventory and allows selection.
	 * The dialog is centered relative to the battle view and uses a custom styled list.
	 * </p>
	 *
	 * @param parent the battle view that owns this dialog
	 * @param player the player whose inventory is used for selection
	 */
	private ConsumableSelectionJDialog(BattleView parent, Player player) {
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

		List<Item> entries =  player.getInventory().getEquipments(Itemtype.CONSUMABLES);
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
		            label.setText("<html>" + item.getName() + "<br>" + item.getDescription() + "</html>");
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
		this.getContentPane().add(buttonPane, BorderLayout.SOUTH);
		
		JButton btnUse = new JButton("Use");
		btnUse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedConsumable = list.getSelectedValue();
				dispose();
			}
		});
		btnUse.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnUse.setFocusPainted(false);
		btnUse.setContentAreaFilled(false);
		btnUse.setForeground(new Color(255, 255, 255));
		buttonPane.add(btnUse);
		this.getRootPane().setDefaultButton(btnUse);
		
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
	 * Opens the consumable selection dialog and returns the selected item.
	 * <p>
	 * This method blocks until the dialog is closed. If the player confirms a selection,
	 * the selected consumable is returned; otherwise {@code null} may be returned.
	 * </p>
	 *
	 * @param parent the battle view that opens the dialog
	 * @param player the player whose consumables are shown
	 * @return the selected {@link Consumable}, or {@code null} if none was selected
	 */
	public static Consumable showDialog(BattleView parent, Player player) {
		ConsumableSelectionJDialog dialog = new ConsumableSelectionJDialog(parent, player);
	    dialog.setModal(true);
	    dialog.setVisible(true);

	    return (Consumable) dialog.selectedConsumable;
	}
}
