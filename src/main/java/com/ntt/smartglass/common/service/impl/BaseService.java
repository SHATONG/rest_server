
package com.ntt.smartglass.common.service.impl;


import com.ntt.smartglass.common.mapper.TableMapper;
import com.ntt.smartglass.common.model.DataResult;
import com.ntt.smartglass.common.service.IService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class BaseService<T> implements IService<T> {
    @Autowired
    protected TableMapper<T> mapper;

}
