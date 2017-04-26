package moises.com.petsapp.ui.fragment;

import java.util.List;

import moises.com.petsapp.model.Pet;
import moises.com.petsapp.ui.base.BasePresenter;
import moises.com.petsapp.ui.base.BaseView;

public interface PetListContract {

    interface View extends BaseView<Presenter> {
        void showLoading(boolean show);
        void showPets(List<Pet> pets);
        void showEmptyList(String message);
        void showError(String error);
    }

    interface Presenter extends BasePresenter {
        void loadPets(String status);
    }
}
