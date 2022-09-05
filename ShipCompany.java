/**
 * 
 */
package model;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Entity
@Table(name="shipping_company")
@Component


/**
 * @author manuelnava
 *
 */
public class ShipCompany {
	
	@Id
	@GeneratedValue
	@Column(name="scid")
	private int scid;
	
	@NotNull
	@Column(name="name")
	@Size(min=1,max=250,message="size must be between 1 and 250")
	private String name;
	
	@NotNull
    @Size(min=1,max=250,message="size must be between 1 and 250")
	@Column(name="home_port")
	private String homePort;
	
	@Column(name="balance")
	@NotNull(message="may not be null")
	@Min(value=1,message="must be gtrater than or equal to 1")
	private BigDecimal balance;
	
	@OneToMany(mappedBy="shippingCompany")
	private List<Ship> ships = new ArrayList<Ship>();
	
	@OneToMany(mappedBy="shippingCompany")
	private List<OrderInfo> orders = new ArrayList<OrderInfo>();

	@Transient
	private String scDesc;
	
	public String getScDesc() {
		return name + ";  Balance = " + balance ;
	}
	public void setScDesc(String scDesc) {
		this.scDesc = scDesc;
	}
	public int getScid() {
		return scid;
	}
	public void setScid(int scid) {
		this.scid = scid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHomePort() {
		return homePort;
	}
	public void setHomePort(String homePort) {
		this.homePort = homePort;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public List<Ship> getShips() {
		return ships;
	}
	public void setShips(List<Ship> ships) {
		this.ships = ships;
	}
	@Override
	public String toString() {
		return "ShippingCompany [scid=" + scid + ", name=" + name + ", homePort=" + homePort + ", balance=" + balance
				+  "]";
	}
}
}
