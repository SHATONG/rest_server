package com.ntt.smartglass.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * DBテーブルマッピング
 * @param <T>
 */
public interface TableMapper<T> extends Mapper<T>, MySqlMapper<T> {

}
