package com.ntt.smartglass.rest.controller;

import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.rest.domain.BuildingsResultData;
import com.ntt.smartglass.rest.entity.Buildings;
import com.ntt.smartglass.rest.service.BuildingsService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.logging.Logger;

/**
 * 管理者API
 */
@RestController
@RequestMapping(value = "buildings")
public class BuildingsController {
    @Autowired
    private BuildingsService buildingsService;
    /**
     * @param data ユーザーデータ
     * @return トークン情報
     * @throws Exception Exception
     */
    @RequestMapping(value = "/allbuildings", method = RequestMethod.POST)
    public DataResult<List<BuildingsResultData>> buildingsList() throws Exception {
        DataResult<List<BuildingsResultData>> buildings = buildingsService.getBuildingsResult();
        System.out.print(buildings);
        return buildings;
    }
}
