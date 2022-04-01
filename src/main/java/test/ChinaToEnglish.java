package test;


import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/3/30 18:11
 */


public class ChinaToEnglish {
    /**
     * 汉字转换位汉语拼音首字母
     * 英文字符不变，特殊字符丢失 支持多音字
     * 生成方式如（长沙市长:cssc,zssz,zssc,cssz）
     */
    public static String converterToFirstSpell(String chines) throws Exception {
        if (StringUtils.isEmpty(chines)) {
            return null;
        }
        // 打散字符串
        char[] ziCharArray = chines.toCharArray();
        // 拼音集合
        List<List<String>> list = new ArrayList<List<String>>();
        int size = ziCharArray.length;
        for (int i = 0; i < size; i++) {
            // 128 ASCII码 可见字符
            char c = ziCharArray[i];
            if (c > 128) {
                List<String> result = duoYinZi(c);
                list.add(result);
            } else {
                List<String> result = new ArrayList<String>();
                result.add(String.valueOf(ziCharArray[i]));
                list.add(result);
            }
        }
        String result = parseTheChineseByObject(list);
        return result;
    }

    private static String parseTheChineseByObject(List<List<String>> list) {
        Set<String> result = new HashSet<String>();
        if (CollectionUtils.isNotEmpty(list)) {
            result.addAll(list.get(0));
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            Set<String> compositePYTemp = new HashSet<String>();
            for (String pinyinFast : result) {
                for (String c : list.get(i)) {
                    String str = pinyinFast + c;
                    compositePYTemp.add(str);
                }
            }
            result = compositePYTemp;
        }
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(result)) {
            for (String str : result) {
                sb.append(str).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString().toUpperCase();
    }

    public static List<String> duoYinZi(char zi) throws Exception {
        List<String> pinyinName = new ArrayList<String>();
        HanyuPinyinOutputFormat config = new HanyuPinyinOutputFormat();
        // 小写
        config.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        // 没有音调数字
        config.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        // 取得当前汉字的所有全拼
        String[] pyArray = PinyinHelper.toHanyuPinyinStringArray(zi, config);
        if (pyArray != null && pyArray.length > 0) {
            for (int pyIndex = 0; pyIndex < pyArray.length; pyIndex++) {
                // 取首字母
                char fast = pyArray[pyIndex].charAt(0);
                int index = pinyinName.indexOf(String.valueOf(fast));
                if (index < 0) {
                    pinyinName.add(String.valueOf(fast));
                }
            }
        }
        return pinyinName;
    }

    public static void main(String[] args) throws Exception {
        String first1 =converterToFirstSpell("长沙银行");
        String first2 =converterToFirstSpell("扁舟");
        String first3 =converterToFirstSpell("给予");
        String first4 =converterToFirstSpell("长沙市长");
        System.err.println(first1);
        System.err.println(first2);
        System.err.println(first3);
        System.err.println(first4);
    }
}
