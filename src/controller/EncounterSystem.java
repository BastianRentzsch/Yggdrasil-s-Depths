// Copyright (c) 2026 Bastian Rentzsch

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;

import audio.MusicPlayer;
import model.entity.Enemy;
import model.entity.Player;
import model.items.Consumable;
import view.GameFrame;
import view.battle.AttackSelectionDialog;
import view.battle.BattleView;
import view.battle.ConsumableSelectionJDialog;
import view.battle.EnemyPanel;
import view.utils.PopupDialog;

/**
 * Utility class that manages random encounters and turn-based battles.
 * <p>
 * This class provides methods for determining whether a random encounter
 * occurs, selecting enemies for a battle, calculating flee attempts, and
 * configuring the battle controls and their associated actions.
 * </p>
 *
 * @author Bastian Rentzsch
 * @since 2026
 */
public class EncounterSystem {
	
	/**
	 * Random number generator used for encounter and battle calculations.
	 */
	private static final Random RANDOM = new Random();
	
	/**
	 * Probability that a random encounter occurs.
	 * A value of {@code 0.3} corresponds to a 30% chance.
	 */
	private static final double ENCOUNTER_CHANCE = 0.3;
	
	/**
	 * Probability that a flee attempt succeeds.
	 * A value of {@code 0.4} corresponds to a 40% chance.
	 */
	private static final double FLEE_CHANCE = 0.4;
	 
	/**
	 * Prevents instantiation of this utility class.
	 */
    private EncounterSystem() {
    }
	
    /**
     * Determines whether a random enemy encounter is triggered.
     *
     * @return {@code true} if an encounter occurs; {@code false} otherwise
     */
    public static boolean checkForEncounter() {
    	if (RANDOM.nextDouble() > ENCOUNTER_CHANCE) return false;
		return true;
    }
	
    /**
     * Creates a random group of enemies for a battle encounter.
     * <p>
     * Between one and three enemies are selected at random from the supplied
     * enemy codex. New enemy instances are created so that the original
     * templates remain unchanged.
     * </p>
     *
     * @param enemyCodex the available enemy templates
     * @return an array containing the enemies for the encounter
     */
    public static Enemy[] getEnemiesForBattle(Enemy[] enemyCodex) {
    	int enemiesCount = (RANDOM.nextInt(3)) + 1;
		Enemy[] enemiesToFight = new Enemy[enemiesCount];
		 
		for (int i = 0; i < enemiesCount; i++) {
			int enemyIndex = RANDOM.nextInt(enemyCodex.length);
			enemiesToFight[i] = new Enemy(enemyCodex[enemyIndex]);
		}
		 
		return enemiesToFight;
    }

    /**
     * Determines whether a flee attempt is successful.
     *
     * @return {@code true} if the player successfully escapes;
     *         {@code false} otherwise
     */
    public static boolean canFlee() {
		if (RANDOM.nextDouble() > FLEE_CHANCE) return false;
		return true;
    }
	
    /**
     * Configures the battle actions and registers their event listeners.
     * <p>
     * The configured actions allow the player to attack enemies, use
     * consumable items, or attempt to flee from combat. Enemy turns,
     * victory and defeat conditions, health updates, battle transitions,
     * and background music changes are handled automatically.
     * </p>
     *
     * @param frame the main application frame
     * @param battleView the active battle view
     * @param enemyPanels the panels representing the enemies in battle
     * @param player the player participating in the battle
     * @param buttons the battle action buttons
     */
    public static void battle(GameFrame frame, BattleView battleView, List<EnemyPanel> enemyPanels, 
			 Player player, JButton[] buttons) {
    	buttons[0].addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			EnemyPanel enemy = AttackSelectionDialog.showDialog(battleView, enemyPanels);
    			int enemiesDefeated = 0;
				 
    			if (enemy == null) return;

    			player.attack(enemy.getEnemy());
    			enemy.updateDisplay();
			    
			    for (EnemyPanel panel : enemyPanels) {
		    		if (panel.isVisible()) {
		    			panel.getEnemy().attack(player);
		    			battleView.updatePlayerHealth(player.getHealth());
		    		}
			    }
			    
			    battleView.validate();
			    battleView.repaint();
			    
			    if (player.getHealth() == 0) {
			    	PopupDialog.showDialog(frame, "You were defeated!", PopupDialog.ERROR);
			    
			    	frame.setGameIsRunning(false);
			    	frame.toggleIsFighting();
			    	frame.showCard(GameFrame.MAIN_MENU);
			    	
			    	MusicPlayer.playMainMenu(frame.getMusicPlayer());
			    }
			    
			    for (EnemyPanel panel : enemyPanels) {
			    	if (!panel.isVisible()) {
			    		enemiesDefeated++;
			    	}
			    }
			    
			    if (enemiesDefeated == enemyPanels.size()) {
			    	PopupDialog.showDialog(frame, "You won!", PopupDialog.PLAIN);
			    
			    	frame.toggleIsFighting();
			    	frame.getWindow().updatePlayerHealth();
			    	frame.showCard(GameFrame.GAME);
			    	
			    	MusicPlayer.playExploration(frame.getMusicPlayer());
			    			
			    }
    		}
    	});
		 
		buttons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consumable selectedConsumable = ConsumableSelectionJDialog.showDialog(battleView, player);
			 
				if (selectedConsumable == null) return;
			 
				selectedConsumable.use(player);
			 
				for (EnemyPanel panel : enemyPanels) {
					if (panel.isVisible()) {
						panel.getEnemy().attack(player);
						battleView.updatePlayerHealth(player.getHealth());
					}
				}
		 
				if (player.getHealth() == 0) {
					PopupDialog.showDialog(frame, "You were defeated!", PopupDialog.ERROR);
				
					frame.setGameIsRunning(false);
					frame.toggleIsFighting();
					frame.showCard(GameFrame.MAIN_MENU);
					
					MusicPlayer.playMainMenu(frame.getMusicPlayer());
				}
			}
		});
	 
		buttons[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (EncounterSystem.canFlee()) {
					PopupDialog.showDialog(frame, "You have successfully escaped the fight.",
							PopupDialog.PLAIN);
				
					player.move(player.getDirFlee());
				
					frame.toggleIsFighting();
					
					frame.getWindow().updatePlayerHealth();
					frame.getWindow().getDungeonPanel().updateView(player);
					frame.showCard(GameFrame.GAME);
					
					MusicPlayer.playExploration(frame.getMusicPlayer());
				}
				else {
					for (EnemyPanel panel : enemyPanels) {
						if (panel.isVisible()) {
							panel.getEnemy().attack(player);
							battleView.updatePlayerHealth(player.getHealth());
						}
					}
				
					if (player.getHealth() == 0) {
						PopupDialog.showDialog(frame, "You were defeated!", PopupDialog.ERROR);
					
						frame.setGameIsRunning(false);
						frame.toggleIsFighting();
						frame.showCard(GameFrame.MAIN_MENU);
						
						MusicPlayer.playMainMenu(frame.getMusicPlayer());
					}
					else {
						PopupDialog.showDialog(frame, "Your escape attempt has failed.", PopupDialog.PLAIN);
					}
				}
			}
		});
    } 
}
