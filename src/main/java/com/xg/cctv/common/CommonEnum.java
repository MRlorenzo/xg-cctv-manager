package com.xg.cctv.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author hh
 * @date 2020/4/5
 */
public class CommonEnum {
    /**
     * 职位关联表相关类型
     */
    public enum PositionLogType{
        INCIDENT(1 , "事件报告"),
        HIGH(2 , "高额事件");
        private int value;
        private String name;
        private static List<Map<String , Object>> enumList;
        PositionLogType(int value , String name){
            this.value = value;
            this.name = name;
        }

        public static PositionLogType getEnumByValue(Integer value) {
            if(value == null){
                return null;
            }
            return Arrays.stream(values()).filter(ot->ot.getValue() == value).findFirst().orElse(null);
        }

        public int getValue(){
            return value;
        }

        public String getName(){
            return name;
        }
    }
}
