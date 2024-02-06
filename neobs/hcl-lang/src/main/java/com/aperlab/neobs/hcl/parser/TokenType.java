package com.aperlab.neobs.hcl.parser;

public enum TokenType {

  // ( )
  LEFT_PAREN, RIGHT_PAREN,
  // { }
  LEFT_BRACE, RIGHT_BRACE,
  // [ ]
  LEFT_BRACKET, RIGHT_BRACKET,

  SLASH,

  EQUALS,

  STRING, NUMBER,

  IDENTIFIER,

  COMMENT,

    EQUAL, EOF
}