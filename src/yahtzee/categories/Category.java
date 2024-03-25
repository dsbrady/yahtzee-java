package yahtzee.categories;

import java.util.LinkedHashSet;
import java.util.Set;

import yahtzee.Die;

public class Category {
	private String name;
	private Integer score;
	private Integer value;

	public Category() {}

	public Category(String name) {
		setName(name);
	}

	public Category(String name, Integer value) {
		setName(name);
		setValue(value);
	}

	public int[] getDiceValueCounts(Die[] dice) {
		int diceValueCounts[] = new int[6];
		for(int i = 0; i < dice.length; i++) {
			diceValueCounts[dice[i].getValue() - 1]++;
		}

		return diceValueCounts;
	}

	public int[] getDiceValues(Die[] dice) {
		int diceValues[] = new int[5];
		for(int i = 0; i < dice.length; i++) {
			diceValues[i] = dice[i].getValue();
		}

		return diceValues;
	}

	public String getName() {
		return name;
	}

	public Integer getScore() {
		return score;
	}

	public Integer getValue() {
		return value;
	}

	public int[] removeDuplicateDice(int[] diceValues) {
		Set<Integer> uniqueValues = new LinkedHashSet<>();
        for(int value : diceValues){
            uniqueValues.add(value);
        }
        int[] uniqueDiceValues = new int[uniqueValues.size()];
        int i = 0;
        for(int val : uniqueValues){
            uniqueDiceValues[i++] = val;
        }
        return uniqueDiceValues;
	}

	public int score(yahtzee.Die[] dice) {
		int score = 0;

		for(Die die : dice) {
			score += die.getValue();
		}

		return score;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setScore(Integer value) {
		this.score = value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

}
