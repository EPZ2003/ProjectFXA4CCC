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
import javafx.stage.Stage;
import WomenShopClasses.*;

import java.util.ArrayList;
import java.util.List;

public class HomePageController implements LoadindFXML {

    Scene scene;
    Stage stage;

    @FXML
    private Button btnPage1;

    @FXML
    private Button btnPage2;

    @FXML
    private Button btnPage3;

    @FXML
    private Label lblHomePAge;

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
            System.out.println("Error during loading : "+ e);
        }
    }

    List<Product> jpdt = new ArrayList<>();

    public void initialize()
    {
        Shoes Nike = new Shoes("Nike", 16.50, 16.2, 42.00);
        Accessory jewelery = new Accessory("Charles Logan", 16.2,18.1);
        Clothes Dolshigatana = new Clothes("Dolshigatana", 12.4,18.4,46.00);
        jpdt.add(Nike);
        jpdt.add(jewelery);
        jpdt.add(Dolshigatana);
        System.out.println("jpdt : " + jpdt);

    }
}
