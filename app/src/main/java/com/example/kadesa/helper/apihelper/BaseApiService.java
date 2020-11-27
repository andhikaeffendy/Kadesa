package com.example.kadesa.helper.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Header;

public interface BaseApiService {


    @GET("articles")
    Call<ResponseBody> getSliderBeforeLogin(@Query("featured") int featured);

    @GET("articles")
    Call<ResponseBody> getArtikelBeforeLogin(@Query("limit") int limit);

    @GET("articles")
    Call<ResponseBody> getListArtikel();

    @GET("states")
    Call<ResponseBody> getListProvinsi();

    @GET("districts")
    Call<ResponseBody> getListKabupaten(@Query("state_id") int state_id);

    @GET("sub_districts")
    Call<ResponseBody> getListKecamatan(@Query("district_id") int district_id);

    @GET("villages")
    Call<ResponseBody> getListDesa(@Query("sub_district_id") int sub_district_id);

    @GET("occupations")
    Call<ResponseBody> getListPekerjaan();

    @GET("profile")
    Call<ResponseBody> getProfileUser();

    @GET("articles")
    Call<ResponseBody> getSliderAfterLogin(@Query("featured") int featured,
                                           @Header("Authorization") String authorization);

    @GET("articles")
    Call<ResponseBody> getArtikelAfterLogin(@Query("limit") int limit,
                                            @Header("Authorization") String authorization);

    @GET("articles")
    Call<ResponseBody> getListArtikelAfterLogin(@Header("Authorization") String authorization);

    @GET("videos")
    Call<ResponseBody> getListVideo(@Header("Authorization") String authorization);

    @GET("vacations")
    Call<ResponseBody> getListVacation(@Header("Authorization") String authorization);

    @GET("institutions")
    Call<ResponseBody> getListLembagaDesa(@Header("Authorization") String authorization);

    @GET("articles/{id}")
    Call<ResponseBody> getDetailArtikel(@Header("Authorization") String authorization,
                                        @Path("id") int id);



//    @FormUrlEncoded
//    @POST("register")
//    Call<ResponseBody> registerRequest(@Field("nama") String nama,
//                                       @Field("email")String email,
//                                       @Field("password") String password,
//                                       @Field("jenis_kelamin") String jenis_kelamin,
//                                       @Field("address") String address,
//                                       @Field("states_id") int state_id,
//                                       @Field("districts_id") int districts_id,
//                                       @Field("sub_districts_id") int sub_districts_id,
//                                       @Field("desa") String desa,
//                                       @Field("phone_number") String phone_number,
//                                       @Field("occupation_id") int occupation_id);

    @FormUrlEncoded
    @POST("register")
    Call<ResponseBody> registerRequest(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("password_confirmation") String password_confirmation,
                                       @Field("name") String name,
                                       @Field("address") String address,
                                       @Field("states_id") int state_id,
                                       @Field("districts_id") int districts_id,
                                       @Field("sub_districts_id") int sub_districts_id,
                                       @Field("village_id") int village_id,
                                       @Field("occupation_id") int occupation_id,
                                       @Field("gender") String gender,
                                       @Field("phone_number") String phone_number);

    @FormUrlEncoded
    @POST("activation")
    Call<ResponseBody> activationCodeSubmit(@Field("email") String email,
                                            @Field("activation_code") String activation_code);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

}