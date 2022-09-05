/**
 * 
 */
package controller;

public class Logout {
	@RequestMapping(value = "/logoutFinished", method = RequestMethod.GET)
	public String logout() { 
		return "logout";
}
}


