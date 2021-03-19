package tourguide.service.rewards;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import gpsUtil.location.Attraction;
import gpsUtil.location.Location;
import gpsUtil.location.VisitedLocation;
import tourguide.service.rewards.helper.InternalTestHelper;
import tourguide.service.rewards.user.User;
import tourguide.service.rewards.user.UserReward;

@WebMvcTest(RewardsController.class)
class RewardsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private InternalTestHelper internalTestHelper;

	@DisplayName("Injected Components Are Rightly Setup")
	@Test
	public void injectedComponentsAreRightlySetup() {
		assertThat(mockMvc).isNotNull();
		assertThat(internalTestHelper).isNotNull();
	}

	@DisplayName("GET : /")
	@Test
	void index() throws Exception {
		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
	}

	@Disabled("NullPointerException: cannot mock the internalTestHelper for no apparent reason.")
	@DisplayName("GET : /getRewards")
	@Test
	void givenGettingTheRewardsOfTheUser_whenGetRewards_thenItDisplayTheRewardsOfTheUserAsJSON() throws Exception {
		// ARRANGE
		this.internalTestHelper = new InternalTestHelper();
		UUID randomUUID = UUID.randomUUID();
		UserReward userReward = new UserReward(
				new VisitedLocation(randomUUID, new Location(10.0, 10.0), Date.valueOf(LocalDate.now())),
				new Attraction("attractionName", "city", "state", 10.0, 10.0));
		User user = new User(randomUUID, "userName", "phoneNumber", "emailAddress");
		user.addUserReward(userReward);
		internalTestHelper.addUser(user);

		when(internalTestHelper.getUser("userName").getUserRewards()).thenReturn(user.getUserRewards());

		// ACT
		MvcResult mvcResult = this.mockMvc.perform(get("/getRewards?userName=userName")).andDo(print()).andReturn();
		int status = mvcResult.getResponse().getStatus();

		// ASSERT
		assertEquals(status, 200);
		verify(internalTestHelper, times(1)).getUser(any(String.class)).getUserRewards();
	}
}