package appconsole;

import regras_negocio.Fachada;

public class Teste3 {

    public static void main(String[] args) {

        try{
            Fachada.criarParticipante("111","01/01/2000"); // criar participante 1
            Fachada.criarParticipante("222","01/01/2000"); // criar participante 2
       
            Fachada.criarEvento("01/01/2022", "teste", 2, 2); // criar evento 1
            Fachada.criarEvento("01/01/2022", "teste", 2, 2); // criar evento 2

            Fachada.criarIngresso(1,"111","99"); 
            Fachada.criarIngresso(2,"111","99");
           
            System.out.println("------------------------------------------------------------");
            System.out.println(Fachada.listarParticipantes());
            System.out.println("------------------------------------------------------------");
            System.out.println(Fachada.listarIngressos());
            System.out.println("------------------------------------------------------------");
            System.out.println(Fachada.listarEventos());
            Fachada.apagarIngresso("1-111");
            System.out.println("------------------------------------------------------------");
            System.out.println(Fachada.listarParticipantes());
            System.out.println("------------------------------------------------------------");
            System.out.println(Fachada.listarIngressos());
            System.out.println("------------------------------------------------------------");
            System.out.println(Fachada.listarEventos());
            System.out.println("------------------------------------------------------------");
            Fachada.apagarParticipante("111");
            //Fachada.apagarIngresso("1-111");
            //Fachada.apagarEvento(1);
            //Fachada.apagarParticipante("111");
        }
        catch(Exception e){

            System.out.println("Erro ---->"+e.getMessage());






        }

        
    }
    
}
