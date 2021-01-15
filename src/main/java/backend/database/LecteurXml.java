package backend.database;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
/*
 * Classe qui permet de lire le fichier xml
 * et extrait les informations du fichier
 */
public final class LecteurXml {

   public static class Role {
      public Role(int i, String n, String p) {
         id = i;
         nom = n;
         personnage = p;
      }
      protected int id;
      protected String nom;
      protected String personnage;
   }

   public LecteurXml() {
      connectionBD();
   }


   public void lecturePersonnes(String nomFichier){
      try {
         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
         XmlPullParser parser = factory.newPullParser();

         InputStream is = new FileInputStream(nomFichier);
         parser.setInput(is, null);

         int eventType = parser.getEventType();

         String tag = null,
                 nom = null,
                 anniversaire = null,
                 lieu = null,
                 photo = null,
                 bio = null;

         int id = -1;

         while (eventType != XmlPullParser.END_DOCUMENT)
         {
            if(eventType == XmlPullParser.START_TAG)
            {
               tag = parser.getName();

               if (tag.equals("personne") && parser.getAttributeCount() == 1)
                  id = Integer.parseInt(parser.getAttributeValue(0));
            }
            else if (eventType == XmlPullParser.END_TAG)
            {
               tag = null;

               if (parser.getName().equals("personne") && id >= 0)
               {
                  insertionPersonne(id,nom,anniversaire,lieu,photo,bio);

                  id = -1;
                  nom = null;
                  anniversaire = null;
                  lieu = null;
                  photo = null;
                  bio = null;
               }
            }
            else if (eventType == XmlPullParser.TEXT && id >= 0)
            {
               if (tag != null)
               {
                  switch (tag) {
                     case "nom":
                        nom = parser.getText();
                        break;
                     case "anniversaire":
                        anniversaire = parser.getText();
                        break;
                     case "lieu":
                        lieu = parser.getText();
                        break;
                     case "photo":
                        photo = parser.getText();
                        break;
                     case "bio":
                        bio = parser.getText();
                        break;
                  }
               }
            }

            eventType = parser.next();
         }
      }
      catch (XmlPullParserException e) {
         System.out.println(e);
      }
      catch (IOException e) {
         System.out.println("IOException while parsing " + nomFichier);
      }
   }

   public void lectureFilms(String nomFichier){
      try {
         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
         XmlPullParser parser = factory.newPullParser();

         InputStream is = new FileInputStream(nomFichier);
         parser.setInput(is, null);

         int eventType = parser.getEventType();

         String tag = null,
                 titre = null,
                 langue = null,
                 poster = null,
                 roleActeur = null,
                 rolePersonnage = null,
                 realisateurNom = null,
                 resume = null;

         ArrayList<String> pays = new ArrayList<>();
         ArrayList<String> genres = new ArrayList<>();
         ArrayList<String> scenaristes = new ArrayList<>();
         ArrayList<Role> roles = new ArrayList<Role>();
         ArrayList<String> annonces = new ArrayList<String>();

         int id = -1,
                 annee = -1,
                 duree = -1,
                 roleId = -1,
                 realisateurId = -1;

         while (eventType != XmlPullParser.END_DOCUMENT)
         {
            if(eventType == XmlPullParser.START_TAG)
            {
               tag = parser.getName();

               if (tag.equals("film") && parser.getAttributeCount() == 1)
                  id = Integer.parseInt(parser.getAttributeValue(0));
               else if (tag.equals("realisateur") && parser.getAttributeCount() == 1)
                  realisateurId = Integer.parseInt(parser.getAttributeValue(0));
               else if (tag.equals("acteur") && parser.getAttributeCount() == 1)
                  roleId = Integer.parseInt(parser.getAttributeValue(0));
            }
            else if (eventType == XmlPullParser.END_TAG)
            {
               tag = null;

               if (parser.getName().equals("film") && id >= 0)
               {
                  insertionFilm(id,titre,annee,pays,langue,
                          duree,resume,genres,realisateurNom,
                          realisateurId, scenaristes,
                          roles,poster,annonces);

                  id = -1;
                  annee = -1;
                  duree = -1;
                  titre = null;
                  langue = null;
                  poster = null;
                  resume = null;
                  realisateurNom = null;
                  roleActeur = null;
                  rolePersonnage = null;
                  realisateurId = -1;
                  roleId = -1;

                  genres.clear();
                  scenaristes.clear();
                  roles.clear();
                  annonces.clear();
                  pays.clear();
               }
               if (parser.getName().equals("role") && roleId >= 0)
               {
                  roles.add(new Role(roleId, roleActeur, rolePersonnage));
                  roleId = -1;
                  roleActeur = null;
                  rolePersonnage = null;
               }
            }
            else if (eventType == XmlPullParser.TEXT && id >= 0)
            {
               if (tag != null)
                  if (tag.equals("titre"))
                     titre = parser.getText();
                  else if (tag.equals("annee"))
                     annee = Integer.parseInt(parser.getText());
                  else if (tag.equals("pays"))
                     pays.add(parser.getText());
                  else if (tag.equals("langue"))
                     langue = parser.getText();
                  else if (tag.equals("duree"))
                     duree = Integer.parseInt(parser.getText());
                  else if (tag.equals("resume"))
                     resume = parser.getText();
                  else if (tag.equals("genre"))
                     genres.add(parser.getText());
                  else if (tag.equals("realisateur"))
                     realisateurNom = parser.getText();
                  else if (tag.equals("scenariste"))
                     scenaristes.add(parser.getText());
                  else if (tag.equals("acteur"))
                     roleActeur = parser.getText();
                  else if (tag.equals("personnage"))
                     rolePersonnage = parser.getText();
                  else if (tag.equals("poster"))
                     poster = parser.getText();
                  else if (tag.equals("annonce"))
                     annonces.add(parser.getText());
            }

            eventType = parser.next();
         }
      }
      catch (XmlPullParserException e) {
         System.out.println(e);
      }
      catch (IOException e) {
         System.out.println("IOException while parsing " + nomFichier);
      }
   }

