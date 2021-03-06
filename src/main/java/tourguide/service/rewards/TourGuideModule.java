package tourguide.service.rewards;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gpsUtil.GpsUtil;
import rewardCentral.RewardCentral;

/**
 * Configuration class used to instanciate the different modules used by the
 * application. <br>
 */
@Configuration
public class TourGuideModule {

	/**
	 * GpsUtil.jar is used to get a list of all the attactions and the user's
	 * location. <br>
	 */
	@Bean
	public GpsUtil getGpsUtil() {
		return new GpsUtil();
	}

	/**
	 * RewardCentral.jar is mainly used to retrieve the points rewarded by each
	 * attractions. <br>
	 */
	@Bean
	public RewardCentral getRewardCentral() {
		return new RewardCentral();
	}

}
