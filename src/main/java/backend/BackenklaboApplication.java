package backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import backend.database.LectureBD;

@SpringBootApplication
public class BackenklaboApplication {

	public static void main(String[] args) {
		//lectur des fichiers et insertion dans la base de donnees
		LectureBD lb = new LectureBD();
		lb.lecturePersonnes("..\\backenklabo\\src\\main\\resources\\fichiersXML\\personnes_utf8.xml");
		lb.lectureFilms("..\\backenklabo\\src\\main\\resources\\fichiersXML\\filmss_utf8.xml");
		lb.lectureClients("..\\backenklabo\\src\\main\\resources\\fichiersXML\\clients_utf8.xml");
		
		SpringApplication.run(BackenklaboApplication.class, args);
	}

}
