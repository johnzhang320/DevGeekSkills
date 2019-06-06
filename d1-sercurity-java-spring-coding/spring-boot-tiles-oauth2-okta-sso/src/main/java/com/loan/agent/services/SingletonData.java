package com.loan.agent.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.loan.agent.services.SelectedValueVo;
public class SingletonData {
	public static SingletonData handler=null;
	private static List<SelectedValueVo> langList = null;
	public static SingletonData getInstance() {
		if (handler==null) {
			handler = new SingletonData();
		}
		return handler;
	}
	private SingletonData() {
		langList = initilize();
	}
	/**
	 * Initialize if LangList ==null
	 * @param lang
	 * @return
	 */
	public List<SelectedValueVo> initilize() {
		List<SelectedValueVo> langList = new ArrayList<SelectedValueVo>();
		langList.add(new SelectedValueVo("English(US)", "en", "false"));
		langList.add(new SelectedValueVo("Chinese(PRC)", "zh_CN", "false"));
		langList.add(new SelectedValueVo("German", "de", "false"));
		langList.add(new SelectedValueVo("French", "fr", "false"));
		return langList;
	}
	/**
	 * Current Language List is null, use this get
	 * @return
	 */
	public List<SelectedValueVo> getLanguageInitList() {
		langList =initilize();
	    return langList;
	}
	
	public String findLanguage(String key, List<SelectedValueVo> langList) {
	    for (SelectedValueVo vo:langList) {
	    	if (key.equalsIgnoreCase(vo.getValue())) {
	    		return vo.getLabel();
	    	}
	    }
	    return null;
	}
	public void setCurrentLanguage(String lang, HttpServletRequest request) {
		/**
		 *  If Change Save to current session
		 */
		List<SelectedValueVo> langList=initilize();
	    for (SelectedValueVo vo:langList) {
	    	if (lang.equalsIgnoreCase(vo.getValue())) {
	    		 vo.setSelected("true");
	    		 request.getSession().setAttribute("LanguageList", langList);
	    		 break;
	    	}
	    }
	    
	}
}
