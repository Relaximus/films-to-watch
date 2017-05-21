package com.ftwatch;

import com.ftwatch.model.Film;
import com.ftwatch.model.Note;
import com.ftwatch.repositores.FilmRepository;
import com.ftwatch.repositores.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class FilmsApplication implements CommandLineRunner{
	@Autowired
	FilmRepository rep;
	@Autowired
    NoteRepository nRep;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(FilmsApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
        List<Film> films = new ArrayList<>();
        Film f = new Film();
		f.setTitle("Payback");
		f.setYear(1984);
        films.add(f);

		/*Film f2 = new Film();
		f2.setTitle("Silent Hill");
        f2.setYear(2016);
        films.add(f);*/

		Note n = new Note();
		n.setFilmTitle("Payback");
		//n.setDt(LocalDateTime.now());
        n.setNoteFilms(films);
        n = nRep.save(n);

        log.info("At the begining we have this note : {}", n);

        /*films = new ArrayList(n.getNoteFilms());
        films.removeIf(film -> film.getId()==1);
        n.setNoteFilms(films);
        n = nRep.save(n);

        log.info("And now we have this note : {}", n);*/
    }
}
