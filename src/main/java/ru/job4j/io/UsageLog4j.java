package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean a = true;
        char b = 'P';
        byte c = 12;
        int d = 33;
        long e = 45L;
        float f = 33.0f;
        double g = 67.0345;
        short h = 55;
        LOG.debug("Variables a : {}, b : {}, "
                        + "c : {}, d : {}, "
                        + "e : {}, f : {}, "
                        + "g : {}, h : {}",
                a, b, c, d, e, f, g, h);
    }
}
