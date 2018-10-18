package Collectors;

import POJOS.MethodInfo;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class MethodInfoCollector extends VoidVisitorAdapter<List<MethodInfo>> {

@Override
    public void visit(MethodDeclaration md, List<MethodInfo> list){
    super.visit(md, list);

    List<Parameter> paras = md.getParameters();
    List<Comment> comments = md.getAllContainedComments();
    List<Statement> statements = md.getBody().get().getStatements();

    int allStatements = md.getBody().get().findAll(Statement.class).size();
    int blocks = md.getBody().get().findAll(BlockStmt.class).size();

        list.add(
                new MethodInfo(
                        md.getNameAsString(),
                        (allStatements-blocks),
                        paras,
                        statements,
                        comments
                        ));

}

}
