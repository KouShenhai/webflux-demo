package test;


import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class Test3 {
    private static final Context GLOBAL_CONTEXT = Context.newBuilder()
            .allowAllAccess(true)
            .option("engine.WarnInterpreterOnly", "false") // 禁用解释模式警告
            .build();
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // 创建 GraalVM 上下文
            // 定义 num1 和 num2 的值
            int num1 = 5;
            int num2 = 10;

            // 创建包含占位符的表达式
            String expression = "#{num1} + #{num2}";

            // 将 num1 和 num2 绑定到上下文
        GLOBAL_CONTEXT.getBindings("js").putMember("num1", num1);
        GLOBAL_CONTEXT.getBindings("js").putMember("num2", num2);

            // 替换表达式中的占位符
            String script = expression.replace("#{num1}", Integer.toString(num1))
                    .replace("#{num2}", Integer.toString(num2));

            // 执行表达式
            Value result = GLOBAL_CONTEXT.eval("js", script);

            // 输出结果
            System.out.println("Result: " + result.asInt());
            System.out.println("耗时: " + (System.currentTimeMillis() - startTime) + " 毫秒");
    }
}
