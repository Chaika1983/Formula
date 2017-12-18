package application;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Track1 extends Task<Void> {
	public Police pol;
	public double yTrack1 = 480;
	final Image mobster = new Image(Main.class.getResource("/image/track1.png").toString());
	final ImageView track1 = new ImageView(mobster);
	private double endUpY = 160;
	private double endBottomY = 600;
	public double vTrack1 = 0;

	public Track1(Police pol) {
		this.pol = pol;
	}

	public Rectangle2D getRectTrack1() {
		return new Rectangle2D(vTrack1, yTrack1, 150, 60);
	}

	public void AnimTimerTrack1() {
		track1.setTranslateX(1800);
		track1.setTranslateY(yTrack1);
		if (pol.dx <= -1) {
			vTrack1 += 2;
			track1.setTranslateX(vTrack1);
		} else {
			vTrack1 -= 9;
			track1.setTranslateX(vTrack1);
		}

		if (vTrack1 <= -100) {
			vTrack1 = 1800;
			track1.setTranslateX(vTrack1);
		}

		if (yTrack1 <= endUpY || yTrack1 >= endBottomY) {
			track1.setTranslateY(endUpY);
			track1.setTranslateY(endBottomY);
		}
		if (pol.getRectPolice().intersects(getRectTrack1())) {
			JOptionPane.showMessageDialog(null, "YOU LOSE!!!!!!");
			System.exit(1);
		}

	}

	protected Void call() throws Exception {
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				AnimTimerTrack1();
			}

		};
		timer.start();

		return null;

	}

}