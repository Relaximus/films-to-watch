package com.ftwatch.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Nobody on 02.04.2017.
 */
@Entity
@Data
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    String filmTitle;
    String note;
    //LocalDateTime dt;

    @OneToMany(cascade = CascadeType.ALL)
    List<Film> noteFilms;
}
