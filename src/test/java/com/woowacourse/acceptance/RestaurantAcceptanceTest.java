package com.woowacourse.acceptance;

import static com.woowacourse.acceptance.support.RestAssuredRequest.httpGetRequest;
import static org.assertj.core.api.Assertions.assertThat;

import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class RestaurantAcceptanceTest extends AcceptanceTest {

    private static final Long 선릉캠퍼스_ID = 2L;
    private static final Long 한식_ID = 1L;
    private static final Long 식당_1_ID = 1L;

    private static ExtractableResponse<Response> 캠퍼스_식당_0페이지_조회_요청(final Long campusId) {
        return httpGetRequest("/api/campuses/" + campusId + "/restaurants?page=0&size=10");
    }

    private static ExtractableResponse<Response> 캠퍼스_식당_무작위_조회_요청(Long campusId, int size) {
        return httpGetRequest("/api/campuses/" + campusId + "/restaurants/random?size=" + size);
    }

    private static ExtractableResponse<Response> 식당_조회_요청(Long restaurantId) {
        return httpGetRequest("/api/restaurants/" + restaurantId);
    }

    @Test
    void 선릉캠퍼스_식당_목록의_0페이지를_조회한다() {
        ExtractableResponse<Response> response = 캠퍼스_식당_0페이지_조회_요청(선릉캠퍼스_ID);
        식당_조회에_성공한다(response);
    }

    @Test
    void 선릉캠퍼스_한식_식당_목록의_0페이지를_조회한다() {
        ExtractableResponse<Response> response = 캠퍼스_카테고리_식당_0페이지_조회_요청(선릉캠퍼스_ID, 한식_ID);
        식당_조회에_성공한다(response);
    }

    @Test
    void 선릉캠퍼스_식당_목록을_2개_무작위로_조회한다() {
        ExtractableResponse<Response> response = 캠퍼스_식당_무작위_조회_요청(선릉캠퍼스_ID, 2);
        식당_조회에_성공한다(response);
    }

    @Test
    void 선릉캠퍼스_식당_1의_상세_정보를_조회한다() {
        ExtractableResponse<Response> response = 식당_조회_요청(식당_1_ID);
        식당_조회에_성공한다(response);
    }

    private ExtractableResponse<Response> 캠퍼스_카테고리_식당_0페이지_조회_요청(Long campusId, Long categoryId) {
        return httpGetRequest(
                "/api/campuses/" + campusId + "/restaurants?categoryId=" + categoryId + "&page=0&size=10");
    }

    private void 식당_조회에_성공한다(final ExtractableResponse<Response> response) {
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}
