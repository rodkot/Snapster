package ru.sbertech.platformv.print.benchmark.templateengine;

import clojure.java.api.Clojure;
import clojure.lang.IFn;
import org.junit.jupiter.api.Test;

public class StencilTest extends ExpectedOutputTest {
    @Test
    public void hello(){
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("ru.sbertech.platformv.print.benchmark.clojure.Hello"));

        IFn hello = Clojure.var("ru.sbertech.platformv.print.benchmark.clojure.Hello", "-hello");
        hello.invoke();
    }
}
