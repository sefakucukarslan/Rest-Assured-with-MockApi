import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;

public class Api_RestAssured {
    @Test
    public void getRequest_Registered_Member(){

        baseURI="http://localhost:3000";
        given().queryParam("Occupation","Dentist")
                .when()
                .get("/Registered_Member")
                .then()
                .statusCode(200)
                .time(lessThan(5000L));
    }
    @Test
    public void getRequest_Spesific_Registered_Member() {

        baseURI = "http://localhost:3000";

        given().queryParam("lastName", "Baudelarie")
                .queryParam("firstName", "Violet")
                .get("/Registered_Member")
                .then()
                .statusCode(200)
                .time(lessThan(2000L));
    }
    @Test
    public void postRequest_Registered_Member(){

        baseURI="http://localhost:3000";

        JSONObject request = new JSONObject();
        request.put("firstName", "Elon");
        request.put("lastName","Musk");
        request.put("phone","666-66-6666");
        request.put("Occupation","Alien hunter");
        System.out.println(request);
        System.out.println(request.toString());

        given()
                .header("ContentType","application/json")
                .contentType(ContentType.JSON)
                .body(request.toString())
                .when()
                .post("/Registered_Member")
                .then()
                .statusCode(201);
    }

    @Test
    public void updateRequest_Registered_Member(){
        baseURI="http://localhost:3000";

        JSONObject request = new JSONObject();
        request.put("firstName", "Sefa");
        request.put("lastName","Kucukarslan");
        request.put("phone","222-44-044405");
        request.put("Occupation","is not Agent");

        System.out.println(request);
        System.out.println(request.toString());

        given()
                .header("ContentType","application/json")
                .contentType(ContentType.JSON)
                .body(request.toString())
                .when()
                .put("/Registered_Member/100")
                .then()
                .statusCode(200);
        // .log.all();
    }
    @Test
    public void deleteRequest_Registered_Member(){

        baseURI="http://localhost:3000";

        given().queryParam("id","100")
                .when()
                .delete("/Registered_Member")
                .then()
                .statusCode(204);
        // .log.all();
    }
}
