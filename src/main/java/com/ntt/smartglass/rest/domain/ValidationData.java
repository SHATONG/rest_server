package com.ntt.smartglass.rest.domain;

/**
 * パラメータエラーメッセージ
 */
public class ValidationData {
    private String code;

    /**
     * コンストラクト
     *
     * @param code    エラーコード
     * @param message エラー情報
     */
    public ValidationData(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
