package com.aperlab.neobs.hcl.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.aperlab.neobs.hcl.parser.TokenType.*;

public class HclParser {

    List<String> errors = new ArrayList<>();
    boolean hadError = false;

    private List<Token> tokens;
    private int current = 0;

    public HclParser() {

    }

    public Node.ConfigFile parse(File source) throws IOException {
        String content;
        try (var reader = new FileReader(source)) {
            content = Files.readString(source.toPath());
        }

        Lexer lexer = new Lexer(this, content);
        this.tokens = lexer.scanTokens();
        return configFile();
    }

    public Node parse(String source) {
        Lexer lexer = new Lexer(this, source);
        this.tokens = lexer.scanTokens();
        return configFile();
    }


    public Node.ConfigFile configFile() {
        return new Node.ConfigFile(body());
    }

    private Node.Body body() {
        List<Node.Declaration> declarations = new ArrayList<>();
        while (!isAtEnd() && !check(RIGHT_BRACE)) {
            Node.Declaration declaration = declaration();
            if(declaration != null) {
                declarations.add(declaration);
            }
        }
        return new Node.Body(declarations);
    }

    private Node.Declaration declaration() {
        if (peek().type == IDENTIFIER && peek(1).type == EQUAL) {
            return attributeDeclaration();
        } else if(check(IDENTIFIER)) {
            return blockDeclaration();
        }
        else {
            return null;
        }
    }

    private Node.Declaration blockDeclaration() {
        Token identifier = consume(IDENTIFIER);
        List<Token> labels = new ArrayList<>();
        if (check(IDENTIFIER) || check(STRING)) {
            while (check(IDENTIFIER) || check(STRING)) {
                labels.add(advance());
            }
        }
        Token leftBrace = consume(LEFT_BRACE);
        Node.Body body = body();
        Token rightBrace = consume(RIGHT_BRACE);
        return new Node.Declaration.Block(identifier, labels, leftBrace, body, rightBrace);
    }

    private Node.Declaration attributeDeclaration() {
        Token identifier = advance();
        Token equal = consume(EQUAL);
        Node.Expr value = expression();
        return new Node.Declaration.Attribute(identifier, equal, value);
    }

    private Node.Expr expression() {
        Token strint = consume(STRING);
        return new Node.Expr.StringLiteral(strint);
    }


    private boolean consume(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }

        return false;
    }

    private Token consume(TokenType type) {

        if (check(type)) {
            var t = advance();
            return t;
        }

        throw new RuntimeException("Expected " + type + " but got " + peek().type);
    }

    private boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().type == EOF;
    }

    private Token peek() {
        return peek(0);
    }

    private Token peek(int n) {
        return tokens.get(current + n);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }


    void error(int line, String message) {
        report(line, "", message);
    }

    private void report(int line, String where,
                               String message) {
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

}
