/**
 * 
 */
package Security;

/**
 * @author manuelnava
 *
 */
public class SecurityConfiguration {
	  public void configure(HttpSecurity httpSecurity) throws Exception{
		    httpSecurity.authorizeRequests()
		     .antMatchers( "/addShip","/addShippingCompany","/createOrder")
		     .hasRole("USER")
		     .and()
		     .formLogin()
		     .and()
		     .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		     .logoutSuccessUrl("/logoutDone").deleteCookies("JSESSIONID")
		     .invalidateHttpSession(true) ;
		  }

		  @Autowired
		  public void configureGlobal(AuthenticationManagerBuilder auth) 
								throws Exception {
		    auth.inMemoryAuthentication()
		      .withUser("user").password("user").roles("USER");
		  }
		}
}
