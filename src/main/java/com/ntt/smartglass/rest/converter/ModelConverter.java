package com.ntt.smartglass.rest.converter;

import com.ntt.smartglass.rest.domain.SmartGlassData;
import com.ntt.smartglass.rest.domain.SmartGlassListResultData;
import com.ntt.smartglass.rest.entity.SmartGlass;
import org.springframework.beans.BeanUtils;



/**
 * 　モデル<=>ドメイン　変換
 */
public class ModelConverter {


    /**
     * @param model SmartGlassモデル
     * @return SmartGlassドメイン
     */
    public static SmartGlassData toSmartGlassData(SmartGlass model) {
        if (model == null) {
            return null;
        }
        SmartGlassData data = new SmartGlassData();
        BeanUtils.copyProperties(model, data);
        return data;
    }


    /**
     * @param model SmartGlassListモデル
     * @return SmartGlassListドメイン
     */
    public static SmartGlassListResultData toSmartGlassListResultData(SmartGlass model, String peerID) {
        if (model == null) {
            return null;
        }
        SmartGlassListResultData data = new SmartGlassListResultData();
        data.setPeerID(peerID);
        BeanUtils.copyProperties(model, data);
        return data;
    }

}
