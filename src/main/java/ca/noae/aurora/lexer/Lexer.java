package ca.noae.aurora.lexer;

import java.util.ArrayList;
import java.util.List;

// TODO: Add support for no whitespace between tokens
public class Lexer {
    private final String input;
    private int currentPosition;

    public Lexer(String input) {
        this.input = input;
        this.currentPosition = 0;
    }

    public List<Token> tokenize() throws LexicalException {
        List<Token> tokens = new ArrayList<>();

        while (hasNextChar()) {
            char c = getCurrentChar();
            if (Character.isDigit(c)) {
                tokens.add(tokenizeNumber());
            } else if (Character.isLetter(c)) {
                tokens.add(tokenizeIdentifierOrKeyword());
            } else if (c == '"') {
                tokens.add(tokenizeString());
                // double strings
            } else if (c == '|' || c == '&' || c == '=') {
                if(peekNextChar() == c) {
                    tokens.add(tokenizeDoubleChar(c));
                } else {
                    TokenType tokenType = TokenType.getSymbolTokenType(c);
                    if (tokenType != null) {
                        tokens.add(new Token(tokenType, String.valueOf(c)));
                    } else {
                        throw new LexicalException("Invalid character: " + c + " at position " + currentPosition);
                    }
                }
            } else if (c == ' ' || c == '\t' || c == '\n') {
                // ignore whitespace
            } else {
                TokenType tokenType = TokenType.getSymbolTokenType(c);
                if (tokenType != null) {
                    tokens.add(new Token(tokenType, String.valueOf(c)));
                } else {
                    throw new LexicalException("Invalid character: " + c + " at position " + currentPosition);
                }
            }
            consumeChar();
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private Token tokenizeNumber() {
        int startPosition = currentPosition;
        while (hasNextChar() && Character.isDigit(getCurrentChar())) {
            consumeChar();
        }
        if (hasNextChar() && getCurrentChar() == '.') {
            consumeChar();
            while (hasNextChar() && Character.isDigit(getCurrentChar())) {
                consumeChar();
            }
            return new Token(TokenType.DOUBLE_LITERAL, input.substring(startPosition, currentPosition));
        } else {
            return new Token(TokenType.INTEGER_LITERAL, input.substring(startPosition, currentPosition));
        }
    }

    private Token tokenizeDoubleChar(char c) {
        consumeChar();
        if (c == '|' && getCurrentChar() == '|') {
            return new Token(TokenType.DOUBLE_PIPE, "||");
        } else if (c == '&' && getCurrentChar() == '&') {
            return new Token(TokenType.DOUBLE_AMPERSAND, "&&");
        } else if (c == '=' && getCurrentChar() == '=') {
            return new Token(TokenType.DOUBLE_EQUAL, "==");
        } else {
            return null;
        }
    }

    private char peekNextChar() {
        if (currentPosition + 1 < input.length()) {
            return input.charAt(currentPosition + 1);
        } else {
            return '\0';
        }
    }


    private Token tokenizeIdentifierOrKeyword() {
        int startPosition = currentPosition;
        while (hasNextChar() && (Character.isLetterOrDigit(getCurrentChar()) || getCurrentChar() == '_')) {
            consumeChar();
        }
        String identifier = input.substring(startPosition, currentPosition);
        TokenType tokenType = TokenType.getKeywordTokenType(identifier);
        if (tokenType != null) {
            return new Token(tokenType, "");
        } else {
            return new Token(TokenType.IDENTIFIER, identifier);
        }
    }

    private Token tokenizeString() throws LexicalException {
        StringBuilder sb = new StringBuilder();
        consumeChar(); // Consume the starting double quote
        while (hasNextChar()) {
            char c = getCurrentChar();
            if (c == '"') {
                consumeChar(); // Consume the ending double quote
                return new Token(TokenType.STRING_LITERAL, sb.toString());
            } else if (c == '\\') {
                consumeChar();
                if (!hasNextChar()) {
                    throw new LexicalException("Invalid escape sequence: \\" + " at position " + currentPosition);
                }
                char escapeChar = getCurrentChar();
                switch (escapeChar) {
                    case 'n' -> sb.append('\n');
                    case 't' -> sb.append('\t');
                    case 'r' -> sb.append('\r');
                    case '\'' -> sb.append('\'');
                    case '\"' -> sb.append('\"');
                    case '\\' -> sb.append('\\');
                    default -> throw new LexicalException("Invalid escape sequence: \\" + escapeChar + " at position " + currentPosition);
                }
            } else {
                sb.append(c);
            }
            consumeChar();
        }
        throw new LexicalException("Unterminated string literal");
    }

    private char getCurrentChar() {
        return input.charAt(currentPosition);
    }

    private boolean hasNextChar() {
        return currentPosition < input.length();
    }

    private void consumeChar() {
        currentPosition++;
    }

    public static class LexicalException extends Exception {
        public LexicalException(String message) {
            super(message);
        }
    }

}