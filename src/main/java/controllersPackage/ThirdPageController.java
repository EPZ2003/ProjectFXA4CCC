package controllersPackage;
import SQLModule.Queries;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.nio.channels.Pipe;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static BackToFrontLinked.PipelineProductListQueries.PipelineProductListQueries;

public class ThirdPageController implements LoadindFXML {
    public static int addProduct;

    public static int getAddProduct() {
        return addProduct;
    }

    public static void setAddProduct(int addProduct) {
        ThirdPageController.addProduct = addProduct;
    }

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

    private static ArrayList<ArrayList<String>> prices = new ArrayList<>();

    public static ArrayList<ArrayList<String>> getPrices() {
        return prices;
    }

    public static void setPrices(ArrayList<ArrayList<String>> prices) {
        ThirdPageController.prices = prices;
    }

    public static ArrayList<ArrayList<String>> refreshPrices() throws SQLException{
        setPrices(new ArrayList<>());
        int id = 1;
        ArrayList<ArrayList<String>> listToReturn = getPrices();
        for (Queries sql : PipelineProductListQueries.listQueriesTableProductPrices){
            listToReturn.add(MySQLOperations.readForRefresh(sql,id,addProduct));
            id++;
        }
        return listToReturn;
    }
    public void goToHomePage(ActionEvent event) {
        loadingFXML("homePage.fxml",event);
    }
    public void goToFirstPage(ActionEvent event) throws SQLException {

        loadingFXML("firstPage.fxml",event);
    }
    public void goToSecondPage(ActionEvent event) throws SQLException {
        refreshPrices();
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

    @FXML
    public void handleBuyButtonClick() throws SQLException {
        // Récupérer l'élément sélectionné dans la ListView
        ArrayList<String> selectedProduct = (ArrayList<String>)lstVprices.getSelectionModel().getSelectedItem();

        // Si un produit est sélectionné, appeler la méthode purchase
        if (selectedProduct != null) {
            purchaseItem(selectedProduct);
            System.out.println("produit acheté");
        }}

    @FXML
    public void handleSellButtonClick() throws SQLException {
        // Récupérer l'élément sélectionné dans la ListView
        ArrayList<String> selectedProduct = (ArrayList<String>)lstVprices.getSelectionModel().getSelectedItem();

        // Si un produit est sélectionné, appeler la méthode purchase
        if (selectedProduct != null) {
            sellItem(selectedProduct);
            System.out.println("produit vendu");
        }}

    public void handleDiscountButtonClick() throws SQLException {
        ArrayList<String> selectedProduct = (ArrayList<String>)lstVprices.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            toggleDiscount(selectedProduct);

        }
    }


    public void initialize() throws SQLException {

        ObservableList<List<String>> prods = FXCollections.observableArrayList(SQLCommand.readTablePrices());
        lstVprices.setItems(prods);

        lstVprices.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetails((ArrayList<String>) lstVprices.getSelectionModel().getSelectedItem()));
    }

    private void displayProductDetails(ArrayList<String> selectedProduct) {
        if(selectedProduct!=null){
            txtAPriceInfo.setText("id : "+selectedProduct.get(0)+"\nréduction : "+selectedProduct.get(1)+"\nprix de vente : "+selectedProduct.get(2)+"\nprix d'achat : "+selectedProduct.get(3));
        }
    }

    public void purchaseItem(ArrayList<String> selectedProduct) throws SQLException
    {   PipelineProductListQueries.InitializeAllList();
        int id = Integer.valueOf(selectedProduct.get(0));
        SQLCommand.updateTableProduct(id,"stock",Integer.valueOf(SQLCommand.readTableProduct().get(id-1).get(2)) + 1);

        Double outcome = Double.valueOf(SQLCommand.readTableMoney().get(0).get(2));
        Double purchasePrice = Double.valueOf(SQLCommand.readTablePrices().get(id-1).get(3));
        SQLCommand.updateTableMoney("outcome",  outcome + purchasePrice);
        Double initialCapital = Double.valueOf(SQLCommand.readTableMoney().get(0).get(0));
        SQLCommand.updateTableMoney("capital",  initialCapital - purchasePrice);
    }



    private void sellItem(ArrayList<String> selectedProduct) throws SQLException
    {
        PipelineProductListQueries.InitializeAllList();
        int id = Integer.valueOf(selectedProduct.get(0));
        SQLCommand.updateTableProduct(id,"stock",Integer.valueOf(SQLCommand.readTableProduct().get(id-1).get(2)) - 1);

        Double income = Double.valueOf(SQLCommand.readTableMoney().get(0).get(1));
        System.out.println(income);

        Double sellPrice = Double.valueOf(SQLCommand.readTablePrices().get(id-1).get(2));
        System.out.println(sellPrice);
        SQLCommand.updateTableMoney("income",  income + sellPrice);
        Double initialCapital = Double.valueOf(SQLCommand.readTableMoney().get(0).get(0));
        SQLCommand.updateTableMoney("capital",  initialCapital +sellPrice);

    }


    private void toggleDiscount(ArrayList<String> selectedProduct) throws SQLException
    {

        PipelineProductListQueries.InitializeAllList();
        int id = Integer.valueOf(selectedProduct.get(0));
        for (int i = 0; i < PipelineProductListQueries.listQueriesTableProductPrices.size() ; i++)
        {

            if (id == PipelineProductListQueries.listQueriesTableProductPrices.get(i).getIdProducts());
            {
                if (tBtnDiscount.isSelected())
            {
                System.out.println("Discount !!");
                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProductPrices.get(i), PipelineProductListQueries.listQueriesTableProductPrices.get(i).getSellPrice()*PipelineProductListQueries.listQueriesTableProductPrices.get(i).getDiscount(),"sellPrice");
                displayProductDetails(MySQLOperations.read(PipelineProductListQueries.listQueriesTableProductPrices.get(i)));
                lstVprices.getSelectionModel().getSelectedItem().set(2, Double.toString((double) Math.round(PipelineProductListQueries.listQueriesTableProductPrices.get(i).getSellPrice()*100)/100));
                lstVprices.refresh();
            }
                else
            {
                System.out.println("Discount stopped...");
                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProductPrices.get(i), PipelineProductListQueries.listQueriesTableProductPrices.get(i).getSellPrice()/PipelineProductListQueries.listQueriesTableProductPrices.get(i).getDiscount(),"sellPrice");
                displayProductDetails(MySQLOperations.read(PipelineProductListQueries.listQueriesTableProductPrices.get(i)));
                lstVprices.getSelectionModel().getSelectedItem().set(2, Double.toString((double) Math.round(PipelineProductListQueries.listQueriesTableProductPrices.get(i).getSellPrice() * 100) /100));
                lstVprices.refresh();

            }


            }
        }
    }


}
