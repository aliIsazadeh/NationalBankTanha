package sample.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.TranslateTransition;
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



    public void initialize(URL location, ResourceBundle resources) {



    }



}
