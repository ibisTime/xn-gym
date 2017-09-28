package com.std.gym.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统参数
 * @author: shan 
 * @since: 2016年12月8日 下午10:01:27 
 * @history:
 */
public enum ESysConfigCkey {
    ADWY("ADWY", "活动违约扣除比例"),

    LXJL("LXJL", "零星教练"), YXJL("YXJL", "一星教练"), EXJL("EXJL", "二星教练"), SAXJL(
            "SAXJL", "三星教练"), SXJL("SXJL", "四星教练"), WXJL("WXJL", "五星教练"),

    LXDR("LXDR", "零星达人"), YXDR("YXDR", "一星达人"), EXDR("EXDR", "二星达人"), SAXDR(
            "SAXDR", "三星达人"), SXDR("SXDR", "四星达人"), WXDR("WXDR", "五星达人"),

    HKFC("HKFC", "推荐分成"), SJFC("SJFC", "私教分成"), WY("WY", "违约扣除比例"), DRFC(
            "DRFC", "达人分成"),

    TTJFC("TTJFC", "团课教练分成"), LHKFC("LHKFC", "部分违约用户分成"), WYSJFC("WYSJFC",
            "用户违约私教获得分成比例"), WYTTJFC("WYTTJFC", "用户违约团课教练分成"), WYDRFC("WYDRFC",
            "用户违约达人获得分成比例"),

    QWY("QWY", "全部违约扣除比例"), QSJFC("QSJFC", "全部违约私教分成"), QTKFC("QTKFC",
            "全部违约团课分成"), QDRFC("QDRFC", "全部违约达人分成"), QHKFC("QHKFC", "全部违约获客分成"),

    BWY("BWY", "两小时前:教练违约扣除比例"), MWY("MWY", "两小时前:达人违约扣除比例"), BWYYHFC(
            "BWYYHFC", "两小时前:教练违约,用户获得比例"), MWYYHFC("MWYYHFC",
            "两小时前:达人违约,用户获得比例"), BLXSQ("BLSXQ", "两小时前教练违约,用户获客分成"), MLXSQ(
            "MLSXQ", "两小时前达人违约,用户获客分成"),

    QBWY("QBWY", "两小时内教练违约,扣除比例"), QMWY("QMWY", "两小时内达人违约,扣除比例"), QBWYYHFC(
            "QBWYYHFC", "两小时内教练违约,用户分成比例"), QDWYYHFC("QDWYYHFC",
            "两小时内达人违约,用户分成比例"), BLXSN("BLSXN", "两小时内教练违约,用户获客分成"), MLXSN(
            "MLSXN", "两小时内达人违约,用户获客分成"),

    SBWY("SBWY", "上课后教练违约,扣除比例"), SMWY("SMWY", "上课后达人违约,扣除比例"), SBWYYHFC(
            "SBWYYHFC", "上课后教练违约,用户分成比例"), SDWYYHFC("SDWYYHFC",
            "上课后达人违约,用户分成比例"), BQ("BQ", "上课后教练违约,用户获客分成"), MQ("MQ",
            "上课后达人违约,用户获客分成"), ;

    public static Map<String, ESysConfigCkey> getDictTypeMap() {
        Map<String, ESysConfigCkey> map = new HashMap<String, ESysConfigCkey>();
        for (ESysConfigCkey activityStatus : ESysConfigCkey.values()) {
            map.put(activityStatus.getCode(), activityStatus);
        }
        return map;
    }

    ESysConfigCkey(String code, String value) {
        this.code = code;
        this.value = value;
    }

    private String code;

    private String value;

    public String getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }
}
