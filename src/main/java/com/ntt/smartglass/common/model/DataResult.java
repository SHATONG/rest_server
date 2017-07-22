package com.ntt.smartglass.common.model;


import com.ntt.smartglass.common.consts.RestConst;

/**
 * データ結果
 *
 * @param <T>
 */
public class DataResult<T> {


    private String statusCode;
    private String message;
    private T content;
    /**
     * コンストラクト
     *
     * @param Content response data
     */
    public DataResult(T Content) {
        this.statusCode = RestConst.SUCCESS_CODE;
        this.content = Content;
        this.message = "";

    }

    /**
     * コンストラクト
     *
     * @param StatusCode    エラーコード
     * @param Message メッセージ
     */
    public DataResult(String StatusCode, String Message) {
        this.statusCode = StatusCode;
        this.message = Message;
        this.content = null;
    }


    public String getStatusCode() {
        return statusCode;
    }



    public String getMessage() {
        return message;
    }



    public T getContent() {
        return content;
    }







}
