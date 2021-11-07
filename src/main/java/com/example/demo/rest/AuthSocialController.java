package com.example.demo.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.common.ETypeAccount;
import com.example.demo.common.Erole;
import com.example.demo.common.GooglePojo;
import com.example.demo.entity.order.Cart;
import com.example.demo.entity.user.Address;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utils.GoogleUtils;
import com.example.demo.utils.JwtUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
@RestController
public class AuthSocialController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private GoogleUtils googleUtils;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@RequestMapping(value = "/oauth2/google")
	public RedirectView loginGoogle(HttpServletRequest request) throws ClientProtocolException, IOException {
		String code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			return new RedirectView("http://localhost:3000/oauth2/redirect?status=false");
		}
		ObjectMapper mapper = new ObjectMapper();
		String token = googleUtils.getToken(code);
		JsonNode node = mapper.readTree(token);
		String accessToken = node.get("access_token").textValue();

		GooglePojo googlePojo = null;
		if (accessToken != null) {
			googlePojo = googleUtils.getUserInfo(accessToken);
		}
		UserDetails userDetail = googleUtils.buildUser(googlePojo);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		User user = userRepository.findOneByEmail(userDetail.getUsername());

		if (user == null) {
			user = new User();
			user.setDisplay(1);
			user.setEmail(googlePojo.getEmail());
			user.setUsername(googlePojo.getEmail());
			user.setFullname(googlePojo.getName());
			user.setPassword(encoder.encode(googlePojo.getSocial_user_id()));
			user.setType_account(ETypeAccount.GOOGLE);
			List<Role> roles = new ArrayList<Role>();
			Role userRole = roleRepository.findOneByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
			user.setRoles(roles);
			
			Address address = new Address(null, null, null, null);
			user.setAddress(address);
			address.setUser(user);
			Cart cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
			userRepository.save(user);
		}
		Authentication authen = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), googlePojo.getSocial_user_id()));
		String jwt = jwtUtils.generateJwtToken(authen);
		return new RedirectView("http://localhost:3000/oauth2/redirect?status=true&token=" + jwt + "&username="
				+ user.getEmail());
	}
}
