package com.ntt.smartglass.rest.service.impl;

import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.common.service.impl.BaseService;
import com.ntt.smartglass.rest.domain.BuildingsResultData;
import com.ntt.smartglass.rest.entity.Buildings;
import com.ntt.smartglass.rest.mapper.BuildingsMapper;
import com.ntt.smartglass.rest.service.BuildingsService;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 *
 */
@Service("BuildingsService")
public class BuildingsServiceImpl extends BaseService<Buildings> implements BuildingsService {

    @Override
    public DataResult<List<BuildingsResultData>> getBuildingsResult() throws Exception {
        DataResult<List<BuildingsResultData>> buildings= ((BuildingsMapper) mapper).selectAllbuildings();
        return buildings;
    }
}
