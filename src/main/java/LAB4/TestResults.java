package LAB4;

import javax.script.Invocable;
import java.util.HashMap;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class TestResults {
    private HashMap<String, Boolean> results;
    private static final String ENGINE_NAME = "nashorn";

    public TestResults(){
        results = new HashMap<>();
    }

    public void runTests(Test[] tests, String jsCode) throws Exception{
        for (Test test : tests){
            ScriptEngine engine = new ScriptEngineManager().getEngineByName(ENGINE_NAME);
            engine.eval(jsCode);
            Invocable invocable
        }
    }
}
