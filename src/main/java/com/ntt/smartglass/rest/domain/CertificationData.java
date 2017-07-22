package com.ntt.smartglass.rest.domain;

import com.ntt.smartglass.common.consts.RestConst;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 認証キー
 */
public class CertificationData {


    @NotNull(message = RestConst.ErrorCode.PARAMETER_LOST+","+"認証キー")
    @Length(min = 1, message = RestConst.ErrorCode.INPUT_ERROR+","+"認証キー")
    private String certificationKey;

    public String getCertificationKey() {
        return certificationKey;
    }

    public void setCertificationKey(String certificationKey) {
        this.certificationKey = certificationKey;
    }



}
