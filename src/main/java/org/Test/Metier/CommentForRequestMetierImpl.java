package org.Test.Metier;

import java.util.List;
import org.Test.Dao.CommentForRequestRepository;
import org.Test.Entities.CommentForRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentForRequestMetierImpl implements CommentForRequestMetier {

	@Autowired
	private CommentForRequestRepository commentForRequestRepository;

	@Override
	public CommentForRequest saveCommentForRequest(CommentForRequest co) {
		return commentForRequestRepository.save(co);
	}

	@Override
	public List<CommentForRequest> listCommentForRequest() {
		return commentForRequestRepository.findAll();
	}

}
