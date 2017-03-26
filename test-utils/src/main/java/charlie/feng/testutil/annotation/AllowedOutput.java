package charlie.feng.testutil.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Add @AllowSystemOut annatation will disable junit system out/err check.
 * refer to NoSystemOutWatcher for detail.
    * refer to NoSystemOutTest for example.
    */
@Retention(RetentionPolicy.RUNTIME)
@Target({
            ElementType.FIELD,
            ElementType.METHOD
        })
public @interface AllowedOutput{
        String[] patterns();
}

