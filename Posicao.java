
public class Posicao 
{
    private int x,y;

    // Getters e Setters
    public int getX() 
    {
        return this.x;
    }
    
    public void setX(int x) 
    {
        if(x >= 0 && x <= 4)
        {
            this.x = x;
        }
    }

    public int getY() 
    {
        return this.y;
    }

    public void setY(int y) 
    {
        if(y >= 0 && y <= 4)
        {
            this.y = y;
        }
    }

}
