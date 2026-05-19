package org.example.mental_therapy_orm.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.mental_therapy_orm.App;
import org.example.mental_therapy_orm.bo.BOFactory;
import org.example.mental_therapy_orm.bo.custom.UserBO;
import org.example.mental_therapy_orm.dto.UserDTO;
import org.example.mental_therapy_orm.exception.AuthenticationException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

public class LoginFormController {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private CheckBox chkShowPassword;
    @FXML private ComboBox<String> cmbRole;
    @FXML private Button btnLogin;

    private UserBO userBO = (UserBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.USER);

    public void initialize() {
        if (cmbRole != null) {
            cmbRole.getItems().addAll("Admin", "Therapist", "Receptionist");
        }
    }

    @FXML
    public void btnLoginOnAction(ActionEvent actionEvent) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if (username.isEmpty() || password.isEmpty()) {
            new Alert(Alert.AlertType.WARNING, "Please enter both username and password!").show();
            return;
        }

        // Hardcoded admin fallback for first-time use
        if (username.equals("admin") && password.equals("admin")) {
            App.setRoot("DashBord");
            return;
        }

        try {
            List<UserDTO> allUsers = userBO.getAll();
            boolean isValid = false;

            for (UserDTO user : allUsers) {
                if (user.getUsername().equals(username)) {
                    // Check if it's a legacy plaintext password or a BCrypt hash
                    if (user.getPassword().startsWith("$2a$") || user.getPassword().startsWith("$2b$")) {
                        if (BCrypt.checkpw(password, user.getPassword())) {
                            isValid = true;
                            break;
                        }
                    } else if (user.getPassword().equals(password)) {
                        isValid = true;
                        break;
                    }
                }
            }

            if (isValid) {
                App.setRoot("DashBord");
            } else {
                throw new AuthenticationException("Invalid Username or Password!");
            }
        } catch (AuthenticationException ae) {
            new Alert(Alert.AlertType.ERROR, ae.getMessage()).show();
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Login System Error: " + e.getMessage()).show();
            e.printStackTrace();
        }
    }
}
