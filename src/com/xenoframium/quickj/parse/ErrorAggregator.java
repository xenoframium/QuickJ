package com.xenoframium.quickj.parse;

import com.xenoframium.quickj.SourceLine;

import java.util.ArrayList;
import java.util.Collections;

public class ErrorAggregator {
    private class LineError implements Comparable<LineError> {
        SourceLine line;
        String message;

        private final String ERROR_FORMATTER = "%s:%d: %s\n\t%s\n";

        LineError(SourceLine line, String message) {
            this.line = line;
            this.message = message;
        }

        @Override
        public int compareTo(LineError o) {
            return line.compareTo(o.line);
        }

        @Override
        public String toString() {
            String filePath = line.getFile().sourceFile.getPath();
            return String.format(ERROR_FORMATTER, filePath, line.getLineNumber(), message, line.getText());
        }
    }

    private ArrayList<LineError> lines = new ArrayList<>();

    public void addError(SourceLine line, String message) {
        lines.add(new LineError(line, message));
    }

    public boolean hasErrors() {
        return lines.size() == 0;
    }

    private void printError(LineError error) {
        System.out.println(error);
    }

    public void printErrors() {
        Collections.sort(lines);
        for (LineError error : lines) {
            printError(error);
        }
    }
}
