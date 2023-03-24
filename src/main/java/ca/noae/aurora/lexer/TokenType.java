package ca.noae.aurora.lexer;

import java.util.HashMap;
import java.util.Map;

public enum TokenType {
    // Keywords
    FUNCTION,
    RETURN,
    IF,
    ELSE,
    WHILE,
    FOR,
    VAR,
    TRUE,
    FALSE,

    // Ignored
    WHITESPACE,
    COMMENT,

    // Symbols
    LPAREN("("),
    RPAREN(")"),
    LBRACE("{"),
    RBRACE("}"),
    LBRACKET("["),
    RBRACKET("]"),
    SEMICOLON(";"),
    COMMA(","),
    DOT("."),
    PLUS("+"),
    MINUS("-"),
    ASTERISK("*"),
    SLASH("/"),
    PERCENT("%"),
    CARET("^"),
    BANG("!"),
    EQUAL("="),
    LESS("<"),
    GREATER(">"),
    AMPERSAND("&"),
    PIPE("|"),
    QUESTION("?"),
    COLON(":"),
    DOUBLE_AMPERSAND("&&"),
    DOUBLE_PIPE("||"),

    // Literals
    IDENTIFIER,
    INTEGER_LITERAL,
    DOUBLE_LITERAL,
    STRING_LITERAL,

    // Types
    INT("int"),
    DOUBLE("double"),
    BOOLEAN("boolean"),
    FLOAT("float"),

    // End of input
    EOF;

    private final String symbol;

    private static final Map<String, TokenType> KEYWORDS = new HashMap<>();
    static {
        KEYWORDS.put("function", FUNCTION);
        KEYWORDS.put("return", RETURN);
        KEYWORDS.put("if", IF);
        KEYWORDS.put("else", ELSE);
        KEYWORDS.put("while", WHILE);
        KEYWORDS.put("for", FOR);
        KEYWORDS.put("var", VAR);
        KEYWORDS.put("true", TRUE);
        KEYWORDS.put("false", FALSE);
        KEYWORDS.put("int", INT);
        KEYWORDS.put("double", DOUBLE);
        KEYWORDS.put("float", FLOAT);
        KEYWORDS.put("boolean", BOOLEAN);
        // Add more keywords as needed
    }

    TokenType() {
        this.symbol = null;
    }

    TokenType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static TokenType getKeywordTokenType(String keyword) {
        return KEYWORDS.getOrDefault(keyword, IDENTIFIER);
    }

    public static TokenType getSymbolTokenType(char symbol) {
        return switch (symbol) {
            case '(' -> LPAREN;
            case ')' -> RPAREN;
            case '{' -> LBRACE;
            case '}' -> RBRACE;
            case '[' -> LBRACKET;
            case ']' -> RBRACKET;
            case ';' -> SEMICOLON;
            case ',' -> COMMA;
            case '.' -> DOT;
            case '+' -> PLUS;
            case '-' -> MINUS;
            case '*' -> ASTERISK;
            case '/' -> SLASH;
            case '%' -> PERCENT;
            case '^' -> CARET;
            case '!' -> BANG;
            case '=' -> EQUAL;
            case '<' -> LESS;
            case '>' -> GREATER;
            case '&' -> AMPERSAND;
            case '|' -> PIPE;
            case '?' -> QUESTION;
            case ':' -> COLON;
            default -> null;
        };
    }
}
