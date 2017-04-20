package com.ftwatch.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.ftwatch.model.Film;
import com.ftwatch.model.Note;
import com.ftwatch.repositores.FilmRepository;
import com.ftwatch.repositores.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService {
    @Autowired
    FilmRepository filmRep;

    @Autowired
    NoteRepository noteRep;

    private Logger log = LoggerFactory.getLogger(NoteService.class);

    protected UriComponentsBuilder addQueryParams(UriComponentsBuilder builder, Note note)  {
        Assert.notNull(note,"The note shouldn't be empty, dude!");

        builder.queryParam("s",note.getFilmTitle());

        return builder;
    }

    private List<Film> findFilmsForNoteAndSave(Note note)  {
        List<Film> films = doGet(note);

        for(Film f : films){
            // try to find one
            Film newF = filmRep.getFilmByTitleAndYear(f.getTitle(),f.getYear());
            // if found replacing the film in the array
            if (newF != null) {
                int i = films.indexOf(f);
                films.set(i, newF);
            } else {
                // saving newcomer
                filmRep.save(f);
            }
        }

        note.setNoteFilms(films);
        noteRep.save(note);

        return films;
    }

    public List<Film> findFilmsForNoteAndSave(long noteId) {
        Note note = noteRep.findOne(noteId);
        return findFilmsForNoteAndSave(note);
    }

    private List<Film> doGet(Note note) {
        try {
            List<Film> films = null;

            URI uri = addQueryParams(UriComponentsBuilder.fromUriString("http://www.omdbapi.com"),note).build().toUri();

            RestTemplate rest = new RestTemplate();
            ResponseEntity<String> entity = rest.getForEntity(uri, String.class);

            if(entity.getStatusCodeValue()==200) {

                    ObjectMapper mapper = new ObjectMapper();
                    String json = entity.getBody();
                    JsonNode search = mapper.readTree(json.getBytes());
                    JsonNode jsonFilms = search.get("Search");
                    CollectionType type = mapper.getTypeFactory()
                            .constructCollectionType(List.class,Film.class);
                    films =  mapper.readValue(jsonFilms.traverse(),type);

            }
            return Optional.of(films).orElse(Collections.emptyList());
        } catch (IOException e) {
            log.error("Error during parsing JSON",e);
            throw new RuntimeException(e);
        }
    }
}
