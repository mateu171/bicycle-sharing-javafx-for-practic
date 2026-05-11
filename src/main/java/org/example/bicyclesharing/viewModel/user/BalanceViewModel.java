package org.example.bicyclesharing.viewModel.user;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.services.TransactionService;
import org.example.bicyclesharing.services.UserService;
import org.example.bicyclesharing.viewModel.BaseViewModel;

public class BalanceViewModel extends BaseViewModel {

  private final DoubleProperty balance = new SimpleDoubleProperty(0);
  private final UserService userService;
  private final TransactionService transactionService;

  public BalanceViewModel(UserService userService, TransactionService transactionService, User currentUser) {
    super(currentUser);
    this.userService = userService;
    this.transactionService = transactionService;
    this.balance.set(currentUser.getBalance());
  }
}