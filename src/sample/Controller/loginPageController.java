package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class loginPageController implements Initializable {


    public Pane movablePane;
    public AnchorPane registerAnchorPane;
    public JFXButton signUpTrans;
    public AnchorPane loginAnchorPane;
    public JFXButton signInTrans;
    public Label lblFailAlert;
    public Label lblFailAllert;
    public Label lblSuccsesAllert;
    public CheckBox checkLoginPass;
    public JFXButton btnLogin;
    public JFXTextField txtPassLogin;
    public JFXTextField txtUserLogin;
    public JFXTextField txtRegisterFirstName;
    public JFXTextField txtRegisterPassRepeat;
    public JFXButton btnRegister;
    public JFXTextField txtRegisterLastName;
    public JFXTextField txtRegisterPass;
    public CheckBox checkRegisterPass;

    public void signIntransPort(){

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.7));
        translateTransition.setNode(movablePane);

        translateTransition.setToX(450);
        translateTransition.play();
        movablePane.setTranslateX(-279);

        registerAnchorPane.setVisible(false);
        signInTrans.setVisible(false);

        loginAnchorPane.setVisible(true);
        signUpTrans.setVisible(true);



    }



    public  void  signUpTransPort(){
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.7));
        translateTransition.setNode(movablePane);

        translateTransition.setToX(10);
        translateTransition.play();
        movablePane.setTranslateX(-279);


        registerAnchorPane.setVisible(true);
        signInTrans.setVisible(true);

        loginAnchorPane.setVisible(false);
        signUpTrans.setVisible(false);


    }
    public void loadMainPage(){

        Parent root;
        try {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/mainPage.fxml"));
            root = loader.load();
            stage = new Stage();
            Stage finalStage = stage;
            finalStage.setResizable(false);
            finalStage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void exit() {

        Alert alert = new Alert(Alert.AlertType.WARNING, "آیا میخواهید خارج شوید؟ ", ButtonType.YES, ButtonType.NO);


        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.YES)
                System.exit(0);
    }









    public void initialize(URL location, ResourceBundle resources) {



    }



}
