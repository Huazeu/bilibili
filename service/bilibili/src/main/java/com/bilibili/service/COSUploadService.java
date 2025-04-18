package com.bilibili.service;

import cn.dev33.satoken.stp.StpUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class COSUploadService {

    private final COSClient cosClient;
    private final String bucketName;

    public COSUploadService(COSClient cosClient, @Value("${cos.bucketName}") String bucketName) {
        this.cosClient = cosClient;
        this.bucketName = bucketName;
    }

    public String uploadFileToCOS(MultipartFile file, String directory) {
        try {
            // 生成唯一的文件名，避免文件重名
            String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();

            // 上传到COS的路径
            if (directory != null && directory.equals("avatars/")) {
                fileName =  "avatars/" + StpUtil.getLoginIdAsLong()+"-" + fileName;
            }else if (directory!= null && directory.equals("bilibili/")) {
                fileName =directory+ "videos/" + StpUtil.getLoginIdAsLong() +"-"+ fileName;
            }

            // 临时文件
            File localFile = File.createTempFile("temp-", "-" + file.getOriginalFilename());
            file.transferTo(localFile);

            // 创建PutObjectRequest请求对象
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName, localFile);

            // 上传文件到COS
            PutObjectResult result = cosClient.putObject(putObjectRequest);

            // 返回文件的公开URL
            return cosClient.getObjectUrl(bucketName, fileName).toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {
            cosClient.shutdown();
        }
    }
}
