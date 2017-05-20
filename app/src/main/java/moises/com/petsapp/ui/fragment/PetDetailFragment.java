package moises.com.petsapp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;
import moises.com.petsapp.ui.base.BaseFragment;
import moises.com.petsapp.ui.view.TextImageView;

public class PetDetailFragment extends BaseFragment {
    public static final String TAG = PetDetailFragment.class.getSimpleName();
    public static final String ARG_PARAM = "pet";
    private Pet pet;

    protected @BindView(R.id.tiv_name) TextImageView tivName;
    protected @BindView(R.id.tiv_category) TextImageView tivCategory;
    protected @BindView(R.id.tiv_tag) TextImageView tivTag;
    protected @BindView(R.id.tiv_status) TextImageView tivStatus;

    public static PetDetailFragment newInstance(@NonNull Pet pet){
        PetDetailFragment detailPetFragment = new PetDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(ARG_PARAM, pet);
        detailPetFragment.setArguments(bundle);
        return detailPetFragment;
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
        View view = inflater.inflate(R.layout.fragment_detail_pet, container, false);
        ButterKnife.bind(this, view);
        showDescription();
        return view;
    }

    private void showDescription(){
        try{
            if(pet == null)
                return;
            tivName.setText1(pet.getName());
            tivCategory.setText1(pet.getCategory() != null ? pet.getCategory().getName() : "No category");
            tivTag.setText1(pet.getTagList() != null && pet.getTagList().size() > 0 ? pet.getTagList().get(0).getName() : "No tags");
            tivStatus.setText1(pet.getStatus());
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }
}
