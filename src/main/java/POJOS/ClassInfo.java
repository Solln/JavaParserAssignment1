package POJOS;

import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;

import java.util.List;

public class ClassInfo {

    private String name;
    private int length;
    private List<MethodInfo> methods;
    private List<Comment> comments;
    private List<FieldDeclaration> fields;

    private List<VariableDeclarator> variablePrimitives;

    private boolean isTooLong, hasTooManyMethods, switchStatementsPresent, isDataClass;


    public ClassInfo(String name, int length, List<Comment> comments, List<MethodInfo> methods, List<FieldDeclaration> fields){
        this.name = name;
        this.length = length;
        this.methods = methods;
        this.comments = comments;
        this.fields = fields;
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

    public boolean isHasTooManyMethods() {
        return hasTooManyMethods;
    }

    public void setHasTooManyMethods(boolean hasTooManyMethods) {
        this.hasTooManyMethods = hasTooManyMethods;
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
}
