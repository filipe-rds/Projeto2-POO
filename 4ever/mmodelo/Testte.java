package mmodelo;
import java.time.LocalDate;
import repositorio.Repositorio;
public class Testte {

	public static void main(String[] args) {
        
		// TODO Auto-generated method stub
		
		Convidado gabriel = new Convidado("123",LocalDate.of(1900,5, 15),"Google");

		Repositorio r1 = new Repositorio();

		LocalDate dataExemplo = LocalDate.of(2023, 11, 28);
		Evento e1 = new Evento (dataExemplo,"qwqhbqb",18,20.0);
		LocalDate dataExemplo1 = LocalDate.of(2023, 11, 28);
		Evento e2 = new Evento (dataExemplo1,"qgjgjqvdukqwqeqwwqeqww",18,20.0);

		LocalDate dataExemplo3 = LocalDate.of(2023, 10, 15);
		Participante p1 = new Participante ("123",dataExemplo3);

		LocalDate dataExemplo4 = LocalDate.of(2023, 10, 15);
		Convidado c1 = new Convidado ("12312412",dataExemplo4,"Google");

		r1.adicionar(p1);
		r1.adicionar(c1);
		r1.adicionar(e1);
		r1.salvarObjetos();
		r1.adicionar(e2);
		r1.salvarObjetos();
		

		System.out.println(gabriel);
		
	}

}