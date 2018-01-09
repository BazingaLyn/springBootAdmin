package com.geekcattle.controller.swagger;

import com.geekcattle.aijia.domain.AccountAjfInfo;
import com.geekcattle.model.vo.AijiaAjfSearchVo;
import com.geekcattle.model.vo.SuggestRequest;
import com.geekcattle.model.vo.SuggestResult;
import com.geekcattle.service.ajf.AccountAjfService;
import com.geekcattle.task.AccountAjfTask;
import com.geekcattle.util.ReturnT;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试工具控制类
 *
 * @author liguolin
 * @create 2018-01-03 10:03
 **/
@RestController
@Api(description = "测试工具控制类API")
public class BazingaLynccController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AccountAjfTask accountAjfTask;


    @Resource
    private AccountAjfService accountAjfService;


    @ApiOperation(value="执行任务")
    @RequestMapping(value="executeAjfTask",method = RequestMethod.POST)
    public ReturnT<Boolean> executeAjfTask(){

        ReturnT<Boolean> returnT = new ReturnT<>();

        accountAjfTask.excuteTask();

        return returnT;
    }

    @ApiOperation(value="艾积分测试")
    @RequestMapping(value="ajfQueryTest",method = RequestMethod.POST)
    public PageInfo<AccountAjfInfo> ajfQueryTest(AijiaAjfSearchVo aijiaAjfSearchVo){

        PageInfo<AccountAjfInfo> accountAjfInfoPageInfo = accountAjfService.pageAjfInfo(aijiaAjfSearchVo.getKey(),aijiaAjfSearchVo.getOffset(),aijiaAjfSearchVo.getLimit());
        return accountAjfInfoPageInfo;
    }

    @RequestMapping(value = "/suggestList", method = {RequestMethod.POST})
    @ResponseBody
    public List<SuggestResult> suggestList(@RequestBody SuggestRequest suggestRequest){

        logger.info("suggestList params {}",suggestRequest.getKeyword());

        List<SuggestResult> suggestResults = accountAjfService.suggestList(suggestRequest.getKeyword());

        return suggestResults;

    }
}
