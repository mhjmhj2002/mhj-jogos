package com.mhj.jogos.infra;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	@Autowired
	private HttpServletRequest request;

	public File write(String baseFolder, MultipartFile file) {
	    try {
	    	String realPath = request.getServletContext().getRealPath("/"+baseFolder);	    	
	        File dir = new File(realPath);
	        if (!dir.exists()) {
	            dir.mkdir();
	        }
	        String path = realPath + "/" + file.getOriginalFilename();
	        File retorno = new File(path);
	        file.transferTo(retorno);
	        return retorno;

	    } catch (IllegalStateException | IOException e) {
	        throw new RuntimeException(e);
	    }
	}
}
