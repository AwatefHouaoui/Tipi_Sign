package org.test.metier;

import java.util.List;
import org.test.entities.CommentForRequest;

public interface CommentForRequestMetier {
	public CommentForRequest saveCommentForRequest(CommentForRequest co);

	public List<CommentForRequest> listCommentForRequest();

}
