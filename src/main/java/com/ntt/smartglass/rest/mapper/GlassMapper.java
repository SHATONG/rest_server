package com.ntt.smartglass.rest.mapper;


import com.ntt.smartglass.common.mapper.TableMapper;
import com.ntt.smartglass.rest.entity.SmartGlass;

import java.util.List;

/**
 * smartglasss table mapper
 */
public interface GlassMapper extends TableMapper<SmartGlass> {
    SmartGlass selectByCertificationKey(String CertificationKey);

    List<SmartGlass> selectByDivId(Integer DivId);
}