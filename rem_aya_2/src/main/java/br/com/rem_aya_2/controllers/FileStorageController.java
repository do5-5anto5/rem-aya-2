package br.com.rem_aya_2.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.rem_aya_2.data_vo_v1.UploadFileResponseVO;
import br.com.rem_aya_2.services.FileStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;

@Tag(name = "File Endpoint")
@RestController
@RequestMapping("/api/file/v1")
public class FileStorageController {

	private Logger logger = Logger.getLogger(FileStorageController.class.getName()); 
	
	@Autowired
	private FileStorageService service;
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://github.com/do5-5anto5"})
	@PostMapping("/uploadFile")
	@Operation(summary = "Uploads a File", description = "Uploads a File",
		responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		}
		)
	public UploadFileResponseVO uploadFile(@RequestParam("file") MultipartFile file) {
		logger.info("Uploading a File.");
		
		var filename = service.storeFile(file);
		
		String fileDownloadUri = ServletUriComponentsBuilder
			.fromCurrentContextPath()
			.path("/api/file/v1/downloadFile/")
			.path(filename).toUriString();
			
		return new UploadFileResponseVO(filename, fileDownloadUri, file.getContentType(), file.getSize());
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://github.com/do5-5anto5"})
	@PostMapping("/uploadMultipleFiles")
	@Operation(summary = "Uploads Multiple Files", description = "Uploads Multiple Files",
	responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
			)
	public List<UploadFileResponseVO> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
		logger.info("Uploading Multiple Files.");
		
		return Arrays.asList(files)
			.stream()
			.map(file -> uploadFile(file))
			.collect(Collectors.toList());
	}
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://github.com/do5-5anto5"})
	@GetMapping("/downloadFile/{filename:.+}")
	@Operation(summary = "Downloads a File.", description = "Downloads a File.",
	responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),			
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),			
			@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
	}
			)
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) {
		logger.info("Downloading a File.");

		Resource resource = service.loadFileAsResource(filename);
		String contentType = "";
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (Exception e) {
			logger.info("Could not determine file type");
		}
		
		if (contentType.isBlank()) contentType = "/application/octet-stream";
		
		return ResponseEntity.ok()
			.contentType(MediaType.parseMediaType(contentType))
			.header(
				HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + resource.getFilename() + "\"")
			.body(resource);
	}
}
