package org.example.bicyclesharing.controller.view.admin;

import org.example.bicyclesharing.controller.view.BaseController;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.util.AppConfig;
import org.example.bicyclesharing.viewModel.admin.EmployeeManagementViewModel;

public class EmployeeManagementController extends BaseController {

  private EmployeeManagementViewModel viewModel;

  @Override
  public void setCurrentUser(User currentUser) {
    viewModel = new EmployeeManagementViewModel(currentUser, AppConfig.employeeService());
  }

}