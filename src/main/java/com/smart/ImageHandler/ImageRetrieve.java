package com.smart.ImageHandler;


import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ImageRetrieve {
	
	@Value("${upload.image.directory}")
	private String uploadDirectory;
	
	public boolean storeImage(MultipartFile file) throws IOException
	{
		if(file.isEmpty())
		{
			System.out.println("No image selected");
			return false;
		}
		String filename=file.getOriginalFilename();  //To get the file name
		Path path=Paths.get(uploadDirectory +File.separator +filename);
		
		Files.write(path, file.getBytes());
		return true;
	}

}
