package service.Comment;

import model.Comment;

import java.util.List;

/**
 * Created by LulzimG on 29/12/16.
 */
public interface CommentService {
    List<Comment> getProjectComments(String projectId);

    Comment getComment(String commentId);

    String addComment(Comment comment);

    String updateComment(Comment comment);

    String deleteComment(Comment comment);

    void insertBatch(List<Comment> comments, String id);
}
