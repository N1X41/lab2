package LAB4;

import javax.script.Invocable;
import java.util.HashMap;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class TestResults {
    private HashMap<String, Boolean> results;
    private static final String ENGINE_NAME = "nashorn";
    private static final String SUCCES_TEST_FORMAT = "PackageID: %s, Test: %s passed!";
    private static final String FAIL_TEST_FORMAT = "";



    public TestResults(){
        results = new HashMap<>();
    }

    public void runTests(Test[] tests, String jsCode, String functionName, String packageID) throws Exception{
        for (Test test : tests){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
            engine.eval(jsCode);
            Invocable invocable = (Invocable)engine;
            String result = invocable.invokeFunction(functionName, test.getParams()).toString();
            boolean isRight = result.equals(test.getExpectedResult());
            if (isRight) {
                System.out.printf(SUCCES_TEST_FORMAT,);
            } else {
                System.out.printf(FAIL_TEST_FORMAT,);
            }
            results.put(test.getTestName(), isRight);
        }
    }

    public HashMap<String, Boolean> getResults() {
        return results;
    }
}
