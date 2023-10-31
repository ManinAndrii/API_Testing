package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import helpers.Helper;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class AddNewContactTests implements Helper {

    String endpoint = "/v1/contacts";



    @Test
    public void addNewContactPositive() throws IOException {

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";

        ContactDTO contactDTO = ContactDTO.builder()
                .name("Jack")
                .lastName("Mob")
                .email("qa39_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Ashkelon")
                .description("friend")
                .build();

        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then()
                .assertThat().statusCode(200);

    }
}
