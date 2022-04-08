package mz.co.kelvin.openmrschallenge.network;

import mz.co.kelvin.openmrschallenge.model.Results;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("patient")
    Call<Results> patient(@Query("q") String q, @Query("v") String v, @Query("limit") int limit);

}
