package com.example.kadesa.helper.apihelper;

public class UtilsApi {

    public static final String BASE_URL = "https://app.kadesa.org/api/v1/";
    public static final String BASE_URL_API = BASE_URL+"api/";

    public static BaseApiService getApiService(){
        return RetrofitClient.getClient(BASE_URL).create(BaseApiService.class);
    }

}
