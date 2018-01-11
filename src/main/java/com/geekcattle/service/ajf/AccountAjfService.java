package com.geekcattle.service.ajf;

import com.geekcattle.aijia.domain.AccountAjfInfo;
import com.geekcattle.model.vo.SuggestResult;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Author lgl
 * @time 2017/11/17 15:34
 * @desc
 */
public interface AccountAjfService {


    PageInfo<AccountAjfInfo> pageAjfInfo(String key, Integer offset, Integer limit);

    SuggestResult suggestList(String keyword);
}
