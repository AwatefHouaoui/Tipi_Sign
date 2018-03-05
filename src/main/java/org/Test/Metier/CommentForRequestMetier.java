package org.Test.Metier;

import java.util.List;
import org.Test.Entities.CommentForRequest;

public interface CommentForRequestMetier {
	public CommentForRequest saveCommentForRequest(CommentForRequest co);

	public List<CommentForRequest> listCommentForRequest();

}
