package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
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

    FadeTransition fadeTransition(Node node, Duration duration, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(node);
        fadeTransition.setDuration(duration);
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setToValue(toValue);
        return fadeTransition;
    }

    public void signIntransPort() {

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.7));
        translateTransition.setNode(movablePane);

        translateTransition.setToX(450);

       // movablePane.setTranslateX(-279);
        //hi


        registerAnchorPane.setVisible(false);
        signInTrans.setVisible(false);


        FadeTransition fadeRegisterAnchorPane = fadeTransition(registerAnchorPane,Duration.seconds(0.7),1,0);
        FadeTransition fadeSignInTransAnchorPane = fadeTransition(signInTrans,Duration.seconds(0.7),1,0);



        loginAnchorPane.setVisible(true);
        signUpTrans.setVisible(true);



        FadeTransition fadeLoginAnchorPane = fadeTransition(loginAnchorPane, Duration.seconds(0.7), 0, 1);
        FadeTransition fadeSignUpTransAnchorPane = fadeTransition(signUpTrans, Duration.seconds(0.7), 0, 1);


        //sorry mr.Ghader  :)

        translateTransition.play();

        fadeRegisterAnchorPane.play();
        fadeSignInTransAnchorPane.play();

        fadeLoginAnchorPane.play();
        fadeSignUpTransAnchorPane.play();



    }


    public void signUpTransPort() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.7));
        translateTransition.setNode(movablePane);

        translateTransition.setToX(10);

        //movablePane.setTranslateX(-279);


        registerAnchorPane.setVisible(true);
        signInTrans.setVisible(true);


        FadeTransition fadeRigisterAnchorPane = fadeTransition(registerAnchorPane,Duration.seconds(0.7),0,1);
        FadeTransition fadeSignInTransAnchorPane = fadeTransition(signInTrans,Duration.seconds(0.7),0,1);


        loginAnchorPane.setVisible(false);
        signUpTrans.setVisible(false);


        FadeTransition fadeLoginAnchorPane = fadeTransition(loginAnchorPane, Duration.seconds(0.7), 1, 0);
        FadeTransition fadeSignUpTransAnchorPane = fadeTransition(signUpTrans, Duration.seconds(0.7), 1, 0);

        //sorry mr.Ghader  :)

        translateTransition.play();

        fadeRigisterAnchorPane.play();
        fadeSignInTransAnchorPane.play();
        fadeLoginAnchorPane.play();
        fadeSignUpTransAnchorPane.play();

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
