import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Main {
    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";
//post
        String response =
                given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(Payload.addPlace()).
                        when().post("/maps/api/place/add/json").
                                then().log().all().assertThat().statusCode(200).
                        header("server", equalTo("Apache/2.4.18 (Ubuntu)")).body("scope", equalTo("APP")).extract().asString();

        JsonPath js = ReusableMethods.rawToJson(response);
        String placeId = js.getString("place_id");
//put
        String newAddress = "minsk, USA";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\n" +
                "\"place_id\":\"" + placeId + "\",\n" +
                "\"address\":\"" + newAddress + "\",\n" +
                "\"key\":\"qaclick123\"\n" +
                "}").
                when().put("maps/api/place/update/json").
                then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

//get
        String responseAfterUpdating = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).
                when().get("maps/api/place/get/json").
                then().assertThat().statusCode(200).assertThat().body("accuracy", equalTo("50")).
                extract().response().asString();
        JsonPath js1 = ReusableMethods.rawToJson(responseAfterUpdating);
        String addressAfterUpdating = js1.getString("address");

        Assert.assertEquals(addressAfterUpdating, newAddress);
    }

}
