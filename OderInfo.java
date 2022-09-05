/**
 * 
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name="order_info")
@Component


public class OderInfo {
	
	@Id
	@GeneratedValue
	@Column(name="oid")
	private int oid;
	
	@ManyToOne
	@JoinColumn(name="scid")
	private ShippingCompany shippingCompany;
	
	@OneToOne
	@JoinColumn(name="sid")
	private Ship ship;
	
	@Column(name="date")
	private String date;

	
	public OrderInfo() {
		super();
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public ShippingCompany getShippingCompany() {
		return shippingCompany;
	}
	public void setShippingCompany(ShippingCompany shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	public Ship getShip() {
		return ship;
	}
	public void setShip(Ship ship) {
		this.ship = ship;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "OrderInfo [oid=" + oid + ", shippingCompany=" + shippingCompany + ", ship=" + ship + ", date=" + date
				+ "]";
	}
}
}
