package com.cts.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorHandlerController implements ErrorController {
	
	@SuppressWarnings("unused")
	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}

	/**
	 * @param httpRequest
	 * @param model
	 * @return custom error view 
	 */
	@RequestMapping("/error")
	public String getErrorPath(HttpServletRequest httpRequest, Model model) {
		String errorMsg = "";
		int httpErrorCode = (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
		switch (httpErrorCode) {
		case 400: {
			errorMsg = "Http Error Code: 400. Bad Request";
			break;
		}
		case 401: {
			errorMsg = "Http Error Code: 401. Unauthorized";
			break;
		}
		case 403: {
			errorMsg = "Http Error Code: 401. Forbidden";
			break;
		}
		case 404: {
			errorMsg = "Http Error Code: 404. Resource not found";
			break;
		}
		case 409: {
			errorMsg = "Http Error Code: 409. Conflict";
			break;
		}
		case 500: {
			errorMsg = "Http Error Code: 500. Internal Server Error";
			break;
		}
		default:
			errorMsg = "Something went wrong! Try Again!";
		}
		model.addAttribute("errorMsg", errorMsg);
		return "error-page";
	}

	@Override
	public String getErrorPath() {
		return null;
	}

}
