package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.user.CommentDto;
import com.example.demo.service.CommentService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/api/comment")
public class CommentController {

	@Qualifier("commentServiceImpl")
	@Autowired
	private CommentService service;

	@PostMapping("")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<MessageResponse> createComment(@RequestBody CommentDto dto) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		dto.setUsername(username);
		MessageResponse result = service.createComment(dto);
		return new ResponseEntity<MessageResponse>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/product/search")
	public ResponseEntity<List<CommentDto>> getAllByProduct(@RequestParam Long productId) {
		List<CommentDto> result = service.getAllCommentByProduct(productId);
		return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/user")
	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	public ResponseEntity<List<CommentDto>> getAllByUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		List<CommentDto> result = service.getAllCommentByUser(username);
		return new ResponseEntity<List<CommentDto>>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<Page<CommentDto>> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
			@RequestParam(name = "limit", defaultValue = "10") int limit,
			@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		SearchDto dto = new SearchDto(page, limit, keyword);
		Page<CommentDto> result = service.getAllComments(dto);
		return new ResponseEntity<Page<CommentDto>>(result, HttpStatus.OK);
	}

}
