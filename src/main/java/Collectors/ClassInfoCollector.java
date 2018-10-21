package Collectors;

import POJOS.ClassInfo;
import POJOS.MethodInfo;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClassInfoCollector extends VoidVisitorAdapter<List<ClassInfo>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration md, List<ClassInfo> list){
        super.visit(md, list);

        List<MethodInfo> methods = new ArrayList<>();

        new MethodInfoCollector().visit(md, methods);

        List<AssignExpr> potentialSetters = md.findAll(AssignExpr.class);

        List<ReturnStmt> potentialGetters = md.findAll(ReturnStmt.class);

        List<Statement> fullStatements = md.findAll(Statement.class);

        list.add(
                new ClassInfo(
                md.getNameAsString(),
                md.getAllContainedComments(),
                methods, potentialSetters, potentialGetters,
                fullStatements,
                md.getFields(), md.getExtendedTypes()));

    }

}
