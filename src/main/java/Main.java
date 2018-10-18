import Collectors.ClassInfoCollector;
import POJOS.ClassInfo;
import POJOS.MethodInfo;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseException;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;

import com.github.javaparser.ast.stmt.Statement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String FILE_PATH = "/Users/Solln/Desktop/CS409 - SA&D/Assignment 1/src/main/resources/ReversePolishNotation.java";

    public static void main(String[] args) throws Exception {

        //Compilation Unit First
        CompilationUnit cu = JavaParser.parse(new File(FILE_PATH));

        //Then arrays for holding Classes -> running both parsers
        List<ClassInfo> classes = new ArrayList<>();
        new ClassInfoCollector().visit(cu, classes);


        //TODO Loop this part, give it one class, will make it easier to display later.
        for (ClassInfo ci : classes){
            doTheChecks(ci);
        }

        for (ClassInfo cinfo : classes) {

            System.out.println("Class Name - " + cinfo.getName() );

            if (cinfo.isTooLong())
                System.out.println("    ISSUE - Is Too Long");

            if (cinfo.isDataClass())
                System.out.println("    ISSUE - Is A Data Class");

            if (cinfo.getVariablePrimitives().size() != 0){
                for (VariableDeclarator var : cinfo.getVariablePrimitives()){
                    System.out.println("    " + var.getType() + " " + var.getName() + " - Is a Primitive");
                }
            }


        }

        // Run Controllers as required
    }

    private static void doTheChecks(ClassInfo ci) {
        LongMethod(ci);
        LongClass(ci);
        LongParamList(ci);
        checkForPrimitives(ci);
        dataClass(ci);
//        SwitchStatements(ci);
    }

    private static void LongMethod(ClassInfo ci) {
        // Not counting empty space or method header
            for (MethodInfo mi : ci.getMethods()) {
                if (mi.getLength() > 10)
                    mi.setTooLong(true);
            }
        }


    private static void LongClass(ClassInfo ci) {
        // Counting empty space or class/method headers
                if (ci.getLength() > 100)
                    ci.setTooLong(true);
            }


    private static void LongParamList(ClassInfo ci) {
        // Counts amount of params being passed to the method
            for (MethodInfo mi : ci.getMethods()) {
                if (mi.getParameters().size() > 5)
                    mi.setParamSizeTooLong(true);
            }
        }


    private static void SwitchStatements(ClassInfo ci){
            for (MethodInfo mi : ci.getMethods()) {

                for (Statement statement : mi.getStatements()){
//                    if (statement.toString().contains("switch")){
//                        System.out.println(mi.getName() + " Contains a switch");
//                    }

                    if (statement.isSwitchStmt()){
                        System.out.println(mi.getName() + " Contains a switch");
                    }
                }

            List<Statement> test = mi.getStatements();
                System.out.println();

            }

    }

    private static void checkForPrimitives(ClassInfo ci){

        List<String> primitives = Arrays.asList(new String[]{"byte", "short", "int", "long", "float", "double", "char", "boolean" });

            List<VariableDeclarator> fieldPrims = new ArrayList<>();

            for (FieldDeclaration field : ci.getFields()) {
                // Check if the field's type is included in the list of primitives
               for (VariableDeclarator variable : field.getVariables()){
                   if (primitives.contains(variable.getTypeAsString())){
                       fieldPrims.add(variable);
                   }
               }
            }

            for (MethodInfo mi : ci.getMethods()) {
                List<Parameter> paraPrims = new ArrayList<>();

                for (Parameter para : mi.getParameters()){
                    // Check if the para's type is included in the list of primitives
                    if (primitives.contains(para.getTypeAsString())){
                        paraPrims.add(para);
                    }
                }
                //TODO Would like to check for fields in this section as well if possible

                mi.setParameterPrimitives(paraPrims);
            }
            ci.setVariablePrimitives(fieldPrims);
        }




    private static void dataClass(ClassInfo ci){

        List<MethodInfo> getterList = new ArrayList<>();
        List<MethodInfo> setterList = new ArrayList<>();

            for (MethodInfo mi : ci.getMethods()) {

                // CHECK IF A GETTER HERE
                if (mi.getStatements().size() == 1) {
                    // Loop all the statements within the method
                    for (Statement statement : mi.getStatements()) {
                        // If its a return statement
                        if (statement.isReturnStmt()) {
                            String returnName = statement.getChildNodes().get(0).toString();
                            // Check the class fields and see if they match the method name
                            for (FieldDeclaration field :ci.getFields()){
                               if (returnName.contains(field.getVariables().get(0).getNameAsString())){
                                   getterList.add(mi);
                               }
                            }
                        }
                    }
                }

                //CHECK IF A SETTER HERE
                //TODO RISKY will break on 1 line methods without 2 variables present
                if (mi.getStatements().size() == 1 && !getterList.contains(mi)) {
                    // Loop all the statements within the method
                    for (Statement statement : mi.getStatements()) {
                        String targetField =  statement.getChildNodes().get(0).getChildNodes().get(0).toString();
                        String valuePara =  statement.getChildNodes().get(0).getChildNodes().get(1).toString();

                        // Check the class fields and see if they match the method name
                        for (FieldDeclaration field : ci.getFields()) {
                            if (targetField.contains(field.getVariables().get(0).getNameAsString())) {
                                for (Parameter parameter : mi.getParameters()) {
                                    if (valuePara.contains(parameter.getNameAsString())){
                                        setterList.add(mi);
                                    }
                                }
                            }
                        }

                    }
                }
            }

            if ((getterList.size() + setterList.size()) == ci.getMethods().size()){
                ci.setDataClass(true);
            }


        }


        }



