package tests;

import io.qameta.allure.AllureId;
import io.qameta.allure.Issue;
import models.Post;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import requests.PostService;
import static generators.TestDataGenerator.generate;
import static specs.ReqSpecs.unauthSpec;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PostsTest extends BaseApiTest {

    private PostService postService;
    private Post expectedPost;

    @BeforeEach
    void setupTestData() throws Exception {
        postService = new PostService(unauthSpec());
        expectedPost = generate(Post.class);
    }

    @AfterEach
    void cleanTestData() {
        postService.getAllCreatedPosts().forEach(post -> postService.delete(post));
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    void getAPost() {
        Post actualResponse = postService.read(expectedPost.id());
        MatcherAssert.assertThat(actualResponse, Matchers.equalTo(expectedPost));
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    void createAPost() {
        Post actualPost = postService.create(expectedPost);
        MatcherAssert.assertThat(actualPost, Matchers.equalTo(expectedPost));
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    void updateAPost() {
        Post actualPost = postService.update(expectedPost.id(), expectedPost);
        MatcherAssert.assertThat(actualPost, Matchers.equalTo(expectedPost));
    }

    @Test
    @AllureId("some generated id")
    @Issue("some issue/task id")
    void deleteAPost() {
        postService.delete(expectedPost.id());
    }
}

