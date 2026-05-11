package org.example.bicyclesharing.controller.view.user;

import org.example.bicyclesharing.controller.view.BaseController;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.util.AppConfig;
import org.example.bicyclesharing.viewModel.user.TransactionViewModel;

public class TransactionController extends BaseController {

  private TransactionViewModel viewModel;

  @Override
  public void setCurrentUser(User currentUser) {
    this.viewModel = new TransactionViewModel(AppConfig.transactionService(),currentUser);
  }

}