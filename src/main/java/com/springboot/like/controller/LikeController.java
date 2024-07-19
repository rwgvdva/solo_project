package com.springboot.like.controller;

import com.springboot.like.dto.LikePostDto;
import com.springboot.like.entity.Like;
import com.springboot.like.mapper.LikeMapper;
import com.springboot.like.service.LikeService;
import com.springboot.utils.UriCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/v11/likes")
@Validated
public class LikeController {
    private final static String LIKE_DEFAULT_URL = "/v11/likes";
    private final LikeService likeService;
    private final LikeMapper mapper;

    public LikeController(LikeService likeService, LikeMapper mapper) {
        this.likeService = likeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity likeQuestion(@Valid @RequestBody LikePostDto likePostDto){
        Like like = likeService.likeQuestion(likePostDto.getMemberId(), likePostDto.getQuestionId());


        if (like == null) {
            return ResponseEntity.ok().build();
        }
        URI location = UriCreator.createUri(LIKE_DEFAULT_URL, like.getLikeId());

        return ResponseEntity.created(location).build();
    }
}
