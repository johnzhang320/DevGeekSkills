package com.loan.agent.mvc.controller;

/**
 * MultiActionController 
 * @author johnzhang
 *
 */
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.log4j.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.loan.agent.calculators.AmortizationLine;
import com.loan.agent.calculators.CleanAmortList;
import com.loan.agent.calculators.LoanBuilder;
import com.loan.agent.calculators.LoanDirector;
import com.loan.agent.calculators.LoanParam;
import com.loan.agent.calculators.LoanProduct;
import com.loan.agent.calculators.PurchaseCurrentLoanBuilder;
import com.loan.agent.calculators.RefinanceCurrentLoanBuilder;
import com.loan.agent.calculators.RefinanceNewLoanBuilder;

import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.loan.agent.calculators.Calculators;
import com.loan.agent.calculators.vo.AffordableLineVo;
import com.loan.agent.calculators.vo.AmortParamVo;
import com.loan.agent.calculators.vo.AmortizationLineVo;
import com.loan.agent.calculators.vo.CompareLoanVo;
import com.loan.agent.calculators.vo.TodayInterestRateVo;

import com.loan.agent.calculators.vo.SummaryTotalsVo;

import com.loan.agent.calculators.vo.YearlyAmortizationVo;
import com.loan.agent.mvc.utils.JasperReportUtils;
import com.loan.agent.mvc.utils.ProcessDownloadFile;
import com.loan.agent.mvc.utils.SysPath;
import com.loan.agent.mvc.utils.Utility;
import com.loan.agent.services.Constant;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.loan.agent.mvc.utils.ProcessUploadFile;
import com.loan.agent.mvc.utils.ProcessDownloadFile;

public class CalculatorController extends MultiActionController {

	private static final String classesPath = SysPath.getClassesPath().replace(
			"%20", " ");

	private static final String pdfResultPath = SysPath.getInstance()
			.getTempPath().replace("%20", " ");

	private static final String MONTHLY_JASPER_BUILDER_FILE = classesPath
			+ "MonthlyAmortizationJSReport.jasper";

	private static final String MONTHLY_BASE_RESULT_FILE_NAME = pdfResultPath
			+ "MonthlyAmortizationJSReport";

	private static final String YEARLY_JASPER_BUILDER_FILE = classesPath
			+ "YearlyAmortizationJSReport.jasper";

	private static final String YEARLY_BASE_RESULT_FILE_NAME = pdfResultPath
			+ "YearlyAmortizationJSReport";

	private static String RESOURCE_URL = "http://www.corralesluxuryandreo.com/webservice/mortgageRates.asmx/getMortgageRates";

	static Logger LOG = Logger.getLogger(CalculatorController.class);

