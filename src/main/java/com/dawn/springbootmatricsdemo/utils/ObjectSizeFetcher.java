package com.dawn.springbootmatricsdemo.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.instrument.Instrumentation;

@Component
@RequiredArgsConstructor
public class ObjectSizeFetcher {

    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) {
        return instrumentation.getObjectSize(o);
    }
}
