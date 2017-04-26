package moises.com.petsapp.ui.fragment;


import android.content.Context;

import java.util.List;

import moises.com.petsapp.R;
import moises.com.petsapp.model.Pet;
import moises.com.petsapp.web.ApiClientAdapter;
import retrofit2.Call;
import retrofit2.Response;

public class PetListInteractor {
    private final Context mContext;

    public PetListInteractor(Context context){
        this.mContext = context;
    }

    public void getPetList(String status, final Callback callback){
        Call<List<Pet>> call = ApiClientAdapter.getInstance().startConnection().getPetList(status);
        call.enqueue(new retrofit2.Callback<List<Pet>>() {
            @Override
            public void onResponse(Call<List<Pet>> call, Response<List<Pet>> response) {
                if(response.isSuccessful() && response.body().size() > 0){
                    callback.onSuccess(response.body());
                }else {
                    callback.onEmptyList(mContext.getString(R.string.message_withot_pets));
                }
            }

            @Override
            public void onFailure(Call<List<Pet>> call, Throwable t) {
                callback.onError(mContext.getString(R.string.message_withot_pets));
            }
        });
    }

    interface Callback{
        void onEmptyList(String message);
        void onError(String error);
        void onSuccess(List<Pet> petList);
    }
}
