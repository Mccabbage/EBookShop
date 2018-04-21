package com.mc.service.impl;

import com.mc.dao.WebBookDao;
import com.mc.dto.DreamResult;
import com.mc.entity.WebBook;
import com.mc.service.WebBookService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by machao on  2018/4/21 19:24
 */
@Service
public class WebBookServiceImpl implements WebBookService {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebBookDao webBookDao;


    public void covertBookUrl(WebBook webBook) throws IOException {
            String bookTitle = webBook.getName();
            String bookUrl = webBook.getShowUrl();
            String filterString = webBook.getReplaceStr();
            Document baseDoc = Jsoup.connect(bookUrl).get();
            Elements articleElements = baseDoc.getElementsByClass("article-list").get(0).getElementsByTag("a");
            List<String> artileUrlList = new ArrayList<>();
            if(articleElements != null && articleElements.size()>0){
                for (Element articleElement : articleElements) {
                    //todo 开多线程转  加线程池
                    String href = articleElement.attr("href");
                    artileUrlList.add(href);
                    readOneUrl(bookUrl + href, filterString);
                }
            }
    }

    @Override
    public List<WebBook> getWebBookList() {
        return webBookDao.getWebBookList();
    }

    @Override
    public int addWebBook(WebBook webBook) {
        return webBookDao.addWebBook(webBook);
    }

    @Override
    public int updateWebBook(WebBook webBook) {
        return webBookDao.updateWebBook(webBook);
    }

    @Override
    @Transactional
    public DreamResult<WebBook> saveWebBook(WebBook webBook) {
        try {
            if(webBook.getId() == 0) {
                Date now = new Date();
                webBook.setState(2);
                webBook.setCreateTime(now);
                webBook.setUpdateTime(now);
                webBookDao.addWebBook(webBook);
                covertBookUrl(webBook);
            }
            return new DreamResult(true, webBook);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
            return new DreamResult(false, e.getMessage());
        }
    }

    /**
     * 解析一个URL
     * @param url
     * @throws IOException
     */
    public static void readOneUrl(String filterString, String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        //标题
        String title = doc.getElementsByClass("bookname").get(0).getElementsByTag("h1").get(0).ownText();
        Element content = doc.getElementById("content");
        //内容
        String contentStr = content.html();
        contentStr = contentStr.replaceAll(filterString, "");
    }
}
