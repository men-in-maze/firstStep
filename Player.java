package fullscreentest;

public class Player {
    private int id;
    
    
    //resourses
    private int iron = 1000;
    private int energy = 300;
    private int titan = 50;
    
    
    //game param
    private int hpOfMainSpaceship = 100;
    private int manpower = 100;
    
    
    //suportships
    private int numberOfFighters = 3;
    private int numberOfMiners = 3;
    
    public Player(int id){
        this.id = id;
    }
    //GGGGEEEETTT
    public int getID(){
        return id;
    }
    public int getIron(){
        return iron;
    }
    public int getEnergy(){
        return energy;
    }
    public int getTitan(){
        return titan;
    }
    public int getHpOfMainSpaceship(){
        return hpOfMainSpaceship;
    }
    public int getManpower(){
        return manpower;
    }
    public int getNumberOfFighters(){
        return numberOfFighters;
    }
    public int getNumberOfMiners(){
        return numberOfMiners;
    }
    //SSSSSEEEEETTT
    public void setID(int id){
        this.id = id;
    }
    public void setIron(int iron){
        this.iron = iron;
    }
    public void setEnergy(int energy){
        this.energy = energy;
    }
    public void setTitan(int titan){
        this.titan = titan;
    }
    public void setHpOfMainSpaceship(int hpOfMainSpaceship){
        this.hpOfMainSpaceship = hpOfMainSpaceship;
    }
    public void setManpower(int manpower){
        this.manpower = manpower;
    }
    public void setNumberOfFighters(int numberOfFighters){
        this.numberOfFighters = numberOfFighters;
    }
    public void setNumberOfMiners(int numberOfMiners){
        this.numberOfMiners = numberOfMiners;
    }
}
