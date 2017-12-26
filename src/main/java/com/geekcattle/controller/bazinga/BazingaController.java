package com.geekcattle.controller.bazinga;

import com.geekcattle.aijia.domain.Activity1219Info;
import com.geekcattle.model.bazinga.AijiaAjf;
import com.geekcattle.model.bazinga.AijiaAjfDetail;
import com.geekcattle.model.vo.AijiaAjfSearchVo;
import com.geekcattle.util.ReturnUtil;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
public class BazingaController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        List<AijiaAjf> Lists = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            AijiaAjf aijiaAjf = new AijiaAjf();
            aijiaAjf.setUsername("李国林"+i);
            aijiaAjf.setActivity1219Amount(new BigDecimal(300+ i * 200));
            aijiaAjf.setAjfBalanceAmount(new BigDecimal(400 + i * 60));
            aijiaAjf.setArtConsumerAmount(new BigDecimal(200 + i * 10));
            aijiaAjf.setChargeRMBTotalAmount(new BigDecimal("200000"));
            aijiaAjf.setUserId(i);
            aijiaAjf.setEndDate(new Date());
            aijiaAjf.setAladdinOrderIds(1000000+i +"");
            aijiaAjf.setMobile("15195817212");
            aijiaAjf.setState(1);
            Lists.add(aijiaAjf);
        }

        PageInfo<AijiaAjf> aijiaAjfPageInfo = new PageInfo<>();

        aijiaAjfPageInfo.setTotal(301);
        aijiaAjfPageInfo.setList(Lists);

        AijiaAjf aijiaAjf = new AijiaAjf();
        aijiaAjf.setUsername("liguolin");

        map.put("pageInfo", aijiaAjfPageInfo);
        map.put("queryParam", aijiaAjfSearchVo);
        return ReturnUtil.Success("加载成功", map, null);
    }



    @RequestMapping(value = "/ajf/detail/{id}", method = {RequestMethod.GET})
    public String detail(@PathVariable Integer id, Model model) {

        logger.info("ajf detail query id is {}",id);
//        String checkRoleId = "";
//        if (!StringUtils.isEmpty(admin.getUid())) {
//            admin = adminService.getById(admin.getUid());
//            if (!"null".equals(admin)) {
//                AdminRole adminRole = new AdminRole();
//                adminRole.setAdminId(admin.getUid());
//                List<AdminRole> adminRoleLists = adminRoleService.getRoleList(adminRole);
//                admin.setUpdatedAt(DateUtil.getCurrentTime());
//                ArrayList<String> checkRoleIds = new ArrayList<String>();
//                for (AdminRole adminRoleList : adminRoleLists) {
//                    checkRoleIds.add(adminRoleList.getRoleId());
//                }
//                checkRoleId = String.join(",", checkRoleIds);
//            }
//        }else {
//            admin.setIsSystem(0);
//        }
        AijiaAjfDetail aijiaAjfDetail = new AijiaAjfDetail();
        aijiaAjfDetail.setUsername("李国林");
        aijiaAjfDetail.setMobile("15195817212");
        aijiaAjfDetail.setActivity1219Amount(new BigDecimal("20000"));
        aijiaAjfDetail.setAjfBalanceAmount(new BigDecimal("400000"));
        aijiaAjfDetail.setAladdinOrderIds("1000002,100001231");
        aijiaAjfDetail.setEndDate(new Date());

        Activity1219Info activity1219Info = new Activity1219Info();
        activity1219Info.setBeginTime("2017-10-21");
        activity1219Info.setJoinDayCount(21);
        activity1219Info.setEndTime("2017-12-02");
        activity1219Info.setTotalAjbAmount(new BigDecimal("10800"));


        AijiaAjfDetail.AladdinOrderInfo aladdinOrderInfo = new AijiaAjfDetail.AladdinOrderInfo();
        aladdinOrderInfo.setBelongCompanyId(200);
        aladdinOrderInfo.setBelongCompanyName("南京公司");
        aladdinOrderInfo.setBuildingId(400);
        aladdinOrderInfo.setBuildingName("南京河西五矿");
        aladdinOrderInfo.setTotalCashAmount(new BigDecimal("20000"));

        AijiaAjfDetail.AladdinOrderInfo aladdinOrderInfo1 = new AijiaAjfDetail.AladdinOrderInfo();
        aladdinOrderInfo1.setBelongCompanyId(200);
        aladdinOrderInfo1.setBelongCompanyName("南京公司");
        aladdinOrderInfo1.setBuildingId(400);
        aladdinOrderInfo1.setBuildingName("南京河西五矿2期");
        aladdinOrderInfo1.setTotalCashAmount(new BigDecimal("30000"));

        List<AijiaAjfDetail.AladdinOrderInfo> aladdinOrderInfos = new ArrayList<>(2);

        aladdinOrderInfos.add(aladdinOrderInfo);
        aladdinOrderInfos.add(aladdinOrderInfo1);
        aijiaAjfDetail.setAladdinOrderInfoList(aladdinOrderInfos);
        aijiaAjfDetail.setActivity1219Info(activity1219Info);
        aijiaAjfDetail.setIndexTime(new Date());
        model.addAttribute("aijiaAjfDetail", aijiaAjfDetail);
        return "bazinga/ajf/detail";
    }


}
