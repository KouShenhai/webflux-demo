package test;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.util.Map;

public class Test6 {

    private static final Context GLOBAL_CONTEXT = Context.newBuilder()
            .allowAllAccess(true)
            .option("engine.WarnInterpreterOnly", "false") // 禁用解释模式警告
            .build();

    public static void main(String[] args) {
        // 1. 创建并初始化 Map
        Map<String, Integer> map = Map.of("1", 3);

        long startTime = System.currentTimeMillis();

        // 2. 定义 JavaScript 函数（接收 Map 参数，返回处理结果）
        String jsCode =
                """
                (function processMap(map) {
                   let sum = 0;
                   for (const [key, value] of map.entries()) {
                       sum += value;
                   }
                   return sum + '（来自JS处理）';
                })
                """;

        // 3. 执行 JavaScript 代码获取函数对象
        Value jsFunction = GLOBAL_CONTEXT.eval("js", jsCode);

        // 4. 调用 JS 函数并传入 Map
        String result = jsFunction.execute(map).asString();

        GLOBAL_CONTEXT.close();

        System.out.println("结果: " + result);
        System.out.println("耗时: " + (System.currentTimeMillis() - startTime) + " 毫秒");
    }

}
