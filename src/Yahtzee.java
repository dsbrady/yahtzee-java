import java.util.ArrayList;
import java.util.Scanner;
import yahtzee.*;

public class Yahtzee {
	private static Scanner scanner;
	private static Player player;
	private static Die[] dice = new Die[]{new Die(), new Die(), new Die(), new Die(), new Die()};

	public static void main(String[] args) {
		scanner = new Scanner(System.in);

		Yahtzee yahtzee = new Yahtzee();

		yahtzee.startGame();
	}

	public void displayDice() {
		for(int i = 0; i < dice.length; ++i) {
			System.out.print((i+1) + ": " + dice[i].getValue() + "\t");
		}
		System.out.print("\n");
	}

	public void displayTurnPrompt(Player player) {
		if(player.getRollsRemaining() > 0) {
			System.out.println("Roll Dice: R");
		}
		if(player.getRollsRemaining() < 3) {
			System.out.println("Score Dice: S");
		}
		System.out.print("\nEnter selection: ");
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

	private void rollDice() {
		for(Die die : dice) {
			if(!die.isLocked()) {
				die.roll();
			}
		}
		displayDice();
	}

	private void startGame() {
		System.out.println("Welcome to Yahtzee!");

		String name = getPlayerName();

		player = new Player(name);

		System.out.println("Alright! Let's get started, " + player.getName() + "!\n");

		// while(player.getTurnsRemaining() > 0) {
			startTurn(player);
		// }
	}

	private void startTurn(Player player) {
		player.getScoreCard().display();
		System.out.println("Turns Remaining: " + player.getTurnsRemaining() + "\tRolls Remaining: " + player.getRollsRemaining());
		startTurnChoice(player);
	}

	private void startTurnChoice(Player player) {
		String choice = "";
		boolean isValidChoice = false;
		while(!isValidChoice) {
			displayTurnPrompt(player);
			choice = scanner.next().toString().toUpperCase();
			isValidChoice = validateTurnChioce(player, choice);
			if(!isValidChoice) {
				System.out.println("Invalid choice. Try again.");
			}
		}

		switch(choice) {
			case "R":
				rollDice();
				break;
			case "S":
				// TODO
				break;
		}

	}

	private boolean validateTurnChioce(Player player, String choice) {
		ArrayList<String> validChoices = new ArrayList<String>();
		boolean isValidChoice = false;

		if(player.getTurnsRemaining() > 0) {
			validChoices.add("R");
		}
		if(player.getTurnsRemaining() < 3) {
			validChoices.add("S");
		}

		if(validChoices.contains(choice)) {
			isValidChoice = true;
		}

		return isValidChoice;
	}

}
