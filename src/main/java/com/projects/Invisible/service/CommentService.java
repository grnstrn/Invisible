package com.projects.Invisible.service;

import com.projects.Invisible.domain.Comment;
import com.projects.Invisible.domain.Message;
import com.projects.Invisible.domain.User;
import com.projects.Invisible.domain.Views;
import com.projects.Invisible.dto.EventType;
import com.projects.Invisible.dto.ObjectType;
import com.projects.Invisible.repo.CommentRepo;
import com.projects.Invisible.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.function.BiConsumer;
@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final BiConsumer<EventType, Comment> wsSender;

    @Autowired
    public CommentService(CommentRepo commentRepo, WsSender wsSender) {
        this.commentRepo = commentRepo;
        this.wsSender = wsSender.getSender(ObjectType.COMMENT, Views.FullComment.class);
    }

    public Comment create(Comment comment, User user) {
        comment.setAuthor(user);
        Comment commentFromDb = commentRepo.save(comment);

        wsSender.accept(EventType.CREATE, commentFromDb);

        return commentFromDb;
    }
}