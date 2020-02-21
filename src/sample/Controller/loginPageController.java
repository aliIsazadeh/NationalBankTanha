package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class loginPageController {


    public Pane movablePane;
    public AnchorPane registerAnchorPane;
    public JFXButton signUpTrans;
    public AnchorPane loginAnchorPane;
    public JFXButton signInTrans;
    public Label lblFailAlert;

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


    public void initialize(URL location, ResourceBundle resources) {


    }


}
