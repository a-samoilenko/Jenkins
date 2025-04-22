package tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Issue;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import models.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WordsCounterTests extends BaseApiTest {

    private static final String urlSuffix = "/posts";
    private final AllureRestAssured allureFilter = new AllureRestAssured();

    @BeforeAll
    public void SetUp() {
        RestAssured.baseURI = BASE_URL;
        RestAssured.basePath = urlSuffix;
        RestAssured.filters(allureFilter);
    }

    @Test
    @AllureId("some id")
    @Issue("some issue id")
    public void countAndPrintTheMostUsedWords() {
        AtomicInteger counter = new AtomicInteger(1);
        List<Post> posts = RestAssured
                .get()
                .then()
                .extract()
                .body()
                .jsonPath()
                .getList("", Post.class);

        Map<String, Long> wordCounts = posts.stream()
                .flatMap(post -> List.of(post.body().split("\\s+")).stream())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));

        wordCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(10)
                .forEach(pair -> {
                    System.out.println(counter.getAndIncrement() + ". " + pair.getKey() + " - " + pair.getValue());
                });
    }
}

