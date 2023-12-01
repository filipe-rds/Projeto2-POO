package modelo;
import repositorio.Repositorio;
public class Teste {

	public static void main(String[] args) {
        
		// TODO Auto-generated method stub
		
		Convidado gabriel = new Convidado("123","18/04/2002","Google");

		Repositorio r1 = new Repositorio();

		
		//Evento e1 = new Evento ("18/04/2023","qwqhbqb",18,20.0);

		//Evento e2 = new Evento ("18/06/2002","qgjgjqvdukqwqeqwwqeqww",18,20.0);


		Convidado c1 = new Convidado ("12312412","18/09/2079","Google");

	
		r1.adicionar(c1);
		r1.adicionar(e1);
		r1.salvarObjetos();
		r1.adicionar(e2);
		r1.salvarObjetos();
		

		System.out.println(gabriel);
		
	}

}