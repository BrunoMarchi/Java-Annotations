package annotations.retention;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.CLASS)
public @interface ClassRetention {
}
