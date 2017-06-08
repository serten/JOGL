package application;
	
import java.awt.Dimension;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		JFrame frame = new JFrame();
		
		frame.setUndecorated(true);
		frame.setResizable(true);
		
		double width = java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = 300;//java.awt.Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		
		frame.setSize((int) width, (int) height);
		
		HBox root;
		Scene scene;
		
		
		final JFXPanel mainJFXPanel = new JFXPanel();
		
		frame.getContentPane().add(mainJFXPanel);
		
		
		
		try {
			root = (HBox)FXMLLoader.load(getClass().getResource("Sample.fxml"));
			scene = new Scene(root,700,height);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			mainJFXPanel.setScene(scene);
			//primaryStage.setScene(scene);
			//primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		frame.pack();
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setVisible(true);
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
