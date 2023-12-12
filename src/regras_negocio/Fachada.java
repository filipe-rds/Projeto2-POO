package regras_negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import modelo.*;
import repositorio.*;

public class Fachada{
    
	private static Repositorio repositorio = new Repositorio();

	static{
		repositorio.carregarObjetos();
	}
	
	private Fachada() {};
	
	
	
	public static void criarEvento(String data, String descricao, int capacidade, double preco ) throws Exception {
		
		
		if (preco < 0) 
			throw new Exception("Preço não deve ser negativo\n");
		else if (capacidade < 2)
			throw new Exception("Capacidade deve ser maior ou igual a 2\n");
		else if (data == "")
			throw new Exception("Data não deve ser vazia\n");
		else if (descricao == "")
			throw new Exception("Descrição não deve ser vazia\n");
			
		Evento evento = new Evento(repositorio.gerarId(), data, descricao, capacidade, preco);
		repositorio.adicionar(evento);
		repositorio.salvarObjetos();
		
	}
	
	public static void criarParticipante(String cpf ,String nascimento ) throws Exception {

		if(repositorio.localizarParticipante(cpf) != null)
			throw new Exception("Participante já existe\n");
		else if (nascimento == "")
			throw new Exception("Você precisa passar uma data de nascimento\n");

		Participante participante = new Participante(cpf, nascimento);
		repositorio.adicionar(participante);
		repositorio.salvarObjetos();
	}
	
	public static void criarConvidado(String cpf ,String nascimento, String empresa) throws Exception {

		if(repositorio.localizarParticipante(cpf) != null)
			throw new Exception("Convidado já existe\n");
		else if (empresa == "")
			throw new Exception("Você precisa passar uma empresa\n");
		
		Convidado convidado = new Convidado(cpf, nascimento, empresa);
		repositorio.adicionar(convidado);
		repositorio.salvarObjetos();
	}
	
	public static void criarIngresso(int id, String cpf, String telefone) throws Exception{

		// Localizando participante e evento no repositório
		Participante participante = repositorio.localizarParticipante(cpf);
		Evento evento = repositorio.localizarEvento(id);
		// Gerando código do ingresso
		String codigo = id + "-" + cpf;
		
		if (evento == null)
			throw new Exception("Evento não existe\n");
		else if (evento.getIngressos().size() >= evento.getCapacidade())
			throw new Exception("Evento atingiu sua capacidade máxima de ingressos\n");
		else if (participante == null)
			throw new Exception("Participante não existe\n");
		else if (telefone == "")
			throw new Exception("Telefone não deve ser vazio\n");
		else if (repositorio.localizarIngresso(codigo) != null)
			throw new Exception ("Já existe este ingresso\n");
		
		
		// Se passar de todas as verificações, crie o ingresso e adicione no repositório
		Ingresso ingresso = new Ingresso(codigo, telefone, evento, participante);
		repositorio.adicionar(ingresso); 
		// ------------------------------------------------------------------------------
		// Adiciona o ingresso no array de ingressos do participante
		ArrayList<Ingresso> ingressosDoParticipante = participante.getIngressos();
		ingressosDoParticipante.add(ingresso);
		// ------------------------------------------------------------------------------
		// Adiciona o ingresso no array de ingressos do evento
		ArrayList<Ingresso> ingressosDoEvento = evento.getIngressos();
		ingressosDoEvento.add(ingresso); 
		// ------------------------------------------------------------------------------
		repositorio.salvarObjetos();
	}
	
	public static void apagarEvento(int id) throws Exception{

		// Localiza o evento no repositório, usando o seu id
		Evento evento = repositorio.localizarEvento(id);

		if (evento == null)
			throw new Exception("Evento não existe\n");
		else if (evento.getIngressos().size() > 0)
			throw new Exception("Evento possui ingressos registrados\n");
		
		repositorio.remover(evento);
		repositorio.salvarObjetos();
	}
	
