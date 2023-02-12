
public class Tabuleiro
{
    public static final int NUM_COL = 5;
    public static final int NUM_LIN = 5;

    Setor[][] tabuleiro;
    
    public void imprimeTabuleiro(Setor[][] tabuleiro)
    {
        for(int i = 0; i < NUM_LIN; i++)
        {
            for(int j = 0; j < NUM_COL; j++)
            {
                imprimeSetor(tabuleiro,i,j);
            }
        }

    }

    public void imprimeSetor(Setor[][] tabuleiro, int linha, int coluna)
    {

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

    public Tabuleiro()
    {
        
    }
}