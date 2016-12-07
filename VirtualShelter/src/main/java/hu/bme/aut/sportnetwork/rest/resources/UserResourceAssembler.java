package hu.bme.aut.sportnetwork.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import hu.bme.aut.sportnetwork.entity.Address;
import hu.bme.aut.sportnetwork.entity.FriendStatus;
import hu.bme.aut.sportnetwork.entity.Rating;
import hu.bme.aut.sportnetwork.entity.User;
import hu.bme.aut.sportnetwork.rest.UserController;

@Component
public class UserResourceAssembler extends 
		ResourceAssemblerSupport<User, UserResource>{

	public UserResourceAssembler() {
		super(UserController.class, UserResource.class);
	}

	@Override
	public UserResource toResource(User user) {
		UserResource resource = createResourceWithId(user.getId(), user);
		return resource;
	}
	
	@Override
	protected UserResource instantiateResource(User entity) {
		UserResource resource = new UserResource();

		resource.setAge(entity.getAge());

		resource.setAddress(toAddress(entity));
		resource.setDeleted(entity.getDeleted());
		resource.setEmail(entity.getEmail());

		resource.setInterest(toInterest(entity.getRatings()));

		resource.setIntroduction(entity.getIntroduction());
		resource.setName(entity.getName());

		if (entity.getPhoneNumber() != null && !entity.getPhoneNumber().isEmpty()) {
			resource.setPhoneNumber(entity.getPhoneNumber());
		}

		resource.setStatus(toStatus(entity.getFriendStatus()));

		if (entity.getHasWarning()) {
			resource.setWarning(entity.getWarningMessage());
		}

		return resource;
	}

	private Address toAddress(User entity) {
		Address a = new Address();
		if (entity.getCountry() != null) {
			a.setCountry(entity.getCountry());
		}
		a.setAddress(entity.getAddress());
		a.setCity(entity.getCity());
		return a;
	}

	private List<InterestWrapper> toInterest(List<Rating> rl) {
		List<InterestWrapper> ret = new ArrayList<>();

		for (Rating r : rl) {
			InterestWrapper w = new InterestWrapper();
			w.setSport(r.getSport().toString());

			double sum = r.getSumValue();
			double number = r.getRateNumbers();

			w.setRate(r.getRateNumbers() == 0 ? "NOT RATED" : String.valueOf(sum / number));

			ret.add(w);
		}

		return ret;
	}

	private int toStatus(FriendStatus fs) {
		switch (fs) {
		case SELF:
			return 1;
		case FRIEND:
			return 2;
		case REQUEST_SENT:
			return 3;
		case REQUEST_RECEIVED:
			return 4;
		case NOT_FRIEND:
			return 5;
		case DECLINED:
			return 6;

		default:
			return 0;
		}
	}

}
