// import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
// import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
// import java.util.Map;
import java.util.Scanner;
// import java.util.Set;

import yahtzee.*;
import yahtzee.categories.*;
import yahtzee.scoring.*;

public class Yahtzee {
	private static Scanner scanner;
	private static Player player;
	private static Die[] dice = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};

	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		Yahtzee yahtzee = new Yahtzee();

		yahtzee.startGame();
	}

	private static  String getPlayerName() {
		String name = "";

		while(name.length() == 0) {
			System.out.print("\nEnter your player's name: ");
			name = scanner.next();

			if(name.length() == 0) {
				System.out.println("Please enter a name.");
			}
		}

		return name;
	}

	private void displayDice() {
		for(int i = 0; i < dice.length; ++i) {
			System.out.print((i+1) + ": " + dice[i].getValue() + "\t");
		}
		System.out.print("\n");
	}

	private void displayScorePrompt() {
		System.out.print("Enter how you want to score the dice (use the shortcut in () from the scorecard): ");
	}

	private void displayTurnPrompt(Player player) {
		if(player.getRollsRemaining() > 0) {
			System.out.println("Roll Dice: R");
		}
		if(player.getRollsRemaining() < 3) {
			System.out.println("Score Dice: S");
		}
		System.out.print("\nEnter selection: ");
	}

	private void endGame() {
		String choice = "";
		System.out.println("Congratulations!  You finished the game with a score of " + player.getScoreCard().getTotalScore() + "!");
		System.out.println("\nWould you like to play again? (Y/N)?");
		while(!choice.equals("Y") && !choice.equals("N")) {
			choice = scanner.next().toString().toUpperCase();
			if(choice.equals("Y")) {
				restartGame();
			} else if(choice.equals("N")) {
				System.out.println("OK. Hope to see you again soon!");
				System.exit(0);
			} else {
				System.out.println("Invalid choice! Please try again.");
				System.out.println("\nWould you like to play again? (Y/N)?");
			}
		}
	}

	private Category getCategoryByScoreChoice(String scoreChoice) {
		LinkedHashMap<String, Category> categories;
		Category category = null;

		for(Section section : player.getScoreCard().getSections()) {
			categories = section.getCategories();

			if(categories.containsKey(scoreChoice)) {
				return categories.get(scoreChoice);
			} else {
				continue;
			}
		}

		return category;
	}

	private String getScoreChoice() {
		String choice = "";
		boolean isValidChoice = false;

		while(!isValidChoice) {
			displayScorePrompt();
			choice = scanner.next().toString().toUpperCase();
			isValidChoice = validateScoreChoice(player, choice);
			if(!isValidChoice) {
				System.out.println("Invalid choice! Try again.");
			}
		}

		return choice;
	}

	private void lockDice(List<String> choiceItems) {
		for(int i = 0; i < dice.length; i++) {
			dice[i].setLocked(!choiceItems.contains(Integer.toString(i+1)));
		}
	}

	private void playGame() {
		System.out.println("Alright! Let's get started, " + player.getName() + "!\n");

		while(player.getTurnsRemaining() > 0) {
			player.setRollsRemaining(3);
			takeTurn(player);
		}

		player.getScoreCard().display();
		endGame();
	}

	private void restartGame() {
		String name = player.getName();

		player = new Player(name);

		playGame();
	}

	private void rollDice() {
		for(Die die : dice) {
			if(!die.isLocked()) {
				die.roll();
			}
		}
		displayDice();
	}

	private void scoreDice(Player player, String scoreChoice) {
		Category category = getCategoryByScoreChoice(scoreChoice);
		int score = category.score(dice);
		Section[] sections = player.getScoreCard().getSections();
		LowerSection lowerSection = (LowerSection) sections[1];
		UpperSection upperSection = (UpperSection) sections[0];

		if(score == 0) {
			System.out.print("This will give you a zero for " + category.getName() + ". Are you sure? (Y/N)");
			String confirmChoice = scanner.next().toString().toUpperCase();
			if(!confirmChoice.equals("Y")) {
				scoreChoice = getScoreChoice();
				scoreDice(player, scoreChoice);
			}
	}

		category.setScore(score);
		if(upperSection.getBonusScore() == null) {
			if(upperSection.checkForBonus()) {
				upperSection.setBonusScore(upperSection.getBonusValue());
			}
		}

		if(lowerSection.getCategories().get("Y").getScore() != null && lowerSection.getCategories().get("Y").getScore() == 50 && !scoreChoice.equals("Y")) {
			if(lowerSection.checkForBonus(dice)) {
				lowerSection.setYahtzeeBonusCount(lowerSection.getYahtzeeBonusCount() + 1);
				lowerSection.setBonusScore();
			}
		}

		player.setRollsRemaining(-1);
		player.getScoreCard().updateTotalScore();
	}

	private void startGame() {
		System.out.println("Welcome to Yahtzee!");

		String name = getPlayerName();

		player = new Player(name);

		playGame();
	}

	private void unlockDice() {
		for(Die die : dice) {
			die.setLocked(false);
		}
	}

	private void takeTurn(Player player) {
		unlockDice();
		player.getScoreCard().display();
		while(player.getRollsRemaining() >=0) {
			takeTurnChoice(player);
		}
		player.setTurnsRemaining();
	}

	private void takeTurnChoice(Player player) {
		String choice = "";
		boolean isValidChoice = false;

		System.out.println("Turns Remaining: " + player.getTurnsRemaining() + "\tRolls Remaining: " + player.getRollsRemaining());
		while(!isValidChoice) {
			displayTurnPrompt(player);
			choice = scanner.next().toString().toUpperCase();
			isValidChoice = validateTurnChoice(player, choice);
			if(!isValidChoice) {
				System.out.println("Invalid choice! Try again.");
			}
		}

		switch(choice) {
			case "R":
				if(player.getRollsRemaining() < 3) {
					isValidChoice = false;
					List<String> choiceItems = null;

					while(!isValidChoice) {
						System.out.print("Enter the number for each die you want to roll again (e.g., 1,4,5): ");
						choice = scanner.next();
						choiceItems = Arrays.asList(choice.split("\\s*,\\s*"));
						isValidChoice = validateLockedDiceChoice(choiceItems);

						if(!isValidChoice) {
							System.out.println("Invalid choice! Try again.");
						}
					}
					lockDice(choiceItems);
				}
				rollDice();
				player.setRollsRemaining(player.getRollsRemaining() - 1);
				break;
			case "S":
				String scoreChoice = getScoreChoice();
				scoreDice(player, scoreChoice);
				break;
		}

	}

	private boolean validateLockedDiceChoice(List<String> choiceItems) {
		ListIterator<String> choiceIterator = choiceItems.listIterator();
		int choiceItem;

		if(choiceItems.size() > 5) {
			return false;
		}

		while(choiceIterator.hasNext()) {
			try {
				choiceItem = Integer.parseInt(choiceIterator.next());
				if(choiceItem < 1 || choiceItem > 5) {
					System.out.println("not in range: " + choiceItem);
					return false;
				}
			} catch(NullPointerException | NumberFormatException e) {
				System.out.println("Error:" + e.toString());
				return false;
			}
		}

		return true;
	}

	private boolean validateScoreChoice(Player player, String choice) {
		LinkedHashMap<String, Category> categories;
		Category category;
		// Set<Map.Entry<String, Category>> categorySet = categories.entrySet();
		// Iterator<Map.Entry<String, Category>> categoryIterator = categorySet.iterator();

		for(Section section : player.getScoreCard().getSections()) {
			categories = section.getCategories();
			category = categories.get(choice);
			if(category != null && category.getScore() == null) {
				return true;
			}
		}

		return false;
	}

	private boolean validateTurnChoice(Player player, String choice) {
		ArrayList<String> validChoices = new ArrayList<String>();
		boolean isValidChoice = false;

		if(player.getRollsRemaining() > 0) {
			validChoices.add("R");
		}
		if(player.getRollsRemaining() < 3) {
			validChoices.add("S");
		}

		if(validChoices.contains(choice)) {
			isValidChoice = true;
		}

		return isValidChoice;
	}

}
