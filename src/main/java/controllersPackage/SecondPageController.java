package controllersPackage;

import GeneralClasses.LoadindFXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SecondPageController implements LoadindFXML {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button btnHomePage;

    @FXML
    private Button btnPage1;

    @FXML
    private Button btnPage3;

    @FXML
    private Label lblErrorAccountability;

    @FXML
    private Label lblAccountability;

    @FXML
    private TextField txtFCapital;

    @FXML
    private TextField txtFOutcome;

    @FXML
    private TextField txtFIncome;

    public void goToHomePage(ActionEvent event) {
        loadingFXML("homePage.fxml",event);
    }
    public void goToFirstPage(ActionEvent event) {
        loadingFXML("firstPage.fxml",event);
    }
    public void goToThirdPage(ActionEvent event) {
        loadingFXML("thirdPage.fxml",event);
    }
    public void displayError(){
        lblErrorAccountability.setText("Error !");
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

}
