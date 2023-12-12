package appconsole;


import modelo.Ingresso;
import regras_negocio.Fachada;

public class Teste3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		//Fachada.criarConvidado("44", "18/04/1920", "AeC");
		
		//Fachada.criarEvento("18/04/2002","w",10,10.0);
		
		//Fachada.criarIngresso(3,"44","1111");
		
		
		//ArrayList<Ingresso> i = Fachada.listarIngressos();
		
		for(Ingresso ing : Fachada.listarIngressos()) 
			System.out.println(
					"cod="+ing.getCodigo()+ ", "+ ing.getTelefone() + ", preco=" +	ing.calcularPreco() + 
					", evento preco=" + ing.getEvento().getPreco() + ", arrecadado=" + ing.getEvento().totalArrecadado() + 
					", idade=" + ing.getParticipante().calcularIdade());
		

	}
		catch(Exception e) {
			
			System.out.println(e.getMessage());
			
		}

    }
}