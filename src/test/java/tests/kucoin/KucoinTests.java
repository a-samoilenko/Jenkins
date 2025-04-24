package tests.kucoin;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.exception.JsonPathException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import tests.BaseApiTest;
import org.junit.jupiter.api.Tag;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import static io.restassured.RestAssured.given;

//@Tag("KucoinTests")
public class KucoinTests extends BaseApiTest {
    SoftAssertions softAssertions = new SoftAssertions();

    @Test
    @Tag("KucoinTests")
    public void anyTest(){
        List<KucoinDTO> kukoinList = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://api.kucoin.com/api/v1/market/allTickers")
                .then()
                .extract().jsonPath().getList("data.ticker",KucoinDTO.class);

        var sortedLowToHignList = kukoinList.stream().limit(100)
                .sorted(Comparator.comparing(a -> Double.valueOf(a.getChangeRate()))).toList();

        var sortedHighToLowList = kukoinList.stream().limit(100)
                .sorted((a,b)->{
                    return Double.valueOf(b.getChangeRate()).compareTo(Double.valueOf(a.getChangeRate()));
                }).toList();

        softAssertions.assertThat(sortedLowToHignList).startsWith(sortedHighToLowList.get(99));
        softAssertions.assertAll();
    }
    @Test
    @Tag("KucoinTests")
    public void jsonPathWithoutRestAssuredTest(){
        File json = new File("src/test/resources/schemas/forJsonpathTesting.json");
        JsonPath jsp = new JsonPath(json);
        List<KucoinDTO> kukoinList = jsp.getList("data.ticker",KucoinDTO.class);
        softAssertions.assertThat(kukoinList).hasSize(1294);
        softAssertions.assertAll();
}
    @Test
    @Tag("Ne_KucoinTests")
    public void oneObjJsonPathWithoutRestAssuredTest (){
        try{
        File json = new File("src/test/resources/schemas/forOneObjJsonpathTesting.json");
        JsonPath jsp = new JsonPath(json);
        KucoinDTO kukoin = jsp.getObject("",KucoinDTO.class);
        System.out.println(kukoin.toString());
        }
        catch (JsonPathException e){
            System.out.println("Ёшки-матрешки, тут такое дело: "+e.getMessage());
        }
    }
}