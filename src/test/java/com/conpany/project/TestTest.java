package com.conpany.project;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * TestTest
 *
 * @author flynacl
 * @date 2018/3/23
 */
public class TestTest extends Tester {
    @Test
    public void test() {
        Path path = Paths.get("");
        System.out.println(path);
    }
}
