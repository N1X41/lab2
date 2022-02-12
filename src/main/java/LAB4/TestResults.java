package LAB4;

import javax.script.Invocable;
import java.util.HashMap;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class TestResults {
    private HashMap<String, Boolean> results;
    private static final String ENGINE_NAME = "nashorn";
    private static final String SUCCES_TEST_FORMAT = "PackageID: %s, Function Name: %s, Test: %s passed!\n";
    private static final String FAIL_TEST_FORMAT = "PackageID: %s, Function Name: %s, Test: %s failed! Expected result: %s, result: %s\n";
    private static final String CODE_COMPILATION_ERROR_FORMAT = "PackageID: %s, FunctionName: %s, Test: %s, failed to compile code! Message: %s\n";

    public TestResults(){
        results = new HashMap<>();
    }

    public void runTests(Test[] tests, String jsCode, String functionName, String packageID) throws Exception{
        try {
            for (Test test : tests) {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                engine.eval(jsScript);
                Invocable invocable = (Invocable) engine;
                String result = invocable.invokeFunction(functionName, test.getParams()).toString();
                boolean isRight = result.equals(test.getExpectedResult());
                if (isRight) {
                    System.out.printf(SUCCES_TEST_FORMAT, packageID, functionName, test.getTestName());
                } else {
                    System.out.printf(FAIL_TEST_FORMAT, packageID, functionName, test.getTestName(), test.getExpectedResult(), result);
                }
                results.put(test.getTestName(), isRight);
            }
        } catch (Exception e){
            System.out.printf(CODE_COMPILATION_ERROR_FORMAT, packageID, functionName, e.getMessage());
        }
    }

    public HashMap<String, Boolean> getResults() {
        return results;
    }
}
