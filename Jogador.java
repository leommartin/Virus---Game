
public abstract class Jogador
{
<<<<<<< HEAD
    protected Posicao pos;
    protected int atk, def;
=======
    Posicao pos;
    int atk, def;
>>>>>>> 76206f21b6764b593a854fdc4dd24ba3351cac56
    
    //Construtores
    public Jogador(Posicao pos,int atk, int def){
        this.setAtk(atk);
        this.setDef(def);
        this.setPos(pos);
    }
    // Getters e Setters
    public Posicao getPos() 
    {
<<<<<<< HEAD
        return this.pos;
=======
        return pos;
>>>>>>> 76206f21b6764b593a854fdc4dd24ba3351cac56
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
    
    public void movimentar(){}

    public void procurar(){}
    
}