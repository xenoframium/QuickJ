package com.xenoframium.quickj.symbols;

import com.xenoframium.quickj.SourceLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleMacro {
    public final String macroName;
    public final String replacement;

    private static final Pattern pattern = Pattern.compile("^[\\s]*#define ([a-zA-Z_0-9]+)([^(]|$)(.*)$");

    public SimpleMacro(SourceLine line) {
        Matcher matcher = pattern.matcher(line.getText());
        matcher.find();
        this.macroName = matcher.group(1);
        this.replacement = matcher.group(3);
    }

    public static boolean isLineSimpleMacro(SourceLine line) {
        Matcher matcher = pattern.matcher(line.getText());
        return matcher.find();
    }
}
