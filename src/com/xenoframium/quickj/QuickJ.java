package com.xenoframium.quickj;

import com.xenoframium.quickj.parse.SourcePreprocessor;

import java.io.File;
import java.io.IOException;

public class QuickJ {
    public static void main(String[] args) throws IOException {
        SourcePreprocessor p = new SourcePreprocessor(new SourceFile(new File("sample.txt")));
        if (p.process()) {
            p.write();
        } else {
            p.printErrors();
        }
        return;
    }
}
