package com.loan.agent.calculators;

import java.util.List;

import com.loan.agent.calculators.vo.AmortizationLineVo;
import com.loan.agent.calculators.vo.YearlyAmortizationVo;

public class CleanAmortList {
	public static void cleanAmortList(List<AmortizationLine> list) {
		for (AmortizationLine obj: list) {
			obj=null;
		}
		list=null;
	}
	
	public static void cleanYearList(List<YearlyAmortizationVo> list) {
		for (YearlyAmortizationVo obj: list) {
			obj=null;
		}
		list=null;
	}
	
	public static void cleanAmortVoList(List<AmortizationLineVo> list) {
		for (AmortizationLineVo obj: list) {
			obj=null;
		}
		list=null;
	}
}
