package com.example.demo.core.utils;

import com.example.demo.core.lang.Session;
import com.example.demo.core.lang.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * 线程池操作，提交线程后，会自动复制session
 */
public class TreadPool {
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    /**
     * 提交有返回值的线程
     *
     * @param function R:返回值类型
     * @return
     */
    public static <R> Future<R> submit(Function<Void, R> function) {
        User login = Session.get();
        return executorService.submit(() -> {
            Session.set(login);
            return function.apply(null);
        });
    }

    /**
     * 提交没有返回值的线程
     *
     * @param consumer
     * @return
     */
    public static Future<Void> submit(Consumer<Void> consumer) {
        User login = Session.get();
        return executorService.submit(() -> {
            Session.set(login);
            consumer.accept(null);
            return null;
        });
    }
}
