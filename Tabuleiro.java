import java.util.*;
public class Tabuleiro
{
    public static final int NUM_COL = 5;
    public static final int NUM_LIN = 5;

    public void imprimeTabuleiro(Setor[][] tabuleiro, Jogador p1, Jogador p2, Posicao posInfeccao)
    {
        int i;
        System.out.printf("\n\n");

        imprimeFim(tabuleiro,0);

        for(i = 0; i < NUM_LIN; i++)
        {
            imprimeMeio(tabuleiro, i, p1, p2, posInfeccao);
            imprimeAbaixo(tabuleiro,i);
        }

        System.out.printf("\n\n");
    }

    public void imprimeAbaixo(Setor[][] tabuleiro, int linha)
    {
        Porta portaSetor;

        int coluna = 0;

        for(coluna = 0; coluna < NUM_COL; coluna++)
        {
            portaSetor = tabuleiro[linha][coluna].getPorta();

            if(tabuleiro[linha][coluna].visitado && portaSetor.isAbaixo())
            {
                System.out.print("|-*-");
            }
            else if(linha < 4 && tabuleiro[linha+1][coluna].visitado)
            {
                Porta portaProxSetor =  tabuleiro[linha+1][coluna].getPorta() ;
                if(portaProxSetor.isAcima())
                {
                    System.out.print("|-*-");
                }
            }
            else
            {
                System.out.print("|---");
            }
        }
        System.out.printf("|\n");
    }

    public void imprimeMeio(Setor[][] tabuleiro, int linha, Jogador p1, Jogador p2, Posicao posInfeccao)
    {
        Porta portaSetor;
        Posicao posP1, posP2;
        boolean posIguais = false;
        int linhaP1, linhaP2, colunaP1, colunaP2;
        int posInfeccaoX, posInfeccaoY;

        posInfeccaoX = posInfeccao.getX();
        posInfeccaoY = posInfeccao.getY();

        posP1 = p1.getPos();
        posP2 = p2.getPos();

        linhaP1 = posP1.getY();
        linhaP2 = posP2.getY();

        colunaP1 = posP1.getX();
        colunaP2 = posP2.getX();

        if(linhaP1 == linhaP2 && colunaP1 == colunaP2)
        {
            posIguais = true;
        }
        
        System.out.print("|");
        for(int coluna = 0; coluna < NUM_COL; coluna++)
        {
            portaSetor = tabuleiro[linha][coluna].getPorta();
            
            if(posIguais && ((linhaP1 == linha) && (colunaP1 == coluna)))
            {
                System.out.print("P12");
            }
            else
            {
                if((colunaP1 == coluna) && (linhaP1 == linha))
                {
                    System.out.print("P1 ");
                }
                else if((colunaP2 == coluna) && (linhaP2 == linha))
                {
                    System.out.print("P2 ");
                }
                else if((posInfeccaoX == coluna) && (posInfeccaoY == linha))
                {
                    System.out.print(" X ");
                }
                else
                {
                    System.out.print("   ");
                }
            } 

            if(tabuleiro[linha][coluna].visitado && (portaSetor.isDireita()))
            {
                System.out.print("*");
            }
            else if(coluna < 4 && tabuleiro[linha][coluna+1].visitado)
            {
                Porta portaProxSetor =  tabuleiro[linha][coluna+1].getPorta() ;
                if(portaProxSetor.isEsquerda())
                {
                    System.out.print("*");
                }
            }
            else
            {
                System.out.print("|");
            }
        }
        System.out.print("\n");
    }

    public void imprimeFim(Setor[][] tabuleiro, int linha)
    {
        for(int coluna = 0; coluna < NUM_COL; coluna++)
        {
            {
                System.out.print("|---");
            }
        }
        System.out.print("|\n");
    }

    public void criarTabuleiro(Setor[][] tabuleiro, int linha, int coluna)
    {
        int i, j;
    
    // Instaciamento de Setores

        // Instanciamento da primeira e ultima linnha
        i = 0;
        while(i < linha)
        {
            for(j = 0; j < coluna; j++)
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
        for(i = 1 ; i < linha - 1; i++)
        {
            for(j = 0; j < coluna; j++)
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


    public void criarPortas(Setor[][] tabuleiro, int linha, int coluna, int posCentralY, int posCentralX)
    {
        Random random = new Random();
        int[] numAleatorio = new int[4];
        
        for (int i = 0; i < linha; i++) 
        {
            for (int j = 0; j < coluna; j++) 
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
                    else if(j < coluna-1)
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

                else if(i < linha-1)
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
                    else if(j < coluna-1)
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
                    else if(j < coluna-1)
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
        tabuleiro[posCentralY][posCentralX].setPorta(portaCentral);
        tabuleiro[posCentralY][posCentralX].setVisitado(true);
    }
}