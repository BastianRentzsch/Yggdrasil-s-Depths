// Copyright (c) 2026 Bastian Rentzsch

package view.pausemenu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import model.Game;
import model.items.Item;
import model.items.Itemtype;
import model.items.equipments.Accessory;
import model.items.equipments.Armor;
import model.items.equipments.EquipmentSlot;
import model.items.equipments.Headwear;
import model.items.equipments.Weapon;
import view.GameFrame;

/**
 * The {@code EquipmentPanel} represents the equipment management UI in the pause menu.
 * <p>
 * It displays the currently equipped items of the player (headwear, armor, accessory, weapon)
 * and allows the user to equip or unequip items via interaction with the
 * {@link EquipmentSelectionDialog}.
 * </p>
 * <p>
 * The panel also updates the player's stats and refreshes the inventory view
 * whenever equipment changes occur.
 * </p>
 * 
 * @author Bastian Rentzsch
 * @since 2026
 */
public class EquipmentPanel extends JPanel {

	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Image representing the currently equipped headwear item.
	 */
	private BufferedImage headwearImage;

	/**
	 * Image representing the currently equipped armor item.
	 */
	private BufferedImage armorImage;

	/**
	 * Image representing the currently equipped accessory item.
	 */
	private BufferedImage accessoryImage;

	/**
	 * Image representing the currently equipped weapon item.
	 */
	private BufferedImage weaponImage;

	/**
	 * Creates an {@code EquipmentPanel} and initializes all equipment slots UI.
	 *
	 * @param frame       the main game frame used for switching views
	 * @param statsPanel  the stats panel that will be updated when equipment changes
	 * @param game        the current game instance containing player data
	 */
	public EquipmentPanel(GameFrame frame, StatsPanel statsPanel, Game game) {
		String headwearText = "";
		String armorText = "";
		String accessoryText = "";
		String weaponText = "";

		Headwear headwear = (Headwear) game.player.getEquipment(EquipmentSlot.HEADWEAR);
		Armor armor = (Armor) game.player.getEquipment(EquipmentSlot.ARMOR);
		Accessory accessory = (Accessory) game.player.getEquipment(EquipmentSlot.ACCESSORY);
		Weapon weapon = (Weapon) game.player.getEquipment(EquipmentSlot.WEAPON);

		try {
			if (headwear == null) {
				this.headwearImage = ImageIO.read(new File("./res/images/items/headwear/headwear_slot.png"));
				headwearText = "<html><h3>No Headwear equiped</h3></html>";
			}
			else {
				this.headwearImage = ImageIO.read(new File("./res/images/items/headwear/"
						+ headwear.getImagename()));
				headwearText = headwear.getName();
			}

			if (armor == null) {
				this.armorImage = ImageIO.read(new File("./res/images/items/armor/armor_slot.png"));
				armorText = "<html><h3>No Armor equiped</h3></html>";
			}
			else {
				this.armorImage = ImageIO.read(new File("./res/images/items/armor/"
						+ armor.getImagename()));
				armorText = armor.getName();
			}

			if (accessory == null) {
				this.accessoryImage = ImageIO.read(new File(
						"./res/images/items/accessory/accessory_slot.png"));
				accessoryText = "<html><h3>No Accessory equiped</h3></html>";
			}
			else {
				this.accessoryImage = ImageIO.read(new File("./res/images/items/accessory/"
						+ accessory.getImagename()));
				accessoryText = accessory.getName();
			}

			if (weapon == null) {
				this.weaponImage = ImageIO.read(new File("./res/images/items/weapon/weapon_slot.png"));
				weaponText = "<html><h3>No Weapon equiped</h3></html>";
			}
			else {
				this.weaponImage = ImageIO.read(new File("./res/images/items/weapon/"
						+ weapon.getImagename()));
				weaponText = weapon.getName();
			}
		}
		catch (IOException e) {
			System.err.println("Error loading graphics");
		}

		this.setBackground(new Color(41, 37, 36));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {10, 50, 10};
		gridBagLayout.rowHeights = new int[] {50, 50, 50, 50};
		gridBagLayout.columnWeights = new double[]{0, 1.0, 0.5};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, 1.0, 1.0};
		this.setLayout(gridBagLayout);

		// ------------------------------------------------------------------------------

		JButton btnHeadwear = new JButton(headwearText);

