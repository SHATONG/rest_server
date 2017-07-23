package com.ntt.smartglass.rest.mapper;


import com.ntt.smartglass.common.mapper.TableMapper;
import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.rest.domain.BuildingsResultData;
import com.ntt.smartglass.rest.entity.Buildings;

import java.util.List;


/**
 * Buildings table mapper
 */
public interface BuildingsMapper extends TableMapper<Buildings> {

    DataResult<List<BuildingsResultData>> selectAllbuildings();
}