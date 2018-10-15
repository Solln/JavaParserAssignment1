package Collectors;

import POJOS.ClassInfo;
import POJOS.MethodInfo;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;

import java.util.ArrayList;
import java.util.List;

public class ClassInfoCollector extends VoidVisitorAdapter<List<ClassInfo>> {

    @Override
    public void visit(ClassOrInterfaceDeclaration md, List<ClassInfo> list){
        super.visit(md, list);

        List<MethodInfo> methods = new ArrayList<>();

        new MethodInfoCollector().visit(md, methods);

        list.add(
                new ClassInfo(
                md.getNameAsString(),
                md.getEnd().get().line,
                md.getAllContainedComments(),
                methods,
                md.getFields()));

    }

}
