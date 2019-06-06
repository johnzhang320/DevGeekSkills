package com.loan.agent.calculators.vo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.Date;

import com.loan.agent.mvc.utils.Utility;

public class UserReviewQuoteVo implements Serializable  {
	 private static final long serialVersionUID = 1L;
	
	private ResultSet rs;
    private Integer quote_id;
    private Integer user_id;
    private Integer agent_id;
    private String loan_type;
    private String loan_amount;
    private String purchase_price;
    private String property_county;
    private String property_state;
    private String purchase_stage;
    private String purchase_date;
    private String property_address;
    private String property_city;
    private String property_zip_code;
    private String property_type;
    private String occupancy_status;
    private String note;
    private String modified_date;
    private String created_date;
    private String estimate_home_value;
    private String refinance_cash_out;
    private String first_loan_balance;
    private String first_loan_rate;
    private String first_loan_term;
    private String first_date;
    private String second_loan_balance;
    private String second_loan_rate;
    private String second_loan_term;   
    private String first_name;
    private String last_name;
    private String email_address;
    private String state;
    private String none_rental_income;
    private String rental_income;
    private String credt_score;
    private String phone_no;
    private String afirst_name;
    private String alast_name;
    private String aemail_address;
    private String awork_phone;
    private CompareLoanVo current_loan;
    private CompareLoanVo reply_loan1;
    private CompareLoanVo reply_loan2;
    private CompareLoanVo reply_loan3;
    private CompareLoanVo reply_loan4;
    private String process_status;
    private String monthly_debt;
    
    
    
