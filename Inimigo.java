public class Inimigo 
{
    private Posicao pos;
    private int atk, def;

    // Construtores
    public Inimigo()
    {

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
    public void atacar(){}

}
