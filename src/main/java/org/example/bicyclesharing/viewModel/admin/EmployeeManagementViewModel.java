package org.example.bicyclesharing.viewModel.admin;

import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.services.EmployeeService;
import org.example.bicyclesharing.viewModel.BaseViewModel;

public class EmployeeManagementViewModel extends BaseViewModel {

  private final EmployeeService employeeService;

  public EmployeeManagementViewModel(User currentUser, EmployeeService employeeService) {
    super(currentUser);
    this.employeeService = employeeService;
  }
}