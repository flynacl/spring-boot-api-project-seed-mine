package com.conpany.project;

import com.company.project.modules.user.model.User;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
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

    @Test
    public void testCondition() {
        User user = new User();
        user.setUsername("sdada");
        System.out.println(ReflectionToStringBuilder.toString(user, ToStringStyle.DEFAULT_STYLE));
    }

    @Test
    public void testSystemUtils() {
        System.out.println(SystemUtils.isJavaAwtHeadless());
        System.out.println(SystemUtils.getHostName());
        System.out.println(SystemUtils.getJavaHome());
        System.out.println(SystemUtils.getJavaIoTmpDir());
        System.out.println(SystemUtils.getUserDir());
        System.out.println(SystemUtils.getUserHome());

    }

    @Test
    public void testOperators() {
        System.out.println(Integer.parseInt("A", 16));

    }
}
