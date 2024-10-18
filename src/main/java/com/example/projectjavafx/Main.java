package com.example.projectjavafx;

import BackToFrontLinked.PipelineProductListQueries;
import SQLModule.MySQLOperations;
import SQLModule.Queries;
import WomenShopClasses.Accessory;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

//to do : Enlever purchasePrice et sellPrice de l'affichage de page 1

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


//TEST A-23
           // PipelineProductListQueries.PipelineProductListQueries(new Shoes("yesSay",34.01,32.02,40.5));
           // Queries test = PipelineProductListQueries.listQueriesTableProduct.get(0);
           // Queries x = PipelineProductListQueries.listQueriesTableProductPrices.get(0);
           // System.out.println(MySQLOperations.read(x));
            //PipelineProductListQueries.PipelineProductListQueries(new Shoes("poeoepeeppe",34.01,32.02,40.5));
            //Queries test = PipelineProductListQueries.listQueriesTableProduct.get(0);

            //MySQLOperations.update(test,34343243,"stock");
            //System.out.println(test.getStock());


        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {



        launch();
    }
}