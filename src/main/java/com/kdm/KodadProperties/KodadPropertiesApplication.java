package com.kdm.KodadProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KodadPropertiesApplication {

	public static void main(String[] args) {
		SpringApplication.run(KodadPropertiesApplication.class, args);
	}

}



//TODO: IMPLEMENTING SECURITY TO ADMIN PATHS AND JWT TOKENS


// TODO:"${DON'T FORGET TO CONFIGURE MULTIPART REQUEST SIZE LIMITS}"
//TODO: ADD DATE TO THE PROPERTY MODEL
//TODO: UPDATE STATUS OF THE PROPERTY BEFORE DELETING



/*** PROJECT LEARINGS
 *
 * @REQUESTPART ENABLES TO SEND ALL JSON DATA AS A STRING VALUE ALONG WITH MULTIPART FILES AND THE BACKEND WILL
 * CONVERT IT INTO JSON DATA
 *
 * ENABLE GETTER(IF FIELDS ARE PRIVATE) ON RESPONSE CLASS(POJO) OR ELSE IT THROWS NOSERIALIZERFOUNDFORCLASS ERROR..
 * OR MAKE FIELDS PUBLIC
 *
 * WHILE UPLOADING TO S3 SHOULD WRITE THE BYTES OF IMAGE TO A FILE AND WE SHOULD UPLOAD IT
 *
 * WHILE DOWNLOADING JUST GET THE S3OBJECT AND USE  IOUTILS TO CONVERT THAT S3OJBJECT CONTENT TO BYTE ARRAY
 *
 * ***/
