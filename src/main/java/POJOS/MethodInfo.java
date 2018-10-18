package POJOS;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.stmt.Statement;

import java.util.List;

public class MethodInfo {

    private String name;
    private int length, paramSize;
    private List<Parameter> parameters;
    private List<Comment> comments;
    private List<Statement> statements;

    private boolean isTooLong, paramSizeTooLong;
    private List<Parameter> parameterPrimitives;


    public MethodInfo(String name, int methodLength, List<Parameter> paras, List<Statement> statements, List<Comment> comments){
        this.name = name;
        this.length = methodLength;
        this.parameters = paras;
        this.comments = comments;
        this.statements = statements;
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

    public List<Statement> getStatements() {
        return statements;
    }

    public void setStatements(List<Statement> statements) {
        this.statements = statements;
    }
}
