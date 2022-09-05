/**
 * 
 */
package Services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ships.model.Ship;
import com.ships.repositories.ShipInterface;

@Service
/**
 * @author manuelnava
 *
 */
public class ShipService {
	@Autowired
	ShipInterface shipInterface;
	
	public ArrayList<Ship> showAll(){
		return (ArrayList<Ship>) shipInterface.findAll();
	}
	
	public Ship save(Ship ship){
		return shipInterface.save(ship);
	}
	
	public Ship getShipByName(String name){
		return shipInterface.findByName(name);
	}
	
	public Ship getShipBySid(int sid){
		return shipInterface.findBySid(sid);
	}
}
}
