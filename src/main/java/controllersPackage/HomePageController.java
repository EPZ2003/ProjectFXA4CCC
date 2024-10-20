package controllersPackage;

import BackToFrontLinked.PipelineProductListQueries;
import GeneralClasses.LoadindFXML;
import SQLModule.MySQLOperations;
import SQLModule.Queries;
import SQLModule.SQLCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import WomenShopClasses.*;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import static WomenShopClasses.Product.*;

public class HomePageController implements LoadindFXML, Initializable {

    Scene scene;
    Stage stage;
    @FXML

    private TextArea txtACapital;
    @FXML
    private Button btnPage1;

    @FXML
    private Button btnPage2;

    @FXML
    private Button btnPage3;

    @FXML
    private Label lblErrorHomePage;

    @FXML
    private Label lblInfoLine1;

    @FXML
    private Label lblInfoLine2;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblInstructions;


    @FXML
    private Button btnConfirmCapital;

    public void goToFirstPage(ActionEvent event) {
        loadingFXML("firstPage.fxml",event);
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
            displayError();
        }
    }

    public void displayError(){
        lblErrorHomePage.setText("Error !");
    }



    public void enterCapital() throws SQLException {

        Double income = Double.valueOf(SQLCommand.readTableMoney().get(0).get(1));
        SQLCommand.updateTableMoney("income",  income + Double.valueOf(txtACapital.getText() ) );
        txtACapital.setDisable(true);

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            PipelineProductListQueries.InitializeAllList();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
