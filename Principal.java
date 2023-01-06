public abstract class Principal
{
    static void criarTabuleiro(Setor[][] tabuleiro, int tam)
    {
        int i, j;
        
        i = 1;
        //Setores normais rosas
        while(i <= tam-2)
        {
            j = 1;
            while(j <= tam-2)
            {
                tabuleiro[i][j] = new SetorNormal(); 
            }    
        }

        tabuleiro[tam-tam][tam-tam] = new SetorNormal();
        tabuleiro[tam-tam][tam-1] = new SetorNormal();
        tabuleiro[tam-1][tam-tam] = new SetorNormal(); 
        tabuleiro[tam-1][tam-1] = new SetorNormal();
        //Fim setores normais

        //Setores ocultos azuis
        i = 1; 
        while(i <= tam-1)
        {
            tabuleiro[i][0] = new SetorOculto();    
            tabuleiro[i][4] = new SetorOculto();
        }
        //Fim setores ocultos

        //Setores privados verdes
        j = 1; 
        while(j <= tam-1)
        {
            tabuleiro[0][j] = new SetorOculto();    
            tabuleiro[4][j] = new SetorOculto();
        }
        //Fim setores privados

        
    }

    public void menuMovimentar()
    {
        // Definir como vai ser escrito o cabeçalho
        System.out.println("Para onde deseja movimentar PLAYER 1 (P1)?");
        System.out.println("Para onde deseja movimentar PLAYER 2 (P2)?");

        System.out.println("U - Up");
        System.out.println("D - Down");
        System.out.println("L - Left");
        System.out.println("R - Right");
    }

    public void menuAtacar()
    {
        System.out.println("Qual inimigo PLAYER 1 (P1) deseja atacar?");
        System.out.println("Qual inimigo PLAYER 2 (P2) deseja atacar?");
        
        // Colocado como exemplo, precisamos verificar se a vida dos inimigos é gerada aleatoriamente e quantos inimigos existem no setor
        System.out.println("- 1/1");
        System.out.println("- 2/2");
        System.out.println("- 3/3");
    }
    
    public static void main(String[] args) 
    {
        int tam=5;
        Setor[][] tabuleiro = new Setor[tam][tam];
        criarTabuleiro(tabuleiro,tam);
        criarPortas(tabuleiro);
        
        Posicao posP1 = new Posicao();
        Posicao posP2 = new Posicao();

        posP1.setX(3);
        posP1.setY(3);
        JogadorSimples p1 = new JogadorSimples(posP1, 0, 0);

        posP2.setX(3);
        posP2.setY(3);
        JogadorSuporte p2 = new JogadorSuporte(posP2, 0, 0);

        


        
        
    }
}