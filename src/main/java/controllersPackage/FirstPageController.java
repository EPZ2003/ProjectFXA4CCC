package controllersPackage;

import BackToFrontLinked.PipelineProductListQueries;
import GeneralClasses.LoadindFXML;
import SQLModule.MySQLOperations;
import WomenShopClasses.Accessory;
import WomenShopClasses.Clothes;
import WomenShopClasses.Product;
import WomenShopClasses.Shoes;
import com.mysql.cj.x.protobuf.MysqlxCrud;
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

public class FirstPageController implements LoadindFXML
{
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
    ArrayList<String> SelectedProduct;

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

    @FXML
    Label lblListedeproduit;

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

    public void handleUpdateButtonClick() throws SQLException {
        // Récupérer l'élément sélectionné dans la ListView
        SelectedProduct = (ArrayList<String>)lstVproduit.getSelectionModel().getSelectedItem();

        // Si un produit est sélectionné, appeler la méthode purchase
        if (SelectedProduct != null) {
            Poppup();
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
            txtAinfo.setText("id : "+selectedProduct.get(0)+"\nnom : "+selectedProduct.get(1)+"\nquantité : "+selectedProduct.get(2)+"\nprix d'achat : "+selectedProduct.get(3) + "\ntaille : "+selectedProduct.size());
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
        tvalues.add("Nom du produit");
        tvalues.add("Quantité");
        tvalues.add("Taille");
        tvalues.add("Capital");
        tvalues.add("Income");
        tvalues.add("Outcome");
        ObservableList<String> type = FXCollections.observableArrayList(tvalues);

        //Affichage
        lstVproduit.setItems(prods);
        cmbType.setItems(type);
        lstVproduit.getSelectionModel().selectedItemProperty().addListener(e-> displayProductDetails((ArrayList<String>) lstVproduit.getSelectionModel().getSelectedItem()));
    }

    public void Poppup()
        {
            lblPrices.setText("Update : " + SelectedProduct.get(1));
            APpopup.setVisible(true);
            lblListedeproduit.setVisible(false);
            btn1.setVisible(false);
            btn2.setVisible(false);
            btn3.setVisible(false);
            txtAinfo.setVisible(false);
            lstVproduit.setVisible(false);
        System.out.println("popup actif");
    }

    public void Cancel()
    {
        lblPrices.setText("Prices");
        APpopup.setVisible(false);
        Panebackground.setVisible(true);
        lblListedeproduit.setVisible(true);
        btn1.setVisible(true);
        btn2.setVisible(true);
        btn3.setVisible(true);
        txtAinfo.setVisible(true);
        lstVproduit.setVisible(true);
        System.out.println("popup inactif");
    }
    public void submit() throws SQLException
    {
        String new_data = txtFNouveauchamp.getText();
        String column = cmbType.getValue();
        int id = Integer.valueOf(SelectedProduct.get(0));
        if(column != null && new_data != "")
        {
            PipelineProductListQueries.InitializeAllList();
            if(column == "Nom du produit" ||column == "Quantité" ||column == "Taiile")
            {
                for (int i = 0; i < PipelineProductListQueries.listQueriesTableProduct.size(); i++)
                {
                    if (PipelineProductListQueries.listQueriesTableProduct.get(i).getId() == id)
                    {
                        if (column == "Nom du produit")
                            MySQLOperations.update(PipelineProductListQueries.listQueriesTableProduct.get(i),new_data,"product_name");

                        if (column == "Quantité") {
                            try {
                                int quantity = Integer.valueOf(new_data);
                                PipelineProductListQueries.listQueriesTableProduct.get(i).setStock(quantity);
                                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProduct.get(i),quantity,"stock");
                            } catch (NumberFormatException e)
                            {
                                System.out.println("erreur format quantité");
                            }

                        }
                        if (column == "Taille") {
                            try {
                                Double size = Double.valueOf(new_data);
                                PipelineProductListQueries.listQueriesTableProduct.get(i).setSpecialAttribute(size);
                                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProduct.get(i),size,"specialAttribute");
                            } catch (NumberFormatException e)
                            {
                                System.out.println("erreur format Taille");
                            }
                        }
                    }
                }
            }
            else
            {
                for (int i = 0; i < PipelineProductListQueries.listQueriesTableProductPrices.size(); i++) {
                    if (PipelineProductListQueries.listQueriesTableProductPrices.get(i).getId() == id) {
                        if (column == "Capital") {
                            try {
                                Double capital = Double.valueOf(new_data);
                                PipelineProductListQueries.listQueriesTableProduct.get(i).setSpecialAttribute(capital);
                            } catch (NumberFormatException e) {
                                System.out.println("erreur format capital");
                            }
                        }
                        if (column == "Prix d'achat") {
                            try {
                                Double purchase = Double.valueOf(new_data);
                                PipelineProductListQueries.listQueriesTableProduct.get(i).setSpecialAttribute(purchase);
                                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProductPrices.get(i),purchase,"income");
                            } catch (NumberFormatException e) {
                                System.out.println("erreur format Prix d'achat");
                            }
                        }
                        if (column == "Prix de vente") {
                            try {
                                Double sell = Double.valueOf(new_data);
                                PipelineProductListQueries.listQueriesTableProduct.get(i).setSpecialAttribute(sell);
                                MySQLOperations.update(PipelineProductListQueries.listQueriesTableProductPrices.get(i),sell,"outcome");
                            } catch (NumberFormatException e) {
                                System.out.println("erreur format Prix de vente");
                            }
                        }
                    }
                }
            }
        }
    }
}