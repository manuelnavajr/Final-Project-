/**
 * 
 */
package controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ships.model.ShippingCompany;
import com.ships.services.SCompanyService;

public class Company {
	@Autowired
	private SCompanyService sCompanyService;
	
	@RequestMapping(value="showShippingCompanies" , method = RequestMethod.GET)
	public String getCompany(Model model) {
		ArrayList<ShippingCompany> companies = sCompanyService.showAll();
		for(ShippingCompany sc : companies)
		{
			System.out.println("sid = " + sc.getScid());
		}
		model.addAttribute("companies",companies);
		return "showShippingCompany";
	}
	
	@RequestMapping(value="addShippingCompany" , method = RequestMethod.GET)
	public String addShipCompany(@ModelAttribute("shippingcompany1") ShippingCompany shippingcompany, HttpServletRequest h) {
		System.out.println("HTTP Request = " + h.getMethod());
		return "addShippingCompany";
	}
	
	@RequestMapping(value = "/addShippingCompany", method = RequestMethod.POST)
	public String addShipCompanyPost(@Valid@ModelAttribute("shippingcompany1") ShippingCompany shippingcompany,BindingResult result, HttpServletRequest h, Model m) {
		if (result.hasErrors()) 
		{
			System.out.println("error");
			return "addShippingCompany";
		} 
		else
		{
			System.out.println("HTTP Request = " + h.getMethod());
			sCompanyService.save(shippingcompany);
			ArrayList<ShippingCompany> companies = sCompanyService.showAll();
			for (ShippingCompany sc : companies)
			{
				System.out.println("Scid=" + sc.getScid());
			}
			m.addAttribute("companies", companies);
			return "showShippingCompany";
		}
	}
}
}
