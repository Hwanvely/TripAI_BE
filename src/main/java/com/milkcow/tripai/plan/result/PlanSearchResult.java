package com.milkcow.tripai.plan.result;

import com.milkcow.tripai.global.result.ResultProvider;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum PlanSearchResult implements ResultProvider {
    OK_FLIGHT_PLAN(200, HttpStatus.OK, "항공권 조회 성공"),
    OK_ACCOMMODATION_PLAN(200, HttpStatus.OK, "숙박 조회 성공"),
    OK_RESTAURANT_PLAN(200, HttpStatus.OK, "맛집 조회 성공"),
    OK_ATTRACTION_PLAN(200, HttpStatus.OK, "명소 조회 성공"),

    INVALID_DATE(410, HttpStatus.BAD_REQUEST, "유효하지 않은 날짜 형식"),
    INVALID_RESTAURANT_DESTINATION(420, HttpStatus.BAD_REQUEST, "유효하지 않은 목적지 형식"),

    FLIGHT_API_KEY_LIMIT_EXCESS(540, HttpStatus.UNAUTHORIZED, "항공권 API 만료"),
    ACCOMMODATION_API_KEY_LIMIT_EXCESS(540, HttpStatus.UNAUTHORIZED, "숙박 API 만료"),
    RESTAURANT_API_KEY_LIMIT_EXCESS(540, HttpStatus.UNAUTHORIZED, "맛집 API 만료"),
    ATTRACTION_API_KEY_LIMIT_EXCESS(540, HttpStatus.UNAUTHORIZED, "명소 API 만료"),

    FLIGHT_API_REQUEST_FAILED(510, HttpStatus.INTERNAL_SERVER_ERROR, "항공권 API 요청 실패"),

    ACCOMMODATION_DESTINATION_API_REQUEST_FAILED(510, HttpStatus.INTERNAL_SERVER_ERROR, "숙박 API 장소 id 검색 요청 실패"),
    ACCOMMODATION_SEARCH_API_REQUEST_FAILED(520, HttpStatus.INTERNAL_SERVER_ERROR, "숙박 API 요청 실패"),

    RESTAURANT_DESTINATION_API_REQUEST_FAILED(510, HttpStatus.INTERNAL_SERVER_ERROR, "맛집 API 장소 id 검색 요청 실패"),
    RESTAURANT_SEARCH_API_REQUEST_FAILED(520, HttpStatus.INTERNAL_SERVER_ERROR, "맛집 API 요청 실패"),

    ATTRACTION_DESTINATION_API_REQUEST_FAILED(510, HttpStatus.INTERNAL_SERVER_ERROR, "명소 API 장소 id 검색 요청 실패"),
    ATTRACTION_SEARCH_API_REQUEST_FAILED(520, HttpStatus.INTERNAL_SERVER_ERROR, "명소 API 요청 실패"),
    ;

    private final Integer code;
    private final HttpStatus status;
    private final String message;
}
