import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import java.io.File;
import static io.restassured.RestAssured.given;

public class JiraTest {
    public static void main(String[] args) {
        RestAssured.baseURI = "http://localhost:8080";
        SessionFilter session = new SessionFilter();
//login
        given().relaxedHTTPSValidation().header("Content-Type", "application/json").filter(session).
                body("{ \"username\": \"marat8905\", \"password\": \"summerset2001\" }\n").
                when().post("rest/auth/1/session");
//comment
        String expectedMessage = "Hello! How are you?";
        String postedCommentResponse = given().pathParam("key", "RES-2").contentType(ContentType.JSON).filter(session).body("{\n" +
                "    \"body\": \"" + expectedMessage + "\",\n" +
                "    \"visibility\": {\n" +
                "        \"type\": \"role\",\n" +
                "        \"value\": \"Administrators\"\n" +
                "    }\n" +
                "}").
                when().post("rest/api/2/issue/{key}/comment").then().assertThat().statusCode(201).extract().response().asString();
        JsonPath js1 = new JsonPath(postedCommentResponse);
        String commentId = js1.getString("id");
//attachment
        given().pathParam("key", "RES-2").header("X-Atlassian-Token", "no-check").contentType(ContentType.MULTIPART).filter(session).
                multiPart("file", new File("/Users/marat/IdeaProjects/MyGoogleMaps/src/main/resources/Text.txt")).
                when().post("/rest/api/2/issue/{key}/attachments").
                then().assertThat().statusCode(200);
//get issue
        String issueDetails = given().pathParam("key", "RES-2").queryParam("fields", "comment").filter(session).
                when().get("/rest/api/2/issue/{key}").
                then().extract().response().asString();

        JsonPath js = new JsonPath(issueDetails);
        int countOfComments = js.getInt("fields.comment.comments.size()");
        System.out.println(countOfComments);
        for (int i = 0; i < countOfComments; i++) {
            String commentIdIssue = js.getString("fields.comment.comments[" + i + "].id");
            if (commentIdIssue.equalsIgnoreCase(commentId)) {
                String message = js.getString("fields.comment.comments[" + i + "].body");
                if (message.equalsIgnoreCase(expectedMessage)) {
                    System.out.println("ALL IS GOOD!!!");
                }
                break;
            }
        }
    }
}
