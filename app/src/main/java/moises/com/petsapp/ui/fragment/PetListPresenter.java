package moises.com.petsapp.ui.fragment;


import java.util.List;
import moises.com.petsapp.model.Pet;

public class PetListPresenter implements PetListContract.Presenter, PetListInteractor.Callback{
    private final PetListContract.View mPetListView;
    private final PetListInteractor mPetListInteractor;

    public PetListPresenter(PetListContract.View petListView, PetListInteractor petListInteractor){
        this.mPetListView = petListView;
        petListView.setPresenter(this);
        this.mPetListInteractor = petListInteractor;
    }

    @Override
    public void start() {
        //Verify anything data
    }

    @Override
    public void loadPets(String status) {
        mPetListView.showLoading(true);
        mPetListInteractor.getPetList(status, this);
    }

    @Override
    public void onEmptyList(String message) {
        mPetListView.showLoading(false);
        mPetListView.showEmptyList(message);
    }

    @Override
    public void onError(String error) {
        mPetListView.showLoading(false);
        mPetListView.showError(error);
    }

    @Override
    public void onSuccess(List<Pet> petList) {
        mPetListView.showLoading(false);
        mPetListView.showPets(petList);
    }
}
