package requests;

import io.restassured.RestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.Post;
import org.apache.http.HttpStatus;
import java.util.ArrayList;
import java.util.List;

public class PostService implements CrudInterface<Post> {

    private final RequestSpecification reqSpec;
    private final List<Integer> createdPosts = new ArrayList<>();

    public PostService(RequestSpecification reqSpec) {
        this.reqSpec = reqSpec;
    }

    public List<Integer> getAllCreatedPosts() {
        return createdPosts;
    }

    @Override
    public Post create(Post item) {
        Post createdPost = RestAssured.given()
                .spec(reqSpec)
                .body(item)
                .put(POSTS_URL)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body()
                .as(Post.class);
        createdPosts.add(createdPost.id());
        return createdPost;
    }

    @Override
    public Post read(int id) {
        return RestAssured.given()
                .spec(reqSpec)
                .get(POSTS_URL + "/" + id)
                .then()
                .extract()
                .body()
                .as(Post.class);
    }

    @Override
    public String delete(int id) {
        return RestAssured.given()
                .spec(reqSpec)
                .put(POSTS_URL + "/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_NO_CONTENT)
                .extract().body()
                .asString();
    }

    @Override
    public Post update(int id, Post item) {
        return RestAssured.given()
                .spec(reqSpec)
                .body(item)
                .put(POSTS_URL + "/" + id)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .extract().body()
                .as(Post.class);
    }

    private static final String POSTS_URL = "/posts";
}
