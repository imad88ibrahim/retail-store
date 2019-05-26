package com.imad.retailstore.dto;

import com.imad.retailstore.constants.UserType;

public class UserDTO extends BaseDTO {
	private static final long serialVersionUID = 1L;

	private Long userId;
	private UserType userType;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", userType=" + userType + "]";
	}

}
