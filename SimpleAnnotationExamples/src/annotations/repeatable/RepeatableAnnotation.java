package annotations.repeatable;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Repeatable(RepeatableAnnotation.List.class)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface RepeatableAnnotation {
    String value();

    @Retention(value = RetentionPolicy.RUNTIME)
    @interface List {
        RepeatableAnnotation[] value(); //needs to be named value
    }
}
