package controllersPackage;

import GeneralClasses.LoadindFXML;
import SQLModule.SQLCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class UpdatePageController implements LoadindFXML, Initializable {
    Scene scene;
    Stage stage;
    @FXML
    private Button btnGoBack;

    @FXML
    private Label lblQuit;

    @FXML
    private Button btnSubmit;

    @FXML
    private ChoiceBox<String> cbxColumnName;

    @FXML
    private Label lblColumnName;

    @FXML
    private Label lblError;

    @FXML
    private Label lblValue;

    @FXML
    private TextField txtAValue;

    public static int idChoosen = 0;

    private String[] columnName = {"product_name","stock","sellPrice","purchasePrice"};

    @FXML
    void submited(ActionEvent event) throws Exception {
        String[] columnNameProduct = {"product_name","stock"};
        String[] columnNamePrices = {"sellPrice","purchasePrice"};
        String columnName = getColumnName(event);
        boolean foundProduct = Arrays.asList(columnNameProduct).contains(columnName);
        boolean foundPrices = Arrays.asList(columnNamePrices).contains(columnName);

        if ( foundProduct ){
            if (columnName == columnNameProduct[0]){
                SQLCommand.updateTableProduct(idChoosen,columnName,txtAValue.getText());
            }else{
                SQLCommand.updateTableProduct(idChoosen,columnName,Integer.valueOf(txtAValue.getText()));
            }

        }else if (foundPrices){
            SQLCommand.updateTableProduct(idChoosen,columnName,Double.valueOf(txtAValue.getText()));
        }else{
            throw new Exception("Bad name column");
        }
        btnSubmit.setDisable(true);
        lblQuit.setVisible(true);


    }
    public void displayError(Exception err){
        lblError.setText("Error : "+ err);
    }
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
            displayError(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbxColumnName.getItems().addAll(columnName);
        cbxColumnName.setOnAction(this::getColumnName);
        lblColumnName.setText("Nom de \n la \ncolonne");
        lblValue.setText("Valeur\n souhaité");
        lblQuit.setVisible(false);
    }

    private String getColumnName(ActionEvent event) {
        return cbxColumnName.getValue();
    }
}
