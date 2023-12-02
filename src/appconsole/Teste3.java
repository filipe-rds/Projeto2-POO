package appconsole;

import regras_negocio.Fachada;

public class Teste3 {

    public static void main(String[] args) {

        try{
            Fachada.criarParticipante("111","01/01/2000");
            //Fachada.criarParticipante("111","01/01/2000");
            //System.out.println(Fachada.listarParticipantes());
            Fachada.criarEvento("01/01/2024", "teste", 2, 2);
            Fachada.criarEvento("01/01/2024", "teste", 2, 2);
            Fachada.criarIngresso(1,"111","99");
            Fachada.criarIngresso(2,"111","99");
            //Fachada.apagarEvento(1);
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
            //Fachada.apagarIngresso("1-111");
            //Fachada.apagarEvento(1);
            //Fachada.apagarParticipante("111");
        }
        catch(Exception e){

            System.out.println("Erro ---->"+e.getMessage());






        }

        
    }
    
}
