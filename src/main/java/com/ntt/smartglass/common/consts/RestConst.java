package com.ntt.smartglass.common.consts;


/**
 * 定数定義
 */
public class RestConst {
    /**
     * 正常終了時にコード
     */
    public static String SUCCESS_CODE = "0";

    /**
     * 異常終了時に列挙型
     */
    public enum ErrorCodeEnum {
        PARAMETER_LOST("1001", "%は必須項目です。"),
        INPUT_ERROR("1002", "入力値%が不正です。"),
        UNAUTHORIZED("1003", "認証失敗でした。"),
        FORBIDDEN("1004", "禁止されています。"),
        NOT_FOUND("1005", "該当URLが存在しないです。"),
        METHOD_NOT_ALLOWED("1006", "許可されていないメソッドです。"),
        BAD_REQUEST("1007", "リクエストが不正である。"),
        INTERNAL_SERVER_ERROR("2001", "サーバ内部エラーになります。"),
        SERVICE_UNAVAILABLE("2002", "サービスは一時的に利用できません。"),
        DATABASE_ERROR("2003", " DATABASE ERROR"),
        LOGIN_FAILED("1101", " ユーザ名またはパスワードが正しくありません。"),
        UNKNOWN("9999", "UNKNOWN ERROR");


        private String code;

        public String getCode() {
            return code;
        }


        public String getMessgae() {
            return messgae;
        }


        private String messgae;

        ErrorCodeEnum(String code, String messgae) {
            this.code = code;
            this.messgae = messgae;
        }

        /**
         * @param code エラーコード
         * @return コードに列挙型対応
         */
        public static ErrorCodeEnum toValueOf(String code) {
            for (ErrorCodeEnum errorCodeEnum : ErrorCodeEnum.values()) {
                if (errorCodeEnum.code.equals(code)) {
                    return errorCodeEnum;
                }
            }
            return UNKNOWN;
        }

    }

    /**
     *異常終了時コード
     */
    public static class ErrorCode {
        public final static String PARAMETER_LOST = "1001";
        public final static String INPUT_ERROR = "1002";

    }


}
