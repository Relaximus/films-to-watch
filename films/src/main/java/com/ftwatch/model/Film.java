package com.ftwatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * Created by Nobody on 02.04.2017.
 */
@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Film {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @JsonProperty("Title")
    private String title;
    @JsonProperty("Year")
    private int year;
    /**
     * The url of the image
     */
    @JsonProperty("Poster")
    private String poster;
    @JsonProperty("imdbID")
    private String imdbId;
    @JsonProperty("Type")
    String type;
}
