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
			StringBuilder filesNames = new StringBuilder();
			Path fileNameAndPath = Paths.get(uploadDirectory , file.getOriginalFilename());
			filesNames.append(file.getOriginalFilename());
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			model.addAttribute("msg" , "successfully uploaded files "+filesNames.toString());
			userManagement.getUserById(id).getInformations().setProfileImagePath(file.getOriginalFilename());
			userManagement.updateUser(userManagement.getUserById(id));
		}
		return "redirect:/users" ;
	}
	
	private boolean validateImage(MultipartFile file) {
		
		return true;
	}

	
}
