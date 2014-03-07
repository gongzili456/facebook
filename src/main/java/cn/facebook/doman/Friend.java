package cn.facebook.doman;

public class Friend {
	private Long userOne;
	private Long userTwo;

	public Long getUserOne() {
		return userOne;
	}

	public void setUserOne(Long userOne) {
		this.userOne = userOne;
	}

	public Long getUserTwo() {
		return userTwo;
	}

	public void setUserTwo(Long userTwo) {
		this.userTwo = userTwo;
	}

	@Override
	public String toString() {
		return "Friend [userOne=" + userOne + ", userTwo=" + userTwo + "]";
	}

}
