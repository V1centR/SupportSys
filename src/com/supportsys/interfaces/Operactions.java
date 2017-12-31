package com.supportsys.interfaces;

import org.json.JSONException;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public interface Operactions {

	/**
	 *
	 * @param jsonStr
	 * @return
	 */
	Integer insertItem(Object jsonStr) throws JSONException;

	/**
	 *
	 * @param jsonStr
	 * @return
	 */
	Integer updateItem(Object jsonStr) throws JSONException;;

	/**
	 *
	 * @param jsonStr
	 * @return
	 */
	Integer deleteItem(Object jsonStr);

	/**
	 *
	 * @param model
	 * @return
	 */
	ModelAndView listItemsView(Model model);

	/**
	 *
	 * @param model
	 * @return
	 */
	ModelAndView updateItemView(Model model,Integer idUser );

	/**
	 *
	 * @param model
	 * @return
	 */
	ModelAndView insertItemView(Model model);


}
