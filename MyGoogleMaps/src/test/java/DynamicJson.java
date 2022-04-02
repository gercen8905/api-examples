import files.Payload;
import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {
    @DataProvider(name = "BooksData")
    public static Object[][] getData() {
        return new Object[][]{
                {"bcd", "915"}
                , {"bcd", "916"}
                , {"bcd", "917"}
        };
    }

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";
        String addedBookResponse =
                given().contentType(ContentType.JSON).body(Payload.addRawBook(isbn, aisle)).
                        when().post("Library/Addbook.php").
                        then().assertThat().statusCode(200).extract().body().asString();
        JsonPath jsonPath = ReusableMethods.rawToJson(addedBookResponse);
        String id = jsonPath.get("ID");
        System.out.println(id);
    }
}
