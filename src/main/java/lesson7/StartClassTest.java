package lesson7;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class StartClassTest {

    public static void start(Class<?> c) throws Exception {
        Method[] declaredMethods = c.getDeclaredMethods();
        ArrayList<Method> arrays = new ArrayList<>();
        ArrayList<Method> arrayList = new ArrayList<>();
        int a = 0;
        int b = 0;
        for (int i = 0; i < declaredMethods.length; i++) {
            Annotation[] declaredAnnotations = declaredMethods[i].getDeclaredAnnotations();
            for (int j = 0; j < declaredAnnotations.length; j++) {
                if (declaredMethods[i].isAnnotationPresent(BeforeSuite.class)) {
                    if (b == 0) {
                        arrayList.add(0, declaredMethods[i]);
                        b++;
                    } else {
                        throw new RuntimeException(c.getDeclaredMethod("before").getDeclaredAnnotation(BeforeSuite.class).message());
                    }
                }
                if (declaredMethods[i].isAnnotationPresent(Test.class)) {
                    arrays.add(declaredMethods[i]);
                }
                if (declaredMethods[i].isAnnotationPresent(AfterSuite.class)) {
                    if (a == 0) {
                        arrayList.add(declaredMethods[i]);
                        a++;
                    } else {
                        throw new RuntimeException(c.getDeclaredMethod("after").getDeclaredAnnotation(AfterSuite.class).message());
                    }
                }
            }
        }
        arrays.sort(Comparator.comparingInt(o -> o.getAnnotation(Test.class).priority()));
        arrayList.addAll(1,arrays);
        for (Method method : arrayList) {
            method.setAccessible(true);
            method.invoke(c.newInstance());
            method.setAccessible(false);
        }
    }
}
