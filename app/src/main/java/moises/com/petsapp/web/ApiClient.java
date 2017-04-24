package moises.com.petsapp.web;

import java.util.List;

import moises.com.petsapp.model.Pet;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiClient {

    @GET(API.FIND_BY_STATUS)
    Call<List<Pet>> getPetList(@Query("status") String status);

    @GET(API.FIND_PET_BY_ID)
    Call<Pet> getPetDescription(@Path("petId") String petId);
}
