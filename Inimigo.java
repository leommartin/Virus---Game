public class Inimigo 
{
    Posicao pos;
    int atk, def;

    // Getter e Setters
    public Posicao getPos() 
    {
        return pos;
    }
    public void setPos(Posicao pos) 
    {
        this.pos = pos;
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

    // Outros métodos 
    public void atacar(){}

}
