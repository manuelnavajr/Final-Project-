/**
 * 
 */
package model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="ship")

/**
 * @author manuelnava
 *
 */
public class Ship {
	@Id
	@GeneratedValue
	@Column(name="sid")
	private int sid;
	
	@NotNull
    @Size(min=1,max=250,message="size must be between 1 and 250")
	@Column(name="name")
	private String name;
	
	@Column(name="passengers")
	@Min(value=0,message="must greater than or equal to 0")
	private int passengers;
	
	@Column(name="cost")
	@NotNull (message="may not be null")
	@Min(value=1,message="must greater than or equal to 1")
	private BigDecimal cost;
	
	@Column(name="metres")
	@Min(value=1,message="must be gtrater than or equal to 1")
	private double metres;
	
	@ManyToOne
	@JoinColumn(name="scid")
	private ShippingCompany shippingCompany;
	
	
	@Transient
	private String shipDesc;
	
		
	public String getShipDesc() {
		return name + "; Cost = " + cost;
	}
	public void setShipDesc(String shipDesc) {
		this.shipDesc = shipDesc;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public double getMetres() {
		return metres;
	}
	public void setMetres(double metres) {
		this.metres = metres;
	}
	public ShippingCompany getShippingCompany() {
		return shippingCompany;
	}
	public void setShippingCompany(ShippingCompany shippingCompany) {
		this.shippingCompany = shippingCompany;
	}
	@Override
	public String toString() {
		return name + ", " + metres + "Mtrs, " + cost;
	}
}

