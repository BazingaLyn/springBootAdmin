package com.geekcattle.task;

import org.springframework.stereotype.Component;

/**
 * @Author lgl
 * @time 2017/11/17 11:26
 * @desc
 */
@Component
public abstract class AbstractBaseTask implements BaseTask {

    public void excuteTask(){

        prepare();

        compute();
    }
}
