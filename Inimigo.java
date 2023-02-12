import java.util.Random;
public class Inimigo 
{
    private Posicao pos;
    private int atk, def;

    // Construtores
    public Inimigo(int atk, int def, Posicao pos)
    {
        this.setAtk(atk);
        this.setDef(def);
        this.setPos(pos);
    }

    // Getter e Setters
    public Posicao getPos() 
    {
        return this.pos;
    }
    public void setPos(Posicao pos) 
    {
        this.pos = pos;
    }

    public int getAtk() 
    {
        return this.atk;
    }
    public void setAtk(int atk) 
    {
        this.atk = atk;
    }

    public int getDef() 
    {
        return this.def;
    }
    public void setDef(int def) 
    {
        this.def = def;
    }

    // Outros métodos 
    public void ataqueDuplo(Jogador p1, Jogador p2, Setor[][] tabuleiro, int indiceInimigo)
    {
        Posicao posP1;
        int linhaP1, colunaP1;

        Random random = new Random(); 
        int numAleatorio, defPlayer, defInimigo, atkInimigo;

        posP1 = p1.getPos();

        linhaP1 = posP1.getY();
        colunaP1 = posP1.getX();
    
        numAleatorio = random.nextInt(6)+1;
                
        atkInimigo = tabuleiro[linhaP1][colunaP1].getInimigo(indiceInimigo).getAtk();
        defInimigo = tabuleiro[linhaP1][colunaP1].getInimigo(indiceInimigo).getDef();

        if(numAleatorio % 2 == 0 && defInimigo > 0)
        {
            System.out.println("Ataque de inimigo realizado no P1");
            defPlayer = p1.getDef();
                    
            defPlayer = defPlayer - atkInimigo;

            if(defPlayer > 0)
            {
                p1.setDef(defPlayer);
            }
            else
            {
                p1.setDef(0);
             }
                    
        }

        numAleatorio = random.nextInt(6)+1;

        if(numAleatorio % 2 == 0 && defInimigo > 0)
        {
            System.out.println("Ataque de inimigo realizado no P2");
            defPlayer = p2.getDef();
                    
            defPlayer = defPlayer - atkInimigo;

            if(defPlayer > 0)
            {
                p2.setDef(defPlayer);
            }
            else
            {
                p2.setDef(0);
            }
                    
        }
        
    }

    public void ataqueUnico(Jogador p, Setor[][] tabuleiro, int indiceInimigo)
    {
        Posicao pos;
        int linha, coluna;

        Random random = new Random(); 
        int numAleatorio, defPlayer, defInimigo, atkInimigo;

        pos = p.getPos();

        linha = pos.getY();
        coluna = pos.getX();

        numAleatorio = random.nextInt(6)+1;
                
        atkInimigo = tabuleiro[linha][coluna].getInimigo(indiceInimigo).getAtk();
        defInimigo = tabuleiro[linha][coluna].getInimigo(indiceInimigo).getDef();

        if(numAleatorio % 2 == 0 && defInimigo > 0)
        {
            defPlayer = p.getDef();
                    
            defPlayer = defPlayer - atkInimigo;

            if(defPlayer > 0)
            {
                p.setDef(defPlayer);
            }
            else
            {
                p.setDef(0);
            }
                    
        }
    }

}
