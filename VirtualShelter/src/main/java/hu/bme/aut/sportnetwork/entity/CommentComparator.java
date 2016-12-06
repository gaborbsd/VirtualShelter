package hu.bme.aut.sportnetwork.entity;

import java.util.Comparator;

public class CommentComparator implements Comparator<Comment> {

	@Override
	public int compare(Comment o1, Comment o2) {
		return o1.getDate().compareTo(o2.getDate());
	}

}
