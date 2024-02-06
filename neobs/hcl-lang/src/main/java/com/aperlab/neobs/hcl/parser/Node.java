package com.aperlab.neobs.hcl.parser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public abstract class Node {

  @Data
  @AllArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  public static class ConfigFile extends Node {
    Body body;
  }

  @Data
  @AllArgsConstructor
  @EqualsAndHashCode(callSuper = true)
  public static class Body extends Node {
    List<Declaration> declarations = new ArrayList<>();
  }

  public static abstract class Declaration extends Node {

    @Getter
    @EqualsAndHashCode(callSuper = true)
    @AllArgsConstructor
    public static class Attribute extends Declaration {
      Token identifier;
      Token equal;
      Expr value;

      @Override
      public String toString() {
        return "Attribute{" +
                "identifier= " + identifier +
                ", equal= " + equal +
                ", value= " + value +
                '}';
      }
    }

    @Getter
    @EqualsAndHashCode(callSuper = true)
    @AllArgsConstructor
    public static class Block extends Declaration {
      Token identifier;
      List<Token> labels = new ArrayList<>();
      Token leftBrace;
      Body body;
      Token rightBrace;
    }
  }

  public static abstract class Expr extends Node{

    public abstract String getValue();

    @Getter
    @AllArgsConstructor
    public static class StringLiteral extends Expr {
      Token literal;

      @Override
      public String getValue() {
        return literal.getLexeme();
      }

      @Override
      public String toString() {
        return "StringLiteral{" +
                "literal= " + literal +
                '}';
      }
    }

  }

  // Other expressions...
}