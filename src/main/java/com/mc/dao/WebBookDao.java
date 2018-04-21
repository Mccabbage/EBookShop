package com.mc.dao;

import com.mc.entity.WebBook;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * created by machao on  2018/4/21 22:01
 */
public interface WebBookDao {

    List<WebBook> getWebBookList();

    int addWebBook(@Param("webBook") WebBook webBook);

    int updateWebBook(@Param("webBook") WebBook webBook);

}
