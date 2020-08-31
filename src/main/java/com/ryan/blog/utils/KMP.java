package com.ryan.blog.utils;

/**
 * @Author Ryan
 * @Date 2020/3/7 16:20
 * version 1.0
 */

/**
 * @ return 返回next数组，从数组0号位开始
 */
public class KMP {
    private static final int FIRST_MEMBER_NEXT=0;
    public static int[] getNextArray(char[] t){
        int[] next=new int[t.length];
        next[0]=FIRST_MEMBER_NEXT;
        int k;
        for (int i=1;i<t.length;i++){
            k=next[i-1];//自行领会吧，不太容易说明白。
            while (true){
              if (t[k]==t[i]){
                  k++;
                  next[i]=k;
                  break;
              }else {
                  if (k>0){
                      k=next[k-1];
                  }else {
                      next[i]=0;
                      break;
                  }
              }
            }
        }
        return next;
    }
    /**
     * @ param s 主串
     * @ param m 模式串
     * @ param return 返回匹配的第一个下标的数组,数组长度初始化为5
     * 若m的长度小于s，返回-1，若匹配不到字串，返回空数组。
     */

//    private static final int MAX_MATCHES=5;


    public static int kmpMatch(String s,String m){
//        int[] p=new int[MAX_MATCHES];
        int p=0;
        char[] chars_s = s.toCharArray();
        char[] chars_m = m.toCharArray();
        if (chars_m.length>chars_s.length){
            p=-1;
            return p;
        }
        int[] nextArray = getNextArray(chars_m);
        int k=0;
        for (int i=0;i<s.length();i++){
            while (true){
                if (chars_s[i]==chars_m[k]){
                    if (k==chars_m.length-1){
                        p=i-chars_m.length+1;
                        return p;
                    }
                    k++;
                    break;
                }else {
                    if (k>0){
                        k=nextArray[k-1];
                    }else{
                        break;
                    }
                }
            }
        }
        return p;
    }
}
