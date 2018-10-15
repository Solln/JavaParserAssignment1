package POJOS;

import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.comments.Comment;

import java.util.List;

public class MethodInfo {

    private String name;
    private int length, paramSize;
    private boolean isTooLong, paramSizeTooLong;

    private List<Parameter> parameters;
    private List<Comment> comments;

    public MethodInfo(String name, int methodLength, List<Parameter> paras, List<Comment> comments){
        this.name = name;
        this.length = methodLength;
        this.parameters = paras;
        this.comments = comments;
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
}
