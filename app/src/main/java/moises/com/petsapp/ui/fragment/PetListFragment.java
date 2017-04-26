package moises.com.petsapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;
import moises.com.petsapp.ui.adapter.PetListAdapter;
import moises.com.petsapp.ui.base.BaseFragment;
import moises.com.petsapp.ui.view.LoadingView;

public class PetListFragment extends BaseFragment implements AdapterView.OnItemClickListener, PetListContract.View{

    public static final String TAG = PetListFragment.class.getSimpleName();
    private PetListContract.Presenter mPresenter;
    private OnPetListFragmentListener mListener;

    private View view;
    protected @BindView(R.id.loading_view) LoadingView mLoadingView;
    protected @BindView(R.id.lv_pets) ListView lvPetList;

    private PetListAdapter mPetListAdapter;

    public static PetListFragment getInstance(){
        return new PetListFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PetListInteractor petListInteractor = new PetListInteractor(getContext());
        new PetListPresenter(this, petListInteractor);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(R.layout.fragment_pet_list, container, false);
            ButterKnife.bind(this, view);
            setupList();
        }
        return view;
    }

    private void setupList(){
        setTitle(getString(R.string.app_name));
        mPetListAdapter = new PetListAdapter(getContext(), new ArrayList<Pet>());
        lvPetList.setAdapter(mPetListAdapter);
        lvPetList.setOnItemClickListener(this);
        mPresenter.loadPets("available");
    }

    @Override
    public void showLoading(boolean show) {
        if(show){
            mLoadingView.showLoading(lvPetList);
        }else{
            mLoadingView.hideLoading("", lvPetList);
        }
    }

    @Override
    public void showPets(List<Pet> pets) {
        mPetListAdapter.addAll(pets);
    }

    @Override
    public void showEmptyList(String message) {
        mLoadingView.hideLoading(message, lvPetList);
    }

    @Override
    public void showError(String error) {
        mLoadingView.hideLoading(error, lvPetList);
    }

    @Override
    public void setPresenter(PetListContract.Presenter presenter) {
        if(presenter != null){
            mPresenter = presenter;
        }else {
            throw new RuntimeException("Presenter can not to be null");
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnPetListFragmentListener){
            mListener = (OnPetListFragmentListener)context;
        }else {
            throw new RuntimeException(context.toString() + "must implement OnPetListFragmentListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Pet pet = (Pet)adapterView.getAdapter().getItem(i);
        if(pet != null)
            mListener.onPetClick(pet);
    }

    public interface OnPetListFragmentListener{
        void onPetClick(Pet pet);
    }
}
