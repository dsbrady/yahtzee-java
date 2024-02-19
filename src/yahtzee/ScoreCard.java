package yahtzee;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

public class ScoreCard {
	private int totalScore = 0;
	private static int upperSectionBonus = 35;
	private LinkedHashMap<String, Integer> upperSection = new LinkedHashMap<String, Integer>(),
		lowerSection = new LinkedHashMap<String, Integer>();

	public ScoreCard() {
		initializeScoreCard();
	}

	public void display() {
		printSection(upperSection, "Upper");
		printSection(lowerSection, "Lower");
		System.out.println("--------------------------------");
		System.out.println("GRAND TOTAL: " + getTotalScore());
		System.out.println("--------------------------------");
	}

	int calculateTurnsRemaining() {
		return countSectionNulls(upperSection) + countSectionNulls(lowerSection);
	}

	private int countSectionNulls(LinkedHashMap<String,Integer> section) {
		var numNullsWrapper = new Object(){int numNulls = 0;};

		Iterator<Entry<String, Integer>> sectionIterator = section.entrySet().iterator();

		sectionIterator.forEachRemaining(entry -> {
			System.out.println(entry.getValue());
			if(entry.getValue() == null) {
				numNullsWrapper.numNulls++;
			}
		});

		return numNullsWrapper.numNulls;
	}

	private void initializeScoreCard() {
		initializeUpperSection();
		initializeLowerSection();
		this.totalScore = 0;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public static int getUpperSectionBonus() {
		return upperSectionBonus;
	}

	private void initializeUpperSection() {
		this.upperSection.put("Aces",  null);
		this.upperSection.put("Twos", null);
		this.upperSection.put("Threes", null);
		this.upperSection.put("Fours", null);
		this.upperSection.put("Fives", null);
		this.upperSection.put("Sixes", null);
		this.upperSection.put("Subtotal", 0);
		this.upperSection.put("Bonus", 0);
		this.upperSection.put("Total", 0);
	}

	private void initializeLowerSection() {
		this.lowerSection.put("Three-of-a-Kind", null);
		this.lowerSection.put("Four-of-a-Kind", null);
		this.lowerSection.put("Full-House", null);
		this.lowerSection.put("Small-Straight", null);
		this.lowerSection.put("Large-Straight", null);
		this.lowerSection.put("Yahtzee", null);
		this.lowerSection.put("Chance", null);
		this.lowerSection.put("Yahtzee-Bonus", 0);
		this.lowerSection.put("Total", 0);
	}

	private void printSection(LinkedHashMap<String,Integer> section, String sectionName) {
		Iterator<Entry<String, Integer>> sectionIterator = section.entrySet().iterator();

		System.out.println("--------------------------------");
		System.out.println(sectionName.toUpperCase() + " SECTION");
		System.out.println("--------------------------------");

		sectionIterator.forEachRemaining(entry -> {
			System.out.println(entry.getKey() + ": " + (entry.getValue() == null ? "" : entry.getValue()));
		});
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public static void setUpperSectionBonus(int upperSectionBonus) {
		ScoreCard.upperSectionBonus = upperSectionBonus;
	}

}
