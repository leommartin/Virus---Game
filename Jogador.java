
public abstract class Jogador
{
    Posicao p;
    int atk, def;
    
    // Getters e Setters
    public Posicao getP() 
    {
        return p;
    }
    public void setP(Posicao p) 
    {
        this.p = p;
    }

    public int getAtk() 
    {
        return atk;
    }
    public void setAtk(int atk) 
    {
        this.atk = atk;
    }

    public int getDef() 
    {
        return def;
    }
    public void setDef(int def) 
    {
        this.def = def;
    }

    // Outro métodos
    public void atacar(){}
    
    public void movimentar(){}

    public void procurar(){}

}