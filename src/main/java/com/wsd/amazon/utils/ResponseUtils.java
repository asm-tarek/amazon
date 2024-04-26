package com.wsd.amazon.utils;

public class ResponseUtils {
    private static final String S100000 = "S100000";
    private static final String E100000 = "E100000";
    private static final String SUCCESS_MSG = "Operation is completed successfully.";

    public static <T> ApiResponse<T> buildSuccessResponse() {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setHasError(false);
        apiResponse.setResponseCode(S100000);
        apiResponse.setResponseMessage(SUCCESS_MSG);
        return apiResponse;
    }

    public static <T> ApiResponse<T> buildSuccessResponse(String message) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setHasError(false);
        apiResponse.setResponseCode(S100000);
        apiResponse.setResponseMessage(message);
        return apiResponse;
    }

    public static <T> ApiResponse<T> buildSuccessResponse(String message, T data) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setHasError(false);
        apiResponse.setResponseCode(S100000);
        apiResponse.setResponseMessage(message);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static <T> ApiResponse<T> buildSuccessResponse(T data) {
        ApiResponse<T> apiResponse = new ApiResponse<T>();
        apiResponse.setHasError(false);
        apiResponse.setResponseCode(S100000);
        apiResponse.setResponseMessage(SUCCESS_MSG);
        apiResponse.setData(data);
        return apiResponse;
    }

    public static ApiResponse<Void> buildErrorResponse(String messageCode, String message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setHasError(true);
        apiResponse.setResponseMessage(message);
        apiResponse.setResponseCode(messageCode);
        return apiResponse;
    }

    public static ApiResponse<Void> buildErrorResponse(String message) {
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setHasError(true);
        apiResponse.setResponseMessage(message);
        apiResponse.setResponseCode(E100000);
        return apiResponse;
    }
}
