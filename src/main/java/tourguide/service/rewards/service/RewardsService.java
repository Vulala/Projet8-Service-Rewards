package tourguide.service.rewards.service;

import java.util.List;

import org.springframework.stereotype.Service;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import tourguide.service.rewards.user.User;
import tourguide.service.rewards.user.UserReward;

/**
 * Service used to calculate the rewards of the user. <br>
 */
@Service
public class RewardsService {

	private final GpsUtil gpsUtil;
	private final AttractionUtility attractionUtility;

	public RewardsService(GpsUtil gpsUtil, AttractionUtility attractionUtility) {
		this.gpsUtil = gpsUtil;
		this.attractionUtility = attractionUtility;
	}

	/**
	 * calculateRewards is used to add an {@link UserReward} to the user who visited
	 * a location. <br>
	 * 
	 * @param user
	 */
	public void calculateRewards(User user) {
		List<VisitedLocation> userLocations = user.getVisitedLocations();
		List<Attraction> attractions = gpsUtil.getAttractions();

		for (VisitedLocation visitedLocation : userLocations) {
			for (Attraction attraction : attractions) {
				if (user.getUserRewards().parallelStream()
						.filter(r -> r.getAttraction().attractionName.equals(attraction.attractionName)).count() == 0
						&& attractionUtility.nearAttraction(visitedLocation, attraction)) {
					user.addUserReward(new UserReward(visitedLocation, attraction,
							attractionUtility.getRewardPoints(attraction, user)));

				}
			}
		}
	}

}
