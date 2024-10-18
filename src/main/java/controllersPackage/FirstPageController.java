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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static BackToFrontLinked.PipelineProductListQueries.PipelineProductListQueries;
import java.sql.SQLException;
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
    private StackPane btn1;

    @FXML
    private StackPane btn2;

    @FXML
    private StackPane btn3;

    @FXML
    public ListView<List<String>> lstVproduit;

    @FXML
    private Button btnLess;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnUpdate;

    @FXML
    private TextArea txtAinfo;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtFNouveauchamp;

    @FXML
    private AnchorPane APpopup;

    @FXML
    private ComboBox<String> cmbType;

    @FXML
    private Pane Panebackground;

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

    @FXML
    public void handleAddButtonClick() throws SQLException {
        // Récupérer l'élément sélectionné dans la ListView
        ArrayList<String> selectedProduct = (ArrayList<String>)lstVproduit.getSelectionModel().getSelectedItem();
        lstVproduit.getSelectionModel().getSelectionMode();

        // Si un produit est sélectionné, appeler la méthode purchase
        if (selectedProduct != null) {
            AddItem(selectedProduct);
            System.out.println("produit ajouté");
        }}

    @FXML
    public void handleDeleteButtonClick() throws SQLException {
        // Récupérer l'élément sélectionné dans la ListView
        ArrayList<String> selectedProduct = (ArrayList<String>)lstVproduit.getSelectionModel().getSelectedItem();

        // Si un produit est sélectionné, appeler la méthode purchase
        if (selectedProduct != null) {
            DeleteItem(selectedProduct);
            System.out.println("produit supprimer");
        }}

    public void AddItem(ArrayList<String> selectedProduct) throws SQLException
    {
        PipelineProductListQueries.InitializeAllList();
        int id = Integer.valueOf(selectedProduct.get(0));
        for (int i = 0; i < PipelineProductListQueries.listQueriesTableProduct.size() ; i++)
        {
            if (id == PipelineProductListQueries.listQueriesTableProduct.get(i).getIdProducts());
            {
                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProduct.get(i), PipelineProductListQueries.listQueriesTableProduct.get(i).getStock()+1,"stock");
                System.out.println(PipelineProductListQueries.listQueriesTableProduct.get(i).getStock()+" en stock");
            }
        }
    }

    public void DeleteItem(ArrayList<String> selectedProduct) throws SQLException
    {
        PipelineProductListQueries.InitializeAllList();
        int id = Integer.valueOf(selectedProduct.get(0));
        for (int i = 0; i < PipelineProductListQueries.listQueriesTableProduct.size() ; i++)
        {
            if (id == PipelineProductListQueries.listQueriesTableProduct.get(i).getIdProducts());
            {
                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProduct.get(i), PipelineProductListQueries.listQueriesTableProduct.get(i).getStock()-1,"stock");
                System.out.println(PipelineProductListQueries.listQueriesTableProduct.get(i).getStock()+" en stock");
                if(PipelineProductListQueries.listQueriesTableProduct.get(i).getStock() <= 0) MySQLOperations.delete(PipelineProductListQueries.listQueriesTableProduct.get(i));
            }
        }
    }

    private void displayProductDetails(ArrayList<String> selectedProduct) {
        if(selectedProduct!=null){
            txtAinfo.setText("id : "+selectedProduct.get(0)+"\nnom : "+selectedProduct.get(1)+"\nquantité : "+selectedProduct.get(2)+"\nprix d'achat : "+selectedProduct.get(3));
        }
    }

    public void initialize() throws SQLException {
        //initialisation des données
        List<String> tvalues = new ArrayList<String>();
        List<List<String>> produits = new ArrayList<>();
        PipelineProductListQueries(new Clothes("nike",84.99,99.99,39.0));
        produits.add(MySQLOperations.read(PipelineProductListQueries.listQueriesTableProduct.getFirst()));
        ObservableList<List<String>> prods = FXCollections.observableArrayList(produits);

        //Choix de la colonne a changé
        tvalues.add("nom du produit");
        tvalues.add("taille");
        tvalues.add("capital");
        tvalues.add("income");
        tvalues.add("outcome");
        ObservableList<String> type = FXCollections.observableArrayList(tvalues);

        //Affichage
        lstVproduit.setItems(prods);
        cmbType.setItems(type);
        lstVproduit.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetails((ArrayList<String>) lstVproduit.getSelectionModel().getSelectedItem()));
    }

    public void Poppup()
        {
            APpopup.setVisible(true);
            btn1.setVisible(false);
            btn2.setVisible(false);
            btn3.setVisible(false);
            txtAinfo.setVisible(false);
            lstVproduit.setVisible(false);
        System.out.println("popup actif");
    }

    public void Cancel()
    {
        APpopup.setVisible(false);
        Panebackground.setVisible(true);
        btn1.setVisible(true);
        btn2.setVisible(true);
        btn3.setVisible(true);
        txtAinfo.setVisible(true);
        lstVproduit.setVisible(true);
        System.out.println("popup inactif");
    }
    public void submit() throws SQLException
    {
        //A ecrire
    }
}
