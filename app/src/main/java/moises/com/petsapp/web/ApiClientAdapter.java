package moises.com.petsapp.web;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientAdapter {

    public static ApiClientAdapter newInstance(){
        return new ApiClientAdapter();
    }

    public ApiClient startConnection(){
        OkHttpClient.Builder httpBuilder = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(API.API_BASE)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.client(httpBuilder.build()).build();
        return retrofit.create(ApiClient.class);
    }
}
