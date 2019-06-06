package com.loan.agent.services;

import com.loan.agent.calculators.vo.AgentReviewQuoteVo;
import com.loan.agent.calculators.vo.UserReviewQuoteVo;

import java.util.List;

public interface UserReviewService {
	public List<UserReviewQuoteVo> getUserQuoteInquire(Integer userId);
	 
	 
    public Integer findUserIdByEmail(String email);	 
	public Integer findUserIdByEmailPassword(String email, String password);

}
