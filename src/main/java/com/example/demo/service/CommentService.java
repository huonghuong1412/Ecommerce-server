package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SearchDto;
import com.example.demo.dto.auth.MessageResponse;
import com.example.demo.dto.user.CommentDto;

@Service
public interface CommentService {
	
	// thêm bình luận sau khi mua hàng
	public MessageResponse createComment(CommentDto dto);
	
	public List<CommentDto> getAllCommentByProduct(Long productId);
	
	public Integer countAllCommentByProduct(Long productId);
	
	public Page<CommentDto> getAllComments(SearchDto dto);
	
	public List<CommentDto> getAllCommentByUser(String username);
	
}
