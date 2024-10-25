package controllersPackage;

import BackToFrontLinked.PipelineProductListQueries;
import GeneralClasses.LoadindFXML;
import SQLModule.MySQLOperations;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static BackToFrontLinked.PipelineProductListQueries.listQueriesTableMoney;


public class SecondPageController implements LoadindFXML, Initializable {

    private Stage stage;
    private Scene scene;


    @FXML
    private Button btnHomePage;

    @FXML
    private Button btnPage1;

    @FXML
    private Button btnPage3;

    @FXML
    private Label lblCapital;

    @FXML
    private Label lblErrorAccountability;

    @FXML
    private Label lblIncome;

    @FXML
    private Label lblOutcome;

    @FXML
    private Label lblResultCapital;

    @FXML
    private Label lblResultIncome;

    @FXML
    private Label lblResultOutcome;

    @FXML
    private Label lblStorage;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try{
            Double outcome = Double.valueOf(SQLCommand.readTableMoney().get(0).get(2));
            Double income = Double.valueOf(SQLCommand.readTableMoney().get(0).get(1));
            Double capital = Double.valueOf(SQLCommand.readTableMoney().get(0).get(0));
            //SQLCommand.updateTableMoney("capital",capital);
            SQLCommand.updateTableMoney("capital",income - outcome);
            lblResultCapital.setText(Math.round(income - outcome)   + " $");
            lblResultOutcome.setText(String.valueOf(Math.round(outcome)+ " $"));
            lblResultIncome.setText(String.valueOf(Math.round(income)+ " $"));

        }catch (Exception err){
            displayError();
        }


    }
}
