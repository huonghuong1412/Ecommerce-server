package com.example.demo.rest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.auth.MessageResponse;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/upload")
public class UploadFileController {

	@Value("${file-upload-dir}")
	String FILE_DIRECTORY;
	
	@Value("${slide-file-upload-dir}")
	String SLIDE_FILE_DIRECTORY;

	@PostMapping(value="/image/product")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> fileUpload(@RequestParam("File") MultipartFile[] multipartFile) throws IOException {

		List<File> files = new ArrayList<File>();
		for (int i = 0; i < multipartFile.length; i++) {
			File file = new File(FILE_DIRECTORY +  multipartFile[i].getOriginalFilename());
			files.add(file);
		}
		FileOutputStream fos = null;
		
		for(int i = 0; i<multipartFile.length; i++) {
			files.get(i).createNewFile();
			fos = new FileOutputStream(files.get(i));
			fos.write(multipartFile[i].getBytes());
			fos.close();
		}
		fos.close();
		return ResponseEntity.ok(new MessageResponse("Upload hình ảnh thành công!"));

	}
	
	@PostMapping("/image/slide")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> imageSlidefileUpload(@RequestParam("File") MultipartFile multipartFile) throws IOException {

		File file = new File(SLIDE_FILE_DIRECTORY +  multipartFile.getOriginalFilename());
		FileOutputStream fos = null;
		
		file.createNewFile();
		fos = new FileOutputStream(file);
		fos.write(multipartFile.getBytes());
		fos.close();
		return ResponseEntity.ok(new MessageResponse("Upload hình ảnh slide thành công!"));

	}

}
