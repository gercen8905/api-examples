import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import java.util.List;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(Payload.dashboard());

//        1. Print No of courses returned by API
//        2. Print Purchase Amount
//        3. Print Title of the first course
//        4. Print All course titles and their respective Prices
//        5. Print no of copies sold by RPA Course
//        6. Verify if Sum of all Course prices matches with Purchase Amount

        List<String> stringListOfCourses = jsonPath.getList("courses.title");
        List<Integer> priceList = jsonPath.getList("courses.price");
        List<Integer> copiesList = jsonPath.getList("courses.copies");
        System.out.println(stringListOfCourses.size());

        System.out.println(jsonPath.getInt("dashboard.purchaseAmount"));

        System.out.println(stringListOfCourses.get(0));
        for (int i = 0; i < stringListOfCourses.size(); i++) {
            System.out.println(stringListOfCourses.get(i) + " " + priceList.get(i));

        }

        for (int i = 0; i < stringListOfCourses.size(); i++) {
            String courseTitle = jsonPath.get("courses[" + i + "].title");
            if (courseTitle.equalsIgnoreCase("Selenium Python")) {
                int copies = jsonPath.getInt("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }


        int sum = 0;
        for (int i = 0; i < 3; i++) {
            sum = copiesList.get(i) * priceList.get(i);
            sum += sum;
        }
        System.out.println(sum);

        Assert.assertNotEquals(sum,jsonPath.getInt("dashboard.purchaseAmount"));
    }
}







