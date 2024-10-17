package com.example.projectjavafx;

import BackToFrontLinked.PipelineProductListQueries;
import WomenShopClasses.Accessory;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try{
            String homePagePath = "/controllersPackage/homePage.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(homePagePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            //TEST
            //Product newClothe = new Clothes("adadas",23.04,342.02,45.00);
            //Product newAcc = new Accessory("col",34.03,323.32);
            //PipelineProductListQueries.PipelineProductListQueries(newAcc);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}