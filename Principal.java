import java.util.Random;
import java.util.Scanner;

public abstract class Principal
{
    public static final int NUM_COL = 5;
    public static final int NUM_LIN = 5;
    public static final int POS_CENTRAL_X = 2;
    public static final int POS_CENTRAL_Y = 2;
    
    static void criarPortas(Setor[][] tabuleiro)
    {
        Random random = new Random();
        int[] numAleatorio = new int[4];
        
        for (int i = 0; i < NUM_LIN; i++) 
        {
            for (int j = 0; j < NUM_COL; j++) 
            {
                Porta portaAnterior;
                Porta p = new Porta();

                // [0] acima
                // [1] abaixo
                // [2] esquerda
                // [3] direita 

                for(int indice = 0; indice < 4; indice++)
                {
                    numAleatorio[indice] = random.nextInt(2);  
                }
                
                // Primeira linha
                if(i == 0)
                {
                    p.setAcima(false); // seta porta acima == false se tiver na 1a linha
                    
                    // seta porta abaixo true or false
                    if (numAleatorio[1] == 1)
                    {
                        p.setAbaixo(true); 
                    }
                    else
                    {
                        p.setAbaixo(false); 
                    }
                    
                    
                    if(j == 0)
                    {
                        p.setEsquerda(false); // seta porta esquerda == false se tiver na 1a coluna

                        // seta porta direita true or false
                        if (numAleatorio[3] == 1)
                        {
                            p.setDireita(true); 
                        }
                        else
                        {
                            p.setDireita(false); 
                        }
                    }
                    else if(j < NUM_COL-1)
                    {
                        portaAnterior=tabuleiro[i][j-1].getPorta();

                        p.setEsquerda(portaAnterior.isDireita());
                        if (numAleatorio[3] == 1)
                        {
                            p.setDireita(true); 
                        }
                        else
                        {
                            p.setDireita(false); 
                        }
                    }   
                    else
                    {         
                        portaAnterior = tabuleiro[i][j-1].getPorta();

                        p.setDireita(false); // seta porta direita == false se tiver na ultima coluna
                        p.setEsquerda(portaAnterior.isDireita()); // seta porta esquerda conforme a porta da coluna anterior
                    }
                }
                // Fim primeira linha

                else if(i < NUM_LIN-1)
                {
                    //p.setAbaixo(false);
                    portaAnterior = tabuleiro[i-1][j].getPorta();

                    //p.setAcima(false); // seta porta direita == false se tiver na ultima coluna
                    p.setAcima(portaAnterior.isAbaixo());
                    if (numAleatorio[1] == 1)
                    {
                        p.setAbaixo(true); 
                    }
                    else
                    {
                        p.setAbaixo(false); 
                    }

                    if(j == 0)
                    {
                        p.setEsquerda(false); 
        
                        if (numAleatorio[3] == 1)
                        {
                            p.setDireita(true); 
                        }
                        else
                        {
                            p.setDireita(false); 
                        }
                    }
                    else if(j < NUM_COL-1)
                    {
                        portaAnterior=tabuleiro[i][j-1].getPorta();

                        p.setEsquerda(portaAnterior.isDireita());
                        if (numAleatorio[3] == 1)
                        {
                            p.setDireita(true); 
                        }
                        else
                        {
                            p.setDireita(false); 
                        }
                    }   
                    else
                    {         
                        portaAnterior = tabuleiro[i][j-1].getPorta();

                        p.setDireita(false); // seta porta direita == false se tiver na ultima coluna
                        p.setEsquerda(portaAnterior.isDireita()); // seta porta esquerda conforme a porta da coluna anterior
                    }

                }
                else
                {
                    p.setAbaixo(false);
                    portaAnterior = tabuleiro[i-1][j].getPorta();
                    p.setAcima(portaAnterior.isAbaixo());
                    
                    if(j == 0)
                    {
                        p.setEsquerda(false); 
        
                        if (numAleatorio[3] == 1)
                        {
                            p.setDireita(true); 
                        }
                        else
                        {
                            p.setDireita(false); 
                        }
                    }
                    else if(j < NUM_COL-1)
                    {
                        portaAnterior=tabuleiro[i][j-1].getPorta();

                        p.setEsquerda(portaAnterior.isDireita());
                        if (numAleatorio[3] == 1)
                        {
                            p.setDireita(true); 
                        }
                        else
                        {
                            p.setDireita(false); 
                        }
                    }   
                    else
                    {         
                        portaAnterior = tabuleiro[i][j-1].getPorta();

                        p.setDireita(false); // seta porta direita == false se tiver na ultima coluna
                        p.setEsquerda(portaAnterior.isDireita()); // seta porta esquerda conforme a porta da coluna anterior
                    }
                }

                tabuleiro[i][j].setPorta(p);
                tabuleiro[i][j].setExisteInimigo(false);
                tabuleiro[i][j].setVisitado(false);
            }
        }

        //Setor principal
        Porta portaCentral= new Porta(true,true,true,true);
        tabuleiro[POS_CENTRAL_X][POS_CENTRAL_Y].setPorta(portaCentral);
        tabuleiro[POS_CENTRAL_X][POS_CENTRAL_Y].setVisitado(true);
    }

