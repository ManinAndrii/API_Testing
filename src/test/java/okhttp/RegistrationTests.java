package okhttp;

import dto.AuthRequestDTO;
import dto.ErrorDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class RegistrationTests implements Helper{

    String endpoint = "/v1/user/registration/usernamepassword";

    @Test
    public void RegistrationPositive () throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("skrydj_"+i+"@mail.com")
                .password("Ll12345$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        Assert.assertTrue(response.isSuccessful());


    }


    @Test
    public void RegistrationNegativeWrongEmail () throws IOException {

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("skrydj_"+i+"mail.com")
                .password("Ll12345$")
                .build();

        RequestBody requestBody = RequestBody.create(gson.toJson(requestDTO), JSON);

        Request request = new Request.Builder()
                .url(baseURL + endpoint)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();

        ErrorDTO errorDTO = gson.fromJson(response.body().string(),ErrorDTO.class);
        System.out.println("Response code is----->" + response.code());
        System.out.println(errorDTO.getStatus() + "===="
                + errorDTO.getMessage() + "====" + errorDTO.getError());

        Assert.assertTrue(!response.isSuccessful());

    }
}
