package com.xenoframium.quickj.util;

import com.xenoframium.quickj.SourceLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceUtil {
    public static void replace(SourceLine line, String regex, String replacement) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(line.getText());
        line.setLine(matcher.replaceAll(replacement));
    }

    public static void replaceWord(SourceLine line, String word, String replacement) {
        replace(line, "\\b"+word+"\\b", replacement);
    }
}
