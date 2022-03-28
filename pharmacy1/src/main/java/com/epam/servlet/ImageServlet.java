package com.epam.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.dbconnection.PropertiesUtil;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@WebServlet("/images/*")
public class ImageServlet extends HttpServlet{
	
	private final String basePath = PropertiesUtil.get("image.base.url");
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		String imagePath = requestUri.replace("/images", "");
		
		this.get(imagePath)
			.ifPresent(image -> {
				resp.setContentType("application/octet-stream");
				try {
					writeImage(image, resp);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			
		 

	}
	
	private void writeImage(InputStream image, HttpServletResponse resp) throws IOException {
		ServletOutputStream outputStream = resp.getOutputStream();
		try	{
			int currentByte;
			while ((currentByte = image.read()) != -1) {
				outputStream.write(currentByte);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				image.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				outputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	public Optional<InputStream> get(String imagePath) throws IOException {
		String imageFullPath = basePath + imagePath;
		Path path = Paths.get(imageFullPath);
		return Files.exists(path)
			? Optional.of(Files.newInputStream(path))
			: Optional.empty();
		
		
	}

}
