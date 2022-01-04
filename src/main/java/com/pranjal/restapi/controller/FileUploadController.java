package com.pranjal.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pranjal.restapi.helper.FileUploadHelper;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	

	//@RequestParam("image")  image is "key" for file  in postman
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile file){
		
		System.out.println(file.getContentType() + "     "+ file.getSize() +"    "+ file.getOriginalFilename() +
				"     "+	file.getName() + "     "+ file.isEmpty());
		
		try {
			//validation
			if(file.isEmpty()) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
			}
			
			if( !file.getContentType().equals("image/jpeg")) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Only jpeg files are allowed");
			}
			
			//file upload code...
			boolean isUpload = fileUploadHelper.uploadFile(file);
			if(isUpload) {
				//return message File upload successfully
				//return ResponseEntity.ok("File upload successfully");
				
				//return image uri after uploading image on server
				//http://localhost:8080/upload-file  this is CurrentContextPath 
				//http://localhost:8080/images/70d6b3aaf2857dce05601505b8ca7db0.jpg  getting this image url  after firing http://localhost:8080/upload-file
				return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().
						path("/images/").path(file.getOriginalFilename()).toUriString() ) ;
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
				
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong !  Try again");
	}
	
	
}
