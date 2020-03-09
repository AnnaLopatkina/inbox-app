package com.inbox.app.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.MimetypesFileTypeMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.inbox.app.user.UserManagement;

@Controller
public class FileUploadController {
	public static String uploadDirectory = System.getProperty("user.dir")+ "/src/main/resources/static/resources/users_images";
	
	private final UserManagement userManagement;
	
	public FileUploadController(UserManagement userManagement) {
		this.userManagement = userManagement ;
	}
	
	@PostMapping("/upload-image/{id}")
	public String upload(Model model , @RequestParam("files") MultipartFile file , @PathVariable Long id) {
		
		if(validateImage(file)) {
			String fileName = generateRandowString(20);
			Path fileNameAndPath = Paths.get(uploadDirectory , fileName);
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			userManagement.getUserById(id).getInformations().setProfileImagePath(fileName);
			userManagement.updateUser(userManagement.getUserById(id));
			
		}
		return "redirect:/users" ;
	}
	
	private boolean validateImage(MultipartFile file) {
		if(file.getOriginalFilename().contains(".png") || file.getOriginalFilename().contains(".PNG")
				|| file.getOriginalFilename().contains(".jpg") || file.getOriginalFilename().contains(".jpeg") ||
				file.getOriginalFilename().contains(".JPG") || file.getOriginalFilename().contains(".JPEG") || 
				file.getOriginalFilename().contains(".gif") || file.getOriginalFilename().contains(".GIF")) {
			return true;
		}
		return false;
	}

	private String generateRandowString(int n) 
    { 
  
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
	
}
