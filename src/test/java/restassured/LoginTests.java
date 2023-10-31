package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.AuthRequestDTO;
import dto.AuthResponseDTO;
import dto.ErrorDTO;
import helpers.Helper;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;

public class LoginTests implements Helper {

    String endpoint = "/v1/user/login/usernamepassword";


   @Test
   public void loginPositive(){

       RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";

       AuthRequestDTO requestDTO = AuthRequestDTO.builder()
               .username("skrydj1984@mail.com")
               .password("Ll12345$")
               .build();

       AuthResponseDTO responseDTO = given()
               .body(requestDTO)
               .contentType(ContentType.JSON)
               .when()
               .post(endpoint)
               .then()
               .assertThat().statusCode(200)
               .extract()
               .as(AuthResponseDTO.class);

       System.out.println(responseDTO.getToken());


   }

    @Test
    public void loginNegativeWrongEmail(){

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";

        AuthRequestDTO requestDTO = AuthRequestDTO.builder()
                .username("skrydj1984mail.com")
                .password("Ll12345$")
                .build();

        ErrorDTO errorDTO = given()
                .body(requestDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(401)
                .extract()
                .as(ErrorDTO.class);

        System.out.println(errorDTO.getMessage());


    }

}
