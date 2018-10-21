import Collectors.ClassInfoCollector;
import POJOS.ClassInfo;
import POJOS.MethodInfo;
import com.github.javaparser.JavaParser;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.VariableDeclarator;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.ReturnStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.model.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;

import java.io.File;

import java.util.*;

public class Main {

    private static final String FILE_PATH = "/Users/Solln/Desktop/CS409 - SA&D/Assignment 1/src/main/resources/CS409TestSystem-master/src";
    static List<File> files = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        TypeSolver reflectionTypeSolver = new ReflectionTypeSolver();
        TypeSolver javaParserTypeSolver = new JavaParserTypeSolver(new File(FILE_PATH));
        reflectionTypeSolver.setParent(reflectionTypeSolver);
        CombinedTypeSolver combinationSolver = new CombinedTypeSolver();
        combinationSolver.add(reflectionTypeSolver);
        combinationSolver.add(javaParserTypeSolver);
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(combinationSolver);
        ParserConfiguration parserConfiguration = new JavaParser().getStaticConfiguration().setSymbolResolver(symbolSolver);


        walk(FILE_PATH);

        System.out.println();
        System.out.println("----------------------------------------");
        System.out.println("----------------------------------------");
        System.out.println();

        for (File javaClass : files) {
            CompilationUnit cu = JavaParser.parse(javaClass);


            //Then arrays for holding Classes -> running both parsers
            List<ClassInfo> classes = new ArrayList<ClassInfo>();
            new ClassInfoCollector().visit(cu, classes);

            for (ClassInfo ci : classes) {
                doTheChecks(ci);
            }


//            LongMethod(ci); // 2 - 2
//            LongClass(ci); // 2 - 2
//            LongParamList(ci); // 2 - 2
//            SwitchStatements(ci); // 3 - 3
//            checkForPrimitives(ci); // 4 - 5
//            dataClass(ci); // 4 - 5
//            TempField(ci); // 3 - 5


            //TODO Print the class location as well

            for (ClassInfo cinfo : classes) {

                if (cinfo.isIssue()) {
                    System.out.println("Class Name - " + cinfo.getName());


                    if (cinfo.isTooLong()) {
                        System.out.println("    ISSUE - Is Too Long");
                    }


                    if (cinfo.isTooManyPrimitives()) {
                        System.out.println("    ISSUE - Has A Primitive Obsession");
//                        int totalCount = 0;
//                        int primCount = 0;
//
//                        for (FieldDeclaration field : cinfo.getFields()) {
//                            totalCount = totalCount + field.getVariables().size();
//                        }
//                        primCount = primCount + cinfo.getVariablePrimitives().size();
//
//
//                        for (MethodInfo meth : cinfo.getMethods()) {
//                            totalCount = totalCount + meth.getVars().size();
//                            primCount = primCount + meth.getVariablePrimitives().size();
//                            totalCount = totalCount + meth.getParameters().size();
//                            primCount = primCount + meth.getParameterPrimitives().size();
//                        }
//
//                        System.out.println("*Total*"+totalCount);
//                        System.out.println("*Prims*"+primCount);
//                        System.out.println(("*Percent*" + ((double) primCount / (double) totalCount) * 100));
                    }


                    if (cinfo.isDataClass())
                        System.out.println("    ISSUE - Is A Data Class");


                    if (cinfo.isLazy())
                        System.out.println("    ISSUE - Is A Lazy Class");


                    if (cinfo.isHasTempFields()) {
                        System.out.println("    ISSUE - Has A Temp Field");
                        for (String temp : cinfo.getWorkedTempFields()){
                            System.out.println("        Field Name - " + temp);
                        }
                    }


                    for (MethodInfo meth : cinfo.getMethods()){

                        if (meth.isIssue()){
                            System.out.println("--------------------");
                            System.out.println();
                            System.out.println("    Method Name - " + meth.getName());


                            if (meth.isTooLong())
                                System.out.println("        Method Is Too Long");


                            if (meth.isParamSizeTooLong())
                                System.out.println("        Too Many Parameters");


                            if (meth.isContainsSwitch())
                                System.out.println("        Has A Switch Statement");


                        }
                    }
                    System.out.println();
                    System.out.println("-----------------------------------------");
                    System.out.println("-----------------------------------------");
                    System.out.println();
                }
            }
        }
    }


    public static void walk(String path) {

        File root = new File( path );
        File[] list = root.listFiles();

        if (list == null) return;

        for ( File f : list ) {
            if (f.isDirectory() ) {
                walk( f.getAbsolutePath() );
            }
            else {
                if (f.getAbsolutePath().contains(".java"))
                files.add(f);
            }
        }
    }


    private static void doTheChecks(ClassInfo ci) {
        LongMethod(ci); // 2 - 2
        LongClass(ci); // 2 - 2
        LongParamList(ci); // 2 - 2
        SwitchStatements(ci); // 3 - 3
        checkForPrimitives(ci); // 4 - 5
        dataClass(ci); // 4 - 5
        TempField(ci); // 3 - 5
        lazyClass(ci); // 3 - 5

    }

    private static void LongMethod(ClassInfo ci) {
        // Not counting empty space or method header
            for (MethodInfo mi : ci.getMethods()) {
                if (mi.getLength() > 15) {
                    ci.setIssue(true);
                    mi.setIssue(true);
                    mi.setTooLong(true);
                }
            }
        }


    private static void LongClass(ClassInfo ci) {
        // Counting empty space or class/method headers

        int count = 0;

        for (MethodInfo meth : ci.getMethods()){
            count = count + meth.getLength();
        }
        count = count + ci.getFields().size();
        ci.setLength(count);

        if (ci.getLength() > 100) {
            ci.setIssue(true);
            ci.setTooLong(true);
        }
            }


    private static void LongParamList(ClassInfo ci) {
        // Counts amount of params being passed to the method
            for (MethodInfo mi : ci.getMethods()) {
                if (mi.getParameters().size() > 5) {
                    ci.setIssue(true);
                    mi.setIssue(true);
                    mi.setParamSizeTooLong(true);
                }
            }
        }


    private static void SwitchStatements(ClassInfo ci) {
        for (MethodInfo mi : ci.getMethods()) {

            for (Statement statement : mi.getWorkedStatements()) {

                // Checks if switch and contains an ENUM

                if (statement.isSwitchStmt()){
                    if(statement.asSwitchStmt().getSelector().calculateResolvedType().isReference()){
                        if (statement.asSwitchStmt().getSelector().calculateResolvedType().asReferenceType().getTypeDeclaration().isEnum()){
                            ci.setIssue(true);
                            mi.setIssue(true);
                            ci.setSwitchStatementsPresent(true);
                            mi.setContainsSwitch(true);
                        }
                    }
                }
            }
        }
    }


    private static void TempField(ClassInfo ci){

        List<String> stringTempFields;


        for (MethodInfo mi : ci.getMethods()) {

            stringTempFields = new ArrayList<>();

            // Get all the Assign Expressions for the Method
            List<AssignExpr> workedTempFields = new ArrayList<>();

            for (FieldDeclaration field : ci.getFields()) {
                for (VariableDeclarator var : field.getVariables()) {
                    if (!var.getInitializer().isPresent()) {
                        // Check if the left right of the assignExpression in the method is declared globally
                        // but is not initialised at a class level
                        for (AssignExpr aE : mi.getTempFields()) {
                            if (var.getNameAsString().equals(aE.getTarget().toString())) {
                                // List of potential temp fields which are declared at class level but not initialised
                                workedTempFields.add(aE);
                            }
                        }
                    }
                }
            }

            // Assign to Method Object
            mi.setTempFields(workedTempFields);

            for (AssignExpr aE : workedTempFields) {
                stringTempFields.add(aE.getTarget().toString());
            }

            Set<String> hs = new HashSet<>();
            hs.addAll(stringTempFields);
            stringTempFields.clear();
            stringTempFields.addAll(hs);

            // Loop every statement in method and check if workedTempField is there

            // Set Flags
            if (stringTempFields.size() != 0) {
                mi.setWorkedTempFields(stringTempFields);
            }
        }

        List<String> tempFieldsInClass = new ArrayList<>();
        List<String> workedTempFieldsInClass = new ArrayList<>();


        for (MethodInfo mi : ci.getMethods()){
            if (mi.getWorkedTempFields() != null) {
                tempFieldsInClass.addAll(mi.getWorkedTempFields());
                workedTempFieldsInClass.addAll(mi.getWorkedTempFields());
            }
        }

        List<String> localInstances = null;
        List<String> classInstances = new ArrayList<>();


        for (MethodInfo mi : ci.getMethods()) {
            localInstances = new ArrayList<>();
            for (Statement si : mi.getWorkedStatements()) {
                for (String tempFieldInQuestion : workedTempFieldsInClass) {
                    if (si.toString().contains(tempFieldInQuestion)){
                        localInstances.add(tempFieldInQuestion);
                    }
                }
            }

            //Remove duplicates
            Set<String> hs = new HashSet<>();
            hs.addAll(localInstances);
            localInstances.clear();
            localInstances.addAll(hs);

            classInstances.addAll(localInstances);
        }

        if (localInstances != null && localInstances.isEmpty() && classInstances != null && !classInstances.isEmpty()) {
            ci.setWorkedTempFields(classInstances);
            ci.setHasTempFields(true);
            ci.setIssue(true);
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

                List<VariableDeclarator> primVars = new ArrayList<>();
                for (VariableDeclarator var : mi.getVars()){
                    if (primitives.contains(var.getTypeAsString())){
                        primVars.add(var);
                    }

                }
                //TODO Would like to check for fields in this section as well if possible

                mi.setParameterPrimitives(paraPrims);
                mi.setVariablePrimitives(primVars);
            }
            ci.setVariablePrimitives(fieldPrims);




        int totalCount = 0;
        int primCount = 0;

        for (FieldDeclaration field : ci.getFields()) {
            totalCount = totalCount + field.getVariables().size();
        }
        primCount = primCount + ci.getVariablePrimitives().size();


        for (MethodInfo meth : ci.getMethods()) {
            totalCount = totalCount + meth.getVars().size();
            primCount = primCount + meth.getVariablePrimitives().size();
            totalCount = totalCount + meth.getParameters().size();
            primCount = primCount + meth.getParameterPrimitives().size();
        }

        double test = ((double) primCount / (double) totalCount) * 100;

        if (test > 75 && totalCount > 10) {
            ci.setIssue(true);
            ci.setTooManyPrimitives(true);
        }







    }


    private static void dataClass(ClassInfo ci) {

        //TODO Check the target and value matches fields and nothing else is there.
        //TODO Example thinks this is a getter
        // ' return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", manager=" + lineManager + "]"; '


        List<ReturnStmt> getterList = new ArrayList<>();
        List<AssignExpr> setterList = new ArrayList<>();

        if (ci.getExtendsList().size() == 0) {

            for (MethodInfo method : ci.getMethods()) {
                if (method.getWorkedStatements().size() == 1) {
                    for (ReturnStmt pg : ci.getPotentialGetters()) {
                        if (method.getWorkedStatements().contains(pg)) {
                                String test = pg.getExpression().get().toString();
                                if (!test.contains(" ") && !test.contains(".") && !test.contains("<") && !test.contains(">") && !test.contains("+"))
                                    getterList.add(pg);
                        }
                    }

                    for (AssignExpr ps : ci.getPotentialSetters()) {
                        for (Statement s : method.getWorkedStatements()) {
                            if (s.findAll(AssignExpr.class).contains(ps)) {
                                    setterList.add(ps);
                            }
                        }
                    }
                }
            }

            if ((getterList.size() + setterList.size()) == ci.getMethods().size() && ci.getLength() != 0) {
                ci.setIssue(true);
                ci.setDataClass(true);
            }
        }
    }


    private static void lazyClass(ClassInfo ci){

        double lazyCount = 0;

        //Check how many statements in the method are block statements
        for (MethodInfo meth : ci.getMethods()){
            for (Statement st : meth.getFullStatements()){
                if (st.isBlockStmt()){
                    lazyCount++;
                }
            }
        }

        // if the block statements make up less that 20% of the statements for the class, then flag it.

        if (lazyCount <= (double)(ci.getLength()/5)){
            ci.setIssue(true);
            ci.setLazy(true);
        }
    }

        }



