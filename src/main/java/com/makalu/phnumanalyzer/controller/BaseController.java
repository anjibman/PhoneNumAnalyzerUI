package com.makalu.phnumanalyzer.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.makalu.phnumanalyzer.form.FileUploadForm;
import com.makalu.phonenum.core.dto.PhoneDto;
import com.makalu.phonenum.core.service.PhoneNumAnalyzerService;
import com.makalu.phonenum.core.service.PhoneNumAnalyzerServiceImpl;
import com.makalu.phonenum.core.util.FileUtil;

@Controller
public class BaseController {

	@RequestMapping("/home.htm")
	public ModelAndView test() {
		return new ModelAndView("uploadForm");
	}

	@RequestMapping(value = "/upload.htm", method = RequestMethod.POST)
	public ModelAndView upload(
			@ModelAttribute("uploadForm") FileUploadForm uploadForm,
			BindingResult result) {
		FileUtil fileUtil = new FileUtil();
		
		InputStream inputStream = null;
		OutputStream outputStream = null;
		
		String serverFileName = null;

		if (result.hasErrors()) {
			return new ModelAndView("uploadForm");
		}

		MultipartFile file = uploadForm.getFile();
		String fileName = file.getOriginalFilename();

		try {
			inputStream = file.getInputStream();
			serverFileName = fileName + new Date().getTime();
			outputStream = new FileOutputStream(
					new File("C:\\PhoneNumAnalyzer\\files\\" + serverFileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	 
			}
		}

		List<String> phoneList = fileUtil
				.generatePhoneList("C:\\PhoneNumAnalyzer\\files\\" + serverFileName);

		PhoneNumAnalyzerService phoneNumAnalyzerService = new PhoneNumAnalyzerServiceImpl();
		List<PhoneDto> processedList = phoneNumAnalyzerService.processPhoneList(phoneList);

		return new ModelAndView("result", "phoneList", processedList);
	}
}