		btnHeadwear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map.Entry<Item, Boolean> selected = EquipmentSelectionDialog.showDialog(
						(StatusScreen) btnHeadwear.getParent().getParent(), game, Itemtype.HEADWEAR);

				if (selected.getValue()) {
					game.player.unequip(EquipmentSlot.HEADWEAR);
					try {
						headwearImage = ImageIO.read(new File(
								"./res/images/items/headwear/headwear_slot.png"));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnHeadwear.setIcon(new ImageIcon(headwearImage));
					btnHeadwear.setText("<html><h3>No Headwear equiped</h3></html>");

					statsPanel.updateStats(game.player);

					InventoryScreen inventoryScreen = new InventoryScreen(game);
			        frame.addCard(inventoryScreen, GameFrame.INVENTORY);
				}
				else if (selected.getKey() != null && !selected.getValue()) {
					game.player.equip(selected.getKey());
					try {
						headwearImage = ImageIO.read(new File("./res/images/items/headwear/"
								+ selected.getKey().getImagename()));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnHeadwear.setIcon(new ImageIcon(headwearImage));
					btnHeadwear.setText("<html>" + selected.getKey().getName() + "<br>Defense: +"
							+ ((Headwear) selected.getKey()).getDefense()  + "</html>");

					statsPanel.updateStats(game.player);

					InventoryScreen inventoryScreen = new InventoryScreen(game);
			        frame.addCard(inventoryScreen, GameFrame.INVENTORY);
				}
			}
		});

		btnHeadwear.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnHeadwear.setHorizontalAlignment(SwingConstants.LEFT);
		btnHeadwear.setForeground(new Color(255, 255, 255));
		btnHeadwear.setContentAreaFilled(false);
		btnHeadwear.setFocusPainted(false);

		btnHeadwear.setIcon(new ImageIcon(this.headwearImage));

		GridBagConstraints gbc_btnHeadwear = new GridBagConstraints();
		gbc_btnHeadwear.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnHeadwear.insets = new Insets(0, 0, 5, 0);
		gbc_btnHeadwear.gridx = 1;
		gbc_btnHeadwear.gridy = 0;
		this.add(btnHeadwear, gbc_btnHeadwear);

		// ------------------------------------------------------------------------------

		JButton btnArmor = new JButton(armorText);

		btnArmor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map.Entry<Item, Boolean> selected = EquipmentSelectionDialog.showDialog(
						(StatusScreen) btnArmor.getParent().getParent(), game, Itemtype.ARMOR);

				if (selected.getValue()) {
					game.player.unequip(EquipmentSlot.ARMOR);
					try {
						armorImage = ImageIO.read(new File("./res/images/items/armor/armor_slot.png"));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnArmor.setIcon(new ImageIcon(armorImage));
					btnArmor.setText("<html><h3>No Armor equiped</h3></html>");
				}
				else if (selected.getKey() != null && !selected.getValue()) {
					game.player.equip(selected.getKey());
					try {
						armorImage = ImageIO.read(new File("./res/images/items/armor/"
								+ selected.getKey().getImagename()));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnArmor.setIcon(new ImageIcon(armorImage));
					btnArmor.setText("<html>" + selected.getKey().getName() + "<br>Defense: +"
							+ ((Armor) selected.getKey()).getDefense()  + "</html>");
				}
				
				statsPanel.updateStats(game.player);

				InventoryScreen inventoryScreen = new InventoryScreen(game);
		        frame.addCard(inventoryScreen, GameFrame.INVENTORY);
			}
		});

		btnArmor.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnArmor.setHorizontalAlignment(SwingConstants.LEFT);
		btnArmor.setForeground(new Color(255, 255, 255));
		btnArmor.setContentAreaFilled(false);
		btnArmor.setFocusPainted(false);

		btnArmor.setIcon(new ImageIcon(this.armorImage));

		GridBagConstraints gbc_btnArmor = new GridBagConstraints();
		gbc_btnArmor.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnArmor.insets = new Insets(0, 0, 5, 0);
		gbc_btnArmor.gridx = 1;
		gbc_btnArmor.gridy = 1;
		this.add(btnArmor, gbc_btnArmor);

		// ------------------------------------------------------------------------------

		JButton btnAccessory = new JButton(accessoryText);

		btnAccessory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map.Entry<Item, Boolean> selected = EquipmentSelectionDialog.showDialog(
						(StatusScreen) btnAccessory.getParent().getParent(), game, Itemtype.ACCESSORY);

				if (selected.getValue()) {
					game.player.unequip(EquipmentSlot.ACCESSORY);
					try {
						accessoryImage = ImageIO.read(new File(
								"./res/images/items/accessory/accessory_slot.png"));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnAccessory.setIcon(new ImageIcon(accessoryImage));
					btnAccessory.setText("<html><h3>No Accessory equiped</h3></html>");

					statsPanel.updateStats(game.player);

					InventoryScreen inventoryScreen = new InventoryScreen(game);
			        frame.addCard(inventoryScreen, GameFrame.INVENTORY);
				}
				else if (selected.getKey() != null && !selected.getValue()) {
					game.player.equip(selected.getKey());
					try {
						accessoryImage = ImageIO.read(new File("./res/images/items/accessory/"
								+ selected.getKey().getImagename()));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnAccessory.setIcon(new ImageIcon(accessoryImage));
					btnAccessory.setText("<html>" + selected.getKey().getName() + "<br>Defense: +"
							+ ((Accessory) selected.getKey()).getDefense()  + "</html>");

					statsPanel.updateStats(game.player);

					InventoryScreen inventoryScreen = new InventoryScreen(game);
			        frame.addCard(inventoryScreen, GameFrame.INVENTORY);
				}
			}
		});

		btnAccessory.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnAccessory.setHorizontalAlignment(SwingConstants.LEFT);
		btnAccessory.setForeground(new Color(255, 255, 255));
		btnAccessory.setContentAreaFilled(false);
		btnAccessory.setFocusPainted(false);

		btnAccessory.setIcon(new ImageIcon(this.accessoryImage));

		GridBagConstraints gbc_btnAccessory = new GridBagConstraints();
		gbc_btnAccessory.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAccessory.insets = new Insets(0, 0, 5, 0);
		gbc_btnAccessory.gridx = 1;
		gbc_btnAccessory.gridy = 2;
		this.add(btnAccessory, gbc_btnAccessory);

		// ------------------------------------------------------------------------------

		JButton btnWeapon = new JButton(weaponText);

		btnWeapon.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Map.Entry<Item, Boolean> selected = EquipmentSelectionDialog.showDialog(
						(StatusScreen) btnWeapon.getParent().getParent(), game, Itemtype.WEAPON);

				if (selected.getValue()) {
					game.player.unequip(EquipmentSlot.WEAPON);
					try {
						weaponImage = ImageIO.read(new File("./res/images/items/weapon/weapon_slot.png"));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnWeapon.setIcon(new ImageIcon(weaponImage));
					btnWeapon.setText("<html><h3>No Weapon equiped</h3></html>");

					statsPanel.updateStats(game.player);

					InventoryScreen inventoryScreen = new InventoryScreen(game);
			        frame.addCard(inventoryScreen, GameFrame.INVENTORY);
				}
				else if (selected.getKey() != null && !selected.getValue()) {
					game.player.equip(selected.getKey());
					try {
						weaponImage = ImageIO.read(new File("./res/images/items/weapon/"
								+ selected.getKey().getImagename()));
					}
					catch (IOException ex) {
						System.err.println("Error loading graphics");
					}
					btnWeapon.setIcon(new ImageIcon(weaponImage));
					btnWeapon.setText("<html>" + selected.getKey().getName() + "<br>Attack: +"
							+ ((Weapon) selected.getKey()).getDamage()  + "</html>");

					statsPanel.updateStats(game.player);

					InventoryScreen inventoryScreen = new InventoryScreen(game);
			        frame.addCard(inventoryScreen, GameFrame.INVENTORY);
				}
			}
		});

		btnWeapon.setFont(new Font("SansSerif", Font.PLAIN, 15));
		btnWeapon.setHorizontalAlignment(SwingConstants.LEFT);
		btnWeapon.setForeground(new Color(255, 255, 255));
		btnWeapon.setFocusPainted(false);
		btnWeapon.setContentAreaFilled(false);

		btnWeapon.setIcon(new ImageIcon(this.weaponImage));

		GridBagConstraints gbc_btnWeapon = new GridBagConstraints();
		gbc_btnWeapon.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnWeapon.insets = new Insets(0, 0, 5, 0);
		gbc_btnWeapon.gridx = 1;
		gbc_btnWeapon.gridy = 3;
		this.add(btnWeapon, gbc_btnWeapon);

		// ------------------------------------------------------------------------------

	}
}
