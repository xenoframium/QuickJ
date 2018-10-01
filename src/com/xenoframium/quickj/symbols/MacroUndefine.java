package com.xenoframium.quickj.symbols;

import com.xenoframium.quickj.SourceLine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MacroUndefine {
    public final String macroName;

    private static final Pattern pattern = Pattern.compile("^#undef ([a-zA-Z_0-9])[\\s]*$");
    private static final Pattern checkPattern = Pattern.compile("^[\\s]*#undef");

    public MacroUndefine(SourceLine line) {
        Matcher matcher = pattern.matcher(line.getText());
        if (!matcher.find()) {
            throw new InvalidUndefineException("Invalid undefine \"" + line.getText() + "\"");
        }
        this.macroName = matcher.group(1);
        String a = macroName;
    }

    public static boolean isLineUndefine(SourceLine line) {
        Matcher matcher = checkPattern.matcher(line.getText());
        return matcher.find();
    }
}
