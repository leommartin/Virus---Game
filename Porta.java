
public abstract class Porta 
{
    private boolean acima;
    private boolean esquerda;
    private boolean abaixo;
    private boolean direita;

    //Getters e Setters
    public boolean isAcima()
    {
        return this.acima;
    }
    public void setAcima(boolean acima) 
    {
        this.acima = acima;
    }

    public boolean isEsquerda()
    {
        return this.esquerda;
    }
    public void setEsquerda(boolean esquerda) 
    {
        this.esquerda = esquerda;
    }
    
    public boolean isAbaixo()
    {
        return this.abaixo;
    }
    public void setAbaixo(boolean abaixo) 
    {
        this.abaixo = abaixo;
    }

    public boolean isDireita()
    {
        return this.direita;
    }
    public void setDireita(boolean direita) 
    {
        this.direita = direita;
    }
    
}
