package com.codegym.dao;

import com.codegym.model.Song;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class SongDAO implements ISongDAO{

    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        sessionFactory = new Configuration()
                .configure("hibernate.conf.xml")
                .buildSessionFactory();
        entityManager = sessionFactory.createEntityManager();
    }
    @Override
    public List<Song> findAll() {
        String query = "SELECT s FROM Song as s";
        TypedQuery<Song> typedQuery = entityManager.createQuery(query,Song.class);
        return typedQuery.getResultList();
    }

    @Override
    public Song findById(Long id) {
        if(id == null){
            return new Song();
        }
        String query = "Select s from Song as s where s.id =:id";
        TypedQuery<Song> typedQuery = entityManager.createQuery(query, Song.class);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    @Override
    public Song save(Song song) {
        Session session = null;
        Transaction transaction = null;

        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Song oldSong = findById(song.getId());
        oldSong.setName(song.getName());
        oldSong.setSinger(song.getSinger());
        oldSong.setGenre(song.getGenre());
        oldSong.setAudio(song.getAudio());
        session.saveOrUpdate(oldSong);
        transaction.commit();
        return oldSong;
    }

    @Override
    public void removeById(Long id) {

    }
}
