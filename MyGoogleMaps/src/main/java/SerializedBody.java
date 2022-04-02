import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.RequestBody;

import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializedBody {
    public static void main(String[] args) {
        RequestBody body = new RequestBody();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);
        body.setLocation(location);
        body.setAccuracy(50);
        body.setName("Our home");
        body.setPhone_number("(+91) 983 893 3937");
        body.setAddress("29, side layout, cohen 09");
        body.setTypes(List.of("shoe park","shop"));
        body.setWebsite("http://google.com");
        body.setLanguage("French-IN");

        RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
                .setContentType(ContentType.JSON)
                .addQueryParam("key", "qaclick123")
                .build();
        ResponseSpecification res = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectStatusCode(200)
                .build();



        given().spec(req).body(body).
                when().post("/maps/api/place/add/json").
                then().spec(res);

    }
}