    static void criarTabuleiro(Setor[][] tabuleiro)
    {
        int i, j;
    
    // Instaciamento de Setores

        // Instanciamento da primeira e ultima linnha
        i = 0;
        while(i < NUM_LIN)
        {
            for(j = 0; j < NUM_COL; j++)
            {
                if(j % 2 == 0 && j != 2)
                {
                    tabuleiro[i][j] = new SetorNormal();
                }
                else
                {
                    tabuleiro[i][j] = new SetorPrivado();
                }
            }

            i = i + 4;
        }

        // Instanciamento das 3 linhas intermediarias
        for(i = 1 ; i < NUM_LIN - 1; i++)
        {
            for(j = 0; j < NUM_COL; j++)
            {
                if(j % 2 == 0 && j != 2)
                {
                    tabuleiro[i][j] = new SetorOculto();
                }
                else
                {
                    tabuleiro[i][j] = new SetorNormal();
                }
            }
        }
        
    // Fim do instanciamento de setores
    
    }
    
    static void menuMovimentar(Jogador p,Setor[][] tabuleiro)
    {
        char move;
        Scanner input = new Scanner(System.in);

        if(p instanceof JogadorSimples)
        {
            System.out.println("Para onde deseja movimentar PLAYER 1 (P1)?");
        }
        else
        {
            System.out.println("Para onde deseja movimentar PLAYER 2 (P2)?");
        }
         
        System.out.println("U - Up");
        System.out.println("D - Down");
        System.out.println("L - Left");
        System.out.println("R - Right");
        
        //verificar se existe inimigo, se nao trocar a posicao do jogador na matriz
        move = input.next().charAt(0);
        
        switch (move) 
        {
            case 'u':
                if(!p.movimentar(p, move,tabuleiro));
                    System.out.println("Precisar matar todos os inimigos para se movimentar em outro setor");
                break;
                
            case 'd':
                
                if(!p.movimentar(p, move,tabuleiro));
                    System.out.println("Precisar matar todos os inimigos para se movimentar em outro setor");
                break;

            case 'l':
                
                if(!p.movimentar(p, move,tabuleiro));
                    System.out.println("Precisar matar todos os inimigos para se movimentar em outro setor");
                break;

            case 'r':
                
                if(!p.movimentar(p, move,tabuleiro));
                    System.out.println("Precisar matar todos os inimigos para se movimentar em outro setor");
                break;
            
            default:
                System.out.println("Tecla inválida.");
                break;
        }

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
        int tam = 5;
        Setor[][] tabuleiro = new Setor[tam][tam];
        criarTabuleiro(tabuleiro);
        criarPortas(tabuleiro);

        Jogador p1,p2;
        
        Posicao posP1 = new Posicao();
        Posicao posP2 = new Posicao();
        
        posP1.setX(POS_CENTRAL_X);
        posP1.setY(POS_CENTRAL_Y);
        p1 = new JogadorSimples(posP1, 2, 6);

        posP2.setX(POS_CENTRAL_X);
        posP2.setY(POS_CENTRAL_Y);
        p2 = new JogadorSuporte(posP2, 1, 7);

        //laco principal

        Scanner input = new Scanner(System.in);
        Posicao posix = p1.getPos();
        int z;

        do
        {
            System.out.print("Num: ");
            z = input.nextInt(); 

            Posicao pos = p1.getPos();

            System.out.println("Y: "+ pos.getY() + " X: " + pos.getX());

            menuMovimentar(p1,tabuleiro);
            
            System.out.println("Y: "+ pos.getY() + " X: " + pos.getX());

            //Posicao pos2 = p1.getPos();

            //System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());

            //menuMovimentar(p1);
            
            // System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());

            //input.nextLine();
            
            //Posicao pos2 = p2.getPos();
            //System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());
            //menuMovimentar(p2);
            //System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());
            
            
            // gerarInimigo(p1);
            // escolheAcao(p1);
            // inimigoAtacar(p1);
            
            // menuMovimentar(p2);
            // gerarInimigo(p2);
            // escolheAcao(p2);
            // inimigoAtacar(p2);
        //}
        }while(z != 0);

        input.close();
        // // while((naoEncontraFonte(p1,p2,fonte)) && (turno <= 25) && (jogadoresVivos(p1,p2)))
        
    }
}