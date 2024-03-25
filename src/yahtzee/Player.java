package yahtzee;

public class Player {
	private String name;
	private int rollsRemaining = 3;
	private ScoreCard scoreCard = new ScoreCard();
	private int turnsRemaining;

	public Player(String name) {
		setName(name);
		setTurnsRemaining();
	}

	public String getName() {
		return name;
	}

	public int getRollsRemaining() {
		return rollsRemaining;
	}

	public ScoreCard getScoreCard() {
		return scoreCard;
	}

	public int getTurnsRemaining() {
		return turnsRemaining;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRollsRemaining(int rollsRemaining) {
		this.rollsRemaining = rollsRemaining;
	}

	public void setScoreCard(ScoreCard scoreCard) {
		this.scoreCard = scoreCard;
	}

	public void setTurnsRemaining() {
		this.turnsRemaining = this.scoreCard.calculateTurnsRemaining();
	}

}
