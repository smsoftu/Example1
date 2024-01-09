package com.example.CustomJavaBeckend;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Image;
import com.itextpdf.kernel.pdf.*; 
 
 

import jakarta.annotation.Resource;


@RestController
public class UserController {
	
	;
	@Autowired
	private UserService userService;
	
	
	static {
		UserService.initialisation();
	}
	
	@CrossOrigin(origins ="*")
	@PostMapping("/login")
	public ResponseEntity<Resp> login(@RequestBody LoginEntity loginEntity) {
		String sessionId=RequestContextHolder.getRequestAttributes().getSessionId();
		return new ResponseEntity(userService.login(loginEntity,sessionId),HttpStatus.OK);
	}
	
	@CrossOrigin(origins ="*")
	@PostMapping("/setLimit")
	public ResponseEntity<Resp> setLimit(@RequestBody LimitEntity limitEntity) {
		return new ResponseEntity(userService.setLimit(limitEntity),HttpStatus.OK);
	}
	
	@CrossOrigin(origins ="*")
	@PostMapping("/logout")
	public ResponseEntity<ResponseUp> logout(@RequestBody LogoutEntity logoutEntity) {
		
		userService.logout(logoutEntity);
		
		return new ResponseEntity<ResponseUp>(new ResponseUp("OK"),HttpStatus.OK); 
	}

	
	
	@CrossOrigin(origins ="*")
	@RequestMapping(path = "/upload-file", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<ResponseUp> uploadFile(@ModelAttribute UploadParamsEntity uploadParamsEntity) {
		
		byte[] content=null;
		List<UploadEntity> list;
		try {
			list=UserService.map.get(uploadParamsEntity.getSessionId());
			if (list==null) {
				throw new Exception("list is null for sessionId="+uploadParamsEntity.getSessionId());
			}
			content=uploadParamsEntity.getFile().getBytes();
			
			if (content.length==0) {
				throw new Exception("content.length=0 for sessionId="+uploadParamsEntity.getSessionId());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<ResponseUp>(new ResponseUp("Error:"+e.getMessage()),HttpStatus.OK);
		} catch(Exception e1) {
			return new ResponseEntity<ResponseUp>(new ResponseUp("Error:"+e1.getMessage()),HttpStatus.OK);
		}
		try {
		UploadEntity uploadEntity=new UploadEntity();
		uploadEntity.setContent(content);
		uploadEntity.setFileName(uploadParamsEntity.getFileName());
		uploadEntity.setId(uploadParamsEntity.getCounter());
		boolean found=false;
		for(int i=0;i<list.size();i++) {
			UploadEntity up=list.get(i);
			if (up.getId()==uploadParamsEntity.getCounter()) {
				found=true;
				up.setContent(content);
				up.setFileName(uploadParamsEntity.getFileName());
				break;
			}
		}
		if (!found) {
			list.add(uploadEntity);
			
		}
		UserService.map.put(uploadParamsEntity.getSessionId(),list);
		} catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<ResponseUp>(new ResponseUp("Error:"+e.getMessage()),HttpStatus.OK);

		}
		
		return new ResponseEntity<ResponseUp>(new ResponseUp("OK"),HttpStatus.OK);
	}
	
	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ResponseEntity<ByteArrayResource> download(@ModelAttribute DownloadEntity downloadEntity) throws IOException {
		
		List<UploadEntity> list = UserService.map.get(downloadEntity.getSessionId());
		
		try {
			
			
			 String fileName=downloadEntity.getOutputFileName();
			 if (!fileName.toLowerCase().endsWith(".pdf")) {
				 fileName+=".pdf";
			 }
			File pdfPath=new File(fileName);
			ByteArrayOutputStream os=new ByteArrayOutputStream();
			PdfWriter writer = new PdfWriter(os); 
		        
		        // Creating PdfWriter object to write pdf file to 
		        // the path 
		        PdfDocument pdfDoc = new PdfDocument(writer); 
		        
		        // Creating a PdfDocument object 
		        Document doc = new Document(pdfDoc);
		        doc.getPdfDocument().addNewPage();
		        Collections.sort(list,new UploadEntityComparator());
		
		        for(int i=0;i<list.size();i++) {
		        
		        	UploadEntity ue=list.get(i); 
		        	byte[] b=ue.getContent();
		        	if (b!=null) {
		        	ImageData imageData = ImageDataFactory.create(b);
		        	Image img = new Image(imageData);

		        	doc.add(img);
		        	}
		        }
		
		        doc.close();
		        UserService.map.put(downloadEntity.getSessionId(),new ArrayList<UploadEntity>());
		        ByteArrayResource resource=new ByteArrayResource(os.toByteArray());
		        HttpHeaders headers=new HttpHeaders();
		        headers.add("Content-Disposition", "attachment; filename=" + fileName);
		
		        return ResponseEntity.ok()
		        		.headers(headers)
		        		.contentLength(os.size())
		        		.contentType(MediaType.APPLICATION_OCTET_STREAM)
		        		.body(resource);
		
		} catch(Exception e) {
			e.printStackTrace();
			String mesage=e.getStackTrace().toString();
			ByteArrayResource body=new ByteArrayResource(mesage.getBytes());
			return new ResponseEntity<ByteArrayResource>(body,HttpStatus.OK);
		}
        
	}

}