	public ModelAndView monthlyAmortizationHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		return new ModelAndView("MonthlyAmortization");
	}

	public ModelAndView yearlyAmortizationHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		return new ModelAndView("YearlyAmortization");
	}

	public ModelAndView indexHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		return new ModelAndView("HomePage");
	}

	public ModelAndView calculateMonthlyExpenseHandler(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Utility.setParamAgentId(request);
		return new ModelAndView("CalculateMonthlyExpense");
	}

	public ModelAndView calculateMonthlyExpenseDialogHandler(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Utility.setParamAgentId(request);
		return new ModelAndView("CalculateMonthlyExpenseDialog");
	}

	public ModelAndView affordableHomePriceHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		return new ModelAndView("AffordableHomePrice");
	}

	public ModelAndView affordableDialogHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		return new ModelAndView("AffordableDialog");
	}

	/**
	 * Fetch dynamic interest rate from web service
	 * http://www.corralesluxuryandreo
	 * .com/webservice/mortgageRates.asmx/getMortgageRates
	 * 
	 * @return
	 */
	public ModelAndView fetchDynamicRateHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		PrintWriter out = Utility.SetPostResponseContent(response);
		HttpClient client = new HttpClient();
		String xmlResponse = null;
		// GetMethod get = new GetMethod(RESOURCE_URL+"/"+path);
		GetMethod get = new GetMethod(RESOURCE_URL);
		get.setRequestHeader("Accept", "text/xml");
		System.out.println("get=" + get);
		try {
			int httpStatus = client.executeMethod(get);
			System.out.println("httpStatus=" + httpStatus);
			if (HttpStatus.SC_OK == httpStatus) {
				xmlResponse = get.getResponseBodyAsString();
				System.out.println("Xml Response: " + xmlResponse);
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				Document doc = db.parse(new ByteArrayInputStream(xmlResponse
						.getBytes()));

				Set<String> setMap = new HashSet<String>();
				List<TodayInterestRateVo> list = new ArrayList<TodayInterestRateVo>();
				NodeList nRate = doc.getElementsByTagName("latestRate");
				NodeList nTerm = doc.getElementsByTagName("symbol");
				NodeList nPostedTime = doc.getElementsByTagName("postedDate");
				for (int i = 0; i < nRate.getLength(); i++) {
					Node n_postedTime = nPostedTime.item(i);
					Node n_term = nTerm.item(i);
					Node n_rate = nRate.item(i);
					System.out.println(n_rate.getFirstChild().getNodeValue());
					String postedTime = n_postedTime.getFirstChild()
							.getNodeValue();
					String term = n_term.getFirstChild().getNodeValue();
					String rate = n_rate.getFirstChild().getNodeValue();

					String renderRate = Utility.renderRate(Double
							.parseDouble(rate) * 100.0);
					String renderPostedTime = postedTime.substring(0, 19)
							.replace('T', ' ');
					// System.out.println(term+":"+renderRate+",postedTime:"+renderPostedTime);
					if (!setMap.contains(term)) {
						TodayInterestRateVo vo = new TodayInterestRateVo();
						vo.setIntRate(renderRate);
						vo.setPostedTime(renderPostedTime);
						vo.setTermName(term);
						list.add(vo);
					}
					setMap.add(term);
				}
				setMap.clear();
				Gson gson = new Gson();
				JsonObject myObj = new JsonObject();
				Integer counter = 1;
				for (TodayInterestRateVo v : list) {
					System.out.println(v.getTermName() + ":" + v.getIntRate()
							+ ",postedTime:" + v.getPostedTime());

					JsonElement curElement = gson.toJsonTree(v);
					myObj.add("rate" + counter.toString(), curElement);
					counter++;
				}

				out.println(myObj.toString());
				out.close();
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// clean up
			get.releaseConnection();
		}
		return null;

	}

	public ModelAndView getRemainBalanceHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter out = Utility.SetPostResponseContent(response);
		Utility.setParamAgentId(request);

		Utility.setRequest(request, response);
		Double loan_amt = Utility.getDoubleParameter("loan_amt");
		Double term = Utility.getDoubleParameter("term");
		Double int_rate = Utility.getDoubleParameter("int_rate");
		Integer first_mm = Utility.getMonth(request
				.getParameter(Constant.FIRST_DATE)); // YPMT_mm));
		Integer first_yyyy = Utility.getYear(request
				.getParameter(Constant.FIRST_DATE)); // YPMT_yyyy));
		Integer paid_num = Calculators.uptoday_months(first_yyyy, first_mm);
		LOG.info("Loan Amt=" + loan_amt + ",term=" + term + ",int_rate="
				+ int_rate + ",first_mm =" + first_mm + ",first_yyyy="
				+ first_yyyy);
		Double remain_balance = Calculators.uptonow_remain_balance(loan_amt,
				paid_num, term, int_rate);

		Gson gson = new Gson();
		JsonObject myObj = new JsonObject();

		CompareLoanVo curLoan = new CompareLoanVo();

		curLoan.setUnpainLoanAmt(remain_balance);
		JsonElement curLoanElement = gson.toJsonTree(curLoan);
		myObj.add("curLoan", curLoanElement);

		out.println(myObj.toString());
		out.close();

		LOG.info("Remain Balance=" + remain_balance);
		return null;
	}

	public ModelAndView compareRefinanceLoansHandler(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// set default date for loan start date half year before today
		Utility.setParamAgentId(request);
		Calendar date = Calendar.getInstance();
		Calendar cldr;
		SimpleDateFormat dateformatter = new SimpleDateFormat("MM-dd-yyyy");

		cldr = (Calendar) date.clone();
		cldr.add(Calendar.DAY_OF_YEAR, -(365 / 2));
		String startDate = dateformatter.format(cldr.getTime());
		LOG.info("Before Half years it was: " + startDate);
		ModelAndView mode = new ModelAndView("CompareRefinanceLoans");
		mode.addObject("loanInitialDate", startDate);

		LOG.info("CompareRefinanceLoans");
		return mode;
	}
	public ModelAndView compareRefinanceLoansDialogHandler(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// set default date for loan start date half year before today
		Utility.setParamAgentId(request);
		Calendar date = Calendar.getInstance();
		Calendar cldr;
		SimpleDateFormat dateformatter = new SimpleDateFormat("MM-dd-yyyy");

		cldr = (Calendar) date.clone();
		cldr.add(Calendar.DAY_OF_YEAR, -(365 / 2));
		String startDate = dateformatter.format(cldr.getTime());
		LOG.info("Before Half years it was: " + startDate);
		ModelAndView mode = new ModelAndView("CompareRefinanceLoansDialog");
		mode.addObject("loanInitialDate", startDate);

		LOG.info("CompareRefinanceLoansDialog");
		return mode;
	}

	public ModelAndView compareRefiOnLineHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		PrintWriter out = Utility.SetPostResponseContent(response);

		// using ServletRequestUtils to obtain form data which never use binding
		// command class
		Utility.setRequest(request, response);
		Double loan_amt = Utility.getDoubleParameter("loan_amt");
		Double[] new_loan_amt = new Double[4];
		new_loan_amt[0] = Utility.getDoubleParameter("new_loan_amt1");
		new_loan_amt[1] = Utility.getDoubleParameter("new_loan_amt2");
		new_loan_amt[2] = Utility.getDoubleParameter("new_loan_amt3");

		Double term = Utility.getDoubleParameter("term");
		Double[] new_term = new Double[4];
		new_term[0] = Utility.getDoubleParameter("term1");
		new_term[1] = Utility.getDoubleParameter("term2");
		new_term[2] = Utility.getDoubleParameter("term3");

		Integer int_term = Utility.getIntegerParameter("term");
		Integer[] new_int_term = new Integer[4];
		new_int_term[0] = Utility.getIntegerParameter("term1");
		new_int_term[1] = Utility.getIntegerParameter("term2");
		new_int_term[2] = Utility.getIntegerParameter("term3");

		Double int_rate = Utility.getDoubleParameter("int_rate");
		Double[] new_int_rate = new Double[4];
		new_int_rate[0] = Utility.getDoubleParameter("int_rate1");
		new_int_rate[1] = Utility.getDoubleParameter("int_rate2");
		new_int_rate[2] = Utility.getDoubleParameter("int_rate3");

		Double[] new_closing_cost = new Double[4];
		new_closing_cost[0] = Utility.getDoubleParameter("closing_cost1");
		new_closing_cost[1] = Utility.getDoubleParameter("closing_cost2");
		new_closing_cost[2] = Utility.getDoubleParameter("closing_cost3");

		Double[] new_point = new Double[4];
		new_point[0] = Utility.getDoubleParameter("point1");
		new_point[1] = Utility.getDoubleParameter("point2");
		new_point[2] = Utility.getDoubleParameter("point3");

		Integer first_mm = Utility.getMonth(request
				.getParameter(Constant.FIRST_DATE)); // YPMT_mm));
		Integer first_yyyy = Utility.getYear(request
				.getParameter(Constant.FIRST_DATE)); // YPMT_yyyy));

		Gson gson = new Gson();
		JsonObject myObj = new JsonObject();

		LoanParam curPM = new LoanParam(loan_amt, int_rate, term, int_term,
				0.0, // closing_fee
				0.0, // point
				first_mm, first_yyyy, null, // LoanProduct currentLoan
				null // LoanParam currentParam
		);
		LoanBuilder curBuilder = new RefinanceCurrentLoanBuilder(curPM);
		LoanProduct curLoanProd = LoanDirector.getInstance().buildProduct(
				curBuilder);
		JsonElement curLoanElement = gson.toJsonTree(curLoanProd
				.getCompareLoanVo());
		myObj.add("curLoan", curLoanElement);

		LoanParam pm = null;
		for (int i = 0; i < Constant.COMP_LOAN_NUM - 1; i++) {
			pm = new LoanParam(new_loan_amt[i], new_int_rate[i], new_term[i],
					new_int_term[i], new_closing_cost[i], // closing_fee
					new_point[i], // point
					first_mm, first_yyyy, curLoanProd, // LoanProduct
														// currentLoan
					curPM // LoanParam currentParam
			);
			LoanBuilder newBuilder = new RefinanceNewLoanBuilder(pm);
			LoanProduct newLoanProd = LoanDirector.getInstance().buildProduct(
					newBuilder);
			JsonElement newLoanElement = gson.toJsonTree(newLoanProd
					.getCompareLoanVo());
			String loanName = "Loan" + (new Integer(i + 1)).toString();
			myObj.add(loanName, newLoanElement);

		}
		myObj.addProperty("remain_balance",
				Utility.renderDollar(curLoanProd.getRemain_balance()));

		out.println(myObj.toString());
		out.close();

		return null;

	}

	public ModelAndView comparePurchaseLoansHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		LOG.info("ComparePurchaseLoans");
		return new ModelAndView("ComparePurchaseLoans");
	}
	public ModelAndView comparePurchaseLoansDialogHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		LOG.info("ComparePurchaseLoansDialog");
		return new ModelAndView("ComparePurchaseLoansDialog");
	}

	public ModelAndView comparePurchOnLineHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter out = Utility.SetPostResponseContent(response);

		Utility.setRequest(request, response);
		Double loan_amt = Utility.getDoubleParameter("loan_amt");

		Double term = Utility.getDoubleParameter("term");
		Double term1 = Utility.getDoubleParameter("term1");
		Double term2 = Utility.getDoubleParameter("term2");
		Double term3 = Utility.getDoubleParameter("term3");

		Integer int_term = Utility.getIntegerParameter("term");
		Integer int_term1 = Utility.getIntegerParameter("term1");
		Integer int_term2 = Utility.getIntegerParameter("term2");
		Integer int_term3 = Utility.getIntegerParameter("term3");

		Double int_rate = Utility.getDoubleParameter("int_rate");
		Double int_rate1 = Utility.getDoubleParameter("int_rate1");
		Double int_rate2 = Utility.getDoubleParameter("int_rate2");
		Double int_rate3 = Utility.getDoubleParameter("int_rate3");

		Double closing_cost1 = Utility.getDoubleParameter("closing_cost1");
		Double closing_cost2 = Utility.getDoubleParameter("closing_cost2");
		Double closing_cost3 = Utility.getDoubleParameter("closing_cost3");

		Double point1 = Utility.getDoubleParameter("point1");
		Double point2 = Utility.getDoubleParameter("point2");
		Double point3 = Utility.getDoubleParameter("point3");

		/**
		 * set 2 month after today as purchase pay date (one is closing month,
		 * another is fund month)
		 */
		Calendar date = Calendar.getInstance();
		Calendar cldr;
		SimpleDateFormat dateformatter = new SimpleDateFormat("MM-dd-yyyy");

		cldr = (Calendar) date.clone();
		cldr.add(Calendar.MONTH, +2);

		String firstDate = dateformatter.format(cldr.getTime());

		Integer first_mm = Utility.getMonth(firstDate); // YPMT_mm));
		Integer first_yyyy = Utility.getYear(firstDate); // YPMT_yyyy));

		Integer paid_num = Calculators.uptoday_months(first_yyyy, first_mm);
		Integer remain_num = int_term - paid_num;
		LOG.info("After 2 month  it was: " + firstDate + ",first_mm="
				+ first_mm + ",first_yyyy=" + first_yyyy + ",paid_num="
				+ paid_num);
		Double remain_balance = Calculators.uptonow_remain_balance(loan_amt, 1,
				term, int_rate);

		Double PMT = Calculators.monthly_payment(loan_amt, int_rate, term);
		Double PMT1 = Calculators.monthly_payment(loan_amt, int_rate1, term1);
		Double PMT2 = Calculators.monthly_payment(loan_amt, int_rate2, term2);
		Double PMT3 = Calculators.monthly_payment(loan_amt, int_rate3, term3);
		Double monthly_saving1 = null;
		Double monthly_saving2 = null;
		Double monthly_saving3 = null;

		if (PMT1 != null)
			monthly_saving1 = PMT - PMT1;
		if (PMT2 != null)
			monthly_saving2 = PMT - PMT2;
		if (PMT3 != null)
			monthly_saving3 = PMT - PMT3;

		LOG.info("Saving1=" + monthly_saving1 + ",saving2=" + monthly_saving2
				+ ",saving3=" + monthly_saving3);

		String BEP1 = Calculators.BEP_prn(loan_amt, PMT, PMT1, closing_cost1,
				point1);
		String BEP2 = Calculators.BEP_prn(loan_amt, PMT, PMT2, closing_cost2,
				point2);
		String BEP3 = Calculators.BEP_prn(loan_amt, PMT, PMT3, closing_cost3,
				point3);

		Double total_int_paid = Calculators.total_int_paid(loan_amt, paid_num,
				term, int_rate, first_mm, first_yyyy);

		Double total_unint_paid = Calculators.total_unint_paid(loan_amt, 0,
				term, int_rate);
		Double total_unint_paid1 = Calculators.total_unint_paid(loan_amt, 0,
				term1, int_rate1);
		Double total_unint_paid2 = Calculators.total_unint_paid(loan_amt, 0,
				term2, int_rate2);
		Double total_unint_paid3 = Calculators.total_unint_paid(loan_amt, 0,
				term3, int_rate3);

		Gson gson = new Gson();
		JsonObject myObj = new JsonObject();

		CompareLoanVo curLoan = new CompareLoanVo();
		curLoan.setMonthPayment(PMT);
		curLoan.setTimesAlreadyPaid(paid_num);
		curLoan.setRemainTimes(remain_num);
		curLoan.setPaidInterest(total_int_paid);
		curLoan.setUnpaidInterets(total_unint_paid);
		curLoan.setUnpainLoanAmt(remain_balance + total_unint_paid);
		JsonElement curLoanElement = gson.toJsonTree(curLoan);
		myObj.add("curLoan", curLoanElement);

		CompareLoanVo loan1 = new CompareLoanVo();
		loan1.setMonthPayment(PMT1);
		loan1.setMonthSaving(monthly_saving1);
		loan1.setBreakEventPoint(BEP1);
		loan1.setRemainTimes(int_term1);
		loan1.setUnpaidInterets(total_unint_paid1);
		if (total_unint_paid1 != null) {
			loan1.setUnpainLoanAmt(remain_balance + total_unint_paid1);
		}
		JsonElement loan1Element = gson.toJsonTree(loan1);
		myObj.add("Loan1", loan1Element);

		CompareLoanVo loan2 = new CompareLoanVo();
		loan2.setMonthPayment(PMT2);
		loan2.setMonthSaving(monthly_saving2);
		loan2.setBreakEventPoint(BEP2);
		loan2.setRemainTimes(int_term2);
		loan2.setUnpaidInterets(total_unint_paid2);
		if (total_unint_paid2 != null)
			loan2.setUnpainLoanAmt(remain_balance + total_unint_paid2);
		JsonElement loan2Element = gson.toJsonTree(loan2);
		myObj.add("Loan2", loan2Element);

		CompareLoanVo loan3 = new CompareLoanVo();
		loan3.setMonthPayment(PMT3);
		loan3.setMonthSaving(monthly_saving3);
		loan3.setBreakEventPoint(BEP3);
		loan3.setRemainTimes(int_term3);
		loan3.setUnpaidInterets(total_unint_paid3);
		if (total_unint_paid3 != null)
			loan3.setUnpainLoanAmt(remain_balance + total_unint_paid3);
		JsonElement loan3Element = gson.toJsonTree(loan3);
		myObj.add("Loan3", loan3Element);

		out.println(myObj.toString());
		out.close();

		return null;

	}

	public ModelAndView affordableHomePriceOnlineHandler(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Utility.setParamAgentId(request);
		AffordableLineVo vo = Calculators.AffordHomePrice(request);

		ModelAndView mode = new ModelAndView("AffordableHomePriceOnline");

		Double income = Utility.getDouble(request
				.getParameter(Constant.NON_RENTAL_INCOME));
		Double rental = Utility.getDouble(request
				.getParameter(Constant.RENTAL_INCOME));
		Double debt = Utility.getDouble(request.getParameter(Constant.DEBT));

		if (debt == null)
			debt = 0.0;

		if (rental == null)
			rental = 0.0;

		Double DTI = 0.0;
		if ((income + rental) > 0.0)
			DTI = (debt / (income + rental)) * 100.00;

		mode.addObject("DTI", Utility.renderRate(DTI));

		mode.addObject("aggressiveAffordablePrice", vo.getAggresivePrice());
		mode.addObject("conservativeAffordablePrice", vo.getConservativePrice());

		return mode;

	}

	public ModelAndView calculateMonthlyExpenseOnlineHandler(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		Utility.setParamAgentId(request);
		Double loan_amt = Utility.getDouble(request
				.getParameter(Constant.LOAN_AMT));
		Double purch_price = Utility.getDouble(request
				.getParameter(Constant.PURCHASE_PRICE));
		Double prop_tax = Utility.getDouble(request
				.getParameter(Constant.PROPERTY_TAX));
		Double prop_ins = Utility.getDouble(request
				.getParameter(Constant.PROPERTY_INSURANCE));
		Double extra_PMT = Utility.getDouble(request
				.getParameter(Constant.EXTRA_PMT));

		Double LTV = ((loan_amt / purch_price) * 100.00);

		Double month_payment = Calculators.monthly_payment(request);

		Double property_tax = purch_price * prop_tax / 1200.00;

		Double property_ins = loan_amt * prop_ins / 1200.00;

		Double estimate_expense = month_payment + property_tax + property_ins
				+ (extra_PMT == null ? 0.0 : extra_PMT);

		ModelAndView mode = new ModelAndView("CalculateMonthlyExpenseOnline");

		mode.addObject("monthlyInterestPayment",
				Utility.renderDollar(month_payment));
		mode.addObject("propertyTax", Utility.renderDollar(property_tax));
		mode.addObject("propertyInsurance", Utility.renderDollar(property_ins));
		mode.addObject("totalMortgageExpense",
				Utility.renderDollar(estimate_expense));

		LOG.info("Monthly Interest=" + month_payment + ",estimate expense="
				+ estimate_expense);
		mode.addObject("LTV", Utility.renderRate(LTV));

		return mode;

	}

	public ModelAndView yearlyAmortPDFHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);

		SummaryTotalsVo summaryVo = SummaryTotalsVo.getInstance();

		AmortParamVo aparamVo = AmortParamVo.getInstance();

		List<AmortizationLine> amortList = Calculators.amortizationCalculator(
				request, summaryVo, aparamVo);

		List<YearlyAmortizationVo> yearlyList = Calculators
				.YearlyAmortization(amortList);

		CleanAmortList.cleanAmortList(amortList);

		// Image Parameters
		Map parameters = new HashMap();

		parameters.put("YEALY_AMORT_INT_PRIN_PAY_CHART",
				DrawXYLineYearlyAmortChart1(yearlyList));

		parameters.put("YEARLY_AMORT_REMAIN_BALANCE_CHART",
				DrawXYLineYearlyAmortChart2(yearlyList));

		parameters.put("YEARLY_AMORT_PIE_CHART",
				DrawYearlyAmortPieChart(summaryVo));

		parameters.put("REPORT_CREATED_BY", "MortgageLoanAgency.com");
		Date date = new Date();
		parameters.put("REPORT_CREATED_ON", Utility.renderDate(date));
		parameters.put("REPORT_TITLE", "Yearly Amortization Graph And Report");
		parameters.put("REPORT_SUB_TITLE", "Yearly Amortization Report");

		parameters.put("YEARLY_AMORT_PIE_CHART_TITLE", "Total Paid $"
				+ summaryVo.getStrSummaryMonthPayment());

		parameters.put("BEGIN_BALANCE", aparamVo.getLoanAmount());
		parameters.put("YEAR_INT_RATE", aparamVo.getInterestRate());
		parameters.put("MONTH", aparamVo.getTerm());
		parameters.put("REALPAYMONTHS", summaryVo.getRealPayMonths());
		parameters.put("MONTHLYPAYMENT", summaryVo.getMonthlyPayment());
		parameters.put("STRSUMMARYMONTHPAYMENT",
				summaryVo.getStrSummaryMonthPayment());
		parameters.put("STRSUMMARYINTPAYMENT",
				summaryVo.getStrSummaryIntPayment());
		parameters.put("STRSUMMARYPRINPAYMENT",
				summaryVo.getStrSummaryPrinPayment());
		parameters.put("FIRST_DATE", aparamVo.getFirst_Date());
		parameters.put("EXTRA_PMT", aparamVo.getExtra_PMT());
		parameters.put("PMT_DATE", aparamVo.getPMT_Date());
		parameters.put("ADJUSTEDMONTHLYPAYMENT",
				summaryVo.getAdjustedMonthlyPayment());
		parameters.put("EXTRA_YPMT", aparamVo.getExtra_YPMT_once());
		parameters.put("YPMT_DATE", aparamVo.getYPMT_Date());
		parameters.put("EXTRA_YPMT_ONCE", aparamVo.getExtra_YPMT_once());
		parameters.put("YPMT_ONCE_DATE", aparamVo.getYPMT_once_Date());

		String realPath = YEARLY_BASE_RESULT_FILE_NAME;

		String jasperPath = YEARLY_JASPER_BUILDER_FILE;

		LOG.info("RealPath=" + realPath);

		File file = new File(YEARLY_JASPER_BUILDER_FILE);

		InputStream jasperBuilderFile = new FileInputStream(file);

		LOG.info("jasperBulderFile=" + jasperBuilderFile);

		String finalResultPath = JasperReportUtils.writeToPDF(realPath,
				jasperBuilderFile, parameters, yearlyList, request, response);

		File currentFile = new File(finalResultPath);

		LOG.info("getName=" + currentFile.getName());

	//	response.setContentType("text/html");
	//	PrintWriter out = response.getWriter();

		String PDF_URL_PATH = SysPath.getInstance().getTempPath()
				+ currentFile.getName();

		LOG.info("pdf path=" + PDF_URL_PATH);

		/*
		 * String PDF_DISPLAY =
		 * "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"+
		 * "<html>"+ "<head>"+
		 * "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">"
		 * + "</head>"+ "<body>"+ // "<H1>Hello Yealy Amortization</H1>"+
		 * "<IFRAME src=\""+PDF_URL_PATH+"\" "+
		 * " style=\"background-color:lightblue;\" WIDTH=730 HEIGHT=625 scrolling=\"no\">"
		 * + "</IFRAME>"+ "</body>"+ "</html>"; out.println(PDF_DISPLAY);
		 */

		LOG.info("Download begin");
		 
		ProcessDownloadFile.downloadFile(response,
				"application/pdf", PDF_URL_PATH, currentFile.getName());
		File oldFile = new File(PDF_URL_PATH);
		oldFile.delete();
		LOG.info("Download end");

		return null;
	}

	public ModelAndView monthlyAmortPDFHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);

		SummaryTotalsVo summaryVo = SummaryTotalsVo.getInstance();

		AmortParamVo aparamVo = AmortParamVo.getInstance();

		List<AmortizationLine> amortList = Calculators.amortizationCalculator(
				request, summaryVo, aparamVo);

		List<YearlyAmortizationVo> yearlyList = Calculators
				.YearlyAmortization(amortList);

		List<AmortizationLineVo> monthlyList = new ArrayList<AmortizationLineVo>();
		int count = 0;
		for (AmortizationLine line : amortList) {
			AmortizationLineVo vo = line.getNewLineVo();
			monthlyList.add(vo);

		}
		if (aparamVo != null) {
			LOG.info("BEGIN_BALANCE=" + aparamVo.getLoanAmount());
		}
		// for (AmortizationLineVo v:monthlyList) {
		// LOG.info(v.getBegin_balance()+","+v.getTotal_month()+","+v.getInt_paid()+","+v.getAccu_int_paid());
		// }
		CleanAmortList.cleanAmortList(amortList);
		LOG.info("MonthlyList.size()=" + monthlyList.size());

		// Image Parameters
		Map parameters = new HashMap();

		parameters.put("YEALY_AMORT_INT_PRIN_PAY_CHART",
				DrawXYLineYearlyAmortChart1(yearlyList));

		parameters.put("YEARLY_AMORT_REMAIN_BALANCE_CHART",
				DrawXYLineYearlyAmortChart2(yearlyList));

		CleanAmortList.cleanYearList(yearlyList);

		parameters.put("YEARLY_AMORT_PIE_CHART",
				DrawYearlyAmortPieChart(summaryVo));

		parameters.put("REPORT_CREATED_BY", "MortgageLoanAgency.com");
		Date date = new Date();
		parameters.put("REPORT_CREATED_ON", Utility.renderDate(date));
		parameters.put("REPORT_TITLE", "Monthly Amortization Graph And Report");
		parameters.put("REPORT_SUB_TITLE", "Monthly Amortization Report");

		parameters.put("YEARLY_AMORT_PIE_CHART_TITLE", "Total Paid $"
				+ summaryVo.getStrSummaryMonthPayment());

		parameters.put("BEGIN_BALANCE", aparamVo.getLoanAmount());
		parameters.put("YEAR_INT_RATE", aparamVo.getInterestRate());
		parameters.put("MONTH", aparamVo.getTerm());
		parameters.put("REALPAYMONTHS", summaryVo.getRealPayMonths());
		parameters.put("MONTHLYPAYMENT", summaryVo.getMonthlyPayment());
		parameters.put("STRSUMMARYMONTHPAYMENT",
				summaryVo.getStrSummaryMonthPayment());
		parameters.put("STRSUMMARYINTPAYMENT",
				summaryVo.getStrSummaryIntPayment());
		parameters.put("STRSUMMARYPRINPAYMENT",
				summaryVo.getStrSummaryPrinPayment());
		parameters.put("FIRST_DATE", aparamVo.getFirst_Date());
		parameters.put("EXTRA_PMT", aparamVo.getExtra_PMT());
		parameters.put("PMT_DATE", aparamVo.getPMT_Date());
		parameters.put("ADJUSTEDMONTHLYPAYMENT",
				summaryVo.getAdjustedMonthlyPayment());
		parameters.put("EXTRA_YPMT", aparamVo.getExtra_YPMT_once());
		parameters.put("YPMT_DATE", aparamVo.getYPMT_Date());
		parameters.put("EXTRA_YPMT_ONCE", aparamVo.getExtra_YPMT_once());
		parameters.put("YPMT_ONCE_DATE", aparamVo.getYPMT_once_Date());

		String realPath = MONTHLY_BASE_RESULT_FILE_NAME;

		String jasperPath = MONTHLY_JASPER_BUILDER_FILE;

		LOG.info("jasperPath=" + jasperPath);

		LOG.info("realPath=" + realPath);

		File file = new File(MONTHLY_JASPER_BUILDER_FILE);

		InputStream jasperBuilderFile = new FileInputStream(file);

		LOG.info("jasperBulderFile=" + jasperBuilderFile);

		String finalResultPath = JasperReportUtils.writeToPDF(realPath,
				jasperBuilderFile, parameters, monthlyList, request, response);

		CleanAmortList.cleanAmortVoList(monthlyList);

		File currentFile = new File(finalResultPath);

		LOG.info("getName=" + currentFile.getName());

	//	response.setContentType("text/html");
	//	PrintWriter out = response.getWriter();

		String PDF_URL_PATH = SysPath.getInstance().getTempPath()
				+ currentFile.getName();

		LOG.info("pdf path=" + PDF_URL_PATH);

		/*
		 * String PDF_DISPLAY =
		 * "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">"+
		 * "<html>"+ "<head>"+
		 * "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">"
		 * + "</head>"+ "<body>"+ // "<H1>Hello Yealy Amortization</H1>"+
		 * "<IFRAME src=\""+PDF_URL_PATH+"\" "+
		 * " style=\"background-color:lightblue;\" WIDTH=730 HEIGHT=625 scrolling=\"no\">"
		 * + "</IFRAME>"+ "</body>"+ "</html>"; out.println(PDF_DISPLAY);
		 */
		LOG.info("Download begin");
		ProcessDownloadFile.downloadFile(response, "application/pdf",
				PDF_URL_PATH, currentFile.getName());
		File oldFile = new File(PDF_URL_PATH);
		oldFile.delete();
		LOG.info("Download end");

		return null;
	}

	public ModelAndView monthlyAmortOnlineHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Utility.setParamAgentId(request);
		LOG.info("monthlyAmortOnlineHandler begin");
		SummaryTotalsVo summaryVo = SummaryTotalsVo.getInstance();

		AmortParamVo aparamVo = AmortParamVo.getInstance();

		List<AmortizationLine> amortList = Calculators.amortizationCalculator(
				request, summaryVo, aparamVo);

		List<YearlyAmortizationVo> yearlyList = Calculators.YearlyAmortization(amortList);

		ModelAndView mode = new ModelAndView("AmortizationOnlineReport");

		mode.addObject("amortList", amortList);

		mode.addObject("summaryVo", summaryVo);

		CleanAmortList.cleanAmortList(amortList);

		mode.addObject("aparam", aparamVo);
		LOG.info("monthlyAmortOnlineHandler End !");
		return mode;
	}

	public ModelAndView yearlyAmortOnlineHandler(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LOG.info("yearlyAmortOnlineHandler begin");
		Utility.setParamAgentId(request);
		SummaryTotalsVo summaryVo = SummaryTotalsVo.getInstance();

		AmortParamVo aparamVo = AmortParamVo.getInstance();

		List<AmortizationLine> amortList = Calculators.amortizationCalculator(
				request, summaryVo, aparamVo);

		List<YearlyAmortizationVo> yearlyList = Calculators.YearlyAmortization(amortList);

		ModelAndView mode = new ModelAndView("YearlyAmortizationOnlineReport");

		for (YearlyAmortizationVo v : yearlyList) {
			LOG.info("YearNo=" + v.getYearNo() + ",BeginBalance="
					+ v.getBeginBalance() + ",Interest Payment="
					+ v.getInterestPayment() + ",Princeple ="
					+ v.getPrinciplePayment() + ",remaining_balance="
					+ v.getRemainBalance());
		}
		mode.addObject("yearlyAmortList", yearlyList);

		CleanAmortList.cleanAmortList(amortList);

		mode.addObject("summaryVo", summaryVo);

		mode.addObject("aparam", aparamVo);
		LOG.info("yearlyAmortOnlineHandler end");
		return mode;
	}

	public java.awt.Image DrawXYLineYearlyAmortChart1(
			List<YearlyAmortizationVo> yearlyList) {

		XYSeries seriesYearlyPayment = new XYSeries("Blue--Yearly Payment");
		XYSeries seriesPaidInterest = new XYSeries("Red--Paid Interest");
		XYSeries seriesPrinciple = new XYSeries("Green--Principle");

		for (YearlyAmortizationVo v : yearlyList) {
			Double year = Utility.getDouble(v.getYear());
			Double paidInterest = Utility.getDouble(v.getInterestPayment());
			Double yearlyPayment = Utility.getDouble(v.getYearlyPayment());
			Double principle = Utility.getDouble(v.getPrinciplePayment());

			seriesYearlyPayment.add(year, paidInterest);
			seriesPaidInterest.add(year, yearlyPayment);
			seriesPrinciple.add(year, principle);
		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesYearlyPayment);
		dataset.addSeries(seriesPaidInterest);
		dataset.addSeries(seriesPrinciple);

		JFreeChart chart = ChartFactory.createXYLineChart("", // "Payment,Interest and Principle Change Chart",
																// // Title
				"Years", // x-axis Label
				"Dollar amount", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		return chart.createBufferedImage(580, 218);
	}

	public java.awt.Image DrawXYLineYearlyAmortChart2(
			List<YearlyAmortizationVo> yearlyList) {

		XYSeries seriesRemainBalance = new XYSeries("Red--Remain Balance");

		for (YearlyAmortizationVo v : yearlyList) {
			Double year = Utility.getDouble(v.getYear());
			Double remainBalance = Utility.getDouble(v.getRemainBalance());

			seriesRemainBalance.add(year, remainBalance);

		}
		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(seriesRemainBalance);

		JFreeChart chart = ChartFactory.createXYLineChart("", // "Remain Balance ",
																// // Title
				"Years", // x-axis Label
				"Dollar amount", // y-axis Label
				dataset, // Dataset
				PlotOrientation.VERTICAL, // Plot Orientation
				true, // Show Legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		return chart.createBufferedImage(290, 217);
	}

	public java.awt.Image DrawYearlyAmortPieChart(SummaryTotalsVo summaryVo) {

		DefaultPieDataset pieDataset = new DefaultPieDataset();
		Double mPay = Utility.getDouble(summaryVo.getStrSummaryMonthPayment());
		Double IntPay = Utility.getDouble(summaryVo.getStrSummaryIntPayment());
		Double Prin = Utility.getDouble(summaryVo.getStrSummaryPrinPayment());
		Double Total = mPay;
		// Integer mPayPercent = (int) ((mPay/Total) * 100);
		Integer intPayPercent = (int) ((IntPay / Total) * 100);
		Integer prinPercent = (int) ((Prin / Total) * 100);

		// pieDataset.setValue("Total Montly(%)",mPayPercent);
		pieDataset.setValue("Total Interest(%)", intPayPercent);
		pieDataset.setValue("Total Principle(%)", prinPercent);

		JFreeChart chart = ChartFactory.createPieChart("",// "Total Paid $"+summaryVo.getStrSummaryMonthPayment(),
															// // Title
				pieDataset, // Dataset
				true, // Show legend
				true, // Use tooltips
				false // Configure chart to generate URLs?
				);
		return chart.createBufferedImage(290, 217);
	}

}
