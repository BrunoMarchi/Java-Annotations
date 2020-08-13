import annotations.documented.DocumentedAnnotation;
import annotations.inherited.InheritedAnnotation;
import annotations.inherited.NonInheritedAnnotation;
import annotations.repeatable.RepeatableAnnotation;
import annotations.retention.ClassRetention;
import annotations.retention.RuntimeRetention;
import annotations.retention.SourceRetention;
import annotations.target.TargetAnnotation;
import example.documented.DocumentedExample;
import example.inherited.Subclass;
import example.inherited.Superclass;
import example.repeatable.RepeatableExample;
import example.retention.ClassRetentionExample;
import example.retention.RuntimeRetentionExample;
import example.retention.SourceRetentionExample;
import example.target.TargetExample;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AnnotationExample {

    public static boolean checkClassForAnnotation(Object obj, Class<? extends Annotation> annotationClass) {
        Class<?> clazz = obj.getClass();
        return clazz.isAnnotationPresent(annotationClass);
    }

    public static void retentionExample() {
        System.out.println("Class Retention annotation? " + checkClassForAnnotation(new ClassRetentionExample(), ClassRetention.class));
        System.out.println("Source Retention annotation? " + checkClassForAnnotation(new SourceRetentionExample(), SourceRetention.class));
        System.out.println("Runtime Retention annotation? " + checkClassForAnnotation(new RuntimeRetentionExample(), RuntimeRetention.class));
    }

    public static void documentedExample() {
        DocumentedExample doc = new DocumentedExample();
        System.out.println("Documented annotation? " + checkClassForAnnotation(doc, DocumentedAnnotation.class));

        DocumentedAnnotation[] t = doc.getClass().getAnnotationsByType(DocumentedAnnotation.class);

        for(DocumentedAnnotation type : t) {
            System.out.println(type.document());
        }
    }

    public static void inheritedExample() {
        System.out.println("Superclass NonInherited annotation? " + checkClassForAnnotation(new Superclass(), NonInheritedAnnotation.class));
        System.out.println("Superclass Inherited annotation? " + checkClassForAnnotation(new Superclass(), InheritedAnnotation.class));

        System.out.println("Subclass NonInherited annotation? " + checkClassForAnnotation(new Subclass(), NonInheritedAnnotation.class));
        System.out.println("Subclass Inherited annotation? " + checkClassForAnnotation(new Subclass(), InheritedAnnotation.class));

    }

    public static void repeatableExample() {
        RepeatableExample repeatable = new RepeatableExample();
        System.out.println("Repeatable annotation? " + checkClassForAnnotation(repeatable, RepeatableAnnotation.class));
        System.out.println("Repeatable List annotation? " + checkClassForAnnotation(repeatable, RepeatableAnnotation.List.class));

        RepeatableAnnotation[] t = repeatable.getClass().getAnnotationsByType(RepeatableAnnotation.class);

        for(RepeatableAnnotation repeat : t) {
            System.out.println(repeat.value());
        }
    }

    public static void targetExample() {
        TargetExample target = new TargetExample();
        System.out.println("Target annotation? " + checkClassForAnnotation(target, TargetAnnotation.class));

        try {
            Field field = target.getClass().getDeclaredField("helloWorld");
            if(field.isAnnotationPresent(TargetAnnotation.class)){
                System.out.println("Hello? " + field.getAnnotation(TargetAnnotation.class).helloWorld());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        retentionExample();
        documentedExample();
        inheritedExample();
        repeatableExample();
        targetExample();
    }
}
