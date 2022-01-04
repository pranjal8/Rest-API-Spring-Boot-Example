package com.pranjal.restapi.helper;

import java.io.File;
import java.io.IOException;
//import java.io.FileOutputStream;
//import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	//static
	//public final String UPLOAD_DIR ="C:\\Users\\Hp\\eclipse-workspace\\spring-boot-projects\\book-rest-example\\src\\main\\resources\\static\\images" ; 
	
	//to serve resources dyamically 
	//path of folder where we can upload images
	public final String UPLOAD_DIR =new ClassPathResource("static/images/").getFile().getAbsolutePath() ; 
	
	public FileUploadHelper() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}


	public boolean uploadFile(MultipartFile file) {
		boolean f= false;	
		try {
			/*
			//old API to write file
			//step 1
			 InputStream inputStream = file.getInputStream();
			 byte data[] = new byte[inputStream.available()];
			inputStream.read(data);
			
			//step 2
			//write file
			FileOutputStream fos= new FileOutputStream(UPLOAD_DIR + File.separator + file.getOriginalFilename());
			System.out.println(fos);
			fos.write(data);
			
			//step 3
			//close fos
			fos.flush();
			fos.close();
			
			//step 4
			f=true;
			*/
			
			//new API to upload file in folder
			Files.copy(file.getInputStream() ,
					       Paths.get( UPLOAD_DIR +File.separator + file.getOriginalFilename()) , 
					       StandardCopyOption.REPLACE_EXISTING);
			
			f=true;
			
		} catch (Exception e) {
			e.printStackTrace();	
		}
				return f;
	}
	
	
}
