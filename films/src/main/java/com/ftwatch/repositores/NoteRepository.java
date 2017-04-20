package com.ftwatch.repositores;

import com.ftwatch.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Nobody on 02.04.2017.
 */
public interface NoteRepository extends JpaRepository<Note, Long> {}
