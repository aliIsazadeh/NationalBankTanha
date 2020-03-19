package sample.Controller;

import DataStructure.Account;
import DataStructure.Person;
import Extras.DBHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import javafx.animation.FadeTransition;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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

    public Label lblSuccessAlert;
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
    public Label lblFailAlertRegister;
    public Label lblFailLogin;
    public JFXTextField txtRegisterUserName;

    static Account account;
    static Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    private DBHelper dbHelper = new DBHelper();

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


        signInTrans.setVisible(false);
        alert("", lblSuccessAlert, "green");


        FadeTransition fadeRegisterAnchorPane = fadeTransition(registerAnchorPane, Duration.seconds(1), 1, 0);
        FadeTransition fadeSignInTransAnchorPane = fadeTransition(signInTrans, Duration.seconds(1), 1, 0);


        loginAnchorPane.setVisible(true);
        signUpTrans.setVisible(true);


        FadeTransition fadeLoginAnchorPane = fadeTransition(loginAnchorPane, Duration.seconds(1), 0, 1);
        FadeTransition fadeSignUpTransAnchorPane = fadeTransition(signUpTrans, Duration.seconds(1), 0, 1);


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

        translateTransition.setToX(0);


        FadeTransition fadeRigisterAnchorPane = fadeTransition(registerAnchorPane, Duration.seconds(1), 0, 1);
        FadeTransition fadeSignInTransAnchorPane = fadeTransition(signInTrans, Duration.seconds(1), 0, 1);


        FadeTransition fadeLoginAnchorPane = fadeTransition(loginAnchorPane, Duration.seconds(1), 1, 0);
        FadeTransition fadeSignUpTransAnchorPane = fadeTransition(signUpTrans, Duration.seconds(1), 1, 0);


        translateTransition.play();

        fadeRigisterAnchorPane.play();
        fadeSignInTransAnchorPane.play();
        fadeLoginAnchorPane.play();
        fadeSignUpTransAnchorPane.play();

        registerAnchorPane.setVisible(true);
        signInTrans.setVisible(true);

        signUpTrans.setVisible(false);

    }

    public void loadMainPage() {

        if (txtUserLogin.getText().equals("") && txtPassLogin.getText().equals("")) {

            alert("لطفا نام کاربری و رمز عبور را وارد کنید.", lblFailLogin, "red");


        } else if (!txtUserLogin.getText().equals("") && txtPassLogin.getText().equals("")) {
            alert("لطفا رمز عبور را وارد کنید", lblFailLogin, "red");

        } else if (txtUserLogin.getText().equals("") && !txtPassLogin.getText().equals("")) {
            alert("لطفا نام کاربری را وارد کنید", lblFailLogin, "red");
        } else if (!txtUserLogin.getText().equals(txtRegisterUserName.getText()) || !txtPassLogin.getText().equals(txtRegisterPass.getText())) {
            alert("رمز عبور یا نام کاربری اشتباه است", lblFailLogin, "red");
        } else if (txtUserLogin.getText().equals(txtRegisterUserName.getText()) && (txtPassLogin.getText().equals(txtRegisterPass.getText()) || txtPassLogin.getText().equals(txtRegisterPass2.getText()))) {

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
    }


    public void exit() {

        Alert alert = new Alert(Alert.AlertType.WARNING, "آیا میخواهید خارج شوید؟ ", ButtonType.YES, ButtonType.NO);


        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.YES)
                System.exit(0);
    }


    public void showPasswordRegister() {

        boolean flag = checkRegisterPass.isSelected();

        if (flag) {


            txtRegisterPass.setVisible(false);
            txtRegisterPass2.setText(txtRegisterPass.getText());
            txtRegisterPass2.setVisible(true);


        }

        if (flag == false) {


            txtRegisterPass.setText(txtRegisterPass2.getText());
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


            txtPassLogin.setText(txtPassLogin2.getText());
            txtPassLogin2.setVisible(false);
            txtPassLogin.setVisible(true);


        }


    }

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }

    public void considerTextsRegister() {


        if (txtRegisterFirstName.getText().equals("") || txtRegisterLastName.getText().equals("") || txtRegisterPass.getText().equals("") || txtRegisterPassRepeat.getText().equals("")) {
            alert("لطفا فیلد هارا پر کنید", lblFailAlertRegister, "red");
        } else if (!txtRegisterPass.getText().equals(txtRegisterPassRepeat.getText()) && !txtRegisterFirstName.getText().equals("") && !txtRegisterLastName.getText().equals("")) {

            alert("تکرار رمز باید با خود رمز عبور مطابقت داشته باشد...", lblFailAlertRegister, "red");

        } else {
            alert("ثبت نام شما با موفقیت انجام شد.اکنون میتوانید وارد سیستم شوید", lblSuccessAlert, "green");
            Person person = new Person();
            Account account = new Account();
            DBHelper dbHelper = new DBHelper();

            addVariable();


            /// hi ali

        }


    }


    public void considerLogin() {

        if (txtUserLogin.getText().equals("") && txtPassLogin.getText().equals("")) {
            alert("لطفا نام کاربری و رمز عبور را وارد کنید.", lblFailLogin, "red");
        }

    }

    public void testFirstName() {
        txtRegisterFirstName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (txtRegisterFirstName.getText().contains("s"))


                    if (!txtRegisterLastName.getText().equals("") && !txtRegisterPass.getText().equals("") && !txtRegisterPassRepeat.getText().equals("") && !txtRegisterUserName.getText().equals("")) {

                        btnRegister.setDisable(false);
                    }


            }
        });


    }

    public void testLastName() {
        txtRegisterLastName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!txtRegisterFirstName.getText().equals("") && !txtRegisterPass.getText().equals("") && !txtRegisterPassRepeat.getText().equals("") && !txtRegisterUserName.getText().equals("")) {

                    btnRegister.setDisable(false);
                }


            }
        });
    }

    public void testUserName() {
        txtRegisterUserName.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!txtRegisterFirstName.getText().equals("") && !txtRegisterPass.getText().equals("") && !txtRegisterPassRepeat.getText().equals("") && !txtRegisterLastName.getText().equals("")) {

                    btnRegister.setDisable(false);
                }


            }
        });

    }

    public void testPass() {
        txtRegisterPass.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!txtRegisterFirstName.getText().equals("") && !txtRegisterUserName.getText().equals("") && !txtRegisterPassRepeat.getText().equals("") && !txtRegisterLastName.getText().equals("")) {

                    txtRegisterPass2.setText(txtRegisterPass.getText());
                    btnRegister.setDisable(false);
                }


            }
        });

    }


    public void testPass2() {
        txtRegisterPass2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                txtRegisterPass.setText(txtRegisterPass2.getText());

            }
        });

    }


    public void testPassRepeat() {
        txtRegisterPassRepeat.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!txtRegisterFirstName.getText().equals("") && !txtRegisterUserName.getText().equals("") && !txtRegisterPass.getText().equals("") && !txtRegisterLastName.getText().equals("")) {


                    btnRegister.setDisable(false);

                }


            }
        });

    }

    public void testUserLogin() {
        txtUserLogin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (!txtPassLogin.getText().equals("")) {

                    btnLogin.setDisable(false);

                }


            }
        });

    }

    public void testPassLogin() {
        txtPassLogin.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                if (!txtUserLogin.getText().equals("")) {

                    btnLogin.setDisable(false);
                    txtPassLogin2.setText(txtPassLogin.getText());

                }


            }
        });

    }


    public void testPassLogin2() {
        txtPassLogin2.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {


                txtPassLogin.setText(txtPassLogin2.getText());


            }
        });

    }


    public EventHandler<KeyEvent> letter_ValidationForUserName(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[ا-ی-ن]") || e.getCharacter().matches("[A-Za-z]")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[ا-ی-ن]") || e.getCharacter().matches(" ")) {
                } else {
                    e.consume();
                }
            }
        };
    }

    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[0-9.]")) {
                    if (txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")) {
                        e.consume();
                    } else if (txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")) {
                        e.consume();
                    }
                } else {
                    e.consume();
                }
            }
        };
    }


    private void addVariable() {
        Person person = new Person();
        Account account = new Account();
        person.setName(txtRegisterFirstName.getText());
        System.out.println(txtRegisterFirstName.getText());
        person.setLastName(txtRegisterLastName.getText());
        account.setUserName(txtRegisterUserName.getText());
        account.setAccountPassword(txtRegisterPass.getText());
        account.setPerson(person);

//        loginPageController loginPageController = new loginPageController();
//        account.setPerson(person);
        setPerson(person);
        setAccount(account);
//        loginPageController.setAccount(account);
//        loginPageController.setPerson(person);
//        loginPageController loginPageController1 = new loginPageController();
//        System.out.println(loginPageController1.getAccount().getPerson().getName());
    }

    public boolean isExistAccount(String userName) {
        DBHelper dbHelper = new DBHelper();
        Account account = dbHelper.readAccount(userName);
        if (account == null)
            return false;
        else
            return true;

    }

    public void initialize(URL location, ResourceBundle resources) {

        txtRegisterFirstName.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(12));
        txtRegisterLastName.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(15));

        txtRegisterUserName.addEventFilter(KeyEvent.KEY_TYPED, letter_ValidationForUserName(10));
        //
        txtUserLogin.addEventFilter(KeyEvent.KEY_TYPED, letter_ValidationForUserName(10));

        txtRegisterPass.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(12));
        txtRegisterPassRepeat.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(12));
        txtRegisterPass2.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(12));
        //
        txtPassLogin.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(12));
        txtPassLogin2.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(12));


    }


}
