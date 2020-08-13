package annotations.inherited;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(value = RetentionPolicy.RUNTIME)
public @interface InheritedAnnotation {
}
