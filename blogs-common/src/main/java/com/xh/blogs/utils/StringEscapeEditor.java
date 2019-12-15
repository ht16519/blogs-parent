package com.xh.blogs.utils;

import com.xh.blogs.consts.StringConst;
import lombok.extern.slf4j.Slf4j;
import java.beans.PropertyEditorSupport;

/**
 * Created by langhsu on 2017/9/2.
 */
@Slf4j
public class StringEscapeEditor extends PropertyEditorSupport {

    private boolean escapeHTML;// 编码HTML
    private boolean escapeJavaScript;// 编码JavaScript

    public StringEscapeEditor() {
        super();
    }

    public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript) {
        super();
        this.escapeHTML = escapeHTML;
        this.escapeJavaScript = escapeJavaScript;
    }

    @Override
    public String getAsText() {
        Object value = getValue();
        return value != null ? value.toString() : StringConst.EMPTY_CHARACTER;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null) {
            setValue(null);
        } else {
//            if (escapeHTML) {
//                text = HtmlUtils.htmlEscape(text);
//                log.debug("value:" + text);
//            }
//            if (escapeJavaScript) {
//                text = StringEscapeUtils.escapeJavaScript(text);
//                log.debug("value:" + text);
//            }
            setValue(text);
        }
    }
}
