import java.util.Random;

public abstract class Principal
{
    public static final int NUM_COL = 5;
    public static final int NUM_LIN = 5;
    
    static void criarPortas(Setor[][] tabuleiro)
    {
        Random random = new Random();
        int[] numAleatorio = new int[4];
        
        for (int i = 0; i < NUM_LIN; i++) {
            for (int j = 0; j < NUM_COL; j++) {
                Porta portaAnterior;
                Porta p =new Porta();
                
                for(int indice = 0; indice < 4; indice++)
                {
                    numAleatorio[indice] = random.nextInt(2);  
                }
                
                if(i==0)
                {
                    p.setAcima(false);
                    if (numAleatorio[1] == 1)
                    {
                        p.setAbaixo(true); 
                    }
                    else
                    {
                        p.setAbaixo(false); 
                    }
                    
                    if(j==0)
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
                    else if(j==NUM_COL-1)
                    {
                        p.setDireita(false);
                        if (numAleatorio[3] == 1)
                        {
                            p.setEsquerda(true); 
                        }
                        else
                        {
                            p.setEsquerda(false); 
                        }
                    }
                    else
                    {         
                        if (numAleatorio[3] == 1)
                        {
                            p.setDireita(true); 
                        }
                        else
                        {
                            p.setDireita(false); 
                        }
                        
                    }

                    
                    
                    portaAnterior=tabuleiro[i][j-1].getPorta();
                    p.setEsquerda(portaAnterior.isEsquerda());
                    


                }
            }
        }
    }

    static void criarTabuleiro(Setor[][] tabuleiro, int tam)
    {
        int i, j;
        
        //Setores normais rosas
        Random random = new Random();
        int[] numAleatorio = new int[4];
        

    // Instaciamento de Setores

        // Instanciamento primeira e ultima linnha
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
    // Fim estanciamento de setores

        i = 1;
        //Setores normais
        while(i <= tam-2)
        {
            j = 1;
            while(j <= tam-2)
            {
                
                Porta p = new Porta(); 
                
                for(int indice = 0; indice < 4; indice++)
                {
                    numAleatorio[indice] = random.nextInt(2);  
                }
               
                if (numAleatorio[0] == 1)
                {
                    p.setAcima(true); 
                }
                else
                {
                    p.setAcima(false); 
                }

                if (numAleatorio[1] == 1)
                {
                    p.setAbaixo(true); 
                }
                else
                {
                    p.setAbaixo(false); 
                }

                if (numAleatorio[2] == 1)
                {
                    p.setEsquerda(true); 
                }
                else
                {
                    p.setEsquerda(false); 
                }

                if (numAleatorio[3] == 1)
                {
                    p.setDireita(true); 
                }
                else
                {
                    p.setDireita(false); 
                }

                tabuleiro[i][j] = new SetorNormal();
                tabuleiro[i][j].setPorta(p);
            }    
        }

        Porta p1 = new Porta(false, true, false, true); // baixo direito   
        Porta p2 = new Porta(false, true, true,false);  // baixa esquerda
        Porta p3 = new Porta(true, false, true,false);  // acima esquerda
        Porta p4 = new Porta(true, false, false, true); //acima direita

        // ordem: acima, abaixo, esquerda, direita
        tabuleiro[0][0] = new SetorNormal(); //baixo direito
        tabuleiro[0][0].setPorta(p1);
        
        tabuleiro[0][4] = new SetorNormal(); //baixo esquerdo
        tabuleiro[0][4].setPorta(p2);
        
        tabuleiro[4][0] = new SetorNormal(); //acima direita
        tabuleiro[4][0].setPorta(p3);
      
        tabuleiro[4][4] = new SetorNormal();  //acima esquerda
        tabuleiro[4][4].setPorta(p4);
        //Fim setores normais

        //Setores ocultos azuis
        i = 1; 
        while(i < NUM_LIN-1)
        {
            Porta pAzul1 = new Porta(true, true, false, true);
            Porta pAzul2 = new Porta(true, true, true, false);
            
            tabuleiro[i][0] = new SetorOculto();
            tabuleiro[i][0].setPorta(pAzul1);
            
            tabuleiro[i][4] = new SetorOculto();
            tabuleiro[i][0].setPorta(pAzul2);
            i++;    
            // Quando estiver na coluna 0, tranca-se a porta da esquerda & quando estiver na coluna 4, tranca-se a porta da direita;
        }
        //Fim setores ocultos

        //Setores privados verdes
        j = 1; 
        while(j < NUM_COL-1)
        {
            Porta pVerde1 = new Porta(false, true, true, true);
            Porta pVerde2 = new Porta(true, false, true, true);  
            
            tabuleiro[0][j] = new SetorOculto();
            tabuleiro[0][j].setPorta(pVerde1); 
            
            tabuleiro[4][j] = new SetorOculto();
            tabuleiro[4][j].setPorta(pVerde2);
            j++;
            // Quando estiver na linha 0, tranca-se a porta acima & quando estiver na linha, tranca-se a porta abaixo;
        }
        //Fim setores privados

        // Setando visitado e existe inimigo de todos os setores
        for(i = 0; i < NUM_LIN; i++)
        {
            for(j = 0; j < NUM_COL; j++)
            {
                tabuleiro[i][j].setVisitado(false);
                tabuleiro[i][j].setExisteInimigo(false);
            }
        }

        //Centro
        Porta p = new Porta(true, true, true, true); 
        tabuleiro[3][3].setPorta(p);
        tabuleiro[3][3].setVisitado(true);
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