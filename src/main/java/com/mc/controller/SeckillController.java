package com.mc.controller;

import com.mc.dto.Exposer;
import com.mc.dto.SeckillExcution;
import com.mc.dto.SeckillResult;
import com.mc.entity.Seckill;
import com.mc.enums.SeckillStateEnum;
import com.mc.exception.RepeatKillException;
import com.mc.exception.SeckillCloseException;
import com.mc.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * created by machao on  2018/4/17 20:34
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        List<Seckill> list = seckillService.queryAllSeckill();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId, Model model){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = seckillService.queryById(seckillId);
        if(seckill == null){
            return "redirect:/seckill/list";
        }
        model.addAttribute("seckill", seckill);
        return "detail";
    }

    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(Long seckillId){
        SeckillResult<Exposer> result;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<Exposer>(true, exposer);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            result = new SeckillResult<Exposer>(false, e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{seckillId}/{md5}/execute",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExcution> execute(@PathVariable("seckillId") Long seckillId,
                                                  @PathVariable("md5") String md5,
                                                  @CookieValue(value = "killPhone", required = false) Long phone){
        if(phone == null){
            return new SeckillResult<SeckillExcution>(false, "未注册");
        }
        SeckillResult<SeckillExcution> result;
        try {
            SeckillExcution seckillExcution = seckillService.excuteSeckill(seckillId, phone, md5);
            result = new SeckillResult<SeckillExcution>(true, seckillExcution);
        } catch (RepeatKillException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.REPEAT_KILL);
            result = new  SeckillResult<SeckillExcution>(false, seckillExcution);
        } catch (SeckillCloseException e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.END);
            result = new  SeckillResult<SeckillExcution>(false, seckillExcution);
        } catch (Exception e) {
            SeckillExcution seckillExcution = new SeckillExcution(seckillId, SeckillStateEnum.INNER_ERROR);
            result = new  SeckillResult<SeckillExcution>(false, seckillExcution);
        }
        return result;
    }

    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> now(){
        return new SeckillResult<Long>(true, new Date().getTime());
    }
}
