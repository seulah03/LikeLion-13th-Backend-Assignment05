package com.likelion.likelioncrud.common.template;


import com.likelion.likelioncrud.common.error.ErrorCode;
import com.likelion.likelioncrud.common.error.SuccessCode;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResTemplate<T> {
    private  final int code;
    private  final String message;
    private  T data;

    //데이터가 없는 성공응답
    public static ApiResTemplate successWithNoContent(SuccessCode successCode){
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage());
    }

    //데이터가 있는 성공응답
    public static <T> ApiResTemplate<T> successResponse(SuccessCode successCode, T data){
        return new ApiResTemplate<>(successCode.getHttpStatusCode(), successCode.getMessage(), data);
    }

    //에러 응답
    public static ApiResTemplate errorResponse(ErrorCode errorCode, String customMessage){
        return new ApiResTemplate<>(errorCode.getHttpStatusCode(), customMessage);
    }
}
