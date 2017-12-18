package application;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mobster2 extends Task<Void> {
	public Police pol;
	public double yMobster2 = 390;
	final Image mobster = new Image(Main.class.getResource("/image/player2.png").toString());
	final ImageView mob2 = new ImageView(mobster);
	private double endUpY = 160;
	private double endBottomY = 600;
	public double vMobster2 = 0;

	public Mobster2(Police pol) {
		this.pol = pol;
	}

	public Rectangle2D getRectMobster2() {
		return new Rectangle2D(vMobster2, yMobster2, 90, 43);
	}

	public void AnimTimerMobster2() {
		mob2.setTranslateX(1400);
		mob2.setTranslateY(yMobster2);
		if (pol.dx <= -1) {
			vMobster2 += 1;
			mob2.setTranslateX(vMobster2);
		} else {
			vMobster2 -= 10;
			mob2.setTranslateX(vMobster2);
		}

		if (vMobster2 <= -100) {
			vMobster2 = 1400;
			mob2.setTranslateX(vMobster2);
		}
		if (yMobster2 <= endUpY || yMobster2 >= endBottomY) {
			mob2.setTranslateY(endUpY);
			mob2.setTranslateY(endBottomY);
		}
		if (pol.getRectPolice().intersects(getRectMobster2())) {
			JOptionPane.showMessageDialog(null, "YOU LOSE!!!!!!");
			System.exit(1);
		}

	}

	protected Void call() throws Exception {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				AnimTimerMobster2();
			}

		};
		timer.start();

		return null;

	}

}
