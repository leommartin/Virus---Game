import java.util.Scanner;

public class JogadorSuporte extends Jogador
{
    // Construtores 
    public JogadorSuporte(Posicao pos,int atk, int def)
    {
        super(pos,atk,def);    
    }

    // Métodos próprios
    public void recuperarDefesa(Jogador p1, Jogador p2, Setor[][] tabuleiro)
    {
        Posicao posP1, posP2;
        int player, def;
        
        posP1 = p1.getPos();
        posP2 = p2.getPos();
    

        if(posP1.getX() == posP2.getX() && posP1.getY() == posP2.getY())
        {
            Boolean acaoValida = false;
            Scanner input = new Scanner(System.in);

            while (!acaoValida)
            {
                System.out.println("Qual Jogador deseja recuperar?");
                System.out.println("1 - P1");
                System.out.println("2 - P2");

                player = input.nextInt();

                switch (player) 
                {    
                    case 1:
                        acaoValida = true;
                        def = p1.getDef();
                        p1.setDef(def+2);
                        break;
                    
                    case 2:
                        acaoValida = true;
                        def = p2.getDef();
                        p2.setDef(def+2);
                        break;
                    
                    default:
                        System.out.println("Tecla inválida.");
                        break;
                }
            }
        }
        else
        {
            def = p2.getDef();
            p2.setDef(def+2); 
            System.out.println("nova vida de p2: "+ p2.getDef());  
        }    
    }
}
