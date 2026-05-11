package org.example.bicyclesharing.controller.view.user;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import org.example.bicyclesharing.controller.view.BaseController;
import org.example.bicyclesharing.domain.Impl.Bicycle;
import org.example.bicyclesharing.domain.Impl.Rental;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.services.BicycleService;
import org.example.bicyclesharing.util.AppConfig;
import org.example.bicyclesharing.util.LocalizationManager;
import org.example.bicyclesharing.viewModel.user.RideHistoryViewModel;

import java.time.format.DateTimeFormatter;

public class RideHistoryController extends BaseController {

  private RideHistoryViewModel viewModel;

  @Override
  public void setCurrentUser(User currentUser) {
    this.viewModel = new RideHistoryViewModel(AppConfig.rentalService(),currentUser);
  }


  }
