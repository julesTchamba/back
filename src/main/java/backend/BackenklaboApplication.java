package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import backend.model.LecteurXml;

@SpringBootApplication
public class BackenklaboApplication {
	public static void main(String[] args) {
		SpringApplication.run(BackenklaboApplication.class, args);
		lireFichierXml();
	}

	private static void lireFichierXml() {
			//lecture des fichiers et insertion dans la base de donnees
			LecteurXml lb = new LecteurXml();
			lb.lecturePersonnes("..\\laboratoire-1_backend\\src\\main\\resources\\fichiersXML\\personnes_utf8.xml");
			//lb.lectureFilms("..\\laboratoire-1_backend\\src\\main\\resources\\fichiersXML\\films_utf8.xml");
			//lb.lectureClients("..\\laboratoire-1_backend\\src\\main\\resources\\fichiersXML\\clients_utf8.xml");
	}

}
