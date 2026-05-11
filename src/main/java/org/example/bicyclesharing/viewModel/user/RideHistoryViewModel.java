package org.example.bicyclesharing.viewModel.user;

import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.bicyclesharing.domain.Impl.Rental;
import org.example.bicyclesharing.domain.Impl.User;
import org.example.bicyclesharing.services.RentalService;
import org.example.bicyclesharing.util.LocalizationManager;
import org.example.bicyclesharing.viewModel.BaseViewModel;

public class RideHistoryViewModel extends BaseViewModel {

  private final RentalService rentalService;

  public RideHistoryViewModel(RentalService rentalService, User currentUser) {
    super(currentUser);
    this.rentalService = rentalService;
  }

}
