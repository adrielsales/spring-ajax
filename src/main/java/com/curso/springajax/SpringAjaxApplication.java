package com.curso.springajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.curso.springajax.service.SocialMetaTagService;

@SpringBootApplication
public class SpringAjaxApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringAjaxApplication.class, args);
	}
	
	@Autowired
	SocialMetaTagService service;

	@Override
	public void run(String... args) throws Exception {
		
		/*facebook ou twitter*/
//		SocialMetaTag ogFace = service.getSocialMetaTagByUrl("https://www.youtube.com/watch?v=AaAE33rMTSk");
//		System.out.println(ogFace.toString());
		
		/*twitter*/
//		SocialMetaTag ogTwitter = service.getSocialMetaTagByUrl("https://www.youtube.com/watch?v=Pd5hBbzs-_Y");
//		System.out.println(ogTwitter.toString());
	}

}
