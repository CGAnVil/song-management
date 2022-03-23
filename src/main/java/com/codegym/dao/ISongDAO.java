package com.codegym.dao;

import com.codegym.model.Song;

import java.util.List;

public interface ISongDAO {
    List<Song> findAll();

    Song findById(Long id);

    Song save(Song song);

    void removeById(Long id);
}
