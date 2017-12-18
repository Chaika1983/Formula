package application;

import javax.swing.JOptionPane;

import javafx.animation.AnimationTimer;
import javafx.concurrent.Task;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Track extends Task<Void> {
	public Police pol;
	public double yTrack = 165;
	final Image mobster = new Image(Main.class.getResource("/image/track.png").toString());
	final ImageView track = new ImageView(mobster);
	private double endUpY = 160;
	private double endBottomY = 600;
	public double vTrack = 0;

	public Track(Police pol) {
		this.pol = pol;
	}

	public Rectangle2D getRectMobster1() {
		return new Rectangle2D(vTrack, yTrack, 200, 51);
	}

	public void AnimTimerTrack() {
		track.setTranslateX(1500);
		track.setTranslateY(yTrack);
		if (pol.dx <= -1) {
			vTrack += 2;
			track.setTranslateX(vTrack);
		} else {
			vTrack -= 7;
			track.setTranslateX(vTrack);
		}

		if (vTrack <= -100) {
			vTrack = 1500;
			track.setTranslateX(vTrack);
		}
		if (yTrack <= endUpY || yTrack >= endBottomY) {
			track.setTranslateY(endUpY);
			track.setTranslateY(endBottomY);
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
				AnimTimerTrack();
			}

		};
		timer.start();

		return null;

	}

}