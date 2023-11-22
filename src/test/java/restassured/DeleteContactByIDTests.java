package restassured;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import dto.ContactDTO;
import dto.ContactResponseDTO;
import helpers.Helper;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.jayway.restassured.RestAssured.given;

public class DeleteContactByIDTests implements Helper {

    String endpoint = "/v1/contacts";
    String id;

    @BeforeMethod
    public void precondition() throws IOException {

        RestAssured.baseURI = "https://contactapp-telran-backend.herokuapp.com";

        ContactDTO contactDTO = ContactDTO.builder()
                .name("QA39")
                .lastName("Automation")
                .email("qa39_" + i + "@mail.com")
                .phone("12345678" + i)
                .address("Rehovot")
                .description("Students")
                .build();


        given()
                .header(authHeader, token)
                .body(contactDTO)
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then();


    }

    @Test
    public void deleteContactByIDPositive() {

        Request request = new Request.Builder()
                .url(baseURL + endpoint + "/" + id)
                .header(authHeader, token)
                .delete()
                .build();








}
