package com.bezkoder.spring.login.img;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class SaveImage {

    public static String localhost = "http://127.0.0.1/";
    public static String serveradmin = localhost + "ANPE/image/Admin/";

    public static String serverdossier = localhost + "ANPE/dossier/";
    public static String serverdemandeur = localhost + "ANPE/image/Demandeur/";
    public static String serverdemandeurcv = localhost + "ANPE/image/Demandeurcv/";
    public static String serverdemandeurdossier = localhost + "ANPE/image/Demandeurdossier/";
    public static String serverrecruteur = localhost + "ANPE/image/Recruteur/";
    public static String serverannonce = localhost + "ANPE/image/Annonce/";
    public static String serverdossierannonce = localhost + "ANPE/image/Dossierannonce/";
    public static String Annoncelocation = "C:/wamp64/www/ANPE/image/Annonce/";
    public static String Demandeurlocation = "C:/wamp64/www/ANPE/image/Demandeur/";

    public static String Dossierannoncelocation = "C:/wamp64/www/ANPE/image/Dossierannonce/";

    public static String Demandeurdossierlocation = "C:/wamp64/www/ANPE/image/Demandeurdossier/";
    public static String Demandeurcvlocation = "C:/wamp64/www/ANPE/image/Demandeurcv/";
    public static String Adminlocation = "C:/wamp64/www/ANPE/image/Admin/";
    public static String Recruteurlocation = "C:/wamp64/www/ANPE/image/Recruteur/";

    public static String Dossierlocation = "C:/wamp64/www/ANPE/dossier/";

    public static String save(String typeImage, MultipartFile file, String nomFichier) {
        String src = "";
        String server = "";
        String location = "";
        if (typeImage == "Admin") {
            location = Adminlocation;
            server = serveradmin;
        } else if(typeImage == "Annonce"){
            location = Annoncelocation;
            server = serverannonce;

        }
        else if(typeImage == "DossierAnnonce"){
            location = Dossierannoncelocation;
            server = serverdossierannonce;

        }else if(typeImage == "Demandeur"){
            location = Demandeurlocation;
            server = serverdemandeur;

        }
        else if(typeImage == "Recruteur"){
            location = Recruteurlocation;
            server = serverrecruteur;

        }else if(typeImage == "DemandeurCV"){
            location = Demandeurcvlocation;
            server = serverdemandeurcv;

        }else if(typeImage == "Dossierannonce"){
            location = Dossierlocation;
            server = serverdossier;
        }
        else if(typeImage == "Dossierdemandeur"){
            location = Dossierlocation;
            server = serverdossier;
        }

        else if(typeImage == "DemandeurDossier"){
            location = Demandeurdossierlocation;
            server = serverdemandeurdossier;

        }  else{
            location = Recruteurlocation;
            server = serverrecruteur;

        }


        /// debut de l'enregistrement
        try {
            int index = Objects.requireNonNull(file.getOriginalFilename()).lastIndexOf(".");

            Path chemin = Paths.get(location);
            if (!Files.exists(chemin)) {
                // si le fichier n'existe pas deja
                Files.createDirectories(chemin);
                Files.copy(file.getInputStream(), chemin
                        .resolve(nomFichier + file.getOriginalFilename()+file.getOriginalFilename().substring(index).toLowerCase()));
                src = server + nomFichier
                        + file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase();
            } else {
                // si le fichier existe pas deja
                String newPath = location + nomFichier +file.getOriginalFilename()
                        + file.getOriginalFilename().substring(index).toLowerCase();
                Path newchemin = Paths.get(newPath);
                if (!Files.exists(newchemin)) {
                    // si le fichier n'existe pas deja
                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier +file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier +file.getOriginalFilename()
                            + file.getOriginalFilename().substring(index).toLowerCase();
                } else {
                    // si le fichier existe pas deja on le suprime et le recrÃ¨e

                    Files.delete(newchemin);

                    Files.copy(file.getInputStream(), chemin
                            .resolve(
                                    nomFichier + file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase()));
                    src = server + nomFichier
                            +file.getOriginalFilename()+ file.getOriginalFilename().substring(index).toLowerCase();
                }

            }
        } catch (Exception e) {
            System.out.println(e);
            // TODO: handle exception
            src = null;
        }

        return src;
    }
}
