package com.mc.service;

import com.mc.dto.DreamResult;
import com.mc.entity.WebBook;

import java.io.IOException;
import java.util.List;

/**
 * 转换小说的URL->书籍
 * created by machao on  2018/4/21 19:19
 */
public interface WebBookService {
    /**转换网文
     * @param webBook
     * @return
     * @throws IOException
     */
    public void covertBookUrl(WebBook webBook) throws IOException;

    public List<WebBook> getWebBookList();

    public int addWebBook(WebBook webBook);

    public int updateWebBook(WebBook webBook);

    public DreamResult<WebBook> saveWebBook(WebBook webBook);
}
