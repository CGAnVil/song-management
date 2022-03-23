package com.codegym.service;

import com.codegym.dao.ISongDAO;
import com.codegym.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SongService implements ISongService{
    @Autowired
    private ISongDAO songDAO;
    @Override
    public List<Song> findAll() {
        return songDAO.findAll();
    }

    @Override
    public Song findById(Long id) {
        return songDAO.findById(id);
    }

    @Override
    public Song save(Song song) {
        return songDAO.save(song);
    }

    @Override
    public void removeById(Long id) {
        songDAO.removeById(id);
    }
}
