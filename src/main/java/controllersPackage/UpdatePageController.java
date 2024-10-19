package controllersPackage;

import GeneralClasses.LoadindFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdatePageController implements LoadindFXML, Initializable {
    Scene scene;
    Stage stage;


    @FXML
    private Button btnGoBack;

    @FXML
    private Label lblError;
    @FXML
    private Button btnSubmit;

    @FXML
    private Label lblColumnName;

    @FXML
    private Label lblValue;

    @FXML
    private TextField txtAColumnName;

    @FXML
    private TextField txtAValue;

    @FXML
    void submited(ActionEvent event) {
        System.out.println(txtAColumnName.getText() + " "+txtAValue.getText());
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
        lblColumnName.setText("Nom de \n la \ncolonne");
        lblValue.setText("Valeur\n souhaité");
    }
}
