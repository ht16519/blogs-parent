package com.xh.blogs.utils;

import com.xh.blogs.consts.CommonConst;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

/**
 * Created by langhsu on 2017/9/2.
 */
public class PreviewTextUtil {

    /**
     * 提取纯文本
     * @param html 代码
     * @return string
     */
    public static String getText(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.none()).trim();
    }

    /**
     * 提取纯文本
     * @param html 代码
     * @param length 提取文本长度
     * @return string
     */
    public static String getText(String html, int length){
        return StringUtils.abbreviate(getText(html), length);
    }

    /**
     * 以下标签可以通过 (b, em, i, strong, u. 纯文本)
     * @param html 代码
     * @return string
     */
    public static String getSimpleHtml(String html) {
        if (html == null)
            return null;
        return Jsoup.clean(html, Whitelist.simpleText());
    }

    /**
     * 获取文章中的img url
     * @param html 代码
     * @return string
     */
    public static String getImgSrc(String html) {
        if (html == null)
            return null;
        Element image = Jsoup.parseBodyFragment(html).select(CommonConst.CSS_QUERY_IMG).first();
        return image == null ? null : image.attr(CommonConst.ATTRIBUTE_KEY_SRC);
    }

//    public static void main(String[] args) {
//        System.out.println(PreviewTextUtil.getText("<script>alert</script>test  ", 5));
//    }
}
