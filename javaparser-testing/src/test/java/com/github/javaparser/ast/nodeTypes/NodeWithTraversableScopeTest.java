package com.github.javaparser.ast.nodeTypes;

import com.github.javaparser.ast.expr.FieldAccessExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import org.junit.jupiter.api.Test;

import static com.github.javaparser.JavaParser.parseExpression;
import static com.github.javaparser.utils.TestUtils.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeWithTraversableScopeTest {
    @Test
    public void traverse1() {
        NodeWithTraversableScope expression = parseExpression("getAddress().name.startsWith(\"abc\")");

        assertInstanceOf(MethodCallExpr.class, expression);
        expression = (NodeWithTraversableScope) expression.traverseScope().get();
        assertInstanceOf(FieldAccessExpr.class, expression);
        expression = (NodeWithTraversableScope) expression.traverseScope().get();
        assertInstanceOf(MethodCallExpr.class, expression);
        assertEquals(false, expression.traverseScope().isPresent());
    }
}
