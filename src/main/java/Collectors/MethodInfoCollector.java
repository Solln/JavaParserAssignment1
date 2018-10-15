package Collectors;

import POJOS.MethodInfo;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class MethodInfoCollector extends VoidVisitorAdapter<List<MethodInfo>> {

@Override
    public void visit(MethodDeclaration md, List<MethodInfo> list){
    super.visit(md, list);

    List<Parameter> paras = md.getParameters();

    List<Comment> comments = md.getAllContainedComments();

    int allStatements = md.getBody().get().findAll(Statement.class).size();
    int blocks = md.getBody().get().findAll(BlockStmt.class).size();

        list.add(
                new MethodInfo(
                        md.getNameAsString(),
                        (allStatements-blocks),
                        paras,
                        comments
                        ));

}

}
