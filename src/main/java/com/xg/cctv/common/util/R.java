package com.xg.cctv.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 *
 * @author 知秋
 * @email fei6751803@163.com
 */
public class R extends HashMap<String, Object> {

    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
    }

    public static R error() {
        return error(500, "unknownException");
    }

    public static R error(String msg) {
        return error(500, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        if(map != null){
            r.putAll(map);
        }
        return r;
    }

    public static R ok() {
        return new R();
    }

    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public R putCollect(Map<String, Object> m) {
        super.putAll(m);
        return this;
    }
}
