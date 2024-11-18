package com.geek01.yupaoBackend.algorithm;

import org.springframework.stereotype.Component;

/**
 * 编辑距离算法
 */
@Component
public class LevenshteinDistance {
    public long getDistance(String s1, String s2)
    {
        int len1 = s1.length();
        int len2 = s2.length();

        long[][] d = new long[len1+1][len2+1];
        int i=0, j=0;
        for(i=0; i<=len1; i++)
            d[i][0] = i;
        for(j=0; j<=len2; j++)
            d[0][j] = j;
        for (i = 1; i < len1+1; i++)
            for (j = 1; j < len2+1; j++)
            {
                int cost = 1;
                if(s1.charAt(i-1) == s2.charAt(j-1))
                {
                    cost = 0;
                }
                long delete = d[i - 1][j] + 1;
                long insert = d[i][j - 1] + 1;
                long substitution = d[i - 1][j - 1] + cost;
                d[i][j] = Math.min(delete,Math.min(insert,substitution));
            }
        return (d[len1][len2]);
    }
}
