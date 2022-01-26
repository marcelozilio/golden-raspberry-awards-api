package texoit.com.goldenraspberryawards.util;

import texoit.com.goldenraspberryawards.entity.Movie;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class MovieUtils {

    public static Set<Movie> getAwardedMoviesAndSortByYear(Set<Movie> movies) {
        return movies.stream()
                .filter(Movie::getWinner)
                .sorted(Comparator.comparingInt(Movie::getYear))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
