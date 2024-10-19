package com.example.projectjavafx;

import BackToFrontLinked.PipelineProductListQueries;
import SQLModule.MySQLOperations;
import SQLModule.Queries;
import WomenShopClasses.Accessory;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;
import controllersPackage.ThirdPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

import static BackToFrontLinked.PipelineProductListQueries.listQueriesTableMoney;


public class Main extends Application {
    ArrayList<Queries> test = PipelineProductListQueries.listQueriesTableProduct;
    @Override
    public void start(Stage stage) {
        try{

            PipelineProductListQueries.InitializeAllList();
            String homePagePath = "/controllersPackage/homePage.fxml";
            FXMLLoader loader = new FXMLLoader(getClass().getResource(homePagePath));
            Parent root = loader.load();
            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();
            //MySQLOperations.linkedQnTMoney();
//TEST A-23
            /*PipelineProductListQueries.listQueriesTableProductPrices.forEach(e -> {
                try {
                    System.out.println(MySQLOperations.read(e));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });*/
            listQueriesTableMoney.add(new Queries(100.00,100.00,1.00));

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        ThirdPageController.setAddProduct(0);


        launch();
    }
}