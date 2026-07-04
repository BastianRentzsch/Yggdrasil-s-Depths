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
import java.util.ArrayList;
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

/**
 * A dialog that allows the player to select an enemy target during a battle.
 * <p>
 * Displays all currently visible enemies in a selectable list and returns the
 * chosen {@link EnemyPanel} when confirmed.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class AttackSelectionDialog extends JDialog {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The enemy panel selected by the player.
	 */
	private EnemyPanel selectedEnemy;

	/**
	 * Creates a new attack selection dialog.
	 *
	 * @param parent the battle view used as reference for dialog positioning
	 * @param enemyPanels the list of enemy panels available for selection
	 */
	private AttackSelectionDialog(BattleView parent, List<EnemyPanel> enemyPanels) {
		this.setUndecorated(true);
		this.setAlwaysOnTop(true);
		this.setType(Type.POPUP);
		this.setResizable(false);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setBounds(100, 100, 450, 120);
		this.getRootPane().setBorder(BorderFactory.createLineBorder(new Color(155, 155, 155), 2));
		this.setLocationRelativeTo(parent);
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(null);
		contentPanel.setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		contentPanel.add(scrollPane, BorderLayout.CENTER);

		JList<EnemyPanel> list = new JList<>();
		list.setForeground(new Color(255, 255, 255));
		list.setBackground(new Color(41, 37, 36));
		list.setFont(new Font("SansSerif", Font.PLAIN, 15));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(list);

		List<EnemyPanel> entries = new ArrayList<>();
		for (EnemyPanel panel : enemyPanels) {
		    if (panel.isVisible()) {
		        entries.add(panel);
		    }
		}
		list.setModel(new AbstractListModel<>() {
			@Override
		    public int getSize() {
		        return entries.size();
		    }

		    @Override
		    public EnemyPanel getElementAt(int index) {
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

		        if (value instanceof EnemyPanel enemyPanel) {
		            label.setText(enemyPanel.getEnemy().getName());
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
		
		JButton btnAttack = new JButton("Attack");
		btnAttack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				selectedEnemy = list.getSelectedValue();
				dispose();
			}
		});
		btnAttack.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAttack.setFocusPainted(false);
		btnAttack.setContentAreaFilled(false);
		btnAttack.setForeground(new Color(255, 255, 255));
		buttonPane.add(btnAttack);
		this.getRootPane().setDefaultButton(btnAttack);
		
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
	 * Opens the dialog and returns the selected enemy after the user confirms.
	 *
	 * @param parent the battle view that owns this dialog
	 * @param enemyPanels the list of enemy panels to choose from
	 * @return the selected {@link EnemyPanel}, or {@code null} if none was selected
	 */
	public static EnemyPanel showDialog(BattleView parent, List<EnemyPanel> enemyPanels) {
		AttackSelectionDialog dialog = new AttackSelectionDialog(parent, enemyPanels);
		dialog.setModal(true);
	    dialog.setVisible(true);
	    
	    return dialog.selectedEnemy;
	}

}
