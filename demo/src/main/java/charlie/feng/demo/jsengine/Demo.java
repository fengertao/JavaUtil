package charlie.feng.demo.jsengine;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Demo {
    public static void main(String[] args) throws ScriptException, FileNotFoundException, InterruptedException, Exception {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        engine.eval("print('Hellow World!')");
        System.out.println("start execute hello.js by loop");
        for (int i = 0; i < 3; i++) {
            engine.eval(new FileReader("src/main/java/charlie/feng/demo/jsengine/hello.js"));
            Thread.sleep(1000);
        }
        System.out.println("Start call SayHello of HelloJSEngine");
        HelloJSEngine hello = new HelloJSEngine();
        engine.put("script_hello", hello);
        engine.eval("script_hello.sayHello()");
        System.out.println("start call say method of js script:");
        String script = "function say(first,second){print(first + ' ' + second + '\\n'); }";
        engine.eval(script);
        Invocable inv = (Invocable) engine;
        inv.invokeFunction("say", "Hello", "Tony");
        script = "function max(first, second) {return (first > second) ? first : second; }";
        engine.eval(script);

        // js and Java interface call

        Object obj = inv.invokeFunction("max", "1", "0");
        if ("1" == obj.toString()) {
            System.out.println(obj.toString());
        } else {
            throw new Exception("asset failure");
        }

        // interface call, use js implement java interface
        script = "function max(first,second) " + "{ return (first > second) ?first:second;}";
        script += "function min(first,second) { return (first < second) ?first:second;}";
        engine.eval(script);
        MaxMin maxMin = inv.getInterface(MaxMin.class);
        if (1 == maxMin.max(1, 0)) {
            // System.out.println(obj.toString());
        } else {
            throw new Exception("asset failure");
        }
        if (0 == maxMin.min(0, 1)) {
            // System.out.println(obj.toString());
        } else {
            throw new Exception("asset failure");
        }

        // script compile and execution, test regexpr usage.
        script = "var email=/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]" + "+(\\.[a-zA-Z0-9_-]+)+$/;";
        script += "var ip = /^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])" + "(\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])){3}$/;";
        script += "if(email.test(str)){println('it is an email')}" + "else if(ip.test(str)){println('it is an ip address')}" + "else{println('I don\\'t know')}";
        engine.put("str", "10.10");
        engine.eval(script);

        // Compilable compilable = (Compilable) engine;
        // CompiledScript compiled = compilable.compile(script);
        // compiled.eval();

        // Compilable compilable = (Compilable) engine;
        // CompiledScript compiled = compilable.compile(script);
        // compiled.eval();

    }
}
