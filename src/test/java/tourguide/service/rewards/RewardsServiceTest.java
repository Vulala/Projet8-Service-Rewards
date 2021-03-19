package tourguide.service.rewards;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import gpsUtil.GpsUtil;
import gpsUtil.location.Attraction;
import gpsUtil.location.VisitedLocation;
import rewardCentral.RewardCentral;
import tourguide.service.rewards.service.AttractionUtility;
import tourguide.service.rewards.service.RewardsService;
import tourguide.service.rewards.user.User;
import tourguide.service.rewards.user.UserReward;

public class RewardsServiceTest {

	private GpsUtil gpsUtil = new GpsUtil();
	private RewardsService rewardsService = new RewardsService(gpsUtil, new AttractionUtility(new RewardCentral()));
	private Attraction attraction = gpsUtil.getAttractions().get(0);

	@BeforeEach
	void setup() {
		gpsUtil = new GpsUtil();
		rewardsService = new RewardsService(new GpsUtil(), new AttractionUtility(new RewardCentral()));
	}

	@DisplayName("Calculate and add a reward to a user from a VisitedLocation")
	@Test
	public void givenAUserVisitedALocation_whenCalculateRewards_thenItAddAUserRewardToTheUser() {
		User user = new User(UUID.randomUUID(), "jon", "000", "jon@tourGuide.com");
		user.addToVisitedLocations(new VisitedLocation(user.getUserId(), attraction, new Date()));

		rewardsService.calculateRewards(user);
		List<UserReward> userRewards = user.getUserRewards();
		assertEquals(userRewards.size(), 1);
	}

}
