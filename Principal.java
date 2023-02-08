
import java.util.*;
// import java.util.Random;
// import java.util.Scanner;

// To do: Ataque do inimigo
// To do: Escrever a matriz

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

    static boolean existeColisao(Posicao pos, Posicao posInfeccao)
    {
        if( (pos.getX() == posInfeccao.getX()) && (pos.getY() == posInfeccao.getY()) )
        {
            return true;
        }
        return false;
    }

    static boolean achouFonte(Jogador p1, Jogador p2, Posicao posInfeccao)
    {
        Posicao posPlayer1;
        Posicao posPlayer2;

        posPlayer1 = p1.getPos();
        posPlayer2 = p2.getPos();
    
        if (existeColisao(posPlayer1,posInfeccao) || existeColisao(posPlayer2,posInfeccao) )
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
    
    // To do: Desmembrar menuMovimentar em 2 métodos, menuMovimentar() e movimentar();
    static void menuMovimentar(Jogador p,Setor[][] tabuleiro)
    {
        char move;
        boolean existeMovimentacao = false;
        Scanner input = new Scanner(System.in);

        while (!existeMovimentacao)
        {
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

            existeMovimentacao = movimentar(move, p, tabuleiro);
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
        // Verificar se é necessário o atributo posição em inimigo.
        linha = pos.getY();
        coluna = pos.getX();
        //System.out.println("Inimigos gerados");
        if( !tabuleiro[linha][coluna].visitado)
        {
            // Gerar os inimigos
            int atk, def, numInimigos;
            Random random = new Random(); 
            numInimigos = random.nextInt(3) + 1;

            System.out.println("Num "+ numInimigos);
            System.out.println("Inimigos gerados");

            for(int i = 0; i < numInimigos; i++)
            {
                // Gerar o ataque e defesa dos inimigos
                atk = random.nextInt(3)+1;
                def = atk;

                Inimigo inimigo = new Inimigo(atk,def,pos);
                tabuleiro[linha][coluna].setInimigo(inimigo);
            }
            int qtdInimigos = tabuleiro[linha][coluna].getListaDeInimigos().size();

            System.out.println("O Setor possui " + qtdInimigos + "." );
            tabuleiro[linha][coluna].existeInimigo = true;
        }

        tabuleiro[linha][coluna].setVisitado(true);

    }

    static char escolheAcao(Jogador p, Setor[][] tabuleiro)
    {
        char acao;
        Posicao pos;
        int linha, coluna;

        Scanner input = new Scanner(System.in);

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
        
        acao = input.next().charAt(0);
        
        switch (acao) 
        {    
            case 'a':
                menuAtacar(p,tabuleiro);
                break;
             
            case 's':
                procurar(p,tabuleiro);
                break;
             
            case 'r':
                if (p instanceof JogadorSuporte)
                {
                    return acao;
                }
                else
                {
                    System.out.println("Tecla inválida."); 
                }
                break;
            
            default:
                System.out.println("Tecla inválida.");
                break;
        }
        return '1';  
    }

    static void procurar(Jogador p, Setor[][] tabuleiro)
    {
        Posicao pos;
        int linha, coluna;
        int defPlayer;
            
        Random random = new Random(); 
        int numAleatorio = random.nextInt(6)+1;

        defPlayer = p.getDef();
        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        if(numAleatorio == 4)
        {
            defPlayer = defPlayer + 1;
            p.setDef(defPlayer);
        }
        else if(numAleatorio == 5)
        {
            defPlayer = defPlayer + 2;
            p.setDef(defPlayer);
        }
        else if(numAleatorio == 6)
        {
            int qtdInimigos;
            int defInimigo;

            qtdInimigos = tabuleiro[linha][coluna].getListaDeInimigos().size();
            
            for (int i = 0; i < qtdInimigos; i++) 
            {    
                defInimigo = tabuleiro[linha][coluna].getInimigo(i).getDef();
                defInimigo = defInimigo - 1;
                tabuleiro[linha][coluna].getInimigo(i).setDef(defInimigo);
            }
            
        }
        
    }

    static void menuAtacar(Jogador p, Setor[][] tabuleiro)
    {
        int indiceInimigo;
        Posicao pos;
        int linha, coluna, qtdInimigos;
        int atk, def;
        Scanner input = new Scanner(System.in);

        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        qtdInimigos = tabuleiro[linha][coluna].getListaDeInimigos().size();

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
        
        if(indiceInimigo >= 1 && indiceInimigo <= qtdInimigos)
        {
            ataqueJogador(indiceInimigo-1, p, tabuleiro, pos);
        }
        else
        {
            System.out.println("Indice do inimigo para atacar é inválido.");
        }  
    }
    
    static void ataqueJogador(int indiceInimigo, Jogador p, Setor[][] tabuleiro, Posicao pos)
    {
        int linha, coluna;
        int atkPlayer;
        int defInimigo;
        
        linha = pos.getY();
        coluna = pos.getX();

        atkPlayer = p.getAtk();

        defInimigo = tabuleiro[linha][coluna].getInimigo(indiceInimigo).getDef();

        // Ataque do jogador em setor Oculto
        if(tabuleiro[linha][coluna] instanceof SetorOculto)
        {
            Random random = new Random(); 
            int numAleatorio = random.nextInt(1);
            
            if(numAleatorio != 0)
            {
                defInimigo = defInimigo - atkPlayer;

                if(defInimigo > 0)
                {
                    tabuleiro[linha][coluna].getInimigo(indiceInimigo).setDef(defInimigo);
                }
                else
                {
                    tabuleiro[linha][coluna].getListaDeInimigos().remove(indiceInimigo);
                }
            }  
        }
        else
        {
            defInimigo = defInimigo - atkPlayer;
            if(defInimigo > 0)
            {
                tabuleiro[linha][coluna].getInimigo(indiceInimigo).setDef(defInimigo);
            }
            else
            {
                tabuleiro[linha][coluna].getListaDeInimigos().remove(indiceInimigo);
            } 
        }

        int qtdInimigos = tabuleiro[linha][coluna].getListaDeInimigos().size();

        if(qtdInimigos < 1)
        {
            tabuleiro[linha][coluna].existeInimigo = false;
        }

        for(int i = 0; i < qtdInimigos; i++)
        {
            int atk = tabuleiro[linha][coluna].getInimigo(i).getAtk();
            int def = tabuleiro[linha][coluna].getInimigo(i).getDef();

            System.out.printf("%d - %d/%d\n", i+1, atk, def);
        }
    }

    static void ataqueInimigo(Jogador p1, Jogador p2, Setor[][] tabuleiro)
    {
        Posicao pos;
        int linhaP1, linhaP2, colunaP1, colunaP2, qtdInimigos;

        Random random = new Random(); 
        int numAleatorio, numJogador, def;

        posP1 = p1.getPos();
        posP2 = p2.getPos();

        linhap1 = posP1.getY();
        linhaP2 = posP2.getY();

        colunaP1 = posP1.getX();
        colunaP2 = posP2.getY();

        qtdInimigos = tabuleiro[linhaP1][colunaP1].getListaDeInimigos().size();

        // Caso os dois jogadores estejam no mesmo setor
        if(linhaP1 == linhaP2 && colunaP1 == colunaP2)
        {
            for(int i = 0; i < qtdInimigos; i++)
            {
                numAleatorio = random.nextInt(6)+1;
                numJogador = random.nextInt(2)+1;
                
                atkInimigo = tabuleiro[linha][coluna].getInimigo(i).getAtk();

                if(numJogador == 1 && numAleatorio % 2 == 0)
                {
                    def = p1.getDef();
                    p1.setDef(def - atkInimigo)
                }
                else if(numJogador == 2 && numAleatorio % 2 == 0)
                {
                    def = p2.getDef();
                    p2.setDef(def - atkInimigo);
                }
            }
        }
        else
        // Definir ataque dos inimigos quando só houver 1 player no setor
    }

    static void recuperarJogador(Jogador p1, Jogador p2, Setor[][] tabuleiro)
    {
        Posicao posP1, posP2;
        int player, def;
        
        posP1 = p1.getPos();
        posP2 = p2.getPos();
        
        if(posP1.getX() == posP2.getX() && posP1.getY() == posP2.getY())
        {
            Scanner input = new Scanner(System.in);

            System.out.println("Qual Jogador deseja recuperar?");
            System.out.println("1 - P1");
            System.out.println("2 - P2");

            player = input.nextInt();

            switch (player) 
            {    
                case 1:
                    def = p1.getDef();
                    p1.setDef(def+2);
                    break;
                
                case 2:
                    def = p2.getDef();
                    p2.setDef(def+2);
                    break;
                
                default:
                    System.out.println("Tecla inválida.");
                    break;
            }
        }
        else
        {
            def = p2.getDef();
            p2.setDef(def+2);   
        }
    }


    public static void main(String[] args) 
    {
        int linha, coluna, turno;
        Posicao posInfeccao = new Posicao();
        Setor[][] tabuleiro = new Setor[NUM_LIN][NUM_COL];
        
        turno=0;
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
            posP1 = p1.getPos();
            linha = posP1.getY();
            coluna = posP1.getX();

            System.out.println("P1: " + "Y: "+ linha + " X: " + coluna);

            if(tabuleiro[linha][coluna].existeInimigo)
            {
                escolheAcao(p1, tabuleiro);
            }
            else
            {
                menuMovimentar(p1,tabuleiro);
                gerarInimigo(p1,tabuleiro);
                
                if(escolheAcao(p1,tabuleiro) == 'r') // errado
                {
                    recuperarJogador(p1,p2,tabuleiro);
                }
                turno++;
            }

            ataqueInimigo(p1,p2,tabuleiro);

            posP2 = p2.getPos();
            linha = posP2.getY();
            coluna = posP2.getX(); 
            
            System.out.println("P2: " + "Y: "+ linha + " X: " + coluna);

            if(tabuleiro[linha][coluna].existeInimigo)
            {
                escolheAcao(p2, tabuleiro);
            }
            else
            {
                menuMovimentar(p2,tabuleiro);
                gerarInimigo(p2,tabuleiro);

                if(escolheAcao(p2,tabuleiro) == 'r')
                {
                    recuperarJogador(p1,p2,tabuleiro);
                }
                turno++;
            }

            //System.out.println("Y: "+ pos.getY() + " X: " + pos.getX());

            //turno++; // verificar se o movimento é valido para contar um turno

        } while( (!achouFonte(p1,p2,posInfeccao)) && (turno <= 25) && (p1EstaVivo(p1)) ); // nao esqueca de colocar p2 no achouFonte

        input.close();
    }
}