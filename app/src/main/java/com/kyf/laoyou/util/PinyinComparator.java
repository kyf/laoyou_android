package com.kyf.laoyou.util;

import java.util.Comparator;
import java.util.Map;

/**
 * Created by kyf on 2015/10/6.
 */
public class PinyinComparator implements Comparator<Map<String, Object>> {

    public int compare(Map<String, Object> o1, Map<String, Object> o2) {
        if (o1.get("letter").toString().equals("@")
                || o2.get("letter").toString().equals("#")) {
            return -1;
        } else if (o1.get("letter").toString().equals("#")
                || o2.get("letter").toString().equals("@")) {
            return 1;
        } else {
            return o1.get("letter").toString().compareTo(o2.get("letter").toString());
        }
    }

}