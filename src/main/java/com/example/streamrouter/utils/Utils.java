package com.example.streamrouter.utils;

import com.example.streamrouter.model.Subject;
import org.jetbrains.annotations.NotNull;

public class Utils {
    @NotNull
    public static String buildRoute(@NotNull Subject child, @NotNull Subject parent) {
        return parent.getRoute().replace("*", String.format("%s.*", child.getName()));
    }
}
