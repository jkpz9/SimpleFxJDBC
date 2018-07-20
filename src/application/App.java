package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class App extends Application {
	
	static  LeftPane leftpane = new LeftPane();
	static RightPane rightpane = new RightPane();
	@Override
	public void start(Stage primaryStage) {
		
			HBox main = new HBox();
			
			main.getChildren().addAll(leftpane, rightpane);
			
			// Binding left and right pane with
			leftpane.prefWidthProperty().bind(main.widthProperty().divide(4));
			rightpane.prefWidthProperty().bind(main.widthProperty().subtract(main.widthProperty().divide(4)));
			
			Scene scene = new Scene(main, 800, 600);
			primaryStage.setScene(scene);
			primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
