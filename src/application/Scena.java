package application;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Scena{
	ConnecktSQL connect = new ConnecktSQL();
	public Stage primaryStage;
	public MediaPlayer player;
	public Police police = new Police();

	public void start(Stage primaryStage){
		try {
			Label l1 = new Label("Login");
			Label l2 = new Label("Pasword");
			Button button = new Button("Ok");
			Button button1 = new Button("Registrate");
			TextField tf = new TextField();
			TextField message = new TextField("Wrong login or password");
			PasswordField ptf = new PasswordField();
			HBox xb = new HBox(10);
			xb.setAlignment(Pos.BOTTOM_RIGHT);
			xb.getChildren().add(button);
			xb.getChildren().add(button1);
			GridPane gp = new GridPane();
			gp.setAlignment(Pos.CENTER);
			gp.setPadding(new Insets(25, 25, 25, 25));
			gp.add(l2, 0, 1);
			gp.add(ptf, 1, 1);
			gp.add(l1, 0, 0);
			gp.add(tf, 1, 0);
			gp.add(xb, 1, 2);

			button.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					connect = new ConnecktSQL();
					boolean access = false;
					access = connect.verifyData(tf.getText(), ptf.getText());
					if (access) {
						primaryStage.setScene(getScene());
					} else {
						gp.add(message, 1, 3);
					}
					connect.closeConnection();

				}
			});
			button1.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					primaryStage.setScene(getRegistration());

				}
			});

			BorderPane root = new BorderPane();
			root.setTop(createMenu());
			root.setCenter(gp);
			Scene scene = new Scene(root, 1366, 768);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setTitle("NFS V - 1.0 Chaika V.V.");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public MenuBar createMenu() {
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("F1 Menu");
		MenuItem menuNew = new MenuItem("New Game");
		MenuItem menuSave = new MenuItem("Save Game");
		MenuItem menuClose = new MenuItem("Close");
		menuBar.getMenus().add(menu);
		menu.getItems().addAll(menuNew, menuSave, menuClose);
		menuNew.setId("New");
		menuSave.setId("Save");
		menuClose.setId("Close");

		return menuBar;
	}

	public void NewGame() {
		primaryStage.close();
		primaryStage.show();
	}

	public void SaveGame() {

	}

	public void Close() {
		System.exit(0);
	}

	public Scene getRegistration(){
		Label label1 = new Label("New Login");
		Label label2 = new Label("New Pasword");
		Button button = new Button("Ok");
		TextField message = new TextField("This login or password already exist");
		TextField tf = new TextField();
		PasswordField ptf = new PasswordField();
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(25, 25, 25, 25));
		gp.add(label1, 0, 0);
		gp.add(tf, 1, 0);
		gp.add(label2, 0, 1);
		gp.add(ptf, 1, 1);
		gp.add(button, 1, 2);
		
		button.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				connect = new ConnecktSQL();
				boolean access = false;
				access = connect.setRegistraion(tf.getText(), ptf.getText());
				if (access) {
					primaryStage.setScene(getScene());
				} else {
					gp.add(message, 1, 3);
				}
				connect.closeConnection();

			}
		});
		BorderPane root = new BorderPane();
		root.setTop(createMenu());
		root.setCenter(gp);
		Scene scene = new Scene(root, 1366, 768);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
		return scene;
		
	}
	public Scene getScene() {
		Mobster mob = new Mobster(police);
		Mobster1 mob1 = new Mobster1(police);
		Mobster2 mob2 = new Mobster2(police);
		Thread thread5 = new Thread(mob2);
		thread5.setDaemon(true);
		thread5.start();
		Track tr = new Track(police);
		Thread thread3 = new Thread(tr);
		thread3.setDaemon(true);
		thread3.start();
		Track1 tr1 = new Track1(police);
		Thread thread4 = new Thread(tr1);
		thread4.setDaemon(true);
		thread4.start();
		Thread thread1 = new Thread(mob);
		thread1.setDaemon(true);
		thread1.start();
		Thread thread2 = new Thread(mob1);
		thread2.setDaemon(true);
		thread2.start();
		Media media = new Media("file:///C:/audio/sektor.mp3");
		player = new MediaPlayer(media);
		player.setAutoPlay(true);

		final Group cars = new Group(police.road, police.road1, police.police, createMenu(), police.mob, mob1.mob1,
				tr.track, tr1.track1, mob2.mob2);

		cars.setEffect(new DropShadow());
		Group root = new Group(cars);
		Scene scene = new Scene(root, 1366, 768);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				police.setPressed(event);

			}
		});

		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				police.setReleased(event);

			}
		});
		AnimationTimer timer = new AnimationTimer() {
			@Override
			public void handle(long now) {
				police.AnimTimerPolice();
			}

		};
		timer.start();

		return scene;

	}

	
}
