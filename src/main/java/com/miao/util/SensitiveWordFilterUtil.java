package com.miao.util;

import com.miao.sensitivewdfilter.WordFilter;

/**
 * @author dhx_
 * @className SenstiveWordFilter
 * @date : 2022/11/04/ 16:01
 **/
public class SensitiveWordFilterUtil {


    /**
     * DFA算法进行敏感词过滤
     * @param src 输入待过滤的内容
     * @return 返回过滤后的内容
     */
    public static String filter(String src) {
//        String s = "你是逗比吗？ｆｕｃｋ！fUcK,你竟然用法轮功，法@!轮!%%%功";
        System.out.println("解析字数 : " + src.length());
        String result;
        long nanoStartTime = System.nanoTime();
        result = WordFilter.doFilter(src);
        nanoStartTime = (System.nanoTime() - nanoStartTime);
        System.out.println("解析时间 : " + nanoStartTime + "ns");
        System.out.println(result);
        System.out.println("是否包含敏感词： " + WordFilter.isContains(src));
        return result;
    }

    public static boolean isContains(String userName) {
        return WordFilter.isContains(userName);
    }

    public void TestFilter() {
        String s = "你是逗比吗？ｆｕｃｋ！fUcK,你竟然用法轮功，法@!轮!%%%功";
        System.out.println("解析问题： " + s);
        System.out.println("解析字数 : " + s.length());
        String re;
        long nano = System.nanoTime();
        re = WordFilter.doFilter(s);
        nano = (System.nanoTime() - nano);
        System.out.println("解析时间 : " + nano + "ns");
        System.out.println("解析时间 : " + nano / 1000000 + "ms");
        System.out.println(re);
        System.out.println();

        nano = System.nanoTime();
        System.out.println("是否包含敏感词： " + WordFilter.isContains(s));
        nano = (System.nanoTime() - nano);
        System.out.println("解析时间 : " + nano + "ns");
        System.out.println("解析时间 : " + nano / 1000000 + "ms");
    }
}
