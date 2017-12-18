package application;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;

public class Mobster extends Task<Void> {

	public Police pol;
	public double yMobster =270;// Math.random() * 500 + 200;

	public Mobster(Police pol) {
		this.pol = pol;
		
	}

	@Override
	protected Void call() throws Exception {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				pol.AnimTimerMobster(yMobster);
			}

		};
		timer.start();
		return null;

	}
}
