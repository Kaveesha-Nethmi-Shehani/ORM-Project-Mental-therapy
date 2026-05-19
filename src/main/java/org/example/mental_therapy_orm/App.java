package org.example.mental_therapy_orm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) {
        // Initialize Hibernate in a background thread to generate database tables immediately
        new Thread(() -> {
            try {
                org.example.mental_therapy_orm.config.FactoryConfiguration.getInstance().getSession().close();
                System.out.println("Hibernate Initialized & Tables Created!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        try {
            // Updated to load LoginForm.fxml based on your resources folder
            Parent root = loadFXML("LoginForm");
            scene = new Scene(root);

            stage.setScene(scene);
            stage.setTitle("MindCare Therapy System");
            stage.setMaximized(true);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRoot(String fxml) {
        try {
            scene.setRoot(loadFXML(fxml));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading FXML file: " + e.getMessage());
        }
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
