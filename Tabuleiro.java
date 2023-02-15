import java.util.*;
public class Tabuleiro
{
    public static final int NUM_COL = 5;
    public static final int NUM_LIN = 5;

    public void imprimeTabuleiro(Setor[][] tabuleiro, Jogador p1, Jogador p2, Posicao posInfeccao)
    {
        int i;
        System.out.printf("\n");
        System.out.printf("----------------------------\n");
        System.out.printf("|   Antivirus por um dia   |\n");
        System.out.printf("----------------------------\n");
        
        imprimeFim(tabuleiro,0);
        imprimePosicoes(p1,p2);

        for(i = 0; i < NUM_LIN; i++)
        {
            imprimeMeio(tabuleiro, i, p1, p2, posInfeccao);
            imprimeCimaAbaixo(tabuleiro,i, p1, p2);

        }

        System.out.printf("\n");
    }

    public void imprimePosicoes(Jogador p1, Jogador p2)
    {
        Posicao posP1, posP2;
        int linhaP1,colunaP1, linhaP2, colunaP2;

        posP1 = p1.getPos();
        posP2 = p2.getPos();

        linhaP1 = posP1.getY();
        linhaP2 = posP2.getY();

        colunaP1 = posP1.getX();
        colunaP2 = posP2.getX();

        System.out.printf("\t\tP1: Setor [%d,%d]    P2: Setor [%d,%d]\n", linhaP1, colunaP1, linhaP2, colunaP2);
    }


