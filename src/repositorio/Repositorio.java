package repositorio;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public int getTotalIngressos(){
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
		
		int tamanho =  eventos.size() ;  

		if (tamanho == 0) {
			    return 1;
		}
		else {

				Evento ultimoEvento = eventos.get(tamanho - 1);  
				int ultimoId = ultimoEvento.getId() ;
				return ultimoId + 1;

		}
		
	}
	
	// Base de dados
	public void carregarObjetos()  	{
		// carregar para o repositorio os objetos salvos nos arquivos csv
		try {
			//caso os arquivos nao existam, serao criados vazios
			File f1 = new File( new File(".\\eventos.csv").getCanonicalPath() ) ; 
			File f2 = new File( new File(".\\participantes.csv").getCanonicalPath() ) ; 
			File f3 = new File( new File(".\\ingressos.csv").getCanonicalPath() ) ; 
			if (!f1.exists() || !f2.exists() || !f3.exists()) {
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

		String linha;	
		String[] partes;	
		Evento evento;
		Participante participante;

		try	{
			String data, descricao, id, capacidade, preco ;
			File f = new File( new File(".\\eventos.csv").getCanonicalPath() )  ;
			Scanner arquivo1 = new Scanner(f);	 //  pasta do projeto
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();		
				partes = linha.split(";");	
				//System.out.println(Arrays.toString(partes));
				id = partes[0];
				data = partes[1];
				descricao = partes[2];
				capacidade = partes[3];
				preco = partes[4];
				evento = new Evento(Integer.parseInt(id), data, descricao,
						Integer.parseInt(capacidade), Double.parseDouble(preco));
				this.adicionar(evento);
			} 
			arquivo1.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de eventos:"+ex.getMessage());
		}

		try	{
			String cpf, nascimento, empresa, listaId;
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			Scanner arquivo2 = new Scanner(f);	 //  pasta do projeto
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				cpf = partes[0];
				nascimento = partes[1];
				if(partes.length==2) {
					participante = new Participante(cpf,nascimento);
					this.adicionar(participante);
				}
				else {
					empresa = partes[2];
					participante = new Convidado(cpf,nascimento,empresa);
					this.adicionar(participante);
				}

			}
			arquivo2.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
		}
		
		try	{
			String codigo, telefone,cpf;
			int id;
			Ingresso ingresso;
			Evento evento1;
			Participante participante1;
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			Scanner arquivo3 = new Scanner(f);	 //  pasta do projeto
			while(arquivo3.hasNextLine()) 	{
				linha = arquivo3.nextLine().trim();	
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				codigo = partes[0];		//contem id e cpf
				telefone = partes[1];
				id = Integer.parseInt(codigo.split("-")[0]);
				cpf = codigo.split("-")[1];
				evento1 = this.localizarEvento(id);
				participante1 = this.localizarParticipante(cpf);
				
				ingresso = new Ingresso(codigo,telefone,evento1,participante1);
				evento1.getIngressos().add(ingresso);
				participante1.getIngressos().add(ingresso);
				this.adicionar(ingresso);
			}
			arquivo3.close();
		}
		catch(Exception ex)		{
			throw new RuntimeException("leitura arquivo de participantes:"+ex.getMessage());
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
			throw new RuntimeException("Problema na criação do arquivo eventos "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\participantes.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f);

			
			for(Participante p : participantes){

				if(p instanceof Convidado){

				Convidado c = (Convidado) p;

				
				arquivo2.write(	
								c.getCpf() + ";" +
								c.getNascimento() + ";" +
								c.getEmpresa() +
						"\n");	
			}
			else {
				arquivo2.write(	
								p.getCpf() + ";" +
								p.getNascimento() + ";" + "\n");	

			}


		}

			arquivo2.close();
		}

		catch(Exception e){
			throw new RuntimeException("Problema na criação do arquivo participantes "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\ingressos.csv").getCanonicalPath())  ;
			FileWriter arquivo3 = new FileWriter(f);

			
			for(Ingresso i : ingressos){

				arquivo3.write(i.getCodigo() +";" + i.getTelefone()+"\n");
			}
			arquivo3.close();
		}

		catch(Exception e){
			throw new RuntimeException("Problema na criação do arquivo ingressos "+e.getMessage());
		}





	}
}
	
	
