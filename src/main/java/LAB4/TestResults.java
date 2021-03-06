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
    private static final String RESULT_FORMAT = "\t Test: %s - %b\n";

    public TestResults(){
        results = new HashMap<>();
    }

    public void runTests(RunMessage msg) throws Exception{
        String packageID = msg.getPackageId();
        String functionName = msg.getFunctionName();
        String jsScript = msg.getJsScript();
        Test[] tests = msg.getTests();
        try {
            for (Test test : tests) {
                ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
                engine.eval(jsScript);
                Invocable invocable = (Invocable) engine;
                String result = invocable.invokeFunction(functionName, test.getParams()).toString();
                boolean isRight = result.equals(test.getExpectedResult());
                if (isRight) {
                    TestingApp.LOGGER.info(String.format(SUCCES_TEST_FORMAT,packageID, functionName, test.getTestName()));
                } else {
                    TestingApp.LOGGER.info(String.format(FAIL_TEST_FORMAT, packageID, functionName, test.getTestName(), test.getExpectedResult(), result));
                }
                results.put(test.getTestName(), isRight);
            }
        } catch (Exception e){
            TestingApp.LOGGER.info(String.format(CODE_COMPILATION_ERROR_FORMAT, packageID, functionName, functionName, e.getMessage()));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        results.forEach((k,v) -> builder.append(String.format(RESULT_FORMAT, k, v)));
        return builder.toString();
    }

    public HashMap<String, Boolean> getResults() {
        return results;
    }
}
