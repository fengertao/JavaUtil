package charlie.feng.demo.script;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class ScriptEngineDemo {

    public static void main(String[] args) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        StringBuilder scp = new StringBuilder();
        scp.append("function foo(IAcctLoginEvent_ActorIp,BeaconEventTriggerData_ActorIP){\n");
        scp.append("print(typeof IAcctLoginEvent_ActorIp + ' ' + IAcctLoginEvent_ActorIp); ");
        scp.append("print(typeof BeaconEventTriggerData_ActorIP + ' ' + BeaconEventTriggerData_ActorIP); ");
        scp.append("    return ");
        scp.append("IAcctLoginEvent_ActorIp==BeaconEventTriggerData_ActorIP;");
        scp.append("}");
        System.out.println(scp.toString());
        engine.eval(scp.toString());
        //Object[] params={127L,127L}; return true in both 1.7 and 1.8
        //Object[] params={128l,128l}; return false in 1.8
        Object[] params = {12345678L, 12345678L};
        System.out.println(((Invocable) engine).invokeFunction("foo", params));

    }

}
