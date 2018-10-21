package Collectors;

import POJOS.MethodInfo;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.IfStmt;
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
    List<VariableDeclarator> vars = md.findAll(VariableDeclarator.class);
    List<Comment> comments = md.getAllContainedComments();
    List<Statement> fullStatementList = md.findAll(Statement.class);
    List<Statement> workedStatementList = new ArrayList<>();

    //Gets the statements - the blocks
    for (Statement s : fullStatementList){
        if (!s.isBlockStmt()){
            workedStatementList.add(s);
        }
    }

    //Gets the Length

    int allStatements = 0, blocks = 0;

    if (md.getBody().isPresent()) {
        allStatements = md.getBody().get().findAll(Statement.class).size();
        blocks = md.getBody().get().findAll(BlockStmt.class).size();
    }


    //checks and applies temp fields

    List<AssignExpr> tempFields = new ArrayList<>();

    for (AssignExpr aE : md.findAll(AssignExpr.class)){
        if (aE.findParent(IfStmt.class).isPresent() || aE.findParent(SwitchStmt.class).isPresent()){
                tempFields.add(aE);
        }
    }

        list.add(
                new MethodInfo(
                        md.getNameAsString(),
                        (allStatements-blocks),
                        paras,
                        vars,
                        fullStatementList,
                        workedStatementList,
                        tempFields,
                        comments
                        ));

}

}
