package runtimeAnnotations;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ActionListenerInstaller {
    public static void processAnnotations(Object clazz, Component[] components) {
        try {
            Class<?> cls = clazz.getClass();
            Method[] methods = cls.getDeclaredMethods();
            Class<ActionListenerFor> actionListenerForClass = ActionListenerFor.class;  // 原始的注解(interface runtimeAnnotations.ActionListenerFor)
            Pattern pattern = Pattern.compile("(?<methodName>^[a-z]+)(?=\\p{Upper})");
            Map<String, Component> componentMap =
                    Arrays.stream(components).collect(Collectors.toMap(component -> ((JButton) component).getText(), Function.identity()));

            for (Method method: methods) {
                // getDeclaredAnnotation方法返回方法上的详细注解(@runtimeAnnotations.ActionListenerFor(source="blackButton"))
                Annotation annotation = method.getDeclaredAnnotation(actionListenerForClass);
                Matcher matcher = pattern.matcher((String) actionListenerForClass.getDeclaredMethod("source").invoke(annotation));
                Component component = null;
                if (matcher.find()) component = componentMap.get(matcher.group("methodName"));
                if (component != null && method.isAnnotationPresent(actionListenerForClass)) addListener(component, clazz, method);
            }
        } catch (ReflectiveOperationException e) { e.printStackTrace(); }
    }

    public static void addListener(final Object button, final Object clazz, final Method method) throws ReflectiveOperationException {
        // @param m 接口声明的方法; @param args 参数
        // 这个例子中, m = actionPerformed, args = ActionEvent
        InvocationHandler invocationHandler = (Object proxy, Method m, Object[] args) -> method.invoke(clazz);

        // 如果想获得某个类的所有实现的接口，可以调用xxx.getClass().getInterfaces(), 这样就可以得到这个类的所有接口了，返回值是一个Class<?>[], 数组内的Class对象是按照声明接口的顺序排列的
        // 例如class test implements FloorWax, DessertTopping, 那么xxx.getClass().getInterfaces()[0]等于FloorWax, xxx.getClass().getInterfaces()[1]等于DessertTopping
        Object listener = Proxy.newProxyInstance(null, new Class[]{ ActionListener.class }/* 代理类的数组接口 */, invocationHandler);

        Method adder = button.getClass().getMethod("addActionListener", ActionListener.class);
        adder.invoke(button, listener);
    }
}