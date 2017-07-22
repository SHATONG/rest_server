package com.ntt.smartglass.rest.service.impl;

import com.ntt.smartglass.common.consts.RestException;
import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.common.model.Property;
import com.ntt.smartglass.common.service.impl.BaseService;
import com.ntt.smartglass.common.util.TokenUtils;
import com.ntt.smartglass.rest.converter.ModelConverter;
import com.ntt.smartglass.rest.domain.GlassAuthResultData;
import com.ntt.smartglass.rest.domain.ServerCache;
import com.ntt.smartglass.rest.domain.SmartGlassData;
import com.ntt.smartglass.rest.domain.SmartGlassListResultData;
import com.ntt.smartglass.rest.entity.SmartGlass;
import com.ntt.smartglass.rest.mapper.GlassMapper;
import com.ntt.smartglass.rest.service.GlassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.naming.AuthenticationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service("glassService")
@Transactional(rollbackFor = Exception.class)
public class GlassServiceImpl extends BaseService<SmartGlass> implements GlassService {

    @Autowired
    private Property property;
    private ServerCache serverCache = ServerCache.getInstance();

    @Override
    public DataResult<GlassAuthResultData> auth(String certificationKey) throws AuthenticationException {
        GlassMapper glassMapper = (GlassMapper) mapper;
        SmartGlass glass = glassMapper.selectByCertificationKey(certificationKey);
        SmartGlassData data = ModelConverter.toSmartGlassData(glass);

        if (null == glass) {
            throw new AuthenticationException();
        }
        GlassAuthResultData resultData = new GlassAuthResultData(data, getToken(certificationKey), property.getApiKey());
        return new DataResult<GlassAuthResultData>(resultData);

    }

    @Override
    public DataResult<String> online(String token, String peerId) throws AuthenticationException {
        serverCache.addOnlineGlass(token, peerId);
        return new DataResult<String>(null);
    }

    @Override
    public DataResult<String> offline(String token) {
        serverCache.removeOnlineGlass(token);
        return new DataResult<String>(null);
    }

    @Override
    public DataResult<List<SmartGlassListResultData>> getGlassList(String divId) {
        Integer div_Id = null;
        try {
            div_Id = Integer.parseInt(divId);
        } catch (NumberFormatException e) {
            throw new RestException.ParaErrorException("拠点情報ID");
        }

        if (-1 == div_Id) {
            throw new RestException.ParaLostException("拠点情報ID");
        }
        GlassMapper glassMapper = (GlassMapper) mapper;
        List<SmartGlass> glassList = glassMapper.selectByDivId(div_Id);
        List<SmartGlassListResultData> data = getSmartGlassListResultDataList(glassList);
        return new DataResult<List<SmartGlassListResultData>>(data);
    }

    private String getToken(String certificationKey) {
        String accessToken = TokenUtils.createToken(certificationKey, property.getTtlMillis(), property.getBase64Security());
        serverCache.addGlassToken(accessToken, certificationKey);
        return accessToken;
    }

    private List<SmartGlassListResultData> getSmartGlassListResultDataList(List<SmartGlass> glassList) {
        List<SmartGlassListResultData> dataList = new ArrayList<SmartGlassListResultData>();
        for (SmartGlass glass : glassList) {
            String peerID = serverCache.getPeerID(glass.getCertificationKey());
            if (null != peerID) {
                SmartGlassListResultData resultData = ModelConverter.toSmartGlassListResultData(glass, peerID);
                dataList.add(resultData);
            }
        }
        return dataList;
    }

}
