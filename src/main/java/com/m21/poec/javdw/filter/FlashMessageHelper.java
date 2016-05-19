package com.m21.poec.javdw.filter;

import javax.servlet.http.HttpServletRequest;

public class FlashMessageHelper {

	public static void addFlashMessage(HttpServletRequest request, String string) {
		request.setAttribute(FlashScopeFilter.FLASH_FRESH_KEY, string);
	}
}
