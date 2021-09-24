package com.example.demo.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.common.Erole;
import com.example.demo.common.JwtUtils;
import com.example.demo.dto.SearchDto;
import com.example.demo.dto.auth.JwtResponse;
import com.example.demo.dto.auth.LoginDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.auth.RegisterDto;
import com.example.demo.dto.user.UserDto;
import com.example.demo.entity.user.Address;
import com.example.demo.entity.user.Role;
import com.example.demo.entity.user.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.impl.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")

public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserService userService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@Validated @RequestBody LoginDto dto) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));

	}

	@PostMapping("/register")
	public ResponseEntity<?> register(@Validated @RequestBody RegisterDto dto) {

		if (userRepository.existsByUsername(dto.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Tài khoản đã được đăng ký!"));
		}
		if (userRepository.existsByEmail(dto.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email đã được đăng ký!"));
		}
		if (userRepository.existsByPhone(dto.getPhone())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Số điện thoại đã được đăng ký!"));
		}

		Address address = new Address(dto.getCity(), dto.getDistrict(), dto.getWard(), dto.getHouse());

		User user = new User(dto.getPhone(), dto.getEmail(), dto.getUsername(), encoder.encode(dto.getPassword()),
				dto.getDateOfBirth(), dto.getFullName(), address);

		address.setUser(user);

		Set<String> strRoles = dto.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(Erole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(Erole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(adminRole);
					break;
				default:
					Role userRole = roleRepository.findByName(Erole.ROLE_USER)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found"));
					roles.add(userRole);
					break;
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Đăng ký tài khoản thành công!"));
	}
	
	@PutMapping("/update-info")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> update(@Validated @RequestBody RegisterDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userRepository.findOneByUsername(username);
		if (userRepository.existsByEmail(dto.getEmail()) && user.getEmail().equals(dto.getEmail()) == false) {
			return ResponseEntity.badRequest().body(new MessageResponse("Email đã được đăng ký!"));
		}
		user.setFullname(dto.getFullName());
		user.setPhone(dto.getPhone());
		user.setEmail(dto.getEmail());
		user.setDateOfBirth(dto.getDateOfBirth());
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Cập nhật thông tin tài khoản thành công!"));
	}
	
	@PutMapping("/update-password")
	@PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> updatePassword(@Validated @RequestBody RegisterDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = userRepository.findOneByUsername(username);
		String oldPassword = dto.getPassword();
		String newPassword = dto.getPasswordNew();
		if (encoder.matches(oldPassword, user.getPassword())) {
			user.setPassword(encoder.encode(newPassword));
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse("Mật khẩu cũ không chính xác!"));
		}
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse("Cập nhật mật khẩu thành công!"));
	}

	@GetMapping("/info")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<UserDto> getNewById() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		UserDto result = userService.getCurrentUser(userDetails.getId());
		return new ResponseEntity<UserDto>(result, HttpStatus.OK);
	}

	@GetMapping("/all/user")
//	@PreAuthorize("hasRole('STAFF_BUSINESS') or hasRole('STAFF_SALE')")
	public ResponseEntity<Page<UserDto>> getList(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		SearchDto dto = new SearchDto(page, limit, keyword);
		Page<UserDto> result = userService.getList(dto);
		return new ResponseEntity<Page<UserDto>>(result, HttpStatus.OK);
	}

	@GetMapping("/customer/{id}")
//	@PreAuthorize("hasRole('STAFF_BUSINESS') or hasRole('STAFF_SALE')")
	public ResponseEntity<UserDto> getCustomerById(@PathVariable Long id) {
		User user = userRepository.getById(id);
		UserDto result = new UserDto(user);
		return new ResponseEntity<UserDto>(result, HttpStatus.OK);
	}

}