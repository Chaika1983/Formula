package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage arg0) throws Exception {
		Scena sc = new Scena();
		sc.start(arg0);

	}

	public static void main(String[] args) {
		launch(args);
	}

}
