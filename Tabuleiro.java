import java.util.*;
public class Tabuleiro
{
    public static final int NUM_COL = 5;
    public static final int NUM_LIN = 5;

    public void imprimeTabuleiro(Setor[][] tabuleiro)
    {
        System.out.printf("\n\n");

        for(int i = 0; i < NUM_LIN; i++)
        {
            for(int j = 0; j < NUM_COL; j++)
            {
                imprimeSetor(tabuleiro,i,j);
            }
        }

        System.out.printf("\n\n");

    }

    public void imprimeSetor(Setor[][] tabuleiro, int linha, int coluna)
    {
        System.out.printf("|---|\n|   |\n|---|");
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