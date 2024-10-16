package controllersPackage;

import GeneralClasses.LoadindFXML;
import WomenShopClasses.Accessory;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class FirstPageController implements LoadindFXML {
    Scene scene;
    Stage stage;

    @FXML
    private Button btnHomePage;

    @FXML
    private Button btnPage2;

    @FXML
    private Button btnPage3;

    @FXML
    private Label lblPrices;

    @FXML
    public ListView<Product> lstVproduit;

    @FXML
    private TextArea txtAVinfo;

    public void goToHomePage(ActionEvent event) {
        loadingFXML("homePage.fxml",event);
    }
    public void goToSecondPage(ActionEvent event) {
        loadingFXML("secondPage.fxml",event);
    }
    public void goToThirdPage(ActionEvent event) {
        loadingFXML("thirdPage.fxml",event);
    }

    @Override
    public void loadingFXML(String FxmlFile, ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(FxmlFile));
            stage = (Stage) ( (Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            System.out.println("Error during loading : "+ e);
        }
    }

    public void initialize() {
        List<Product> produits = new ArrayList<>();
        produits.add(new Accessory("cora",49.99,59.99));
        produits.add(new Shoes("balenciaga",499.99,759.99,42.5));
        produits.add(new Clothes("nike",59.99,79.99,42.5));
        produits.add(new Clothes("adidas",59.99,79.99,42.5));
        produits.add(new Clothes("reebook",59.99,79.99,42.5));
        produits.add(new Clothes("newbalance",19.99,79.99,42.5));
        produits.add(new Shoes("nike",59.99,79.99,42.5));
        produits.add(new Clothes("reebook",59.99,79.99,42.5));
        ObservableList<Product> prods= FXCollections.observableArrayList(produits);

        lstVproduit.setItems(prods);
        lstVproduit.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetails(lstVproduit.getSelectionModel().getSelectedItem()));
    }

    private void displayProductDetails(Product selectedProduct) {
        if(selectedProduct!=null){
            txtAVinfo.setText("nom : "+selectedProduct.getName()+"\nprix d'achat : "+selectedProduct.getPurchasePrice()+"\nprix de vente : "+selectedProduct.getSellPrice()+"\nquantité : "+selectedProduct.getNbItems());
        }
    }
}
