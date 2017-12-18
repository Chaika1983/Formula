package application;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mobster1 extends Task<Void> {
	public Police pol;
	public double yMobster1 = 300;
	final Image mobster = new Image(Main.class.getResource("/image/player2.png").toString());
	final ImageView mob1 = new ImageView(mobster);
	private double endUpY = 160;
	private double endBottomY = 600;
	public double vMobster = 0;

	public Mobster1(Police pol) {
		this.pol = pol;
	}

	public Rectangle2D getRectMobster1() {
		return new Rectangle2D(vMobster, yMobster1, 85, 39);
	}

	public void AnimTimerMobster1() {
		mob1.setTranslateX(1400);
		mob1.setTranslateY(yMobster1);
		if (pol.dx <= -1) {
			vMobster += 1;
			mob1.setTranslateX(vMobster);
		} else {
			vMobster -= 8;
			mob1.setTranslateX(vMobster);
		}

		if (vMobster <= -100) {
			vMobster = 1400;
			mob1.setTranslateX(vMobster);
		}
		if (yMobster1 <= endUpY || yMobster1 >= endBottomY) {
			mob1.setTranslateY(endUpY);
			mob1.setTranslateY(endBottomY);
		}
		if (pol.getRectPolice().intersects(getRectMobster1())) {
			JOptionPane.showMessageDialog(null, "YOU LOSE!!!!!!");
			System.exit(1);
		}

	}

	protected Void call() throws Exception {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				AnimTimerMobster1();
			}

		};
		timer.start();

		return null;

	}

}
