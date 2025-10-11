package ru.kuzdikenov.util;

import org.junit.Assert;
import org.junit.Test;
import ru.kuzdikenov.http.ConnectionOperations;

import java.util.HashMap;
import java.util.Map;


public class TestPasswordUtil {

    @Test
    public void testHashPassword() {
        String password = "qwerty007";
        String hash = "1B435C76EBB69FC6130027F05FA139FF";
        String actual = PasswordUtil.encrypt(password);
        Assert.assertEquals(hash, actual);
    }
}
