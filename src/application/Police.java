package application;

import javax.swing.JOptionPane;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class Police {
	final static Image POLICE_IMAGE = new Image(Main.class.getResource("/image/Police.png").toString());
	final static Image ROAD_IMAGE = new Image(Main.class.getResource("/image/Road.jpg").toString());
	final Image mobster = new Image(Main.class.getResource("/image/Player1.png").toString());
	final ImageView mob = new ImageView(mobster);
	final ImageView road = new ImageView(ROAD_IMAGE);
	final ImageView road1 = new ImageView(ROAD_IMAGE);
	final ImageView police = new ImageView(POLICE_IMAGE);
	public static final int MAX_V = 270;
	public static final int MAX_TOP = 170;
	public static final int MAX_BOTTOM = 520;
	private double sMobster = 0;
	public double yMobster = 0;
	public double score = 0;
	private double endUpY = 160;
	private double endBottomY = 400;
	private double vPolice = 0;
	private double sPolice = 0;
	public double dx = 0;
	public double yPolice = 230;
	private double dy = 0;
	private double roadd = 0;
	private double roadd1 = 0;
	public boolean North, South, East, West;

	public Rectangle2D getRectPolice() {
		return new Rectangle2D(sPolice, yPolice, 82, 39);
	}

	public Rectangle2D getRectMobster() {
		return new Rectangle2D(sMobster, yMobster, 82, 39);
	}

	public void setPressed(KeyEvent event) {
		switch (event.getCode()) {
		case UP:
			North = true;
			dy = 8;
			break;
		case DOWN:
			South = true;
			dy = -8;
			break;
		case LEFT:
			West = true;
			dx = -0.3;
			break;
		case RIGHT:
			East = true;
			dx = 0.3;
			break;
		default:
			break;
		}
	}

	public void setReleased(KeyEvent event) {
		switch (event.getCode()) {
		case UP:
			North = false;
			dy = 0;
			break;
		case DOWN:
			South = false;
			dy = 0;
			break;
		case LEFT:
			West = false;
			dx = 0;
			break;
		case RIGHT:
			East = false;
			dx = -1;
			break;
		default:
			break;
		}
	}

	public void AnimTimerPolice() {
		vPolice += dx;
		yPolice -= dy;
		if (vPolice <= 0) {
			vPolice = 0;
		}
		if (vPolice >= MAX_V) {
			vPolice = MAX_V;
		}
		if (yPolice <= MAX_TOP) {
			yPolice = MAX_TOP;
		}
		if (yPolice >= MAX_BOTTOM) {
			yPolice = MAX_BOTTOM;
		}
		if (roadd1 - vPolice <= 0) {
			roadd = 0;
			roadd1 = 1360;
		} else {
			roadd -= vPolice;
			roadd1 -= vPolice;
			police.setTranslateX(vPolice);
			police.setTranslateY(yPolice);
			road.setTranslateX(roadd);
			road1.setTranslateX(roadd1);
		}
		if (getRectPolice().intersects(getRectMobster())) {
			JOptionPane.showMessageDialog(null, "YOU LOSE!!!!!!");
			System.exit(1);
		}
	}

	public void AnimTimerMobster(double yMobster) {
		this.yMobster = yMobster;
		mob.setTranslateX(1000);
		if (dx <= -1) {
			sMobster += 2;
			mob.setTranslateX(sMobster);
		} else {
			sMobster -= 4;
			mob.setTranslateX(sMobster);
		}

		if (sMobster <= -100) {
			sMobster = 1000;
			mob.setTranslateX(sMobster);
		}

		mob.setTranslateY(yMobster);
		if (yMobster <= endUpY || yMobster >= endBottomY) {
			mob.setTranslateY(endUpY);
			mob.setTranslateY(endBottomY);
		}

	}

	public void getTime() {
		final Timeline timeline = new Timeline();
		timeline.setCycleCount(Animation.INDEFINITE);
		timeline.setAutoReverse(true);
		timeline.getKeyFrames().addAll(new KeyFrame(Duration.millis(1000), new KeyValue(mob.translateXProperty(), 3200))
		
		);

		timeline.play();
	}
}
