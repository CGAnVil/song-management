package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class SongForm {
    private Long id;
    private String name;
    private String singer;
    private String genre;
    private MultipartFile audio;

    public SongForm() {
    }

    public SongForm(Long id, String name, String singer, String genre, MultipartFile audio) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.genre = genre;
        this.audio = audio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public MultipartFile getAudio() {
        return audio;
    }

    public void setAudio(MultipartFile audio) {
        this.audio = audio;
    }
}
