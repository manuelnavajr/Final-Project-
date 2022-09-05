/**
 * 
 */
package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.ships.model.OrderInfo;
import com.ships.model.Ship;
import com.ships.model.ShippingCompany;
import com.ships.services.OrderService;
import com.ships.services.SCompanyService;
import com.ships.services.ShipService;
public class Order {
	@Controller
	@SessionAttributes({"shipMap","shipCompanyMap","order1","orders"})
	public class OrderController {
		@Autowired
		private OrderService orderService;
		@Autowired
		private ShipService shipService;
		@Autowired
		private SCompanyService sCompanyService;
		
		@RequestMapping(value="showOrders" , method = RequestMethod.GET)
		public String getOrders(Model model) {
			ArrayList<OrderInfo> orders = orderService.showAll();
			for(OrderInfo o : orders)
			{
				System.out.println("oid = " + o.getOid());
			}
			model.addAttribute("orders",orders);
			return "showOrders";
		}
		
		@RequestMapping(value="createOrder" , method = RequestMethod.GET)
		public String addOrders( Model m) {
			//Ship Name:
			ArrayList<Ship> ships = shipService.showAll();
			Map<Integer,String> shipMap = new HashMap<>();
			shipMap.put(0,"");
			for (Ship s : ships)
			{
				System.out.println("Ship Name: "+ s.getName());
				System.out.println("Ship Cost: "+ s.getCost());
				shipMap.put(s.getSid(), s.getName()+" ; Cost = "+s.getCost());
			}
			System.out.println("shipMap: "+ shipMap);
			m.addAttribute("shipMap", shipMap);
			
			//Shipping Company
			ArrayList<ShippingCompany> shipCompanys = sCompanyService.showAll();
			Map<Integer,String> shipCompanyMap = new HashMap<>();
			for (ShippingCompany sc : shipCompanys)
			{
				shipCompanyMap.put(sc.getScid(),  sc.getName()+"; Balance = "+sc.getBalance());
			}
			System.out.println("shipCompanyMap: "+ shipCompanyMap);
			m.addAttribute("shipCompanyMap", shipCompanyMap);
			
			// add a empty order to order1
			m.addAttribute("order1", new OrderInfo());
			return "createOrder";
		}
		
		@RequestMapping(value = "/createOrder", method = RequestMethod.POST)
		public String addOrdersPost(@ModelAttribute("order1") OrderInfo orderInfo, BindingResult result, HttpServletRequest h, Model m) {
			if (orderInfo.getShip().getSid()==0)
			{
				System.out.println("No Ship and/or Shipping Company selecte.");
	 			return "errorShip";
			}
			
			Ship ship = shipService.getShipBySid(orderInfo.getShip().getSid());
			ShippingCompany shippingCompany = sCompanyService.getShippingCompanyByScid(orderInfo.getShippingCompany().getScid());
			
			if (ship.getCost().doubleValue() > shippingCompany.getBalance().doubleValue() ) 
			{
				System.out.println("Shipping company balance is less than cost of ship.");
				return "errorOrder";
			}
			else
			{
				System.out.println("HTTP Request = " + h.getMethod());
				orderInfo.setDate(new String(new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime())));
				orderService.save(orderInfo);
				m.addAttribute("order1",orderService.showAll());
				return "redirect:showOrders";
			}
		}
	}
}
