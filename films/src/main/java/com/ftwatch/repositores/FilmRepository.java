package com.ftwatch.repositores;

import com.ftwatch.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Nobody on 02.04.2017.
 */
public interface FilmRepository extends JpaRepository<Film, Long> {
    public Film getFilmByTitleAndYear(String title, int year);
}
