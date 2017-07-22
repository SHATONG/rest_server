package com.ntt.smartglass.common.consts;

import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 *
 */
public class RestException {


    public static class ParaErrorException extends HttpMessageNotReadableException {
        public ParaErrorException(String msg) {
            super(msg);
        }
    }

    public static class ParaLostException extends HttpMessageNotReadableException {
        public ParaLostException(String msg) {
            super(msg);
        }




    }
}