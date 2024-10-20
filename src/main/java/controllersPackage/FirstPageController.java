package controllersPackage;

import BackToFrontLinked.PipelineProductListQueries;
import GeneralClasses.LoadindFXML;
import SQLModule.MySQLOperations;
import SQLModule.Queries;
import SQLModule.SQLCommand;
import WomenShopClasses.Accessory;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static controllersPackage.ThirdPageController.addProduct;

public class FirstPageController implements LoadindFXML, Initializable {
    Scene scene;
    Stage stage;
    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddProduct;

    @FXML
    private Button btnHomePage;

    @FXML
    private Button btnPage2;

    @FXML
    private Button btnPage3;

    @FXML
    private Button btnThrow;

    @FXML
    private Button btnUpdate;

    @FXML
    private Label lblPrices;

    @FXML
    private ListView<ArrayList<String>> lstVproduit;

    @FXML
    private TextArea txtAVinfo;

    public static ArrayList<ArrayList<String>> product = new ArrayList<>();

    public static ArrayList<ArrayList<String>> getProduct() {
        return product;
    }

    public static void setProduct(ArrayList<ArrayList<String>> product) {
        FirstPageController.product = product;
    }

    public void goToHomePage(ActionEvent event) {
        loadingFXML("homePage.fxml",event);
    }
    public void goToSecondPage(ActionEvent event) {
        loadingFXML("secondPage.fxml",event);
    }
    public void goToThirdPage(ActionEvent event) {

        loadingFXML("thirdPage.fxml",event);


    }
    @FXML
    void goToAddProductPage(ActionEvent event) {
        loadingFXML("addProductPage.fxml",event);
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
            displayError();
        }
    }
    public static ArrayList<ArrayList<String>> refreshProduct() throws SQLException{
        setProduct(new ArrayList<>());
        int id = 1;
        ArrayList<ArrayList<String>> listToReturn = getProduct();
        for (Queries sql : PipelineProductListQueries.listQueriesTableProduct){
            listToReturn.add(MySQLOperations.readForRefresh(sql,id,addProduct));
            id++;
        }
        return listToReturn;
    }


    public void displayError(){
        txtAVinfo.setText("Error !");
    }


    private void displayProductDetails(ArrayList<String> selectedProduct) {
        if(selectedProduct!=null){
            txtAVinfo.setText("nom : "+selectedProduct.get(1)+"\nstock : "+selectedProduct.get(2)+"\nspecialeAttribut : "+selectedProduct.get(3));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<ArrayList<String>> prods = null;
        try {
            prods = FXCollections.observableArrayList(refreshProduct());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        lstVproduit.setItems(prods);

        lstVproduit.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetails( (ArrayList<String>) lstVproduit.getSelectionModel().getSelectedItem()));

    }

    public void goToPopUp(ActionEvent event) {
        loadingFXML("updatePage.fxml",event);
    }

    public void addStock(ActionEvent event) throws SQLException {
        // Récupérer l'élément sélectionné dans la ListView
        ArrayList<String> selectedProduct = (ArrayList<String>)lstVproduit.getSelectionModel().getSelectedItem();

        // Si un produit est sélectionné, appeler la méthode purchase
        if (selectedProduct != null) {
            addStockItem(selectedProduct);
            System.out.println("produit vendu");
        }
        loadingFXML("firstPage.fxml",event);
    }

    public void addStockItem(ArrayList<String> selectedProduct) throws SQLException {
        PipelineProductListQueries.InitializeAllList();
        int id = Integer.valueOf(selectedProduct.get(0));
        SQLCommand.updateTableProduct(id,"stock",Integer.valueOf(SQLCommand.readTableProduct().get(id-1).get(2)) + 1);

    }
    @FXML
    public void throwStock(ActionEvent event) throws SQLException {
        // Récupérer l'élément sélectionné dans la ListView
        ArrayList<String> selectedProduct = (ArrayList<String>)lstVproduit.getSelectionModel().getSelectedItem();

        // Si un produit est sélectionné, appeler la méthode purchase
        if (selectedProduct != null) {
            throwStockItem(selectedProduct);
            System.out.println("produit vendu");
        }
        loadingFXML("firstPage.fxml",event);
    }
    public void throwStockItem(ArrayList<String> selectedProduct) throws SQLException {
        PipelineProductListQueries.InitializeAllList();
        int id = Integer.valueOf(selectedProduct.get(0));
        SQLCommand.updateTableProduct(id,"stock",Integer.valueOf(SQLCommand.readTableProduct().get(id-1).get(2)) - 1);

    }

}