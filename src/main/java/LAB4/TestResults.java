package LAB4;

import java.util.HashMap;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class TestResults {
    private HashMap<String, Boolean> results;
    private static final String ENGINE_NAME = "nashorn";

    public TestResults(){
        results = new HashMap<>();
    }

    public void runTests(Test[] tests) throws Exception{
        for (Test test : tests){
            ScriptEngine engine = ScriptEngineManager();
        }
    }
}
