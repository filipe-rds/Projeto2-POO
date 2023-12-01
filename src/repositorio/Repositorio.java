package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import modelo.*;

public class Repositorio {
	// Atributtes
	private ArrayList<Evento> eventos = new ArrayList<Evento>();
	private ArrayList<Participante> participantes = new ArrayList<Participante>();
	private ArrayList<Ingresso> ingressos = new ArrayList<Ingresso>();
	
	// Getters
	public ArrayList<Evento> getEventos() {
		return eventos;
	}
	
	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}
	
	public ArrayList<Ingresso> getIngressos() {
		return ingressos;
	}
	
	public int getTotalEventos(){
		return eventos.size();
	}
	
	public int getTotalParticipantes(){
		return participantes.size();
	}
	
	public int getTotalProfessores(){
		return ingressos.size();
	}
	
	// Methods
	
	// Evento
	public void adicionar(Evento e) 	{
		eventos.add(e);
	}
	public void remover(Evento e) {
		eventos.remove(e);
	}
	public Evento localizarEvento(int id) {
		for(Evento e : eventos)
			if (e.getId() == id)	
				return e;

		return null;
	}
	
	// Participante
	public void adicionar(Participante p) 	{
		participantes.add(p);
	}
	
	public void remover(Participante p) {
		participantes.remove(p);
	}
	
	public Participante localizarParticipante(String cpf) {  
		for(Participante p : participantes)
			if (p.getCpf().equals(cpf))	
				return p;

		return null;
	}

	
	
	
	
	// Ingresso
	public void adicionar(Ingresso i) 	{
		ingressos.add(i);
	}
	public void remover(Ingresso i) {
		ingressos.remove(i);
	}
	public Ingresso localizarIngresso(String codigo) {  
		for(Ingresso i : ingressos)
			if (i.getCodigo().equals(codigo))	
				return i;

		return null;
	}
	
	public int gerarId() {
		
		int valor =  eventos.size() ;  

		if (valor == 0) {
			    return 1;
		}
		else {

				Evento eventoTeste = eventos.get(valor - 1);
				int valor1 = eventoTeste.getId() ;
				return valor1 + 1;

		}
		
	}
	
	// Base de dados
	public void carregarObjetos()  	{
		// carregar para o repositorio os objetos dos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\eventos.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\participantes.csv").getCanonicalPath() ) ; 
			File f3 = new File( new File(".\\ingressos.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() || !f3.exists() ) {
				//System.out.println("criando arquivo .csv vazio");
				FileWriter arquivo1 = new FileWriter(f1); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(f2); arquivo2.close();
				FileWriter arquivo3 = new FileWriter(f3); arquivo3.close();
				return;
			}
		}
		catch(Exception ex)		{
			throw new RuntimeException("criacao dos arquivos vazios:"+ex.getMessage());
		}

		

	}

	public void	salvarObjetos()  {
		//gravar nos arquivos csv os objetos que estão no repositório
		try	{
			File f = new File( new File(".\\eventos.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f);

			
			for(Evento e : eventos){

				String dataFormatada = e.getData();

				arquivo1.write(	
								e.getId()+";"+
								dataFormatada +";"+
								e.getDescricao()+";"+
								e.getCapacidade()+";"+
								e.getPreco() +
								"\n");	
			}
			arquivo1.close();
		}

		catch(Exception e){
			throw new RuntimeException("problema na criação do arquivo  eventos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f);

			
			for(Participante p : participantes){

				if(p instanceof Convidado){

				Convidado c = (Convidado) p;

				String dataFormatada = p.getNascimento();
				arquivo2.write(	
								c.getCpf() + ";" +
								dataFormatada + ";" +
								c.getEmpresa() +
						"\n");	
			}
			else {
				String dataFormatada = p.getNascimento();
				arquivo2.write(	
								p.getCpf() + ";" +
								dataFormatada + ";" + "\n");	

			}
		}

			arquivo2.close();
		}

		catch(Exception e){
			throw new RuntimeException("problema na criação do arquivo  eventos "+e.getMessage());
		}



	}
}
	
	
