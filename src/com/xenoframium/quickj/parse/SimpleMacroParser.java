package com.xenoframium.quickj.parse;

import com.xenoframium.quickj.SourceFile;
import com.xenoframium.quickj.SourceLine;
import com.xenoframium.quickj.symbols.InvalidSyntaxException;
import com.xenoframium.quickj.symbols.MacroUndefine;
import com.xenoframium.quickj.symbols.SimpleMacro;
import com.xenoframium.quickj.util.SourceUtil;

import java.util.*;

public class SimpleMacroParser implements CodeProcessor {
    private LinkedHashMap<String, SimpleMacro> macros = new LinkedHashMap<>();
    private ErrorAggregator aggregator;

    public SimpleMacroParser(ErrorAggregator aggregator) {
        this.aggregator = aggregator;
    }

    private void applyMacro(SimpleMacro macro, SourceLine line) {
        SourceUtil.replaceWord(line, macro.macroName, macro.replacement);
    }

    private boolean processLine(SourceLine line) {
        if (SimpleMacro.isLineSimpleMacro(line)) {
            SimpleMacro macro = new SimpleMacro(line);
            macros.put(macro.macroName, macro);
            return true;
        } else if (MacroUndefine.isLineUndefine(line)) {
            MacroUndefine macro = new MacroUndefine(line);
            macros.remove(macro.macroName);
            return true;
        } else {
            for (SimpleMacro macro : macros.values()) {
                applyMacro(macro, line);
            }
            return false;
        }
    }

    @Override
    public void process(SourceFile file) {
        List<SourceLine> lines = file.sourceLines;
        List<Integer> linesToRemove = new ArrayList<>();
        int i = 0;
        for (SourceLine line : lines) {
            try {
                boolean shouldRemoveLine = processLine(line);
                if (shouldRemoveLine) {
                    linesToRemove.add(i);
                }
            } catch (InvalidSyntaxException exception) {
                aggregator.addError(line, exception.message);
            }
            i++;
        }
        for (i = linesToRemove.size() - 1; i >= 0; i--) {
            file.removeLine(linesToRemove.get(i));
        }
    }
}
