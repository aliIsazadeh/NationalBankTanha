package sample.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
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
import javafx.scene.layout.BorderPane;
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
    public JFXPasswordField txtPassLogin;
    public JFXTextField txtUserLogin;
    public JFXTextField txtRegisterFirstName;
    // public JFXTextField txtRegisterPassRepeat;
    public JFXButton btnRegister;
    public JFXTextField txtRegisterLastName;
    //  public JFXTextField txtRegisterPass;
    public CheckBox checkRegisterPass;
    public JFXPasswordField txtRegisterPass;
    public JFXPasswordField txtRegisterPassRepeat;
    public JFXTextField txtRegisterPass2;
    public JFXTextField txtPassLogin2;

    private FadeTransition fadeTransition(Node node, Duration duration, double fromValue, double toValue) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setNode(node);
        fadeTransition.setDuration(new Duration(1500));
        fadeTransition.setFromValue(fromValue);
        fadeTransition.setRate(1.7);
        fadeTransition.setToValue(toValue);


        return fadeTransition;

    }

    public void signIntransPort() {

        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.7));
        translateTransition.setNode(movablePane);

        translateTransition.setToX(445);

        // movablePane.setTranslateX(-279);
        //hi


        signInTrans.setVisible(false);


        FadeTransition fadeRegisterAnchorPane = fadeTransition(registerAnchorPane, Duration.seconds(1), 1, 0);
        FadeTransition fadeSignInTransAnchorPane = fadeTransition(signInTrans, Duration.seconds(1), 1, 0);


        loginAnchorPane.setVisible(true);
        signUpTrans.setVisible(true);


        FadeTransition fadeLoginAnchorPane = fadeTransition(loginAnchorPane, Duration.seconds(1), 0, 1);
        FadeTransition fadeSignUpTransAnchorPane = fadeTransition(signUpTrans, Duration.seconds(1), 0, 1);


        //sorry mr.Ghader  :)

        translateTransition.play();

        fadeRegisterAnchorPane.play();
        //registerAnchorPane.setVisible(false);
        fadeSignInTransAnchorPane.play();

        fadeLoginAnchorPane.play();
        fadeSignUpTransAnchorPane.play();


    }


    public void signUpTransPort() {
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.7));
        translateTransition.setNode(movablePane);

        translateTransition.setToX(0);

        //movablePane.setTranslateX(-279);


        FadeTransition fadeRigisterAnchorPane = fadeTransition(registerAnchorPane, Duration.seconds(1), 0, 1);
        FadeTransition fadeSignInTransAnchorPane = fadeTransition(signInTrans, Duration.seconds(1), 0, 1);


        FadeTransition fadeLoginAnchorPane = fadeTransition(loginAnchorPane, Duration.seconds(1), 1, 0);
        FadeTransition fadeSignUpTransAnchorPane = fadeTransition(signUpTrans, Duration.seconds(1), 1, 0);

        //sorry mr.Ghader  :)

        translateTransition.play();

        fadeRigisterAnchorPane.play();
        fadeSignInTransAnchorPane.play();
        fadeLoginAnchorPane.play();
        fadeSignUpTransAnchorPane.play();

        registerAnchorPane.setVisible(true);
        signInTrans.setVisible(true);
        // loginAnchorPane.setVisible(false);
        signUpTrans.setVisible(false);

    }

    public void loadMainPage() {

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


    //for registering
    public void considerTXT() {


    }

    //for login
    public void considerRight() {


    }

    public void showPasswordRegister() {

        boolean flag = checkRegisterPass.isSelected();

        if (flag) {


            txtRegisterPass.setVisible(false);
            txtRegisterPass2.setText(txtRegisterPass.getText());
            txtRegisterPass2.setVisible(true);


        }

        if (flag == false) {

            txtRegisterPass2.setVisible(false);
            txtRegisterPass.setVisible(true);


        }


    }

    public void showPassLogin() {
        boolean flag1 = checkLoginPass.isSelected();

        if (flag1) {

            txtPassLogin.setVisible(false);
            txtPassLogin2.setText(txtPassLogin.getText());
            txtPassLogin2.setVisible(true);


        }

        if (flag1 == false) {


            txtPassLogin2.setVisible(false);
            txtPassLogin.setVisible(true);


        }


    }


    public void initialize(URL location, ResourceBundle resources) {


    }


}
