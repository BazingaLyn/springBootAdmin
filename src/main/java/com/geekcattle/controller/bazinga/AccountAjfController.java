package com.geekcattle.controller.bazinga;

import com.alibaba.fastjson.JSON;
import com.geekcattle.aijia.domain.AccountAjfInfo;
import com.geekcattle.aijia.domain.Activity1219Info;
import com.geekcattle.model.bazinga.AijiaAjf;
import com.geekcattle.model.bazinga.AijiaAjfDetail;
import com.geekcattle.model.vo.AijiaAjfSearchVo;
import com.geekcattle.model.vo.SuggestRequest;
import com.geekcattle.model.vo.SuggestResult;
import com.geekcattle.service.ajf.AccountAjfService;
import com.geekcattle.util.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 底层资源的控制类
 *
 * @author liguolin
 * @create 2017-12-25 14:48
 **/
@Controller
@RequestMapping("/bazinga")
public class AccountAjfController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Resource
    private AccountAjfService accountAjfService;

    @RequiresPermissions("bazinga:ajf:manager")
    @RequestMapping(value = "/ajf/manager", method = {RequestMethod.GET})
    public String index(Model model) {
        return "bazinga/ajf/index";
    }


    @RequiresPermissions("bazinga:ajf:manager")
    @RequestMapping(value = "/ajf/list", method = {RequestMethod.GET})
    @ResponseBody
    public ModelMap list(AijiaAjfSearchVo aijiaAjfSearchVo) {

        ModelMap map = new ModelMap();

        PageInfo<AccountAjfInfo> aijiaAjfPageInfo = accountAjfService.pageAjfInfo(aijiaAjfSearchVo.getKey(),aijiaAjfSearchVo.getOffset(),aijiaAjfSearchVo.getLimit());

        map.put("pageInfo", aijiaAjfPageInfo);
        map.put("queryParam", aijiaAjfSearchVo);
        return ReturnUtil.Success("加载成功", map, null);
    }



    @RequestMapping(value = "/suggestList", method = {RequestMethod.POST})
    @ResponseBody
    public SuggestResult suggestList(SuggestRequest suggestRequest){

        logger.info("suggestList params {}",suggestRequest.getTopicName());

        SuggestResult suggestResults = accountAjfService.suggestList(suggestRequest.getTopicName());

        logger.info("suggestList result {}", JSON.toJSONString(suggestResults));

        return suggestResults;

    }



    @RequestMapping(value = "/ajf/detail/{id}", method = {RequestMethod.GET})
    public String detail(@PathVariable Integer id, Model model) {

        logger.info("ajf detail query id is {}",id);

        return "bazinga/ajf/detail";
    }


}
