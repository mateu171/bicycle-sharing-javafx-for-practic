package org.example.bicyclesharing.viewModel.user;

import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.services.UserService;
import org.example.bicyclesharing.viewModel.BaseViewModel;

public class ProfileViewModel extends BaseViewModel {

  private final UserService userService;

  public ProfileViewModel(UserService userService,User currentUser) {
    super(currentUser);
    this.userService = userService;

  }
}