   public void lectureClients(String nomFichier){
      try {
         XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
         XmlPullParser parser = factory.newPullParser();

         InputStream is = new FileInputStream(nomFichier);
         parser.setInput(is, null);

         int eventType = parser.getEventType();

         String tag = null,
                 nomFamille = null,
                 prenom = null,
                 courriel = null,
                 tel = null,
                 anniv = null,
                 adresse = null,
                 ville = null,
                 province = null,
                 codePostal = null,
                 carte = null,
                 noCarte = null,
                 motDePasse = null,
                 forfait = null;

         int id = -1,
                 expMois = -1,
                 expAnnee = -1;

         while (eventType != XmlPullParser.END_DOCUMENT)
         {
            if(eventType == XmlPullParser.START_TAG)
            {
               tag = parser.getName();

               if (tag.equals("client") && parser.getAttributeCount() == 1)
                  id = Integer.parseInt(parser.getAttributeValue(0));
            }
            else if (eventType == XmlPullParser.END_TAG)
            {
               tag = null;

               if (parser.getName().equals("client") && id >= 0)
               {
                  insertionClient(id,nomFamille,prenom,courriel,tel,
                          anniv,adresse,ville,province,
                          codePostal,carte,noCarte,
                          expMois,expAnnee,motDePasse,forfait);

                  nomFamille = null;
                  prenom = null;
                  courriel = null;
                  tel = null;
                  anniv = null;
                  adresse = null;
                  ville = null;
                  province = null;
                  codePostal = null;
                  carte = null;
                  noCarte = null;
                  motDePasse = null;
                  forfait = null;

                  id = -1;
                  expMois = -1;
                  expAnnee = -1;
               }
            }
            else if (eventType == XmlPullParser.TEXT && id >= 0)
            {
               if (tag != null)
               {
                  switch (tag) {
                     case "nom-famille":
                        nomFamille = parser.getText();
                        break;
                     case "prenom":
                        prenom = parser.getText();
                        break;
                     case "courriel":
                        courriel = parser.getText();
                        break;
                     case "tel":
                        tel = parser.getText();
                        break;
                     case "anniversaire":
                        anniv = parser.getText();
                        break;
                     case "adresse":
                        adresse = parser.getText();
                        break;
                     case "ville":
                        ville = parser.getText();
                        break;
                     case "province":
                        province = parser.getText();
                        break;
                     case "code-postal":
                        codePostal = parser.getText();
                        break;
                     case "carte":
                        carte = parser.getText();
                        break;
                     case "no":
                        noCarte = parser.getText();
                        break;
                     case "exp-mois":
                        expMois = Integer.parseInt(parser.getText());
                        break;
                     case "exp-annee":
                        expAnnee = Integer.parseInt(parser.getText());
                        break;
                     case "mot-de-passe":
                        motDePasse = parser.getText();
                        break;
                     case "forfait":
                        forfait = parser.getText();
                        break;
                  }
               }
            }

            eventType = parser.next();
         }
      }
      catch (XmlPullParserException e) {
         System.out.println(e);
      }
      catch (IOException e) {
         System.out.println("IOException while parsing " + nomFichier);
      }
   }

   private void insertionPersonne(int id, String nom, String anniv, String lieu, String photo, String bio) {
      // On insere la personne dans la BD
   }

   private void insertionFilm(int id, String titre, int annee,
                              ArrayList<String> pays, String langue, int duree, String resume,
                              ArrayList<String> genres, String realisateurNom, int realisateurId,
                              ArrayList<String> scenaristes,
                              ArrayList<Role> roles, String poster,
                              ArrayList<String> annonces) {
      // On le film dans la BD
   }

   private void insertionClient(int id, String nomFamille, String prenom,
                                String courriel, String tel, String anniv,
                                String adresse, String ville, String province,
                                String codePostal, String carte, String noCarte,
                                int expMois, int expAnnee, String motDePasse,
                                String forfait) {
      // On le client dans la BD
   }

   private void connectionBD() {
      // On se connecte a la BD
      try {
         RSQUser user = new RSQUser("EQUIPE104", "SSW5H3lj");
         RSQAddress hostAddress = new RSQAddress("gti660ora12c.logti.etsmtl.ca","1521"," GTI660");
         RSQConnect dbConnect = new RSQConnect();
         dbConnect.open(user, hostAddress);
         //creation fichier log
         RSQLog.createLogFile("..\\back\\src\\main\\resources\\logFile.log");
         RSQLog.init();
         if(dbConnect.isConnected) {
            RSQLog.message(Level.INFO, "Connection a la BD reussie !");

         } else {
            RSQLog.message(Level.SEVERE, "Connection a la BD Echoue !");
         }


      } catch (Exception e) {
         System.err.println(e.getMessage());
         e.printStackTrace();
      }
   }


}
