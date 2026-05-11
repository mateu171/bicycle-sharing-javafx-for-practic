package org.example.bicyclesharing.viewModel.admin;

import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.services.StationService;
import org.example.bicyclesharing.viewModel.BaseViewModel;

public class StationManagementViewModel extends BaseViewModel {

  private final StationService stationService;

  public StationManagementViewModel(User currentUser, StationService stationService) {
    super(currentUser);
    this.stationService = stationService;
  }
}