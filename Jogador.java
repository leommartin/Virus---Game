
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
    
    public void movimentar(Jogador p, char direcao)
    {
        Posicao pos;
        int linha,coluna;
        
        pos = p.getPos();
        linha = pos.getY();
        coluna = pos.getX();

        // m[y][x] ; para alterar m[l][c], altere l
        
        switch (direcao) 
        {
            case 'u':

                if(linha > 0)
                {
                    pos.setY(linha - 1);
                }
                break;

            case 'd':
            
                if(linha < 4);
                {
                    pos.setY(linha + 1);
                }
                break;
            
            case 'l':

                if(coluna > 0)
                {
                    pos.setX(coluna - 1);
                }
                break;
            
            case 'r':

                if(coluna < 4)
                {
                    pos.setX(coluna + 1);
                }
                break;

            default:
                break;
        }
    }

    public void procurar(){}
    
}