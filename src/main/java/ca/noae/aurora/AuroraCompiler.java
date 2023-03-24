package ca.noae.aurora;

import ca.noae.aurora.codegen.Instruction;
import ca.noae.aurora.lexer.Token;
import ca.noae.aurora.parser.SyntaxTree;

import java.text.ParseException;
import java.util.List;

public class AuroraCompiler {
    private final List<Token> tokens;
    private int currentPosition;

    public AuroraCompiler(List<Token> tokens) {
        this.tokens = tokens;
        this.currentPosition = 0;
    }

    public SyntaxTree parse() throws ParseException {
        // TODO: Implement parsing logic
        return null;
    }

    public void analyzeSemantics() /* throws SemanticException */ {
        // TODO: Implement semantic analysis logic
    }

    public List<Instruction> generateCode() /* throws CodeGenerationException */ {
        // TODO: Implement code generation logic
        return null;
    }
}
