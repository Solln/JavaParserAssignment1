package POJOS;

import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.type.ClassOrInterfaceType;

import java.util.List;

public class ClassInfo {

    private  List<Statement> fullStatements;
    boolean isIssue = false;

    private String name;
    private int length;
    private List<MethodInfo> methods;
    private List<ReturnStmt> potentialGetters;
    private List<AssignExpr> potentialSetters;
    private List<Comment> comments;
    private List<FieldDeclaration> fields;
    private List<VariableDeclarator> tempFields;
    private List<String> workedTempFields;
    private NodeList<ClassOrInterfaceType> extendsList;


    private List<VariableDeclarator> variablePrimitives;

    private boolean isTooLong, hasTooManyParams, switchStatementsPresent, tooManyPrimitives, isDataClass, hasTempFields, isLazy;


    public ClassInfo(String name, List<Comment> comments, List<MethodInfo> methods, List<AssignExpr> potentialSetters, List<ReturnStmt> potentialGetters, List<Statement> fullStatements, List<FieldDeclaration> fields, NodeList<ClassOrInterfaceType> extendedTypes){
        this.name = name;
        this.methods = methods;
        this.potentialSetters = potentialSetters;
        this.potentialGetters = potentialGetters;
        this.comments = comments;
        this.fields = fields;
        this.extendsList = extendedTypes;
        this.fullStatements = fullStatements;
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

    public List<MethodInfo> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodInfo> methods) {
        this.methods = methods;
    }

    public boolean isTooLong() {
        return isTooLong;
    }

    public void setTooLong(boolean tooLong) {
        isTooLong = tooLong;
    }

    public boolean isSwitchStatementsPresent() {
        return switchStatementsPresent;
    }

    public void setSwitchStatementsPresent(boolean switchStatementsPresent) {
        this.switchStatementsPresent = switchStatementsPresent;
    }

    public boolean isDataClass() {
        return isDataClass;
    }

    public void setDataClass(boolean dataClass) {
        isDataClass = dataClass;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<FieldDeclaration> getFields() {
        return fields;
    }

    public void setFields(List<FieldDeclaration> fields) {
        this.fields = fields;
    }

    public List<VariableDeclarator> getVariablePrimitives() {
        return variablePrimitives;
    }

    public void setVariablePrimitives(List<VariableDeclarator> variablePrimitives) {
        this.variablePrimitives = variablePrimitives;
    }

    public List<ReturnStmt> getPotentialGetters() {
        return potentialGetters;
    }

    public void setPotentialGetters(List<ReturnStmt> potentialGetters) {
        this.potentialGetters = potentialGetters;
    }

    public List<AssignExpr> getPotentialSetters() {
        return potentialSetters;
    }

    public void setPotentialSetters(List<AssignExpr> potentialSetters) {
        this.potentialSetters = potentialSetters;
    }

    public List<VariableDeclarator> getTempFields() {
        return tempFields;
    }

    public void setTempFields(List<VariableDeclarator> tempFields) {
        this.tempFields = tempFields;
    }

    public NodeList<ClassOrInterfaceType> getExtendsList() {
        return extendsList;
    }

    public void setExtendsList(NodeList<ClassOrInterfaceType> extendsList) {
        this.extendsList = extendsList;
    }

    public boolean isIssue() {
        return isIssue;
    }

    public void setIssue(boolean issue) {
        isIssue = issue;
    }

    public boolean isHasTooManyParams() {
        return hasTooManyParams;
    }

    public void setHasTooManyParams(boolean hasTooManyParams) {
        this.hasTooManyParams = hasTooManyParams;
    }

    public boolean isTooManyPrimitives() {
        return tooManyPrimitives;
    }

    public void setTooManyPrimitives(boolean tooManyPrimitives) {
        this.tooManyPrimitives = tooManyPrimitives;
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

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    public List<Statement> getFullStatements() {
        return fullStatements;
    }

    public void setFullStatements(List<Statement> fullStatements) {
        this.fullStatements = fullStatements;
    }
}
