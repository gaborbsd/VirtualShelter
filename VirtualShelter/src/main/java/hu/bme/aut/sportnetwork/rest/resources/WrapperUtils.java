package hu.bme.aut.sportnetwork.rest.resources;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.auth.AuthOperations;
import hu.bme.aut.sportnetwork.entity.Comment;
import hu.bme.aut.sportnetwork.entity.User;

@Component
public class WrapperUtils {

	private static AuthOperations authOperation;

	@Autowired
	private AuthOperations authOperation0;

	@PostConstruct
	public void initStatic() {
		authOperation = this.authOperation0;
	}

	public static void addMember(List<UserLinkWrapper> members, User m) {
		members.add(toWrapper(m));
	}

	public static void addComment(List<CommentWrapper> comments, Comment c) {
		UserLinkWrapper u = new UserLinkWrapper();
		u.setId(c.getOwner().getId());
		u.setName(c.getOwner().getName());
		CommentWrapper w = new CommentWrapper();
		w.setMessage(c.getMessage());
		w.setId(c.getId());
		w.setMine(c.getOwner().getName().equals(authOperation.getLoggedInUserName()));
		w.setWriter(u);
		// w.setDate(c.getDateOfComment());
		comments.add(w);
	}

	public static UserLinkWrapper toWrapper(User u) {
		UserLinkWrapper wrapper = new UserLinkWrapper();
		wrapper.setId(u.getId());
		wrapper.setName(u.getName());
		return wrapper;
	}
	
	public static List<UserLinkWrapper> toWrapperListCloseCurrent(List<User> us) {
		List<UserLinkWrapper> ret = new ArrayList<>();
		for (User u : us) {
			if (!u.getName().equals(authOperation.getLoggedInUserName())) {
				ret.add(toWrapper(u));
			}
		}
		return ret;
	}

}
