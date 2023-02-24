package com.bezkoder.spring.login.img;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ConfigImage {

    //pour l'image de l'entite
    public static String saveimg(String uploaDir, String nomfile1, MultipartFile multipartFile) throws IOException {

        Path UploadPath = Paths.get(uploaDir);

        if(!Files.exists(UploadPath)) {
            Files.createDirectories(UploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path fichierPath = UploadPath.resolve(nomfile1);

            Files.copy(inputStream, fichierPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Impossible d'enregistrer le fichier image:" + nomfile1, ioe);
        }
        return "OK";
    }

    //pour le dossier de l'entite
    public static String savedossier(String uploaDir, String nomfile2, MultipartFile multipartFile) throws IOException {

        Path UploadPath = Paths.get(uploaDir);

        if(!Files.exists(UploadPath)) {
            Files.createDirectories(UploadPath);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path fichierPath = UploadPath.resolve(nomfile2);

            Files.copy(inputStream, fichierPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Impossible d'enregistrer le dossier annonce:" + nomfile2, ioe);
        }
        return "OK";
    }



    public static void saveimgA(String uploaDira, String nomfilea, MultipartFile multipartFile) throws IOException{

        Path UploadPatha = Paths.get(uploaDira);

        if(!Files.exists(UploadPatha)) {
            Files.createDirectories(UploadPatha);
        }
        try(InputStream inputStream = multipartFile.getInputStream()){
            Path fichierPatha = UploadPatha.resolve(nomfilea);

            Files.copy(inputStream, fichierPatha, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe){
            throw new IOException("Impossible d'enregistrer le fichier image:" + nomfilea, ioe);
        }
    }
}
