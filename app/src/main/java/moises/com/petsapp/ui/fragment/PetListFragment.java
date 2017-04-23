package moises.com.petsapp.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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
import moises.com.petsapp.ui.view.LoadingView;
import moises.com.petsapp.web.ApiClient;
import moises.com.petsapp.web.ApiClientAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetListFragment extends BaseFragment implements AdapterView.OnItemClickListener{

    public static final String TAG = PetListFragment.class.getSimpleName();
    private OnPetListFragmentListener mListener;

    private View view;

    protected @BindView(R.id.loading_view)
    LoadingView mLoadingView;
    protected @BindView(R.id.lv_pets) ListView lvPetList;

    private PetListAdapter mPetListAdapter;

    public static PetListFragment getInstance(){
        return new PetListFragment();
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
        mLoadingView.showLoading(lvPetList);
        loadPetList();
    }

    private void loadPetList(){
        ApiClient ap = ApiClientAdapter.newInstance().startConnection();
        Call<List<Pet>> call = ap.getPetListTest("available");
        call.enqueue(new Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if(response.isSuccessful() && response.body().size() > 0){
                    Log.d(TAG, response.message() + " \n >>> " + response.body().size());
                    mLoadingView.hideLoading("", lvPetList);
                    mPetListAdapter.addAll(response.body());
                }else {
                    mLoadingView.hideLoading(getString(R.string.message_withot_pets), lvPetList);
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                Log.d(TAG, t.getMessage() + " FAILED >>> " + t.toString());
                mLoadingView.hideLoading(getString(R.string.message_withot_pets), lvPetList);
            }
        });
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
