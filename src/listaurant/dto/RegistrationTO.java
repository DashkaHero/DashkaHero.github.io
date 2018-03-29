package listaurant.dto;

import listaurant.model.Member;

public class RegistrationTO {

	private Member member;
	
	public RegistrationTO() {
		
	}
	
	public RegistrationTO(Member member) {
		this.member = member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Member getMember() {
		return member;
	}
	
	
}
