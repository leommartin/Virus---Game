
import java.util.*;
// import java.util.Random;
// import java.util.Scanner;

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
                    // numAleatorio[indice] = random.nextInt(2);
                    numAleatorio[indice] = 1;  
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
    static void gerarPosicaoInfeccao(Posicao posInfeccao)
    {
        Random random = new Random(); 

        posInfeccao.setX(random.nextInt(5));
        posInfeccao.setY(random.nextInt(5));
        while(posInfeccao.getX() == 2 && posInfeccao.getX() == 2)
        {
            posInfeccao.setX(random.nextInt(5));
            posInfeccao.setY(random.nextInt(5));
        }
    }

    static boolean achouFonte(Jogador p, Posicao posInfeccao)
    {
        Posicao posPlayer;

        posPlayer = p.getPos();
    
        if( (posPlayer.getX() == posInfeccao.getX()) && (posPlayer.getY() == posInfeccao.getY()))
        {
            return true;
        }

        return false;
    }

    static boolean p1EstaVivo(Jogador p)
    {
        if(p.getDef() > 0)
        {
            return true;
        }
        return false;
    }
    
    static boolean menuMovimentar(Jogador p,Setor[][] tabuleiro)
    {
        char move;
        Posicao pos;
        int linha, coluna;
        boolean existeMovimentacao = false;
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
        
        move = input.next().charAt(0);
        
        if(move == 'u' || move == 'd' || move == 'l' || move == 'r')
        {
            if(p.movimentar(p, move,tabuleiro))
            {   
                existeMovimentacao = true;
            }
        }
        else
        {
            System.out.println("Tecla inválida.");
        }

        if (existeMovimentacao)
        {
            pos = p.getPos();
            linha = pos.getY();
            coluna = pos.getX();
            tabuleiro[linha][coluna].setVisitado(true);
        }
        else
        {
            System.out.println("È necessário eliminar todos os inimigos para se movimentar.");
        }

        return existeMovimentacao;
    }

    static void gerarInimigo(Jogador p, Setor[][] tabuleiro)
    {
        Posicao pos;
        int linha, coluna;
        
        pos = p.getPos();
        // Verificar se é necessário o atributo posição em inimigo.
        linha = pos.getX();
        coluna = pos.getY();
        
        if( !tabuleiro[linha][coluna].visitado )
        {
            // Gerar os inimigos
            int atk, def, numInimigos;
            Random random = new Random(); 
            numInimigos = random.nextInt(3) + 1;

            ArrayList<Inimigo> listaDeInimigos = new ArrayList<Inimigo>(numInimigos);

            for(int i = 0; i < listaDeInimigos.size(); i++)
            {
                // Gerar o ataque e defesa dos inimigos
                atk = random.nextInt(3)+1;
                def = atk;

                Inimigo inimigo = new Inimigo(atk,def,pos);

                listaDeInimigos.add(i, inimigo);
            }
        }

    }

    static void escolheAcao(Jogador p, Setor[][] tabuleiro)
    {
        char acao;
        Posicao pos;
        int linha, coluna;

        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();
        
        // Scanner input = new Scanner(System.in);

        if(p instanceof JogadorSimples)
        {
            System.out.println("Qual ação PLAYER 1 (P1) deseja realizar?");

            System.out.println("A - Attack");
        
            if(tabuleiro[linha][coluna] instanceof SetorNormal || tabuleiro[linha][coluna] instanceof SetorOculto )
            {
                System.out.println("S - Search");
            }
        }
        else
        {
            System.out.println("Qual ação PLAYER 2 (P2) deseja realizar?");

            System.out.println("A - Attack");
        
            if(tabuleiro[linha][coluna] instanceof SetorNormal || tabuleiro[linha][coluna] instanceof SetorOculto )
            {
                System.out.println("S - Search");
            }
            
            System.out.println("R - Recover");
        }
        
        // acao = input.next().charAt(0);
        
        // switch (acao) {
        //     case 'a':
        //         atacar();
        //         break;
        //     case 's':
        //         procurar();
        //         break;
        //     case 'r':
        //         recuperar();
        //         break;
        //     case 'm':
        //         menuMovimentar(p, tabuleiro);
        //         break;
            
        //     default:
        //         System.out.println("Tecla inválida.");
        //         break;
        // }
        
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
        int tam = 5, turno = 0;
        Posicao posInfeccao = new Posicao();
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

        gerarPosicaoInfeccao(posInfeccao);

        //laco principal

        Scanner input = new Scanner(System.in);

        System.out.printf("InfY: %d , InfX: %d\n", posInfeccao.getY(), posInfeccao.getX());

        do
        {
            Posicao pos = p1.getPos();

            System.out.println("Y: "+ pos.getY() + " X: " + pos.getX());

            if(menuMovimentar(p1,tabuleiro))
            {
                gerarInimigo(p1,tabuleiro);
                escolheAcao(p1,tabuleiro);
                
            }
            
            System.out.println("Y: "+ pos.getY() + " X: " + pos.getX());

            turno++; // verificar se o movimento é valido para contar um turno

            //Posicao pos2 = p1.getPos();

            //System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());

            //menuMovimentar(p1);
            
            // System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());

            //input.nextLine();
            
            //Posicao pos2 = p2.getPos();
            //System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());
            //menuMovimentar(p2);
            //System.out.println("Y: "+ pos2.getY() + " X: " + pos2.getX());
            
            
            // // gerarInimigo(p1)
            // escolheAcao(p1);
            // inimigoAtacar(p1);
            
            // menuMovimentar(p2);
            // gerarInimigo(p2);
            // escolheAcao(p2);
            // inimigoAtacar(p2);
        //}
        } while( (!achouFonte(p1,posInfeccao)) && (turno <= 25) && (p1EstaVivo(p1)) ); // nao esqueca de colocar p2 no achouFonte

        input.close();
        // // while((naoEncontraFonte(p1,p2,fonte)) && (turno <= 25) && (jogadoresVivos(p1,p2)))
        
    }
}