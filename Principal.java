
import java.util.*;
// import java.util.Random;
// import java.util.Scanner;

// To do: Escrever a matriz

public abstract class Principal
{
    public static final int NUM_COL = 5;
    public static final int NUM_LIN = 5;
    public static final int POS_CENTRAL_X = 2;
    public static final int POS_CENTRAL_Y = 2;  
    public static final Scanner input = new Scanner(System.in);

    
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

    static boolean existeColisao(Posicao pos, Posicao posInfeccao)
    {
        if( (pos.getX() == posInfeccao.getX()) && (pos.getY() == posInfeccao.getY()) )
        {
            return true;
        }
        return false;
    }

    static boolean achouFonte(Jogador p, Posicao posInfeccao)
    {
        Posicao posPlayer;

        posPlayer = p.getPos();
    
        if (existeColisao(posPlayer, posInfeccao))
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

    static boolean p2EstaVivo(Jogador p)
    {
        if(p.getDef() > 0)
        {
            return true;
        }
        return false;
    }
    
    static void menuMovimentar(Jogador p,Setor[][] tabuleiro)
    {
        Posicao pos;
        int linha, coluna;

        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();
        
        if(!tabuleiro[linha][coluna].existeInimigo)
        {
            char move;
            boolean existeMovimentacao = false;
            // Scanner input = new Scanner(System.in);

            while (!existeMovimentacao)
            {
                if(p instanceof JogadorSimples)
                {
                    System.out.printf("\n\nPara onde deseja movimentar PLAYER 1 (P1)?\n");
                }
                else
                {
                    System.out.printf("\n\nPara onde deseja movimentar PLAYER 2 (P2)?\n");
                }
                
                System.out.println("U - Up");
                System.out.println("D - Down");
                System.out.println("L - Left");
                System.out.println("R - Right");
                
                move = input.next().toLowerCase().charAt(0);

                existeMovimentacao = movimentar(move, p, tabuleiro);
            }
        }
    }

    static boolean movimentar(char move, Jogador p, Setor[][] tabuleiro)
    {
        if(move == 'u' || move == 'd' || move == 'l' || move == 'r')
        {
            if(p.movimentar(p, move,tabuleiro))
            {   
                return true;
            }
        }

        System.out.println("Tecla inválida.");
        return false;
    }

    static void gerarInimigo(Jogador p, Setor[][] tabuleiro)
    {
        Posicao pos;
        int linha, coluna;
        
        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        if( !tabuleiro[linha][coluna].visitado)
        {
            int atk, def, numInimigos;
            Random random = new Random(); 
            numInimigos = random.nextInt(3) + 1;

            for(int i = 0; i < numInimigos; i++)
            {
                atk = random.nextInt(3)+1;
                def = atk;

                Inimigo inimigo = new Inimigo(atk,def,pos);
                tabuleiro[linha][coluna].setInimigo(inimigo);
            }

            tabuleiro[linha][coluna].existeInimigo = true;
            tabuleiro[linha][coluna].setVisitado(true);
        }   

    }

    static char escolheAcao(Jogador p, Setor[][] tabuleiro)
    {
        char acao;
        Posicao pos;
        int linha, coluna;

        // Scanner input = new Scanner(System.in);

        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        if(p instanceof JogadorSimples)
        {
            System.out.println("Qual ação PLAYER 1 (P1) deseja realizar?");

            if(tabuleiro[linha][coluna].existeInimigo) 
            {
                System.out.println("A - Attack");
            }
        
            if(tabuleiro[linha][coluna] instanceof SetorNormal || tabuleiro[linha][coluna] instanceof SetorOculto ) // != SetorPrivado
            {
                System.out.println("S - Search");
            }

            if(!tabuleiro[linha][coluna].existeInimigo && tabuleiro[linha][coluna] instanceof SetorPrivado)
            {
                System.out.println("M - Movimentar para outro setor");
            }
        }
        else
        {
            System.out.println("Qual ação PLAYER 2 (P2) deseja realizar?");

            if(tabuleiro[linha][coluna].existeInimigo)
            {
                System.out.println("A - Attack");
            }

            if(tabuleiro[linha][coluna] instanceof SetorNormal || tabuleiro[linha][coluna] instanceof SetorOculto )
            {
                System.out.println("S - Search");
            }
            
            System.out.println("R - Recover");
        }
        
        acao = input.next().toLowerCase().charAt(0);

        System.out.println("acao : " + acao);
        return acao;
        
    }

    static void menuAtacar(Jogador p, Setor[][] tabuleiro)
    {
        int indiceInimigo;
        Posicao pos;
        int linha, coluna, qtdInimigos;
        int atk, def;
        boolean indiceValido = false;
        // Scanner input = new Scanner(System.in);

        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        qtdInimigos = tabuleiro[linha][coluna].getListaDeInimigos().size();
        if (qtdInimigos > 0)
        {
            while(!indiceValido)
            {
                System.out.println("O Setor possui " + qtdInimigos + "." );

                if(p instanceof JogadorSimples)
                {
                    System.out.println("Qual inimigo PLAYER 1 (P1) deseja atacar?");
                }
                else
                {
                    System.out.println("Qual inimigo PLAYER 2 (P2) deseja atacar?");
                }
                
                for(int i = 0; i < qtdInimigos; i++)
                {
                    atk = tabuleiro[linha][coluna].getInimigo(i).getAtk();
                    def = tabuleiro[linha][coluna].getInimigo(i).getDef();

                    System.out.printf("%d - %d/%d\n", i+1, atk, def);
                }

                indiceInimigo = input.nextInt();

                if((indiceInimigo >= 1) && (indiceInimigo <= qtdInimigos))
                {
                    indiceValido = true;
                    p.atacar(indiceInimigo-1, p, tabuleiro, pos);
                }
                else
                {
                    System.out.println("Indice do inimigo para atacar é inválido.");
                } 
            }
        }
    }

    static void ataqueDeInimigo(Jogador p1, Jogador p2, Setor[][] tabuleiro)
    {
        int linhaP1, colunaP1, linhaP2, colunaP2;
        Posicao posP1, posP2;
        int qtdInimigos;

        posP1 = p1.getPos();
        linhaP1 = posP1.getY();
        colunaP1 = posP1.getX();

        posP2 = p2.getPos();
        linhaP2 = posP2.getY();
        colunaP2 = posP2.getX();
        
        if(linhaP1 == linhaP2 && colunaP1 == colunaP2)
        {
            if (tabuleiro[linhaP1][colunaP1].existeInimigo)
            {
                qtdInimigos = tabuleiro[linhaP1][colunaP1].getListaDeInimigos().size();
                for (int i = 0; i < qtdInimigos; i++)
                {
                    tabuleiro[linhaP1][colunaP1].getInimigo(i).ataqueDuplo(p1, p2, tabuleiro,i);
                }
            } 
        }
        else
        {   
            if (tabuleiro[linhaP1][colunaP1].existeInimigo)
            {
                qtdInimigos = tabuleiro[linhaP1][colunaP1].getListaDeInimigos().size();
                for (int i = 0; i < qtdInimigos; i++) 
                {
                    tabuleiro[linhaP1][colunaP1].getInimigo(i).ataqueUnico(p1,tabuleiro,i);
                }
            }
        
            if (tabuleiro[linhaP2][colunaP2].existeInimigo)
            {
                qtdInimigos = tabuleiro[linhaP2][colunaP2].getListaDeInimigos().size();
                for (int i = 0; i < qtdInimigos; i++)
                {
                    tabuleiro[linhaP2][colunaP2].getInimigo(i).ataqueUnico(p2, tabuleiro,i);
                }
            }
        }
    }

    public static void main(String[] args) 
    {
        int linhaP1, colunaP1, linhaP2, colunaP2, ciclo, numAcao;

        char acao; 
        Posicao posInfeccao = new Posicao();
        Tabuleiro tabuleiro = new Tabuleiro();
        boolean acaoValida = false;

        
        ciclo = 0;
        tabuleiro.criarTabuleiro(tabuleiro);
        criarPortas(tabuleiro);

        Jogador p1;
        Jogador p2;
        
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

        // Scanner input = new Scanner(System.in);

        System.out.printf("InfY: %d , InfX: %d\n", posInfeccao.getY(), posInfeccao.getX());
        
        // Verificar erro quando a entrada eh char no lugar de int e vice versa
        // Nao remove inimigo depois que morreu
        // deixa o mapa do jeito que deveria ser com as portas e paredes
        do
        {
            posP1 = p1.getPos();
            linhaP1 = posP1.getY();
            colunaP1 = posP1.getX();
            numAcao=1;
            
            // P1 turno
            System.out.println("P1: " + "Y: "+ linhaP1 + " X: " + colunaP1);

            menuMovimentar(p1,tabuleiro);

            if(achouFonte(p1, posInfeccao))
            {
                System.out.println("P1 encontrou a fonte e venceu o jogo!");
                System.exit(0);
            }

            gerarInimigo(p1,tabuleiro);

            acaoValida = false;

            while (!acaoValida || numAcao<=2)
            {
                acao = escolheAcao(p1,tabuleiro);
                    
                switch (acao) 
                {    
                    case 'a':
                        numAcao++;
                        acaoValida = true;
                        menuAtacar(p1,tabuleiro);
                        break;
                        
                    case 's':
                        numAcao++;
                        acaoValida = true;
                        p1.procurar(p1,tabuleiro);
                        break;
                    case 'm':
                        numAcao=1;
                        acaoValida = true;
                        menuMovimentar(p1,tabuleiro);
                        if(achouFonte(p1, posInfeccao))
                        {
                            System.out.println("P1 encontrou a fonte e venceu o jogo!");
                            System.exit(0);
                        }

                        break;
                        
                    default:
                        System.out.println("Tecla inválida.");
                        break;
                }   
            }            
            
            linhaP1 = posP1.getY();
            colunaP1 = posP1.getX();
            System.out.println("P1: " + "Y: "+ linhaP1 + " X: " + colunaP1);
            
            System.out.printf("\n\t\t Vida P1: %d.", p1.getDef());
            System.out.printf("\n\t\t Vida P2: %d.\n", p2.getDef()); 

            // P2 turno
            if(p2EstaVivo(p2))
            {
                posP2 = p2.getPos();
                linhaP2 = posP2.getY();
                colunaP2 = posP2.getX();
                numAcao = 1;
                
                System.out.println("P2: " + "Y: "+ linhaP2 + " X: " + colunaP2);

                menuMovimentar(p2,tabuleiro);
                if(achouFonte(p2, posInfeccao))
                {
                    System.out.println("P2 encontrou a fonte e venceu o jogo!");
                    System.exit(0);
                }

                gerarInimigo(p2,tabuleiro);

                acaoValida = false;

                while (!acaoValida || numAcao <= 2)
                {
                    acao = escolheAcao(p2,tabuleiro);

                    switch (acao) 
                    {    
                        case 'a':
                            numAcao++;
                            acaoValida = true;
                            menuAtacar(p2,tabuleiro);
                            break;
                            
                        case 's':
                            numAcao++;
                            acaoValida = true;
                            p2.procurar(p2,tabuleiro);
                            break;
                            
                        case 'r':
                            numAcao++;
                            acaoValida = true;
                            if(p2 instanceof JogadorSuporte)
                            {
                                JogadorSuporte jogadorP2 = (JogadorSuporte) p2;
                                jogadorP2.recuperarDefesa(p1,p2,tabuleiro);
                            }
                            break;
                            
                        default:
                            System.out.println("Tecla inválida.");
                            break;
                    }
                }

                linhaP2 = posP2.getY();
                colunaP2 = posP2.getX();
                System.out.println("P2: " + "Y: "+ linhaP2 + " X: " + colunaP2);
            
            }

            ataqueDeInimigo(p1, p2,tabuleiro);

            System.out.printf("\n\t\t Vida P1: %d.", p1.getDef());
            System.out.printf("\n\t\t Vida P2: %d.\n", p2.getDef()); 

            ciclo++;
        } while((ciclo <= 25) && (p1EstaVivo(p1)));

        input.close();
    }
}