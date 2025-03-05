package test;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.util.HashMap;
import java.util.Map;

public class Test6 {

    private static final Context GLOBAL_CONTEXT = Context.newBuilder()
            .allowAllAccess(true)
            .option("engine.WarnInterpreterOnly", "false") // 禁用解释模式警告
            .build();

    public static final Map<String, String> map = new HashMap<>();

    public static void main(String[] args) {
        map.put("1","3");
        long s = System.currentTimeMillis();
        // 2. 定义JavaScript函数（接收Map参数，返回处理结果）
        String jsCode =
                """
                        (function processMap(map) {\s
                           let sum = 0;\s
                           for (const key in map) {\s
                               sum += map[key];\s
                           }\s
                           return sum + '（来自JS处理）';\s
                        })""";

        // 3. 执行JavaScript代码获取函数对象
        Value jsFunction = GLOBAL_CONTEXT.eval("js", jsCode);

        // 5. 调用JS函数并传入Map
        String result = jsFunction.execute(map).asString();

        GLOBAL_CONTEXT.close();

        System.out.println("结果: " + result);
        System.out.println(System.currentTimeMillis() - s);
    }

}
