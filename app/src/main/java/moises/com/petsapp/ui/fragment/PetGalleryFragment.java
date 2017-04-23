package moises.com.petsapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import butterknife.BindView;
import butterknife.ButterKnife;
import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;
import moises.com.petsapp.ui.adapter.PetGalleryAdapter;

public class PetGalleryFragment extends BaseFragment{
    public static final String TAG = PetGalleryFragment.class.getSimpleName();
    public static final String ARG_PARAM = "pet";
    private Pet pet;

    protected @BindView(R.id.gv_gallery) GridView gvGallery;

    public static PetGalleryFragment newInstance(@NonNull Pet pet){
        PetGalleryFragment petGalleryFragment = new PetGalleryFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PARAM, pet);
        petGalleryFragment.setArguments(bundle);
        return petGalleryFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            pet = (Pet)getArguments().getSerializable(ARG_PARAM);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery_pet, container, false);
        ButterKnife.bind(this, view);
        showGallery();
        return view;
    }

    private void showGallery(){
        try{
            if(pet == null)
                return;
            PetGalleryAdapter mPetGalleryAdapter = new PetGalleryAdapter(getContext(), pet);
            gvGallery.setAdapter(mPetGalleryAdapter);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
