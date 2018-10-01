package com.xenoframium.quickj;

import java.io.File;

public class SourceLine implements Comparable<SourceLine> {
    SourceFile file;
    int lineNumber;
    String line;

    public SourceLine(SourceFile sourceFile, int lineNumber, String line) {
        file = sourceFile;
        this.lineNumber = lineNumber;
        this.line = line;
    }

    @Override
    public int compareTo(SourceLine o) {
        int comparison = file.sourceFile.compareTo(o.file.sourceFile);
        if (comparison != 0) {
            return comparison;
        }
        return Integer.compare(lineNumber, o.lineNumber);
    }

    public void setLine(String string) {
        line = string;
    }

    public SourceFile getFile() {
        return file;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    public String getText() {
        return line;
    }
}