	public String getAwork_phone() {
		return awork_phone;
	}
	public void setAwork_phone(String awork_phone) {
		this.awork_phone = awork_phone;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getAgent_id() {
		return agent_id;
	}
	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}
	public String getCreated_date() {
		return created_date;
	}
	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}
	 
	 
	public String getFirst_date() {
		return first_date;
	}
	public void setFirst_date(String first_date) {
		this.first_date = first_date;
	}
	public String getMonthly_debt() {
		return monthly_debt;
	}
	public void setMonthly_debt(String monthly_debt) {
		this.monthly_debt = monthly_debt;
	}
	public String getProcess_status() {
		return process_status;
	}
	public void setProcess_status(String process_status) {
		this.process_status = process_status;
	}
	public String getProperty_state() {
		return property_state;
	}
	public void setProperty_state(String property_state) {
		this.property_state = property_state;
	}
	public Integer getQuote_id() {
		return quote_id;
	}
	public void setQuote_id(Integer quote_id) {
		this.quote_id = quote_id;
	}
	public String getLoan_type() {
		return loan_type;
	}
	public void setLoan_type(String loan_type) {
		this.loan_type = loan_type;
	}
	public String getLoan_amount() {
		return loan_amount;
	}
	public void setLoan_amount(String loan_amount) {
		this.loan_amount = loan_amount;
	}
	public String getPurchase_price() {
		return purchase_price;
	}
	public void setPurchase_price(String purchase_price) {
		this.purchase_price = purchase_price;
	}
	public String getProperty_county() {
		return property_county;
	}
	public void setProperty_county(String property_county) {
		this.property_county = property_county;
	}
	public String getPurchase_stage() {
		return purchase_stage;
	}
	public void setPurchase_stage(String purchase_stage) {
		this.purchase_stage = purchase_stage;
	}
	public String getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(String purchase_date) {
		this.purchase_date = purchase_date;
	}
	public String getProperty_address() {
		return property_address;
	}
	public void setProperty_address(String property_address) {
		this.property_address = property_address;
	}
	public String getProperty_city() {
		return property_city;
	}
	public void setProperty_city(String property_city) {
		this.property_city = property_city;
	}
	public String getProperty_zip_code() {
		return property_zip_code;
	}
	public void setProperty_zip_code(String property_zip_code) {
		this.property_zip_code = property_zip_code;
	}
	public String getProperty_type() {
		return property_type;
	}
	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}
	public String getOccupancy_status() {
		return occupancy_status;
	}
	public void setOccupancy_status(String occupancy_status) {
		this.occupancy_status = occupancy_status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getModified_date() {
		return modified_date;
	}
	public void setModified_date(String modified_date) {
		this.modified_date = modified_date;
	}
	public String getEstimate_home_value() {
		return estimate_home_value;
	}
	public void setEstimate_home_value(String estimate_home_value) {
		this.estimate_home_value = estimate_home_value;
	}
	public String getRefinance_cash_out() {
		return refinance_cash_out;
	}
	public void setRefinance_cash_out(String refinance_cash_out) {
		this.refinance_cash_out = refinance_cash_out;
	}
	public String getFirst_loan_balance() {
		return first_loan_balance;
	}
	public void setFirst_loan_balance(String first_loan_balance) {
		this.first_loan_balance = first_loan_balance;
	}
	public String getFirst_loan_rate() {
		return first_loan_rate;
	}
	public void setFirst_loan_rate(String first_loan_rate) {
		this.first_loan_rate = first_loan_rate;
	}
	public String getFirst_loan_term() {
		return first_loan_term;
	}
	public void setFirst_loan_term(String first_loan_term) {
		this.first_loan_term = first_loan_term;
	}
	public String getSecond_loan_balance() {
		return second_loan_balance;
	}
	public void setSecond_loan_balance(String second_loan_balance) {
		this.second_loan_balance = second_loan_balance;
	}
	public String getSecond_loan_rate() {
		return second_loan_rate;
	}
	public void setSecond_loan_rate(String second_loan_rate) {
		this.second_loan_rate = second_loan_rate;
	}
	public String getSecond_loan_term() {
		return second_loan_term;
	}
	public void setSecond_loan_term(String second_loan_term) {
		this.second_loan_term = second_loan_term;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_address() {
		return email_address;
	}
	public void setEmail_address(String email_address) {
		this.email_address = email_address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getNone_rental_income() {
		return none_rental_income;
	}
	public void setNone_rental_income(String none_rental_income) {
		this.none_rental_income = none_rental_income;
	}
	public String getRental_income() {
		return rental_income;
	}
	public void setRental_income(String rental_income) {
		this.rental_income = rental_income;
	}
	public String getCredt_score() {
		return credt_score;
	}
	public void setCredt_score(String credt_score) {
		this.credt_score = credt_score;
	}
	public String getPhone_no() {
		return phone_no;
	}
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}
	public String getAfirst_name() {
		return afirst_name;
	}
	public void setAfirst_name(String afirst_name) {
		this.afirst_name = afirst_name;
	}
	public String getAlast_name() {
		return alast_name;
	}
	public void setAlast_name(String alast_name) {
		this.alast_name = alast_name;
	}
	public String getAemail_address() {
		return aemail_address;
	}
	public void setAemail_address(String aemail_address) {
		this.aemail_address = aemail_address;
	}
	public CompareLoanVo getCurrent_loan() {
		return current_loan;
	}
	public void setCurrent_loan(CompareLoanVo current_loan) {
		this.current_loan = current_loan;
	}
	public CompareLoanVo getReply_loan1() {
		return reply_loan1;
	}
	public void setReply_loan1(CompareLoanVo reply_loan1) {
		this.reply_loan1 = reply_loan1;
	}
	public CompareLoanVo getReply_loan2() {
		return reply_loan2;
	}
	public void setReply_loan2(CompareLoanVo reply_loan2) {
		this.reply_loan2 = reply_loan2;
	}
	public CompareLoanVo getReply_loan3() {
		return reply_loan3;
	}
	public void setReply_loan3(CompareLoanVo reply_loan3) {
		this.reply_loan3 = reply_loan3;
	}
	public CompareLoanVo getReply_loan4() {
		return reply_loan4;
	}
	public void setReply_loan4(CompareLoanVo reply_loan4) {
		this.reply_loan4 = reply_loan4;
	}
 
    

}
