package tourguide.service.rewards;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsoniter.output.JsonStream;

import tourguide.service.rewards.helper.InternalTestHelper;

/**
 * RewardsController is the main controller of this service. <br>
 * The purpose is to calculate the rewards of the user. <br>
 */
@RestController
public class RewardsController {

	@Autowired
	private InternalTestHelper internalTestHelper;

	/**
	 * Get : /
	 * 
	 * @return "The list of endpoints availables"
	 */
	@GetMapping("/")
	public String index() {
		return "Endpoints availables with this service : /getRewards?userName=internalUser0";
	}

	/**
	 * GET mapping to get the user's rewards. <br>
	 * URI : http://localhost:8082/getRewards?userName=internalUser0
	 * 
	 * @param userName
	 * @return json
	 */
	@GetMapping("/getRewards")
	public String getRewards(@RequestParam("userName") String userName) {
		internalTestHelper.initializeTheInternalUsers();
		return JsonStream.serialize(internalTestHelper.getUser(userName).getUserRewards());
	}
}
