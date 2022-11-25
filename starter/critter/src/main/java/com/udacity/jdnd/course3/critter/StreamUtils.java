package com.udacity.jdnd.course3.critter;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class StreamUtils {
    public static <T1, T2> Stream<T2> safeMap(@Nullable Collection<T1> collection, Function<T1, T2> function) {
        return Optional.ofNullable(collection)
                .map(collection1 -> collection1.stream().map(function))
                .orElse(Stream.empty());
    }
}
