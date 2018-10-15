import Collectors.ClassInfoCollector;
import POJOS.ClassInfo;
import POJOS.MethodInfo;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.Parameter;

import java.beans.ParameterDescriptor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String FILE_PATH = "/Users/Solln/Desktop/CS409 - SA&D/Assignment 1/src/main/resources/ReversePolishNotation.java";

    public static void main(String[] args) throws Exception {

        //Compilation Unit First
        CompilationUnit cu = JavaParser.parse(new File(FILE_PATH));

        //Then arrays for holding Classes -> running both parsers
        List<ClassInfo> classes = new ArrayList<>();
        new ClassInfoCollector().visit(cu, classes);

        doTheChecks(classes);

        for (ClassInfo cinfo : classes) {
            System.out.println("Name - " + cinfo.getName());
            System.out.println("Too Long - " + cinfo.isTooLong());
            System.out.println("Length - " + cinfo.getLength());
            for (MethodInfo meth : cinfo.getMethods()) {
                System.out.println("        Meth Name - " + meth.getName());
                System.out.println("        Meth Too Long - " + meth.isTooLong());
                for (Parameter para : meth.getParameters()){
                    System.out.println("                Para Type - " + para.getTypeAsString() + ", Para Name - " + para.getName());
                    System.out.println("                Too Many Params? - " + meth.isParamSizeTooLong());
                }

            }
        }

        // Run Controllers as required
    }

    private static void doTheChecks(List<ClassInfo> classes) {
        LongMethod(classes);
        LongClass(classes);
        LongParamList(classes);
    }

    private static void LongMethod(List<ClassInfo> classes) {
        // Not counting empty space or method header
        for (ClassInfo ci : classes) {
            for (MethodInfo mi : ci.getMethods()) {
                if (mi.getLength() > 10)
                    mi.setTooLong(true);
            }
        }
    }

    private static void LongClass(List<ClassInfo> classes) {
        // Counting empty space or class/method headers
        for (ClassInfo ci : classes) {
                if (ci.getLength() > 100)
                    ci.setTooLong(true);
            }
    }

    private static void LongParamList(List<ClassInfo> classes) {
        // Counts amount of params being passed to the method
        for (ClassInfo ci : classes) {
            for (MethodInfo mi : ci.getMethods()) {
                if (mi.getParameters().size() > 5)
                    mi.setParamSizeTooLong(true);
            }
        }
    }

    private static void checkForPrimitives(List<ClassInfo>classes){
        for (ClassInfo ci : classes) {
            for (MethodInfo mi : ci.getMethods()) {
                for (Parameter para : mi.getParameters()){
                    String type = para.getType().asString();

                }
            }
        }
    }




}