	public static void apagarParticipante(String cpf) throws Exception {

		// Verifica a existência do participante.
		Participante participante = repositorio.localizarParticipante(cpf); // Resgata o objeto participante atrelado ao CPF.

		if (participante == null){
			throw new Exception("Participante não existe\n"); }
		// Se o participante não tiver ingressos, já é removido automaticamente 
		else if (participante.getIngressos().size() == 0)
			repositorio.remover(participante);
		else {
			ArrayList<Ingresso> ingressosDoParticipante = participante.getIngressos();  // Resgata a lista de ingressos do participante.
			int tamanho = ingressosDoParticipante.size(); // Descobrir a quantidade de ingressos.
			Ingresso ultimoIngresso = ingressosDoParticipante.get(tamanho-1); // Resgatar o ultimo ingresso do participante.
			Evento eventoDoIngresso = ultimoIngresso.getEvento(); // Resgatar o evento atrelado ao ingresso do participante.
			String dataEventoDoIngresso = eventoDoIngresso.getData(); // Resgatar a data do evento atralelado ao ingresso e atrelado ao participante.
				
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // Formatar a data para dia/mês/ano.
			LocalDate dataTransformada = LocalDate.parse(dataEventoDoIngresso, formatter); // Transformando a String em um objeto LocalDate
			LocalDate dataAtual = LocalDate.now(); // Resgatando o horário atual

			// Caso a data do evento vinculado ao último ingresso estiver ultrapassada, entrará nessa condição.
			if(dataTransformada.isBefore(dataAtual)){ 
				ingressosDoParticipante.clear(); // Apaga todos os ingressos atrelado ao participante.

				// Resgata todos os ingressos e, em seguida, remove todos os ingressos associados ao CPF especificado.
					
				ArrayList<Ingresso> ingressosApagar = repositorio.getIngressos(); // Resgata todos os ingressos do repositório.
					
				for (int i = ingressosApagar.size()- 1; i >= 0; i--) {
					Ingresso ingressoBase = ingressosApagar.get(i);
					String codigo = ingressoBase.getCodigo();
					String[] partes = codigo.split("-");
					if (partes[1].equals(cpf)) {
						ingressosApagar.remove(i);
					}
				}

				// Resgata todos os eventos e, para cada evento, verifica na lista de ingressos do evento e remove o CPF especificado.

				ArrayList <Evento> eventosApagar = repositorio.getEventos();

				for (int i = eventosApagar.size()-1 ; i >= 0; i--){
					Evento eventoBase = eventosApagar.get(i);
					ArrayList<Ingresso> ingressosParaApagar = eventoBase.getIngressos();

					for (int j = ingressosParaApagar.size()-1; j >=0;j--){
						Ingresso ingressoBase = ingressosParaApagar.get(j);
						String codigo = ingressoBase.getCodigo();
						String[] partes = codigo.split("-");
						if (partes[1].equals(cpf)) 
							ingressosParaApagar.remove(j);
					}
				}
						
			}
			// Lance uma exceção se a data não foi ultrapassada.
			else {
				throw new Exception("Não é possível apagar, pois o último ingresso não foi ultrapassado: " + ultimoIngresso.getCodigo()+ "\n");
			}
			repositorio.remover(participante);
		}
		repositorio.salvarObjetos();
	}

	public static void apagarIngresso(String codigo) throws Exception{
		
		
		if (repositorio.localizarIngresso(codigo) == null){
			throw new Exception ("Ingresso inexistente\n");
		}

		Ingresso i = repositorio.localizarIngresso(codigo);
		String code = i.getCodigo();
		String [] partes = code.split("-");
		// Partes[0] = id , Partes[1] = cpf 
		Participante participante = repositorio.localizarParticipante(partes[1]);
		ArrayList<Ingresso> ingressosParticipante = participante.getIngressos();

		// Varre os ingressos do participante, até achar o que será removido
		for (Ingresso in : ingressosParticipante){
			if (in.getCodigo().equals(codigo)){
				ingressosParticipante.remove(in);
				break;
			}
		}
		// 
		int numeroInt = Integer.parseInt(partes[0]);
		Evento evento = repositorio.localizarEvento(numeroInt);
		ArrayList<Ingresso> ingressosEvento = evento.getIngressos();
		
		// Varre os ingressos do evento, até achar o que será removido
		for (Ingresso on : ingressosEvento){
			if (on.getCodigo().equals(codigo)){
				ingressosEvento.remove(on);
				break;
			}
		}

		// Após remover o ingressos do evento e do participante, ele será removido do repositório
		ArrayList<Ingresso> ingressosRepositorio = repositorio.getIngressos();
		ingressosRepositorio.remove(i);
		//---------------------------------------------------------------

        repositorio.salvarObjetos();
	}

	public static ArrayList<Evento> listarEventos() throws Exception{
		
		ArrayList<Evento> e = repositorio.getEventos();
		if ( e.size() == 0){
			throw new Exception("Não existe eventos\n");
		}
		return e;	
	}

	public static ArrayList<Participante> listarParticipantes() throws Exception{
		ArrayList<Participante> p = repositorio.getParticipantes();
		if ( p.size() == 0){
			throw new Exception("Não existe participantes\n");
		}
		return p;
	}

	public static ArrayList<Ingresso> listarIngressos() throws Exception{
		ArrayList<Ingresso> i = repositorio.getIngressos();
		if ( i.size() == 0){
			throw new Exception("Não existe ingressos\n");
		}
		return i;
	}
}