    public void imprimeCimaAbaixo(Setor[][] tabuleiro, int linha, Jogador p1, Jogador p2)
    {
        Porta portaSetor, portaSetorP1, portaSetorP2;
        Posicao posP1, posP2;

        posP1 = p1.getPos();
        posP2 = p2.getPos();

        portaSetorP1 = tabuleiro[posP1.getY()][posP1.getX()].getPorta();
        portaSetorP2 = tabuleiro[posP2.getY()][posP2.getX()].getPorta();      

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
        System.out.printf("|");

        if(linha == 0)
        {
            System.out.printf("\t\t|------");
            if(portaSetorP1.isAcima())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("-");
            }
            System.out.printf("------|");

            System.out.printf("    |------");
            if(portaSetorP2.isAcima())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("-");
            }
            System.out.printf("------|");
        }
        else if(linha == 3)
        {
            System.out.printf("\t\t|------");
            if(portaSetorP1.isAbaixo())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("-");
            }
            System.out.printf("------|");

            System.out.printf("    |------");
            if(portaSetorP2.isAbaixo())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("-");
            }
            System.out.printf("------|");
        }
        else if(linha == 1)
        {
            System.out.printf("\t\t|             |");
            System.out.printf("    |             |");
        }
        else if(linha == 2)
        {
            System.out.printf("\t\t|  P1    ");
            if(posP1.getX() == posP2.getX() && posP1.getY() == posP2.getY())
            {
                System.out.printf("P2");
            }
            else
            {
                System.out.printf("  ");
            }
            System.out.printf("   |");
            System.out.printf("    |  ");
            if(posP1.getX() == posP2.getX() && posP1.getY() == posP2.getY())
            {
                System.out.printf("P1");
            }
            else
            {
                System.out.printf("  ");
            }
            System.out.printf("    P2");
            System.out.printf("   |");
        }
        System.out.printf("\n");
    }

    public void imprimeMeio(Setor[][] tabuleiro, int linha, Jogador p1, Jogador p2, Posicao posInfeccao)
    {
        Porta portaSetor, portaSetorP1, portaSetorP2;
        Posicao posP1, posP2;
        boolean posIguais = false;
        int linhaP1, linhaP2, colunaP1, colunaP2;
        int posInfeccaoX, posInfeccaoY, qtdInimigos;
        int def, atk;

        posInfeccaoX = posInfeccao.getX();
        posInfeccaoY = posInfeccao.getY();

        posP1 = p1.getPos();
        posP2 = p2.getPos();

        portaSetorP1 = tabuleiro[posP1.getY()][posP1.getX()].getPorta();
        portaSetorP2 = tabuleiro[posP2.getY()][posP2.getX()].getPorta(); 

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
       
        if (linha == 1)
        {
            System.out.print("\t\t| ");
            qtdInimigos= tabuleiro[linhaP1][colunaP1].getListaDeInimigos().size();
            if (!(tabuleiro[linhaP1][colunaP1] instanceof SetorOculto))
            {
                if (qtdInimigos != 0)
                {
                    int resto= 3-qtdInimigos;
                    for (int i = 0; i < qtdInimigos; i++)
                    {
                        atk=tabuleiro[linhaP1][colunaP1].getInimigo(i).getAtk();
                        def=tabuleiro[linhaP1][colunaP1].getInimigo(i).getDef();
                        System.out.printf("%d/%d ", atk, def);
                    }

                    if (resto == 1)
                    {
                        System.out.printf("    ");
                    }
                    else if (resto == 2)
                    {
                        System.out.printf("\t      ");
                    }

                    System.out.print("|");
                }
                else
                {
        
                    System.out.printf("\t      |");
                    
                }
            }
            else
            {
                {
        
                    System.out.printf("\t      |");
                    
                }
            }
            
            qtdInimigos= tabuleiro[linhaP2][colunaP2].getListaDeInimigos().size();
            if (!(tabuleiro[linhaP1][colunaP1] instanceof SetorOculto))
            {
                if (qtdInimigos != 0)
                {
                    System.out.printf("\t   | ");
                    
                    int resto= 3-qtdInimigos;
                    for (int i = 0; i < qtdInimigos; i++)
                    {
                        atk=tabuleiro[linhaP2][colunaP2].getInimigo(i).getAtk();
                        def=tabuleiro[linhaP2][colunaP2].getInimigo(i).getDef();
                        System.out.printf("%d/%d ", atk, def);
                    }

                    if (resto == 1)
                    {
                        System.out.printf("    ");
                    }
                    else if (resto == 2)
                    {
                        System.out.printf("        ");
                    }

                    System.out.print("|");
                }
                else
                {
                    
                    System.out.printf("    |");
                    System.out.printf("\t\t |");

                }
            }
            else
            {
                System.out.printf("    |");
                System.out.printf("\t\t |");
            }
        }
        else if(linha == 2)
        {
            System.out.printf("\t\t");
            if(portaSetorP1.isEsquerda())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("|");
            }
            
            System.out.printf("             ");

            if(portaSetorP1.isDireita())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("|");
            }
            System.out.printf("    ");

            if(portaSetorP2.isEsquerda())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("|");
            }
            
            System.out.printf("             ");

            if(portaSetorP2.isDireita())
            {
                System.out.printf("*");
            }
            else
            {
                System.out.printf("|");
            }
        }
        else if ( linha == 3)
        {
            if (linhaP1 == linhaP2 && colunaP1 == colunaP2)
            {
                int atkP1, defP1, atkP2, defP2;
                
                atkP1= p1.getAtk();
                defP1=p1.getDef();
                atkP2= p2.getAtk();
                defP2=p2.getDef();
                System.out.printf("\t\t|  %d/%d",atkP1,defP1);
                System.out.printf("\t %d/%d  |",atkP2,defP2);
                
                System.out.printf("\t   |  %d/%d ",atkP1,defP1);
                System.out.printf("  %d/%d  |",atkP2,defP2);

            }
            else
            {
                atk= p1.getAtk();
                def=p1.getDef();
                System.out.printf("\t\t|  %d/%d",atk,def);
                System.out.printf("\t      |");
                atk= p2.getAtk();
                def=p2.getDef();
                System.out.printf("\t   |\t    %d/%d",atk,def);
                System.out.printf("  |");
            }
            
        } 

        // final da escrita de vidas de inimigo
        System.out.print("\n");
        
    }

    public void imprimeFim(Setor[][] tabuleiro, int linha)
    {
        for(int coluna = 0; coluna < NUM_COL; coluna++)
        {
            System.out.print("|---");
            
        }
        System.out.print("|");
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
                    System.out.printf(" i:%d  j: %d",i, j);
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