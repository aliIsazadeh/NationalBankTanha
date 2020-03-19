package sample.Controller;

import DataStructure.Account;
import DataStructure.Person;
import Extras.CreateCardNumber;
import Extras.DBHelper;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class mainPageController implements Initializable {

    public JFXTextField txtAccountType;
    public JFXTextField txtCardNumber;
    public JFXTextField txtSecendPassWord;
    public JFXTextField txtSecendPassWordRepeat;
    public Label lblNotice;
    public Label lblFailNotice;
    public JFXButton btnBack;
    private DBHelper dbHelper = new DBHelper();


    public AnchorPane mainAnchorPane;
    public JFXTextField txtNationalCode;
    public JFXTextField txtFatherName;
    public DatePicker timePickerBornTime;
    public JFXTextField txtBornPlace;
    public JFXTextField txtJob;
    public JFXTextField txtAddress;
    public JFXTextField txtPhoneNumber;
    public CheckBox checkSendMessage;
    public AnchorPane infoAnchorPane;
    //
    public JFXComboBox comboMarriage;
    public JFXComboBox comboGender;
    public JFXComboBox comboAccount;

    //
    public JFXButton btnPersonalInfo;
    public JFXButton btnAddMoney;
    public JFXButton btnMinMoney;
    public JFXButton btnCardToCard;
    public JFXButton btnCash;
    public JFXButton btnPassWord;
    public JFXButton btnHistory;
    public JFXButton btnOther;
    public ImageView faceImage;

    public Label lblSuccess;
    public JFXButton btnConfirmInfos;
    boolean flagPH = true;
    private loginPageController loginPageController ;

    private int findComboIndex(JFXComboBox Box) {
        return Box.getSelectionModel().getSelectedIndex();
    }


    private void addVariable() {
       loginPageController loginPageController = new loginPageController();
        Account account = loginPageController.getAccount();

        System.out.println(account.getUserName());
        Person person = loginPageController.getPerson();
        System.out.println( "name :"+person.getName());

        System.out.println(txtNationalCode.getText());

        person.setNationalNumber(Long.parseLong(txtNationalCode.getText()));
        person.setFatherName(txtFatherName.getText());
        LocalDate localData = timePickerBornTime.getValue();
        person.setBornTime(localData.getYear() + "," + localData.getMonth() + "," + localData.getDayOfMonth());
        person.setBornPlace(txtBornPlace.getText());
        person.setJob(txtJob.getText());
        person.setAddress(txtAddress.getText());

        if (findComboIndex(comboMarriage) == 0) {
            person.setMarriage(false);
        }
        if (findComboIndex(comboMarriage) == 1) {
            person.setMarriage(true);
        }

        if (findComboIndex(comboAccount) == 0) {
            account.setAccountType("جاری");
        }
        if (findComboIndex(comboAccount) == 1) {
            account.setAccountType("قرض الحسنه");
        }
        if (findComboIndex(comboAccount) == 2) {
            account.setAccountType("پسنداز");
        }

        if (findComboIndex(comboMarriage) == 0) {
            person.setGender("مرد");
        }
        if (findComboIndex(comboMarriage) == 1) {
            person.setGender("زن");
        }
        account.setPerson(person);
        loginPageController.setAccount(account);

        dbHelper.insertAccount(account);

    }


    public void activePhoneText() {

        boolean flag = checkSendMessage.isSelected();

        if (flag)
            txtPhoneNumber.setVisible(true);

        if (!flag)
            txtPhoneNumber.setVisible(false);


    }


    private boolean emptyFinder(TextField txtField) {
        boolean empty = true;
        if (txtField.getText().equals("")) {
            empty = false;

        }
        return empty;
    }

    private void alert(String message, Label lbl, String color) {
        lbl.setText(message);
        lbl.setStyle("-fx-text-fill: " + color + ";");
    }


    public void recordInfos() {

        LocalDate localData = timePickerBornTime.getValue();
        if (checkSendMessage.isSelected()) {
            //      if(txtPhoneNumber.getText().equals(""))
            //  flagPH = false;

        }


        if (txtNationalCode.getText().equals("") || txtFatherName.getText().equals("") || txtJob.getText().equals("") || findComboIndex(comboAccount) == -1 || findComboIndex(comboMarriage) == -1 || findComboIndex(comboGender) == -1 || txtAddress.getText().equals("") || txtBornPlace.getText().equals("") || txtSecendPassWord.getText().equals("") || txtSecendPassWordRepeat.getText().equals("")) {

            alert("لطفا تمام فیلد هارا پر کنید", lblFailNotice, "red");


        } else {


            addVariable();

            //    Account account = new Account();
            btnPersonalInfo.setDisable(false);
            btnAddMoney.setDisable(false);
            btnMinMoney.setDisable(false);
            btnCardToCard.setDisable(false);
            btnCash.setDisable(false);
            btnPassWord.setDisable(false);
            btnHistory.setDisable(false);
            btnOther.setDisable(false);

            btnConfirmInfos.setVisible(false);
            lblSuccess.setVisible(true);

            if (findComboIndex(comboGender) == 0) {
                Image imageMale = new Image("./sample/bankPics/1.png");
                faceImage.setImage(imageMale);
            } else if (findComboIndex(comboGender) == 1) {
                Image imageFemale = new Image("./sample/bankPics/MasterFM.png");
                faceImage.setImage(imageFemale);

            }


            CreateCardNumber createCardNumber = new CreateCardNumber();
            txtCardNumber.setText(createCardNumber.createCardNumber());


            lblNotice.setVisible(false);
            lblFailNotice.setVisible(false);


            if (findComboIndex(comboAccount) == 0) {
                txtAccountType.setText("جاری");
                //   account.setAccountType("جاری");
            } else if (findComboIndex(comboAccount) == 1) {
                txtAccountType.setText("قرضا لحسنه");
                //  account.setAccountType("قرضا لحسنه");
            } else if (findComboIndex(comboAccount) == 2) {
                txtAccountType.setText("پسنداز");
                //  account.setAccountType("پسنداز");
            }

            infoAnchorPane.setVisible(false);


        }

    }


    public void profileLoad() throws IOException {


        lblSuccess.setVisible(false);


        try {
            Parent root = FXMLLoader.load(getClass().getResource("../FXML/personalInfos.fxml"));
            mainAnchorPane.getChildren().addAll(root);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }

    }


    public void addMoneyLoad() {

        lblSuccess.setVisible(false);
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/addMoney.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }


    }


    public void minMoneyLoad() {

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/minMoney.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }


    }

    public void remainLoad() {

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/reamainMoney.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }


    }

    public void cardToCardLoad() {

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/cardToCard.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }


    }

    public void HistoryLoad() {
        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/history.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }


    }

    public void ChangePassLoad() {

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/changePassWord.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }


    }

    public void anotherOptions() {

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/bill.fxml"));
            mainAnchorPane.getChildren().addAll(anchorPane);
        } catch (IOException ex) {
            System.out.println("Problem in loading");
        }


    }


    public void exit() {

        Alert alert = new Alert(Alert.AlertType.WARNING, "آیا میخواهید خارج شوید؟ ", ButtonType.YES, ButtonType.NO);


        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent())
            if (result.get() == ButtonType.YES)
                System.exit(0);
    }

    public void back() {
        Alert alert = new Alert(Alert.AlertType.WARNING, "آیا می خواهید به صفحه اصلی برگردید؟ ", ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == ButtonType.YES) {

                Parent root;
                try {
                    Stage stage = (Stage) btnBack.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/loginPage.fxml"));
                    root = loader.load();
                    stage = new Stage();
                    Stage finalStage = stage;
                    finalStage.setResizable(false);
                    finalStage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(new Scene(root, 870, 580));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

    }


    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                TextField txt_TextField = (TextField) e.getSource();
                if (txt_TextField.getText().length() >= max_Lengh) {
                    e.consume();
                }
                if (e.getCharacter().matches("[ا-ی-ن]") || e.getCharacter().matches("[ ]")) {
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


    public void test(Account account) {

        account.setAccountType("fffff");

        System.out.println(account.getAccountType());
        System.out.println(account.getPerson().getName());


    }


    public void initialize(URL location, ResourceBundle resources) {


        txtNationalCode.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(10));
        txtFatherName.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(15));
        txtJob.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(20));
        txtPhoneNumber.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(11));
        //    txtAddress.addEventFilter(KeyEvent.KEY_TYPED , letter_Validation(50));
        txtBornPlace.addEventFilter(KeyEvent.KEY_TYPED, letter_Validation(10));
        txtSecendPassWord.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));
        txtSecendPassWordRepeat.addEventFilter(KeyEvent.KEY_TYPED, numeric_Validation(4));


        String[] items1 = {"مجرد", "متاهل"};
        comboMarriage.getItems().addAll(items1);

        String[] items2 = {"جاری", "قرض احسنه", "پسنداز"};
        comboAccount.getItems().addAll(items2);

        String[] items3 = {"مرد", "زن"};
        comboGender.getItems().addAll(items3);


//        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("../FXML/infoCompletion.fxml"));
//        mainAnchorPane.getChildren().addAll(anchorPane);


    }
}
