package com.codegym.controller;

import com.codegym.model.Song;
import com.codegym.model.SongForm;
import com.codegym.service.ISongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
public class SongController {
    @Autowired
    private ISongService songService;

    @Value("${file-upload}")
    private String uploadPath;

    @GetMapping("/songs/list")
    public ModelAndView showListSong() {
        ModelAndView modelAndView = new ModelAndView("/song/list");
        List<Song> songs = songService.findAll();
        modelAndView.addObject("songs", songs);
        return modelAndView;
    }

    @GetMapping("/songs/create")
    public ModelAndView showCreateSong() {
        ModelAndView modelAndView = new ModelAndView("/song/create");
        modelAndView.addObject("song", new SongForm());
        return modelAndView;
    }

    @PostMapping("songs/create")
    public ModelAndView createSong(@ModelAttribute SongForm songForm) {
        String fileName = songForm.getAudio().getOriginalFilename();
        long currentTime = System.currentTimeMillis();
        fileName = currentTime + fileName;

        try {
            FileCopyUtils.copy(songForm.getAudio().getBytes(), new File(uploadPath + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Song song = new Song(songForm.getId(), songForm.getName(), songForm.getSinger(), songForm.getGenre(), fileName);
        songService.save(song);
        return new ModelAndView("redirect:/songs/list");
    }

}
