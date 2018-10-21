package POJOS;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public class MethodInfo {

    private  List<Statement> workedStatements;
    private  List<Statement> fullStatements;

    boolean isIssue;

    private String name;
    private int length, paramSize;
    private List<Parameter> parameters;
    private List<VariableDeclarator> vars;

    private List<Comment> comments;
    private List<AssignExpr> tempFields;
    private List<String> workedTempFields;

    private boolean isTooLong, paramSizeTooLong, containsSwitch, hasTempFields;
    private List<Parameter> parameterPrimitives;
    private List<VariableDeclarator> variablePrimitives;


    public MethodInfo(String name, int methodLength, List<Parameter> paras, List<VariableDeclarator> vars, List<Statement> fullStatementList, List<Statement> workedStatements, List<AssignExpr> tempFields, List<Comment> comments){
        this.name = name;
        this.length = methodLength;
        this.parameters = paras;
        this.comments = comments;
        this.workedStatements = workedStatements;
        this.fullStatements = fullStatementList;
        this.vars = vars;
        this.tempFields = tempFields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getParamSize() {
        return paramSize;
    }

    public void setParamSize(int paramSize) {
        this.paramSize = paramSize;
    }

    public boolean isTooLong() {
        return isTooLong;
    }

    public void setTooLong(boolean tooLong) {
        isTooLong = tooLong;
    }

    public boolean isParamSizeTooLong() {
        return paramSizeTooLong;
    }

    public void setParamSizeTooLong(boolean paramSizeTooLong) {
        this.paramSizeTooLong = paramSizeTooLong;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Parameter> getParameterPrimitives() {
        return parameterPrimitives;
    }

    public void setParameterPrimitives(List<Parameter> parameterPrimitives) {
        this.parameterPrimitives = parameterPrimitives;
    }

    public boolean isContainsSwitch() {
        return containsSwitch;
    }

    public void setContainsSwitch(boolean containsSwitch) {
        this.containsSwitch = containsSwitch;
    }

    public List<VariableDeclarator> getVars() {
        return vars;
    }

    public void setVars(List<VariableDeclarator> vars) {
        this.vars = vars;
    }

    public List<VariableDeclarator> getVariablePrimitives() {
        return variablePrimitives;
    }

    public void setVariablePrimitives(List<VariableDeclarator> variablePrimitives) {
        this.variablePrimitives = variablePrimitives;
    }

    public List<AssignExpr> getTempFields() {
        return tempFields;
    }

    public void setTempFields(List<AssignExpr> tempFields) {
        this.tempFields = tempFields;
    }

    public boolean isIssue() {
        return isIssue;
    }

    public void setIssue(boolean issue) {
        isIssue = issue;
    }

    public boolean isHasTempFields() {
        return hasTempFields;
    }

    public void setHasTempFields(boolean hasTempFields) {
        this.hasTempFields = hasTempFields;
    }

    public List<String> getWorkedTempFields() {
        return workedTempFields;
    }

    public void setWorkedTempFields(List<String> workedTempFields) {
        this.workedTempFields = workedTempFields;
    }

    public List<Statement> getWorkedStatements() {
        return workedStatements;
    }

    public void setWorkedStatements(List<Statement> workedStatements) {
        this.workedStatements = workedStatements;
    }

    public List<Statement> getFullStatements() {
        return fullStatements;
    }

    public void setFullStatements(List<Statement> fullStatements) {
        this.fullStatements = fullStatements;
    }
}
