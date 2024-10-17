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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ThirdPageController implements LoadindFXML {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button btnBuy;

    @FXML
    private Button btnHomePAge;

    @FXML
    private Button btnPag2;

    @FXML
    private Button btnPage1;

    @FXML
    private Button btnSell;

    @FXML
    private Label lblAccoutability;

    @FXML
    private ListView<Product> lstVprices;

    @FXML
    private ToggleButton tBtnDiscount;

    @FXML
    private TextArea txtAPriceInfo;

    public void goToHomePage(ActionEvent event) {
        loadingFXML("homePage.fxml",event);
    }
    public void goToFirstPage(ActionEvent event) {
        loadingFXML("firstPage.fxml",event);
    }
    public void goToSecondPage(ActionEvent event) {
        loadingFXML("secondPage.fxml",event);
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
        ObservableList<Product> prods = FXCollections.observableArrayList(produits);

        lstVprices.setItems(prods);
        lstVprices.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetails(lstVprices.getSelectionModel().getSelectedItem()));
    }

    private void displayProductDetails(Product selectedProduct) {
        if(selectedProduct!=null){
            txtAPriceInfo.setText("nom : "+selectedProduct.getName()+"\nprix d'achat : "+selectedProduct.getPurchasePrice()+"\nprix de vente : "+selectedProduct.getSellPrice()+"\nquantité : "+selectedProduct.getNbItems());
        }
    }
}
