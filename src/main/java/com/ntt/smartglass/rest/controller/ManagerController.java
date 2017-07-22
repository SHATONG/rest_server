package com.ntt.smartglass.rest.controller;

import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.rest.domain.ManagerLoginResultData;
import com.ntt.smartglass.rest.domain.SmartGlassListResultData;
import com.ntt.smartglass.rest.domain.UserData;
import com.ntt.smartglass.rest.service.GlassService;
import com.ntt.smartglass.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.HashMap;
import java.util.List;

/**
 * 管理者API
 */
@RestController
@RequestMapping(value = "smartglass")
public class ManagerController {

    @Autowired
    private UserService userService;
    @Autowired
    private GlassService service;

    /**
     * @param data ユーザーデータ
     * @return トークン情報
     * @throws Exception Exception
     */
    @RequestMapping(value = "/managerlogin", method = RequestMethod.POST)
    public DataResult<ManagerLoginResultData> managerlogin(@RequestBody UserData data) throws Exception {
        return userService.getManagerLoginResult(data.getUsername(), data.getPassword());
    }

    /**
     * SmartGlass一覧取得API
     *
     * @param div_id 拠点情報ID
     * @return SmartGlass一覧
     */
    @RequestMapping(value = "/glasslist", method = RequestMethod.GET)
    public DataResult<List<SmartGlassListResultData>> glasslist(@RequestParam(value = "div_id", defaultValue = "-1") String div_id) throws AuthenticationException {
        return service.getGlassList(div_id);
    }
}
