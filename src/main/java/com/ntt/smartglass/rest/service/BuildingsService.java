package com.ntt.smartglass.rest.service;

import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.common.service.IService;
import com.ntt.smartglass.rest.domain.BuildingsResultData;
import com.ntt.smartglass.rest.entity.Buildings;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * ガラスのサービス クラス
 */

@Service
public interface BuildingsService extends IService<Buildings> {
       //get all buildings information
        DataResult<List<BuildingsResultData>> getBuildingsResult() throws Exception;
}
