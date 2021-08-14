package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.to_entity.CommentDto;

@Service
public interface CommentService {
	
	public CommentDto createComment(CommentDto dto);
	
	public Page<CommentDto> getAllCommentByProduct(SearchDto dto);
	
	public Page<CommentDto> getAllComments(SearchDto dto);
	
	public List<CommentDto> getAllCommentByUser(String username);
	
}
