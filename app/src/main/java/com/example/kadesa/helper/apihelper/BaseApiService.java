package com.example.kadesa.helper.apihelper;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Header;

public interface  BaseApiService {


    @GET("articles")
    Call<ResponseBody> getSliderBeforeLogin(@Query("featured") int featured);

    @GET("articles")
    Call<ResponseBody> getArtikelBeforeLogin(@Query("limit") int limit);

    @GET("application_letters")
    Call<ResponseBody> getListLetter(@Header("Authorization") String authorization);

    @GET("application_letters/{id}")
    Call<ResponseBody> getDetailLetter(@Header("Authorization") String authorization,
                                       @Path("id") int id);

    @GET("articles")
    Call<ResponseBody> getListArtikel();

    @GET("states")
    Call<ResponseBody> getListProvinsi();

    @GET("all_districts")
    Call<ResponseBody> getAllDistrict();

    @GET("districts")
    Call<ResponseBody> getListKabupaten(@Query("state_id") int state_id);

    @GET("sub_districts")
    Call<ResponseBody> getListKecamatan(@Query("district_id") int district_id);

    @GET("villages")
    Call<ResponseBody> getListDesa(@Query("sub_district_id") int sub_district_id);

    @GET("occupations")
    Call<ResponseBody> getListPekerjaan();

    @GET("profile")
    Call<ResponseBody> getProfileUser(@Header("Authorization") String authorization);

    @GET("detail_village")
    Call<ResponseBody> getDetailDesa(@Query("village_id") int village_id);


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

    @GET("vacations/{id}")
    Call<ResponseBody> getDetailDesaku(@Path("id") int id,
                                       @Header("Authorization") String authorization);

    @GET("institutions")
    Call<ResponseBody> getDataPemerintahan(@Header("Authorization") String authorization);

    @GET("articles/{id}")
    Call<ResponseBody> getDetailArtikel(@Path("id") int id,
                                        @Query("auth_token") String authorization);

    @GET("videos/{id}")
    Call<ResponseBody> getDetailVideo(@Path("id") int id,
                                      @Query("auth_token") String authorization);

    @GET("institutions/{id}")
    Call<ResponseBody> getLembaga(@Path("id") int id,
                                  @Header("Authorization") String authorization);

    @GET("institution_members/{id}")
    Call<ResponseBody> getDetailMemberLembaga(@Path("id") int id,
                                              @Header("Authorization") String authorization);
    @DELETE("application_letters/{id}")
    Call<ResponseBody> deleteLetter (@Path("id") int id,
                                     @Header("Authorization") String authorization);


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
    @POST("send_activation_code")
    Call<ResponseBody> resendActivationCode(@Field("email") String email);

    @FormUrlEncoded
    @POST("reset_password")
    Call<ResponseBody> resetPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> loginRequest(@Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("users/sign_out")
    Call<ResponseBody> logoutRequest(@Field("auth_token") String token);

    @FormUrlEncoded
    @POST("application_letters")
    Call<ResponseBody> submitLetter(@Header("Authorization") String authorization,
                                    @Field("application_letter_type_id") int applicationLetterType,
                                    @Field("name") String name,
                                    @Field("birth_district_id") int birth_district_id,
                                    @Field("gender") String gender,
                                    @Field("birth_date") String date,
                                    @Field("marriage_status") int marriage_status,
                                    @Field("address") String address,
                                    @Field("company_name") String company_name,
                                    @Field("business_type") String business_type,
                                    @Field("building_status") int building_status,
                                    @Field("starting_year") int starting_year,
                                    @Field("sppt_number") String sppt_number,
                                    @Field("company_address") String company_address);

    @FormUrlEncoded
    @PUT("application_letters/{id}")
    Call<ResponseBody> editLetter(@Header("Authorization") String authorization,
                                  @Path("id") int id,
                                  @Field("application_letter_type_id") int applicationLetterType,
                                  @Field("name") String name,
                                  @Field("birth_district_id") int birth_district_id,
                                  @Field("gender") String gender,
                                  @Field("birth_date") String date,
                                  @Field("marriage_status") int marriage_status,
                                  @Field("address") String address,
                                  @Field("company_name") String company_name,
                                  @Field("business_type") String business_type,
                                  @Field("building_status") int building_status,
                                  @Field("starting_year") int starting_year,
                                  @Field("sppt_number") String sppt_number,
                                  @Field("company_address") String company_address);

}
