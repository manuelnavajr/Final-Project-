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

import com.ships.model.Ship;
import com.ships.services.ShipService;

 class ShipController {
	@Autowired
	ShipService shipService;
	
	@RequestMapping(value="showShips" , method = RequestMethod.GET)
	public String getShips(Model model){
		ArrayList<Ship> ships = shipService.showAll();
		for(Ship s : ships)
		{
			System.out.println("sid = " + s.getSid());
		}
		model.addAttribute("ships",ships);
		return "showShips";
	}
	
	@RequestMapping(value="addShip" , method = RequestMethod.GET)
	public String addShip(@ModelAttribute("ship1") Ship ship, HttpServletRequest h){
		System.out.println("HTTP Request = " + h.getMethod());
		return "addShip";
	}
	
	@RequestMapping(value = "/addShip", method = RequestMethod.POST)
	public String addShipPost(@Valid@ModelAttribute("ship1") Ship ship, BindingResult result, HttpServletRequest h, Model m) {

		if (result.hasErrors()) 
		{
			System.out.println("error");
			return "addShip";
		} 
		else
		{
			System.out.println("HTTP Request = " + h.getMethod());

			shipService.save(ship);

			ArrayList<Ship> ships = shipService.showAll();

			for (Ship s : ships)
			{
				System.out.println("Cid=" + s.getSid());
			}
			m.addAttribute("ships", ships);
			return "showShips";
		}
	}
}
}
