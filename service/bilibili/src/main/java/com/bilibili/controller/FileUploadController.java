/**
 * @ Tool：IntelliJ IDEA
 * @ Author：wkl
 * @ Date：2025-04-15-16:42
 * @ Version：1.0
 * @ Description：文件上传，用户头像等，后期集成视频上传
 */

package com.bilibili.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;

import com.bilibili.exception.BlibiliException;
import com.bilibili.result.Result;
import com.bilibili.service.COSUploadService;
import com.bilibili.service.UserService;
import com.qcloud.cos.COSClient;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("upload")
public class FileUploadController {
        private final COSUploadService cosUploadService;
    @Autowired
    public FileUploadController(COSClient cosClient) {
        this.cosUploadService = new COSUploadService(cosClient, "bilibili-1310247625");
    }

        @Autowired
        private COSClient cosClient;

//        @Autowired
//        private MinioConfig minioConfig;
//        @Autowired
//        private MinioClient minioClient;
        @Autowired
    private UserService userService;

    @Value("${minio.bucketName}")
    private String bucketName;

//    @PostConstruct
//    public void init() throws Exception {
//        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
//            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
//        }
//    }
    @PostMapping("/video")
    @SaCheckLogin
    public Result uploadVideoFileToCOS(@RequestParam("file") MultipartFile file) {
        String dictory = "bilibili/"; // 上传到的目录
        String fileUrl = cosUploadService.uploadFileToCOS(file, dictory);

        if (fileUrl != null) {
            return Result.ok(fileUrl); // 返回上传后的文件URL
        } else {
            return Result.fail("上传失败");
        }
    }
    @PostMapping("file")
    @SaCheckLogin
    public Result uploadCommonfile(@RequestParam("file") MultipartFile file){
        String type = file.getContentType();
        String dictory=type+"/";
        String uploaded = cosUploadService.uploadFileToCOS(file, dictory);
        if (uploaded == null) {
            throw new BlibiliException(200,"上传失败");
        }
        return Result.ok(uploaded);
    }

//
//    @PostMapping("/cos")
//    public Result uploadFileToCOS(@RequestParam("file") MultipartFile file) throws Exception {
//        String fileName = file.getOriginalFilename();
//        File localFile = File.createTempFile("temp-", "-" + fileName);
//        file.transferTo(localFile);
//        bucketName = "bilibili-1310247625";
//        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, fileName,  localFile);
//// 设置存储类型（如有需要，不需要请忽略此行代码）, 默认是标准(Standard), 低频(standard_ia)
//// 更多存储类型请参见 https://cloud.tencent.com/document/product/436/33417
//        putObjectRequest.setStorageClass(StorageClass.Standard_IA);
//
//        try {
//            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
//            System.out.println(putObjectResult.getRequestId());
//          String url= String.valueOf(cosClient.getObjectUrl(bucketName,fileName));
//            return Result.ok(url);
//
//        } catch (CosServiceException e) {
//            e.printStackTrace();
//        } catch (CosClientException e) {
//            e.printStackTrace();
//        } finally {
//            // 确认本进程不再使用 cosClient 实例之后，关闭即可
//            cosClient.shutdown();
//            localFile.delete();
//
//        }
//        return Result.fail("上传失败");
//    }

//    @PostMapping("/file")
//    public Result uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
//        String fileName = file.getOriginalFilename();
//        MinioClient minioClient = MinioClient.builder().endpoint(minioConfig.getEndpoint())
//                .credentials(minioConfig.getAccessKey(), minioConfig.getSecretKey())
//                .build();
//        String contentType = file.getContentType();
//        assert contentType != null;
//        if (!contentType.startsWith("image/") && !contentType.startsWith("video/")) {
//            throw new IllegalArgumentException("只允许上传图片和视频文件");
//        }
//
////        接下啦就可以使用minioClient进行文件上传了
//        minioClient.putObject(PutObjectArgs.builder()
//                .bucket(bucketName).object(fileName).stream(file.getInputStream(), file.getSize(), -1).
//                contentType(file.getContentType()).build());
//        String url= minioConfig.getEndpoint()+"/"+bucketName+"/"+fileName;
//
//        return  Result.ok(url) ;
//    }
    @PostMapping("/avatar")
    @SaCheckLogin
    public Result uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws Exception {
        if (avatar.getSize() > 5 * 1024 * 1024) { // 5MB
            throw new IllegalArgumentException("头像只允许上传5MB以内的文件");
        }
        String contentType = avatar.getContentType();
        assert contentType!= null;
        if (!contentType.startsWith("image/")) {

            throw new IllegalArgumentException("只允许上传图片文件");
        }
        Long userId = StpUtil.getLoginIdAsLong();
        String fileName = userId + "-" + UUID.randomUUID() + ".jpg";
        String url = cosUploadService.uploadFileToCOS(avatar, "avatars/");
        return  Result.ok(url);

    }
//    @PostMapping("/avatar")
//    @SaCheckLogin
//    public Result uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws Exception {
//        if (avatar.getSize() > 5 * 1024 * 1024) { // 10MB
//            throw new IllegalArgumentException("头像只允许上传5MB以内的文件");
//        }
//        String contentType = avatar.getContentType();
//        assert contentType != null;
//        if (!contentType.startsWith("image/")) {
//            throw new IllegalArgumentException("只允许上传图片和视频文件");
//        }
//        Long userId = StpUtil.getLoginIdAsLong();
//        String fileName = "avatars/" + userId + "-" + UUID.randomUUID() + ".jpg";
//        minioClient.putObject(PutObjectArgs.builder()
//                .bucket(bucketName)
//                .object(fileName)
//                .stream(avatar.getInputStream(), avatar.getSize(), -1)
//                .contentType(avatar.getContentType())
//                .build());
//        String url= minioConfig.getEndpoint()+"/"+bucketName+"/"+fileName;
//        userService.updateAvatar(userId,url);
//        return   Result.ok(url);
//    }


}
