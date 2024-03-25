package yahtzee;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import yahtzee.categories.*;
import yahtzee.scoring.*;

public class ScoreCard {
	private Section[] sections = {new UpperSection(), new LowerSection()};

	private int totalScore = 0;

	public ScoreCard() {
		initializeScoreCard();
	}

	private int calculateTotalScore() {
		int totalScore = 0;

		for(yahtzee.scoring.Section section : sections) {
			totalScore += section.calculateSubtotal() + (section.getBonusScore() != null ? section.getBonusScore() : 0);
		}

		return totalScore;
	}

	public int calculateTurnsRemaining() {
		return countSectionNulls(sections[0]) + countSectionNulls(sections[1]);
	}

	private int countSectionNulls(Section section) {
		LinkedHashMap<String, Category> categories = section.getCategories();
		Set<Map.Entry<String, Category>> categorySet = categories.entrySet();

		Map.Entry<String, Category> categoryEntry;
		Iterator<Map.Entry<String, Category>> categoryIterator = categorySet.iterator();
		var numNullsWrapper = new Object(){int numNulls = 0;};

		while(categoryIterator.hasNext()) {
			categoryEntry = categoryIterator.next();

			if(categoryEntry.getValue().getScore() == null) {
				numNullsWrapper.numNulls++;
			}
		}

		return numNullsWrapper.numNulls;
	}

	public void display() {
		for(yahtzee.scoring.Section section : sections) {
			printSection(section);
		}

		System.out.println("--------------------------------");
		System.out.println("GRAND TOTAL: " + getTotalScore());
		System.out.println("--------------------------------");
	}

	private void initializeScoreCard() {
		for(yahtzee.scoring.Section section : sections) {
			try {
				section.populateCategories();
			} catch(NoSuchMethodException e) {
				System.out.println(e.getMessage());
				System.exit(1);
			}
		}

		this.totalScore = 0;
	}

	public Section[] getSections() {
		return sections;
	}

	public int getTotalScore() {
		return totalScore;
	}

	private void printSection(yahtzee.scoring.Section section) {
		LinkedHashMap<String, Category> categories = section.getCategories();
		Map.Entry<String, Category> categoryEntry;
		Set<Map.Entry<String, Category>> categorySet = categories.entrySet();
		Iterator<Map.Entry<String, Category>> categoryIterator = categorySet.iterator();

		System.out.println("--------------------------------");
		System.out.println(section.getName().toUpperCase() + " SECTION");
		System.out.println("--------------------------------");

		while(categoryIterator.hasNext()) {
			categoryEntry = categoryIterator.next();
			System.out.println("(" + categoryEntry.getKey() + ") " + categoryEntry.getValue().getName() + ": " + (categoryEntry.getValue().getScore() == null ? "" : categoryEntry.getValue().getScore()));
		}

		System.out.println("--------------------------------");
		System.out.println("Sub-Total: " + section.calculateSubtotal());
		System.out.println("--------------------------------");
		System.out.println(section.getBonusName() + ": " + (section.getBonusScore() == null ? "" : section.getBonusScore()));
	}

	public void updateTotalScore() {
		this.totalScore = calculateTotalScore();
	}

}
