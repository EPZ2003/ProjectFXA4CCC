package controllersPackage;

import BackToFrontLinked.PipelineProductListQueries;
import GeneralClasses.LoadindFXML;
import SQLModule.MySQLOperations;
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

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static BackToFrontLinked.PipelineProductListQueries.PipelineProductListQueries;

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
    private ListView<List<String>> lstVprices;

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

    public void initialize() throws SQLException {
        //System.out.println(MySQLOperations.read(PipelineProductListQueries.listQueriesTableProductPrices.get(0)));
        //List<List<String>> prices = new ArrayList<>();
        //PipelineProductListQueries(new Shoes("cora",49.99,59.99,28.0));
        //prices.add(MySQLOperations.read(PipelineProductListQueries.listQueriesTableProductPrices.get(0)));
        //ObservableList<List<String>> prods = FXCollections.observableArrayList(prices);
        //lstVprices.setItems(prods);
        //lstVprices.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetails(lstVprices.getSelectionModel().getSelectedItem()));
    }

    private void displayProductDetails(List<String> selectedProduct) {
        if(selectedProduct!=null){
            txtAPriceInfo.setText("id : "+selectedProduct.get(0)+"\nréduction : "+selectedProduct.get(1)+"\nprix de vente : "+selectedProduct.get(2)+"\nprix d'achat : "+selectedProduct.get(3));
        }
    }
}
