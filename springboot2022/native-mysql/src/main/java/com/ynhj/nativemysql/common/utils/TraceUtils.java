package com.ynhj.nativemysql.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

/**
 * @date: 2021-12-31
 * @author: yangniuhaojiang
 * @title: TraceIdUtils
 * @version: 1.0
 * @description： update_version: update_date: update_author: update_note:
 */
public class TraceUtils {
    public final static String TRACE_ID = "traceId";

    public static String getTraceId() {
        return MDC.get(TRACE_ID);
    }

    public static void addTraceId(String traceId) {
        if (StringUtils.isEmpty(traceId)) {
            MDC.put(TRACE_ID, SnowflakeIdUtils.next().toString());
        } else {
            MDC.put(TRACE_ID, traceId);
        }

    }

    public static void clear() {
        MDC.clear();
    }
}
