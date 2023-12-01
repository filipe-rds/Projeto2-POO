package modelo;

public class Convidado extends Participante{
	// Atributtes
	private String empresa;
	
	// Constructors
	public Convidado(String cpf,String nascimento,String empresa) {
		
		super(cpf,nascimento);
		this.empresa = empresa;
		
	}
	
	// Getters and Setters
	public String getEmpresa() {
		return empresa;
	}
	
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	// Methods
	@Override
	public String toString() {
        return "Convidado [ cpf=" + getCpf() +", nascimento=" + getNascimento() +", empresa=" + empresa + ", Ingressos=" + getIngressos() +']';
  }
	
	
	

}