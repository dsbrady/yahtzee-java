package yahtzee;

import java.util.Random;

public class Die {
	private boolean isLocked = false;

	private int value;

	public Die() {

	}

	public int getValue() {
		return value;
	}

	public boolean isLocked() {
		return isLocked;
	}

	public void roll() {
		Random random = new Random();

		setValue(random.nextInt(6) + 1);
	}

	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
