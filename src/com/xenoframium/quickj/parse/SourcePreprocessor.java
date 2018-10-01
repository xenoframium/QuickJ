package com.xenoframium.quickj.parse;

import com.xenoframium.quickj.SourceFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SourcePreprocessor {
    private SourceFile file;
    private ErrorAggregator errorAggregator = new ErrorAggregator();
    private CodeProcessor[] processors = {new SimpleMacroParser(errorAggregator), new FunctionMacroParser()};

    public SourcePreprocessor(SourceFile file) {
        this.file = file;
    }

    public boolean process() {
        for (CodeProcessor processor : processors) {
            processor.process(file);
        }
        return errorAggregator.hasErrors();
    }

    public void printErrors() {
        errorAggregator.printErrors();
    }

    public void write() {

    }
}
