package controllers;

import com.google.gson.Gson;
import enums.RequestType;
import enums.ResponseStatus;
import enums.Roles;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import messages.AlertMessage;
import model.Admin;
import model.Doctor;
import model.Person;
import model.User;
import tcp.Request;
import tcp.Response;
import utility.ClientSocket;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    @FXML
    private CheckBox login_checkBox;

    @FXML
    private AnchorPane login_form;

    @FXML
    private Button login_logerBtn;

    @FXML
    private PasswordField login_password;

    @FXML
    private TextField login_username;

    @FXML
    private AnchorPane main_form;

    @FXML
    private CheckBox register_checkBox;

    @FXML
    private AnchorPane register_form;

    @FXML
    private Hyperlink register_loginHere;
    @FXML
    private Hyperlink login_registerHere;

    @FXML
    private PasswordField register_password;

    @FXML
    private PasswordField register_passwordRepeat;

    @FXML
    private Button register_signUpBtn;

    @FXML
    private TextField register_username;

    @FXML
    private TextField register_name;
    @FXML
    private TextField register_surname;
    @FXML
    private TextField register_patronymic;
    @FXML
    private TextField register_showPassword;
    @FXML
    private TextField register_showPasswordRepeat;
    @FXML
    private TextField login_showPassword;

   private AlertMessage alert = new AlertMessage();

   public void loginAccount() throws IOException {
       if(login_username.getText().isEmpty() || login_password.getText().isEmpty()){
           alert.errorMessage("Incorrect Username/Password");
       }
       else{
           if(!login_showPassword.isVisible()){
               if(!login_showPassword.getText().equals(login_password.getText())){
                   login_showPassword.setText(login_password.getText());
               }
           }
           else{
               if(!login_showPassword.getText().equals(login_password.getText())){
                   login_password.setText(login_showPassword.getText());
               }
           }
           User user = new User();
           user.setLogin(login_username.getText());
           user.setPassword(login_password.getText());
           Request requestModel = new Request();
           requestModel.setRequestMessage(new Gson().toJson(user));
           requestModel.setRequestType(RequestType.LOGIN);
           ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
           ClientSocket.getInstance().getOut().flush();
           String answer = ClientSocket.getInstance().getInStream().readLine();
           Response responseModel = new Gson().fromJson(answer, Response.class);
           if (responseModel.getResponseStatus() == ResponseStatus.OK) {
               alert.successMessage("Success");
               login_logerBtn.getScene().getWindow().hide();
              /* ClientSocket.getInstance().setUser(new Gson().fromJson(responseModel.getResponseData(), User.class));
*/             String data = responseModel.getResponseData();
               User user1 = new Gson().fromJson(data, User.class);
               Roles role = Roles.valueOf(user1.getRole());
               FXMLLoader loader;
               Parent root = null;
               Stage stage;
               switch (role){
                   case User:
                       break;
                   case Admin:
                       requestModel.setRequestMessage(new Gson().toJson(user1));
                       requestModel.setRequestType(RequestType.GET_ADMIN);
                       ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
                       ClientSocket.getInstance().getOut().flush();
                       answer = ClientSocket.getInstance().getInStream().readLine();
                       responseModel = new Gson().fromJson(answer, Response.class);
                       data = responseModel.getResponseData();
                       Admin admin = new Gson().fromJson(data, Admin.class);
                       loader = new FXMLLoader(getClass().getResource("/mainAdmin.fxml"));
                       MainAdminController adminController = new MainAdminController(admin.getAdminId(), admin.getUser().getLogin());
                       loader.setController(adminController);
                       root = loader.load();;
                       stage = new Stage();
                       stage.setTitle("Admin Portal");
                       stage.setScene(new Scene(root));
                       stage.show();
                       break;
                   case Doctor:{
                       requestModel.setRequestMessage(new Gson().toJson(user1));
                       requestModel.setRequestType(RequestType.GET_DOCTOR);
                       ClientSocket.getInstance().getOut().println(new Gson().toJson(requestModel));
                       ClientSocket.getInstance().getOut().flush();
                       answer = ClientSocket.getInstance().getInStream().readLine();
                       responseModel = new Gson().fromJson(answer, Response.class);
                       data = responseModel.getResponseData();
                       Doctor doctor = new Gson().fromJson(data, Doctor.class);
                       loader = new FXMLLoader(getClass().getResource("/doctorMainForm.fxml"));
                       MainDoctorController doctorController = new MainDoctorController(doctor.getDoctorId(), doctor.getUser().getLogin());
                       try {
                           loader.setController(doctorController);
                           root = loader.load();
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                       stage = new Stage();
                       stage.setTitle("Doctor Portal");
                       stage.setScene(new Scene(root));
                       stage.show();
                       break;
                   }
               }

           } else {
               alert.errorMessage("Error!");
           }
       }
   }

    public void registerAccount() throws IOException {
        if(register_username.getText().isEmpty() || register_password.getText().isEmpty() || register_passwordRepeat.getText().isEmpty()){
            alert.errorMessage("please fill all blank fields");
        }
        else if( !register_password.getText().equals(register_passwordRepeat.getText())){
            alert.errorMessage("passwords don't match");
        }
        else if(register_password.getText().length() < 8){
            alert.errorMessage("Invalid password, at least 8 characters needed!");
        }
        else{
            User user = new User();
            user.setLogin(register_username.getText());
            user.setPassword(register_password.getText());
            user.setRole(Roles.User);
            Person person = new Person();
            person.setName(register_name.getText());
            person.setLastName(register_surname.getText());
            person.setPatronymic(register_patronymic.getText());
            user.setPerson(person);
            Request request = new Request();
            request.setRequestMessage(new Gson().toJson(user));
            request.setRequestType(RequestType.REGISTER);
            ClientSocket.getInstance().getOut().println(new Gson().toJson(request));
            ClientSocket.getInstance().getOut().flush();
            String answer = ClientSocket.getInstance().getInStream().readLine();
            Response response = new Gson().fromJson(answer, Response.class);
            if (response.getResponseStatus() == ResponseStatus.OK) {
                alert.successMessage("Вы успешно зарегистрировались");
                ClientSocket.getInstance().setUser(new Gson().fromJson(response.getResponseData(), User.class));
                registerClear();
                login_form.setVisible(true);
                register_form.setVisible(false);

            } else {
                alert.errorMessage("Пользователь с таким именем уже существует");
            }
        }
    }

    public void registerShowPassword(){
        if(register_checkBox.isSelected()){
            register_showPassword.setText(register_password.getText());
            register_showPasswordRepeat.setText(register_passwordRepeat.getText());
            register_showPasswordRepeat.setVisible(true);
            register_showPassword.setVisible(true);
            register_password.setVisible(false);
            register_passwordRepeat.setVisible(false);
        }
        else{
            register_password.setText(register_showPassword.getText());
            register_passwordRepeat.setText(register_showPasswordRepeat.getText());
            register_showPasswordRepeat.setVisible(false);
            register_showPassword.setVisible(false);
            register_password.setVisible(true);
            register_passwordRepeat.setVisible(true);
        }
    }

    public void loginShowPassword(){
       if(login_checkBox.isSelected()){
           login_showPassword.setText(login_password.getText());
           login_showPassword.setVisible(true);
           login_password.setVisible(false);
       }else{
           login_password.setText(login_showPassword.getText());
           login_showPassword.setVisible(false);
           login_password.setVisible(true);
       }
    }
    public void registerClear(){
        register_name.clear();
        register_surname.clear();
        register_patronymic.clear();
        register_username.clear();
        register_password.clear();
        register_passwordRepeat.clear();
    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == login_registerHere){
            login_form.setVisible(false);
            register_form.setVisible(true);
        }else if(event.getSource() == register_loginHere){
            login_form.setVisible(true);
            register_form.setVisible(false);
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
