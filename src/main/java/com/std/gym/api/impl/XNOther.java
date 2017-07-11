package com.std.gym.api.impl;

import com.std.gym.api.AProcessor;
import com.std.gym.exception.BizException;
import com.std.gym.exception.ParaException;

public class XNOther extends AProcessor {

    @Override
    public Object doBusiness() throws BizException {
        throw new BizException("702xxx", "无效API功能号");
    }

    @Override
    public void doCheck(String inputparams) throws ParaException {
        throw new ParaException("702xxx", "无效API功能号");

    }

}
