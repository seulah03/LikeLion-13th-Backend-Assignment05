package com.likelion.likelioncrud.common.error;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    MEMBER_NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND, "해당 사용자가 없습니다. memberID =", "NOT_FOUND_404"),
    POST_NOT_EXCEPTION(HttpStatus.NOT_FOUND, "해당 게시글이 없습니다. postID =", "NOT_FOUND_404"),

    VALIDATION_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "유효성 검사에 실패 하였습니다", "BAD_REQUEST_400"),

    INTERNAL_SERVER_ERROR(HttpStatus.BAD_REQUEST, "내부 오류가 발생하였습니다.","NOT_FOUND_500" );
    private  final HttpStatus httpStatus;
    private  final String message;
    private  final String code;

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

//    no usages



}
