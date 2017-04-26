package moises.com.petsapp.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;
import moises.com.petsapp.ui.adapter.SectionPageAdapter;
import moises.com.petsapp.ui.base.BaseFragment;
import moises.com.petsapp.ui.view.LoadingView;
import moises.com.petsapp.web.ApiClient;
import moises.com.petsapp.web.ApiClientAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContainerFragment extends BaseFragment {
    private static final String TAG = ContainerFragment.class.getSimpleName();
    private String petId;
    private AppBarLayout appBar;
    private TabLayout tabLayout;
    protected @BindView(R.id.loading_view) LoadingView mLoadingView;
    protected @BindView(R.id.vp_page_container) ViewPager mViewPage;

    public static ContainerFragment newInstance(@NonNull String petId){
        ContainerFragment containerFragment = new ContainerFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PetDetailFragment.ARG_PARAM, petId);
        containerFragment.setArguments(bundle);
        return containerFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null)
            petId = getArguments().getString(PetDetailFragment.ARG_PARAM);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager_container, container, false);
        ButterKnife.bind(this, view);
        setupTab(container);
        return view;
    }

    private void setupTab(ViewGroup container){
        try {
            setTitle(getString(R.string.title_detail_fragment));
            View parent = (View)container.getParent();
            appBar = (AppBarLayout)parent.findViewById(R.id.appBar);
            tabLayout = new TabLayout(getActivity());
            tabLayout.setTabTextColors(Color.parseColor("#FFFFFF"), Color.parseColor("#FFFFFF"));
            loadPetDescription();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadPetDescription(){
        if(petId == null)
            return;
        mLoadingView.showLoading(mViewPage);
        ApiClient apiClient = ApiClientAdapter.getInstance().startConnection();
        Call<Pet> petCall = apiClient.getPetDescription(petId);
        petCall.enqueue(new Callback<Pet>() {
            @Override
            public void onResponse(Call<Pet> call, Response<Pet> response) {
                if(response.isSuccessful()){
                    mLoadingView.hideLoading("", mViewPage);
                    addFragments(response.body());
                }else {
                    mLoadingView.hideLoading(getString(R.string.message_something_went_wrong), mViewPage);
                }
            }

            @Override
            public void onFailure(Call<Pet> call, Throwable t) {
                mLoadingView.hideLoading(getString(R.string.message_something_went_wrong), mViewPage);
            }
        });
    }

    private void addFragments(Pet pet){
        SectionPageAdapter sectionPageAdapter = new SectionPageAdapter(getFragmentManager());
        sectionPageAdapter.addFragment(PetDetailFragment.newInstance(pet), getString(R.string.description));
        sectionPageAdapter.addFragment(PetGalleryFragment.newInstance(pet), getString(R.string.gallery));

        mViewPage.setAdapter(sectionPageAdapter);
        tabLayout.setupWithViewPager(mViewPage);
        appBar.addView(tabLayout);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        appBar.removeView(tabLayout);
    }
}
