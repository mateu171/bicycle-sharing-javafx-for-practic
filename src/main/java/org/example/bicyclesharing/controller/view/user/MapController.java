package org.example.bicyclesharing.controller.view.user;

import javafx.fxml.FXML;
import org.example.bicyclesharing.controller.view.BaseController;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.viewModel.user.MapViewModel;

public class MapController extends BaseController {

  private MapViewModel viewModel;

  @FXML
  public void initialize() {

  }

  @Override
  public void setCurrentUser(User currentUser) {
    viewModel = new MapViewModel(currentUser);
  }

}