package com.ntt.smartglass.rest.domain;

import com.ntt.smartglass.common.consts.RestConst;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 *
 */
public class PeerIdData {

    @NotNull(message = RestConst.ErrorCode.PARAMETER_LOST+","+"peerID")
    @Length(min = 1, message = RestConst.ErrorCode.INPUT_ERROR+","+"peerID")
    private String peerID;

    public String getPeerID() {
        return peerID;
    }

    public void setPeerID(String peerID) {
        this.peerID = peerID;
    }
}
