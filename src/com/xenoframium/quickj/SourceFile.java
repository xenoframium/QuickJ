package com.xenoframium.quickj;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SourceFile {
    public final List<SourceLine> sourceLines = new ArrayList<>();
    public final File sourceFile;

    public SourceFile(File file) throws IOException {
        sourceFile = file;
        List<String> lines = Files.readAllLines(Paths.get(file.toURI()));
        for (int i = 0; i < lines.size(); i++) {
            sourceLines.add(new SourceLine(this, i + 1, lines.get(i)));
        }
    }

    public void removeLine(int lineNumber) {
        sourceLines.remove(lineNumber);
        for (int i = lineNumber; i < sourceLines.size(); i++) {
            sourceLines.get(i).lineNumber--;
        }
    }
}
