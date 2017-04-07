package com.std.forum.ao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.forum.ao.ISubsystemAO;
import com.std.forum.bo.ISubsystemBO;
import com.std.forum.bo.base.Page;
import com.std.forum.bo.base.Paginable;
import com.std.forum.core.StringValidater;
import com.std.forum.domain.Subsystem;
import com.std.forum.dto.req.XN610090Req;
import com.std.forum.dto.req.XN610091Req;
import com.std.forum.enums.EBelong;
import com.std.forum.enums.EBoolean;
import com.std.forum.exception.BizException;

@Service
public class SubsystemAOImpl implements ISubsystemAO {

    @Autowired
    private ISubsystemBO subsystemBO;

    @Override
    public void editSubsystemByGlobal(XN610090Req req) {
        Subsystem subsystem = subsystemBO.getSubsystem(req.getCode());
        if (subsystem.getOrderNo() != StringValidater.toInteger(req
            .getOrderNo())) {
            List<Subsystem> subsystemList = subsystemBO.querySubsystemList(
                EBoolean.NO.getCode(),
                StringValidater.toInteger(req.getOrderNo()));
            if (CollectionUtils.isNotEmpty(subsystemList)) {
                throw new BizException("xn0000", "顺序不能重复");
            }
        }
        if (EBelong.GLOBAL.getCode().equals(subsystem.getBelong())
                || EBelong.LOCAL.getCode().equals(subsystem.getBelong())) {
            subsystemBO.refreshSubsystem(req.getCode(), req.getName(),
                req.getUrl(), req.getPic(),
                StringValidater.toInteger(req.getLocation()),
                StringValidater.toInteger(req.getOrderNo()),
                req.getCompanyCode(), req.getRemark());
        } else {
            throw new BizException("xn0000", "地方子系统配置，不可修改");
        }
    }

    @Override
    public void editSubsystemByLocal(XN610091Req req) {
        Subsystem subsystem = subsystemBO.getSubsystem(req.getCode());
        if (subsystem.getOrderNo() != StringValidater.toInteger(req
            .getOrderNo())) {
            List<Subsystem> subsystemList = subsystemBO.querySubsystemList(
                req.getCompanyCode(),
                StringValidater.toInteger(req.getOrderNo()));
            if (CollectionUtils.isNotEmpty(subsystemList)) {
                throw new BizException("xn0000", "顺序不能重复");
            }
        }
        if (EBelong.LOCAL.getCode().equals(subsystem.getBelong())) {
            subsystemBO.saveSubsystem(req.getName(), req.getUrl(),
                req.getPic(), StringValidater.toInteger(req.getLocation()),
                StringValidater.toInteger(req.getOrderNo()),
                subsystem.getCode(), req.getCompanyCode(), req.getRemark());
        } else if (EBelong.GLOBAL.getCode().equals(subsystem.getBelong())) {
            throw new BizException("xn0000", "总部子系统，地方不可修改");
        } else {
            subsystemBO.refreshSubsystem(req.getCode(), req.getName(),
                req.getUrl(), req.getPic(),
                StringValidater.toInteger(req.getLocation()),
                StringValidater.toInteger(req.getOrderNo()),
                req.getCompanyCode(), req.getRemark());
        }
    }

    @Override
    public Paginable<Subsystem> querySubsystemPage(int start, int limit,
            Subsystem condition) {
        Paginable<Subsystem> page = null;
        List<String> companyCodeList = new ArrayList<String>();
        if (!condition.getCompanyCode().equals(EBoolean.NO.getCode())) {
            condition.setBelong("NO_1");
            companyCodeList.add(condition.getCompanyCode());
            companyCodeList.add("0");
            condition.setCompanyCodeList(companyCodeList);
            condition.setCompanyCode("");
            List<Subsystem> list = subsystemBO.querySubsystemList(condition);
            List<Subsystem> SubsystemList = new ArrayList<Subsystem>();
            SubsystemList.addAll(list);
            for (Subsystem sub : SubsystemList) {
                for (Subsystem subsystem : list) {
                    if (subsystem.getCode().equals(sub.getBelong())) {
                        list.remove(subsystem);
                        break;
                    }
                }
            }
            page = new Page<Subsystem>(start, limit, list.size());
            List<Subsystem> dataList = querySubsystem(list, page.getStart(),
                page.getPageSize());
            page.setList(dataList);
        } else {
            condition.setCompanyCode("");
            page = subsystemBO.getPaginable(start, limit, condition);
        }
        return page;
    }

    private List<Subsystem> querySubsystem(List<Subsystem> list, int start,
            int limit) {
        list.indexOf(start);
        list.lastIndexOf(limit);
        return list;
    }

    @Override
    public List<Subsystem> querySubsystemList(String companyCode) {
        List<Subsystem> resultSubsystem = new ArrayList<Subsystem>();
        List<Subsystem> localList = subsystemBO.querySubsystemList(companyCode);
        List<Subsystem> globalList = subsystemBO.querySubsystemList("0");
        for (Subsystem global : globalList) {
            if (CollectionUtils.isNotEmpty(localList)) {
                for (Subsystem local : localList) {
                    // 本地菜单的上级是全局的
                    if (local.getBelong().equals(global.getCode())
                            && EBelong.GLOBAL.getCode().equalsIgnoreCase(
                                global.getBelong())) {
                        resultSubsystem.add(global);
                    }
                    // 本地菜单的上级是可配的
                    if (local.getBelong().equals(global.getCode())
                            && EBelong.LOCAL.getCode().equalsIgnoreCase(
                                global.getBelong())) {
                        resultSubsystem.add(local);
                    }
                }
            }
        }
        for (Subsystem subsystem : resultSubsystem) {
            for (Subsystem global : globalList) {
                if (subsystem.getBelong().equals(global.getCode())) {
                    globalList.remove(global);
                    break;
                }
            }
        }
        resultSubsystem.addAll(globalList);
        return resultSubsystem;
    }

    @Override
    public Subsystem getSubsystem(String code) {
        return subsystemBO.getSubsystem(code);
    }

}
