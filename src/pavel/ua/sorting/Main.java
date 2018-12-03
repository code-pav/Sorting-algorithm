package pavel.ua.sorting;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pavel.ua.sorting.strategy.ISortingStrategy;

import java.util.ArrayList;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		Pane root = new Pane();
		Scene scene = new Scene(root, Paramethers.WIDTH+100, Paramethers.HEIGHT);
		Algorithm alg = new Algorithm(root);
		
		alg.init();

		primaryStage.setResizable(false);
		primaryStage.setTitle("Sorting algorythm's");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e->{
			e.consume();
			System.exit(1);
		});
		primaryStage.show();

		ArrayList<? extends ISortingStrategy> list = new ArrayList<>();
	}

}
