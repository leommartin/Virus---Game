
        //Setores normais rosas
       Random random = new Random();
       int[] numAleatorio = new int[4];
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