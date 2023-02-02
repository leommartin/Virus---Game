
public abstract class Jogador
{
    protected Posicao pos;
    protected int atk, def;
    
    //Construtores
    public Jogador(Posicao pos,int atk, int def)
    {
        this.setAtk(atk);
        this.setDef(def);
        this.setPos(pos);
    }
    // Getters e Setters
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

    // Outro métodos
    public void atacar(){}
    
    public boolean movimentar(Jogador p, char direcao, Setor[][] tabuleiro)
    {
        Posicao pos;
        Porta porta;
        int linha,coluna;
        
        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        porta=tabuleiro[linha][coluna].getPorta();

        // m[y][x] ; para alterar m[l][c], altere l
        if (!tabuleiro[linha][coluna].existeInimigo())
        {
            if(direcao == 'u')
            {     
                if(porta.isAcima())
                    pos.setY(linha - 1);
            }
            else if(direcao == 'd')
            {
                if(porta.isAbaixo());
                    pos.setY(linha + 1);
            }
            else if(direcao == 'l')
            {
                if(porta.isEsquerda())
                    pos.setX(coluna - 1);
            }
            else
            {
                if(porta.isDireita())
                    pos.setX(coluna + 1);
           
            }
            return true;
        }
        return false;
    }

    public void procurar(){}
    
}