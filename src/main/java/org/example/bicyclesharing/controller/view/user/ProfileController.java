package org.example.bicyclesharing.controller.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.bicyclesharing.controller.view.BaseController;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.services.NavigationService;
import org.example.bicyclesharing.util.AppConfig;
import org.example.bicyclesharing.viewModel.user.ProfileViewModel;

public class ProfileController extends BaseController {
  private ProfileViewModel viewModel;

  @Override
  public void setCurrentUser(User currentUser) {
    viewModel = new ProfileViewModel(
        AppConfig.userService(),
        currentUser
    );
  }

  @FXML
  private void initialize() {

  }
}
