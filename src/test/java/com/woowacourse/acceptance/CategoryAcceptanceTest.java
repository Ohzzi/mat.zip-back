package com.woowacourse.acceptance;

import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class CategoryAcceptanceTest extends AcceptanceTest {

    @Test
    void 카테고리_전체_조회() {
        ExtractableResponse<Response> response = 카테고리_전체_조회_요청();
        카테고리_조회에_성공한다(response);
    }

    private static ExtractableResponse<Response> 카테고리_전체_조회_요청() {
         return RestAssured
                .given().log().all()
                .when().get("/api/categories")
                .then().log().all()
                .extract();
    }

    private void 카테고리_조회에_성공한다(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}