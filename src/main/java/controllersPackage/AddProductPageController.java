package controllersPackage;
import BackToFrontLinked.PipelineProductListQueries;
import SQLModule.Queries;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import GeneralClasses.LoadindFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddProductPageController implements LoadindFXML, Initializable {
    Scene scene;
    Stage stage;
    @FXML
    private Label lblDisplayError;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnsubmitAddProduct;

    @FXML
    private ChoiceBox<String> cBxCategories;

    private String[] categories = {"Accessory","Clothes","Shoes"};

    @FXML
    private Label lblSuccesProduct;

    @FXML
    private Label lblBrand;

    @FXML
    private Label lblIdAddProduct;

    @FXML
    private Label lblPurchasePrice;

    @FXML
    private Label lblSpecialProduct;

    @FXML
    private Label lblTitleAddProduct;

    @FXML
    private TextField textABrandAddProduct;

    @FXML
    private TextField textAIdAddProduct;

    @FXML
    private TextField textAPPriceAddProduct;

    @FXML
    private TextField textASellPriceAddProduct;

    @FXML
    private TextField textASpecialProduct;

    @FXML
    private TextField textStockAddProduct;

    @FXML
    void goToFirstPage(ActionEvent event) {
        loadingFXML("firstPage.fxml",event);
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblSpecialProduct.setText("No more \nattribute\n to add");

        cBxCategories.getItems().addAll(categories);
        cBxCategories.setOnAction(this::getCategories);
        lblSuccesProduct.setText("");
        btnsubmitAddProduct.setDisable(false);
    }
    public void submitAllField(ActionEvent event){
        try{
            lblDisplayError.setText("");
            ArrayList<String> productList = new ArrayList<>();
            ArrayList<String> productPricesList = new ArrayList<>();
            String checkBoxValue = cBxCategories.getValue();
            //Queries --> table_product
            productList.add(textABrandAddProduct.getText());
            productList.add("0");
            if (checkBoxValue != "Accessory"){
                productList.add(textASpecialProduct.getText());
            }else{
                productList.add("0");
            }
            //Queries --> table_product_prices
            if (checkBoxValue == "Accessory"){
                productPricesList.add("0.5");
            }
            else if (checkBoxValue == "Shoes"){
                productPricesList.add("0.8");
            }else{
                productPricesList.add("0.7");
            }
            productPricesList.add(textASellPriceAddProduct.getText());
            productPricesList.add(textAPPriceAddProduct.getText());
            instancePipeline(productList,productPricesList);
            btnsubmitAddProduct.setDisable(true);
            lblSuccesProduct.setText("Produit Enregistré veuillez\n revenir a l'inventaire");
            btnCancel.setText("Revenir à l'inventaire");
        } catch (Exception e) {
            lblDisplayError.setText(e.toString());
        }


    }
    public void instancePipeline(ArrayList<String> productList, ArrayList<String> productPricesList){

            Queries product = new Queries(
                    productList.get(0),
                    Integer.valueOf(productList.get(1)),
                    productList.get(2) == "0" ? null:Double.valueOf(productList.get(2))
            );

        Queries prices = new Queries(
                Float.valueOf(productPricesList.get(0)),
                Double.valueOf(productPricesList.get(1)),
                Double.valueOf(productList.get(2))
        );
        PipelineProductListQueries.PipelineQueries(product);
        PipelineProductListQueries.PipelineQueries(prices);
    }
    public void getCategories(ActionEvent event){
        String checkBoxValue = cBxCategories.getValue();
        if (checkBoxValue == "Clothes") {
            lblSpecialProduct.setDisable(false);
            textASpecialProduct.setDisable(false);
            lblSpecialProduct.setText("Size");

        }
        else if (checkBoxValue == "Shoes"){
            lblSpecialProduct.setDisable(false);
            textASpecialProduct.setDisable(false);
            lblSpecialProduct.setText("Shoes size");

        }
        else{
            lblSpecialProduct.setDisable(true);
            textASpecialProduct.setDisable(true);
            lblSpecialProduct.setText("No more \nattribute\n to add");
            textASpecialProduct.setText("");


        }
    }

}
