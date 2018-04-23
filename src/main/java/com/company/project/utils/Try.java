package com.company.project.utils;

import java.util.Objects;
import java.util.function.Function;

/**
 * 包装会抛出异常的函数，使其不抛出受检异常
 *
 * @author yanfeixiang
 * @date 2018/3/28
 * @link https://segmentfault.com/a/1190000007832130
 */
public class Try {
    /**
     * 包装会抛出异常的函数
     * @param mapper 会抛出异常的函数
     * @param defaultR 抛出异常后，返回的默认值
     * @param <T> 入参
     * @param <R> 返回值
     * @return <R>
     */
    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> mapper, R defaultR) {
        Objects.requireNonNull(mapper);
        return t -> {
            try {
                return mapper.apply(t);
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
                return defaultR;
            }
        };
    }

    @FunctionalInterface
    public static interface UncheckedFunction<T, R> {

        /**
         *
         * @param t 入参
         * @return R
         * @throws Exception 异常
         */
        R apply(T t) throws Exception;
    }
}
