package com.kdm.KodadProperties.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;


import com.amazonaws.services.s3.model.DeleteObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImagesUploaderService {

    @Value("${bucketName}")
    private String bucketName;

    @Autowired
    private AmazonS3 amazonS3client;

    public String uploadDefaultImage(MultipartFile defImg,String propertyId){

        String fileName=propertyId+defImg.getOriginalFilename();
        try {
            File uploadingFile=new File(defImg.getOriginalFilename());
            FileOutputStream fileOutputStream=new FileOutputStream(uploadingFile);
            fileOutputStream.write(defImg.getBytes());

            amazonS3client.putObject(bucketName,fileName,uploadingFile);

            fileOutputStream.flush();
            fileOutputStream.close();
            uploadingFile.delete();
            return "image uploaded";
        } catch (IOException e) {
            throw new IllegalStateException("image can not be uploaded!!!"+e.getMessage());
        }
    }

    public String uploadAllImages(List<MultipartFile> allImgs,String propertyId){

        try{
        for (MultipartFile file: allImgs){
                String fileName=propertyId+file.getOriginalFilename();
                File uploadingFile=new File(file.getOriginalFilename());
                FileOutputStream fileOutputStream=new FileOutputStream(uploadingFile);
                fileOutputStream.write(file.getBytes());

                amazonS3client.putObject(bucketName,fileName,uploadingFile);

                fileOutputStream.flush();
                fileOutputStream.close();
                uploadingFile.delete();
            }
        }catch (IOException e) {
            throw new IllegalStateException("image can not be uploaded!!!"+e.getMessage());
        }
        return "All Images Uploaded Successfully!!!";
    }

    public String deleteDefaultImage(String key){
        try{
            amazonS3client.deleteObject(bucketName,key);
            return "image deleted successfully!!!";
        }catch (AmazonServiceException e){
            throw new IllegalStateException("unable to delete the default image!!! amazon s3 key: "+key);
        }
    }

    public String  deleteAllImages(List<String> allImgsPath) {
        try{
            for(String key:allImgsPath){
                amazonS3client.deleteObject(bucketName,key);
            }
            return "all images deleted successfully!!!";
        }catch (AmazonServiceException e){
            throw new IllegalStateException("unable to delete all images!!! delete manually");
        }
    }

//    public byte[] downlaodDefaultImage(String key){
//        S3Object s3Object=amazonS3client.getObject(bucketName,key);
//        try {
//            return IOUtils.toByteArray(s3Object.getObjectContent());
//        } catch (IOException e) {
//            throw new IllegalStateException("unable to get image!!!"+e.getMessage());
//        }
//    }

//    public List<byte[]> getAllImages(List<String> allImgsPaths){
//
//        List<byte[]> response=new ArrayList<>();
//
//        try {
//            for (String key : allImgsPaths) {
//                S3Object s3Object = amazonS3client.getObject(bucketName, key);
//                response.add(IOUtils.toByteArray(s3Object.getObjectContent()));
//            }
//
//            return response;
//        }catch (IOException | AmazonServiceException e) {
//            throw new IllegalStateException("unable to load images!!!"+e.getMessage());
//        }
//    }

}
