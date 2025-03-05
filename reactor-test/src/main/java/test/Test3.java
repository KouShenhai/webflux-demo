package test;


import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Value;

public class Test3 {
    public static void main(String[] args) {
        // 创建 GraalVM 上下文
        try (Context context = Context.create()) {
            // 定义 num1 和 num2 的值
            int num1 = 5;
            int num2 = 10;

            // 创建包含占位符的表达式
            String expression = "#{num1} + #{num2}";

            // 将 num1 和 num2 绑定到上下文
            context.getBindings("js").putMember("num1", num1);
            context.getBindings("js").putMember("num2", num2);

            // 替换表达式中的占位符
            String script = expression.replace("#{num1}", Integer.toString(num1))
                    .replace("#{num2}", Integer.toString(num2));

            // 执行表达式
            Value result = context.eval("js", script);

            // 输出结果
            System.out.println("Result: " + result.asInt());
        }
    }
}
