package org.example.bicyclesharing.controller.view.user;

import javafx.fxml.FXML;
import org.example.bicyclesharing.controller.view.BaseController;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.util.AppConfig;
import org.example.bicyclesharing.viewModel.user.BalanceViewModel;

public class BalanceController extends BaseController {

  private User currentUser;
  private BalanceViewModel viewModel;

  @Override
  public void setCurrentUser(User user) {
    this.currentUser = user;
    this.viewModel = new BalanceViewModel(AppConfig.userService(), AppConfig.transactionService(),
        currentUser);
  }

  @FXML
  public void initialize() {

  }

}
